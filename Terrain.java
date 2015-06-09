import java.util.*;
import java.io.*;

public class Terrain {

    private Tile[][]  terrain;
    private int maxX;
    private int maxY;

    public Terrain() {
	maxX = 200;
	maxY = 80;
	terrain = new Tile[maxY][maxX];
	for (int i=0;i<maxY;i++) {
	    for (int k=0;k<maxX;k++) {
		Tile a = new Tile();
		terrain[i][k] = a;
	    }
	}
	randomize();
	Baller b = new Baller();
	terrain[10][10].setCharacter(b);
    }

       
    public Tile[][] getTerrain() {
	return terrain;
    }

    public int getmaxX() {
	return maxX;
    }

    public int getmaxY() {
	return maxY;
    }

    
    public void soften(int d,int e,int f,int g) {
	Random rand = new Random();
	int c = 0;
	while (c < 6) {
	    for (int a=d;a<e;a++) {
		for (int b=f;b<g;b++) {
		    if (terrain[a][b].getType() == "grass"  && 
			rand.nextInt(100) > 40) {  
			if (b-1 > 0) {
			    terrain[a][b-1].setType("grass");
			    terrain[a][b-1].setImage("#");
			    
			}
			if (b+1 < maxX) {
			    terrain[a][b+1].setType("grass");
			    terrain[a][b+1].setImage("#");
			    b++;
			}
			if (a-1 > 0) {
			    terrain[a-1][b].setType("grass");
			    terrain[a-1][b].setImage("#");
			}
			if (a+1 < maxY) {
			    terrain[a+1][b].setType("grass");
			    terrain[a+1][b].setImage("#");
			}
			
			
		    }
		}
	    }
	    c = c + 1;
	}
    }
    

    public void randomize() {
	
	Random rand = new Random();
	int[][] arr = new int[2][5];
	for (int b=0;b<2;b++) {
	    for (int a=0;a<5;a++) {
		if (rand.nextInt(100) < 70) {
		    arr[b][a] = 1; 
		} else {
		    arr[b][a] = 0;
		}
	    }
	}
	arr[0][0] = 0;
	for (int i=0;i<2;i++) {
	    for (int k=0;k<5;k++) {
		if (arr[i][k] == 1) {
		    for (int a=i*20;a<i*20+20;a++) {
			for (int b=k*20;b<k*20+20;b++) {
			    terrain[a][b].setType("water");
			    terrain[a][b].setImage("~");
			}
		    }
		}
	    }
	}
	soften(0,maxY,0,maxX);


	
	int g = rand.nextInt(5) + 8;

	for (int h = 0;h<g;h++) {
	    int randX = rand.nextInt(100);
	    int randY = rand.nextInt(40);
	    Enemy enemy = new Enemy();
	    if (terrain[randY][randX].getType() == "grass") {
		terrain[randY][randX].setCharacter(enemy);
		terrain[randY][randX].setType("enemy");
		terrain[randY][randX].setImage("m");
	    }
	}
	  
    }


    public String toString() {
	String s = "";
	for (int i=0;i<maxY;i++) {
	    for (int k=0;k<maxX;k++) {
		s = s + terrain[i][k].getImage();
	    }
	    s = s + "\n";
	}
	return s;
    }
 

    public static void main(String[] args) {
	Terrain t = new Terrain();
	System.out.println(t);
    }

}
