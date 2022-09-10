package controlador;

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
	
	
	public void opciones(int opcion, DataOutputStream salida){
		List<String> clientes = new ArrayList<>();
		switch(opcion) {
		
		case 1:
			List<Cliente> allClients = clienteDao.seleccionar();
			allClients.stream().forEach((c) -> {
				try {
					salida.writeUTF(c.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			break;
			
		default:
			clientes.add("Error");
			break;
		}
		
	}
}
