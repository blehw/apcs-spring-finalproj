
import java.io.*;
import java.util.*;

public class Enemy extends Baller {

    private String ID;
    private int swag;
    private int ballskillz;
    private int charitimasma;
    private int rep;
    private String direction;
    private String move;
    private Boolean replicate;
    public Enemy() {
	ID = "enemy";
	
	int skills = 10;
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
	replicate = false;
	direction = "right";
	
    }

    public Boolean getReplicate(){
    	return replicate;
    }

    public void setReplicate(boolean r){
    	replicate = r;
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

    public String toString() {
	return "swag: " + swag + " ballskillz: " + ballskillz +
	    " charitimasma: " + charitimasma;
    }

    

}
