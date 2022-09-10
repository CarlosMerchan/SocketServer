package Conexion;

import java.net.*;
import java.io.*;

public class Conexion {
 
	private ServerSocket server;
	private Socket  socket;
	private final int PUERTO = 6958;//puerto que vamos a usar en la maquina para ejecutar
	private DataOutputStream salida;
	private BufferedReader entrada;
	
	public void iniciar() {
		try {
				server = new ServerSocket(PUERTO);
				socket = new Socket();
				socket = server.accept();//esperar a que manden peticion proveniente de un programa externo
				
				entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String mensaje =entrada.readLine();//recibe la informacion que mandan desde el cliente
				System.out.println(mensaje);
				salida = new DataOutputStream(socket.getOutputStream());
				salida.writeUTF("Conectado al servidor");
				socket.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
