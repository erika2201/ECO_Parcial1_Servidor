package model;

public class Orden {
   
	private String key;


    public Orden(String key, boolean isActive) {
        this.key = key;
    }

    public Orden() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}