package model;

import processing.core.PApplet;

/**
 * @author erika
 *
 */
public class Particula {

	String name;
	int number;
	int x, y;
	String color;
	PApplet app;

	private int r, g, b;
	private int dirX, dirY, speed;
	private boolean pause;

	public Particula(String name, PApplet app, int x, int y, String color) {
		this.app = app;
		this.name = name;
		this.x = x;
		this.y = y;
		this.color = color;

		this.r = r;
		this.g = g;
		this.b = b;
		this.dirX = -1;
		this.dirY = 1;
		this.speed = 1;
		this.pause = false;

	}

	public void draw(Particula p) {
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
		app.ellipse(p.getX(), p.getY(), 50, 50);
	}

	public void move() {
		//se comienzan a mover
		x+=dirX*speed;
        y+=dirY*speed;
        
        //Rebote
        if(x>app.width||0>x) {
            dirX=-dirX;
        }
        
        if(y>app.height||0>y) {
            dirY=-dirY;
        }
	}

	public void dirRandom (Particula p, int randomX, int randomY) {
		
	    p.setDirX(randomX-p.getX());
	    p.setDirY(randomY-p.getY());

	    int dist = (int) Math.sqrt(p.getDirX() * p.getDirX() + p.getDirY() * p.getDirY());
	    if(dist != 0.0) {
	    	p.setDirX(p.getDirX()/dist);
	    	p.setDirY(p.getDirY()/dist);
	    }
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

	public int getDirX() {
		return dirX;
	}

	public void setDirX(int dirX) {
		this.dirX = dirX;
	}

	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Boolean getPause() {
		return pause;
	}

	public void setPause(Boolean pause) {
		this.pause = pause;
	}
	
}
