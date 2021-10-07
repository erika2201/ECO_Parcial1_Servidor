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
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Orden;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	private String name;
	private int number;
	private int x;
	private int y;
	private String color;
	
	private int r, g, b;
	private ArrayList<Orden> cantidad;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");

	}

	public void settings() {
		size(1200, 700);
	}

	public void setup() {
		cantidad = new ArrayList<Orden>();
		initServer();
		
		name = "";
		number = 0;
		x = 250;
		y = 250;
		color = "";
		
		r=0;
		g=0;
		b=0;
	}

	public void draw() {
		background(255);
		switch (color) {
		case "ROJO":
			r=255;
			g=0;
			b=0;
			
			break;

		case "VERDE":
			r=0;
			g=255;
			b=0;
			break;
			
		case "AZUL":
			r=0;
			g=0;
			b=255;
			break;
		default:

		}
			
		for (int i = 0; i < cantidad.size(); i++) {
			fill(r,g,b);
			ellipse(x, y, 50, 50);
		}
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
						// Recibir mensajitos

						while (true) {
							System.out.println("Esperando mensaje....");
							String line = reader.readLine();
							System.out.println("Recibido: " + line);
							
							Gson gson = new Gson();
							Orden obj = gson.fromJson(line, Orden.class); //deserializaciòn
							//cambiar luego
							cantidad.add(obj);

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		).start();
	}

	public void sendMessage(String msg) {
		new Thread(() -> {
			try {
				writer.write(msg + "\n");
				writer.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}
}
	