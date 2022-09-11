package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import conexionMysql.ClienteDao;
import entidad.Cliente;

public class Controlador {

	private ClienteDao clienteDao;

	public Controlador() {
		clienteDao = new ClienteDao();
	}

	public void opciones(String mensaje, DataOutputStream salida ) {
		String[] opciones =  mensaje.split(",");
		int opc = Integer.parseInt(opciones[0]);
		List<String> clientes = new ArrayList<>();
		try {
			switch (opc) {
			case 1:
				ListaClientes(salida);
				break;
			case 2:
				agregarCliente(salida,opciones);
				break;
			case 3:				
				consultarCliente(salida,opciones);
				break;
			case 4:
				agregarSaldo(salida,opciones);
			default:
				clientes.add("Error");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	private void ListaClientes(DataOutputStream salida) throws IOException {
		List<Cliente> clientes = clienteDao.seleccionar();
		String cadenaSalida ="";
		for(Cliente c : clientes) {
			cadenaSalida+= c.toString() + "\n";
		}
		salida.writeUTF(cadenaSalida);
	}
	
	private void consultarCliente(DataOutputStream salida,String[] mensaje) throws IOException {
			Cliente cliente = clienteDao.getCliente(Integer.parseInt(mensaje[1]));
			System.out.println(cliente);
			salida.writeUTF(cliente.toString());
			
		}
	
	private void agregarSaldo(DataOutputStream salida,String[] mensaje) throws IOException {
		Cliente cliente = clienteDao.getCliente(Integer.parseInt(mensaje[1]));
		cliente.setSaldo(cliente.getSaldo() + Double.parseDouble(mensaje[2]));
		int n = clienteDao.actulizar(cliente);	
		if(n == 1) {
			System.out.println("si actualizo");
			salida.writeUTF(cliente.toString());
		}else {
			System.out.println("no actualizo");
			salida.writeUTF("no Actulizo");
		}
		
	}
	
	private void agregarCliente(DataOutputStream salida,String[] mensaje) throws IOException {
		Cliente cliente = new Cliente(mensaje[1],mensaje[2],Double.parseDouble(mensaje[3]));
		System.out.println(cliente);
		int n  = clienteDao.insertar(cliente);
		if(n == 1) {
			System.out.println("Cliente Creado");
			salida.writeUTF("Cliente Creado");
		}else {
			System.out.println("error al crear cliente");
			salida.writeUTF("error al crear cliente");
		}
		
	}
	}
