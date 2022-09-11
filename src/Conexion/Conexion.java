package Conexion;

import java.net.*;

import controlador.Controlador;

import java.io.*;

public class Conexion {
 
	private ServerSocket server;
	private Socket  socketCliente;
	private final int PUERTO = 5000;//puerto que vamos a usar en la maquina para ejecutar
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
				socketCliente = server.accept();//esperar a que manden peticion proveniente de un programa externo
				entrada = new DataInputStream(socketCliente.getInputStream()); //entrada de datos del cliente
				salida = new DataOutputStream(socketCliente.getOutputStream());
				System.out.println("Se ha conectado un cliente");
				String opcEntrada;
				while(conectado) {					
					opcEntrada  = entrada.readUTF();										
					controlador.opciones(opcEntrada, salida);					
					}
				System.out.println("cliente desconectado");
				
				
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
