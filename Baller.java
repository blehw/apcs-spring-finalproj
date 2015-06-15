
import java.io.*;
import java.util.*;

public class Baller extends Person {

    private String ID;
    private int swag;
    private int ballskillz;
    private int charitimasma;
    private int rep;
    private String direction;
    private String move;
    private int mana;
    private Boolean replicate;
    int x;
    int y;

    public Baller() {
	ID = "baller";
	x = 0;
	y = 0;
	/*
	int skills = 5;
	Random rand = new Random();

	swag = 5;
	ballskillz = 5;
	charitimasma = 5;
	
	int a = rand.nextInt(skills);
	swag = swag + a;
	skills = skills - a;

	int b = rand.nextInt(skills);
	ballskillz = ballskillz + b;
	skills = skills - b;
	
	charitimasma = charitimasma + skills;
	rep = 1;
	*/
	replicate = false;
	ballskillz = 200;
	mana = 100;
	direction = "right";
	move = "magic";
	
    }
    public Boolean getReplicate(){
    	return replicate;
    }
    public int getX(){
    	return x;
    }
    public void setX(int a){
    	x = a;
    }
    public int getY(){
    	return y;
    }
    public void setY(int a){
    	y = a;
    }
    public int getMana(){
    	return mana;
    }

    public void setMana(int m){
    	mana = m;
    }

    public String getID() {
	return ID;
    }
    
    public int getSwag() {
	return swag;
    }

    public void setSwag(int n) {
	swag += n;
    }

    public int getBallskillz() {
	return ballskillz;
    }

    public void setBallskillz(int n) {
	ballskillz += n;
    }

    public int getCharitimasma() {
	return charitimasma;
    }

    public void setCharitimasma(int n) {
	charitimasma += n;
    }

    public int getRep() {
	return rep;
    }

    public void setRep(int n) {
	rep += n;
    }

    public String getDirection() {
	return direction;
    }

    public void setDirection(String s) {
	direction = s;
    }

    public String getMove() {
	return move;
    }

    public void setMove(String s) {
	move = s;
    }

    public String toString() {
	return "swag: " + swag + " ballskillz: " + ballskillz +
	    " charitimasma: " + charitimasma;
    }

    

}
