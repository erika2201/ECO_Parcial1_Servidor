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
	public static void main(String[] args) {
		PApplet.main("main.Main");

	}

	public void settings() {
		size(1200, 700);
	}

	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	private String color;
	private int r, g, b;

	private ArrayList<Particula> arrayPart;


	public void setup() {
		r = 0;
		g = 0;
		b = 0;
		color = " ";
		arrayPart = new ArrayList<Particula>();
		initServer();
	}

	public void draw() {
		background(255);
		drawParticulas2();

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
							Orden obj = gson.fromJson(line, Orden.class); // deserializaciòn

							// cambiar luego
							createParticulas(obj.getName(), obj.getNumber(), obj.getX(), obj.getY(), obj.getColor());

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		).start();
	}

	// Un for que recorre el array de las particulas, recibe los parametros de la
	// clase PARTCIULA
	public void createParticulas(String name, int numPart, int x, int y, String color) {
		for (int i = 0; i < numPart; i++) {
			Particula p = new Particula(name, this, x, y, color);
			arrayPart.add(p);
		}
	}

	public void drawParticulas2() {
		for (int i = 0; i < arrayPart.size(); i++) {
			drawParticulas(arrayPart.get(i));
		}
	}

	// Se dibujan, pero llamo este metodo arriba en el for que recorre
	public void drawParticulas(Particula p) {
		switch (color) {
		case "ROJO":
			r = 255;
			g = 0;
			b = 0;

			break;

		case "VERDE":
			r = 0;
			g = 255;
			b = 0;
			break;

		case "AZUL":
			r = 0;
			g = 0;
			b = 255;
			break;
		default:

		}

		fill(r, g, b);
		ellipse(p.getX(),p.getY(),50,50);
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
