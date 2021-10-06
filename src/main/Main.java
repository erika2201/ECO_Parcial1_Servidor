package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import model.Orden;
import processing.core.PApplet;

public class Main extends PApplet {
	

	public static void main(String[] args) {
		PApplet.main("main.Main");

	}

	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	public void settings() {
		size(1200, 700);
	}

	public void setup() {
		background(0);
		initServer();
	}

	public void draw() {
		background(0);
		
	}

	public void initServer() {
		new Thread(
				() -> {
					try {
						// Paso 1: Esperar una conexion
						ServerSocket server = new ServerSocket(2021);
						System.out.println("Esperando conexión....");
						socket = server.accept();
						
						// Paso 3: Cliente y Server conectados
						System.out.println("Cliente conectado!!!");

						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						reader = new BufferedReader(isr);

						OutputStream os = socket.getOutputStream();
						OutputStreamWriter osw = new OutputStreamWriter(os);
						writer = new BufferedWriter(osw);
						// Recibir mensajes

						while (true) {
							System.out.println("Esperando mensaje....");
						//	String line = reader.readLine();
							
							Gson gson = new Gson();
						//	Orden orden = gson.fromJson(line, Orden.class);

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
	}

	public void sendMessage(String msg) {
		
	}
}