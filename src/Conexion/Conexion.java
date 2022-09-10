package conexion;

import java.net.*;

import controlador.Controlador;

import java.io.*;

public class Conexion {
 
	private ServerSocket server;
	private Socket  socketCliente;
	private final int PUERTO = 6958;//puerto que vamos a usar en la maquina para ejecutar
	private DataOutputStream salida;
	private DataInputStream entrada;
	private boolean conectado;
	private Controlador controlador;
	
	
	public Conexion() {
		conectado = true;
		controlador = new Controlador();
	}
	
	public void iniciar() {
		try {
				server = new ServerSocket(PUERTO);				
				
				while(conectado) {
					socketCliente = server.accept();//esperar a que manden peticion proveniente de un programa externo
					System.out.println("Se ha conectado un cliente");
					entrada = new DataInputStream(socketCliente.getInputStream()); //entrada de datos del cliente
					salida = new DataOutputStream(socketCliente.getOutputStream());
					String mensajeRecibido  = entrada.readUTF();
					controlador.opciones(Integer.parseInt(mensajeRecibido), salida);
					conectado = false;
					socketCliente.close();
					}
				System.out.println("cliente desconectado");
				
				
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
