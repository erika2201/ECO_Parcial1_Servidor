package main;

import model.Orden;
import processing.core.PApplet;

public class Particula {

	String name;
	int number;
	int x, y;
	String color;
	PApplet app;


	public Particula(String name, PApplet app, int x, int y, String color) {
		this.app = app;
		this.name = name;
		this.x = x;
		this.y = y;
		this.color = color;
		
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
