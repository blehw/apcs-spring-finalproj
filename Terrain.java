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

    
    public void soften1() {
	Random rand = new Random();
	int c = 0;
	while (c < 6) {
	    for (int a=0;a<maxY;a++) {
		for (int b=0;b<maxX;b++) {
		    if (terrain[a][b].getType() == "grass"  && 
			rand.nextInt(100) < 40) {  
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

    
    public void soften2() {
	Random rand = new Random();
	int c = 0;
	while (c < 3) {
	    for (int a=maxY-1;a>0;a--) {
		for (int b=0;b<maxX;b++) {
		    if (terrain[a][b].getType() == "grass"  && 
			rand.nextInt(100) < 40) {  
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

    public void soften3() {
	Random rand = new Random();
	int c = 0;
	while (c < 3) {
	    for (int a=maxY-1;a>0;a--) {
		for (int b=maxX-1;b>0;b--) {
		    if (terrain[a][b].getType() == "grass"  && 
			rand.nextInt(100) < 40) {  
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

    public void soften4() {
	Random rand = new Random();
	int c = 0;
	while (c < 3) {
	    for (int a=0;a<maxY;a++) {
		for (int b=maxX-1;b>0;b--) {
		    if (terrain[a][b].getType() == "grass"  && 
			rand.nextInt(100) < 40) {  
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
	int[][] arr = new int[8][20];
	for (int b=0;b<8;b++) {
	    for (int a=0;a<20;a++) {
		if (rand.nextInt(100) < 85) {
		    arr[b][a] = 1; 
		} else {
		    arr[b][a] = 0;
		}
	    }
	}
	arr[0][0] = 0;
	for (int i=0;i<4;i++) {
	    for (int k=0;k<10;k++) {
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
	soften1();
	soften2();
	soften3();
	soften4();
	
	int g = rand.nextInt(5) + 8;

	for (int h = 0;h<g;h++) {
	    int randX = rand.nextInt(100);
	    int randY = rand.nextInt(40);
	    Enemy enemy = new Enemy();
	    if (terrain[randY][randX].getType() == "grass") {
		terrain[randY][randX].setCharacter(enemy);
	    }
	}
	for (int a = 0;a<200;a++) {
	    terrain[79][a].setType("mana");
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
