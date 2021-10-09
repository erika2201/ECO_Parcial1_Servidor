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
import model.Particula;
import processing.core.PApplet;

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
	
	private ArrayList<Particula> arrayPart;

	public void setup() {
		arrayPart = new ArrayList<Particula>();
		initServer();
	}

	public void draw() {
		background(0);
		drawMove();
		title();
		showName();
	}
	
	public void title() {
		fill(255);
		textSize(18);
		textAlign(CENTER);
		text("Visualizador de partículas", width/2, 50);
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
				}).start();
	}

	
	// Inicio el for para crear particulas
	public void createParticulas(String name, int numPart, int x, int y, String color) {
		for (int i = 0; i < numPart; i++) {
			Particula p = new Particula(name, this, x, y, color);
			arrayPart.add(p);
		}
	}

	
	//Dibujo las particulas, llamando los métodos de abajo
	public void drawMove() {
		int randomX;
		int randomY;
		for (int i = 0; i < arrayPart.size(); i++) {
			randomX= (int) random (-10,900);
			randomY= (int) random (-10,900);
			drawParticulas(arrayPart.get(i));
			dirRandom(arrayPart.get(i),randomX,randomY);
			moveParticulas(arrayPart.get(i));
		}
	}

	
	// Llama el metodo dibujar de la clase particula
	public void drawParticulas(Particula p) {
		p.draw(p);
	}
	
	//Llama el metodo mover de la clase particula
	public void moveParticulas(Particula p) {
		if (!p.getPause()) {
			p.move();
		}
	}
	
	//Llama al metodo dirRandom de la clase particula
	public void dirRandom (Particula p, int randomX, int randomY) {
	p.dirRandom(p, randomX, randomY);
	}
	
	
	//Para que muestre el nombre si me paro dentro de una particula
	public void showName() {
		for (int i = 0; i < arrayPart.size(); i++) {
			if(dist(mouseX,mouseY,arrayPart.get(i).getX(),arrayPart.get(i).getY())<25) {
				//nombre
				fill(255);
				text("Grupo:" + arrayPart.get(i).getName(),arrayPart.get(i).getX(),arrayPart.get(i).getY());
				//que se paren
				arrayPart.get(i).setPause(true);
			}else {
				arrayPart.get(i).setPause(false);
			}
		}
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
