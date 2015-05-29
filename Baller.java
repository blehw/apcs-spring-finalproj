
import java.io.*;
import java.util.*;

public class Baller {

    private int swag;
    private int ballskillz;
    private int charitimasma;
    private int rep;
    
    public Baller() {
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

    public String toString() {
	return "swag: " + swag + " ballskillz: " + ballskillz +
	    " charitimasma: " + charitimasma;
    }

    public static void main(String[] args) {
	Baller b = new Baller();
	System.out.println(b);
    }

}
