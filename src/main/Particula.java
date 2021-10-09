package main;

import processing.core.PApplet;

public class Particula {

	String name;
	int number;
	int x, y;
	String color;
	PApplet app;
	
	private int r , g ,b;

	public Particula(String name, PApplet app, int x, int y, String color) {
		this.app = app;
		this.name = name;
		this.x = x;
		this.y = y;
		this.color = color;	
		
		this.r = r;
		this.g = g;
		this.b = b;

	}

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

		app.fill(r, g, b);
		app.noStroke();
		app.ellipse(p.getX(),p.getY(),50,50);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
