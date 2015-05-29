import java.util.*;
import java.io.*;

public class Terrain {

    private Tile[][]  terrain;
    private int maxX;
    private int maxY;

    public Terrain() {
	maxX = 100;
	maxY = 40;
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

	/*
	int c = 0;
	while (c < 6) {
	    for (int a=0;a<maxY;a++) {
		for (int b=0;b<maxX;b++) {
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
	*/
	
	
	int g = rand.nextInt(5) + 8;

	for (int h = 0;h<g;h++) {
	    int randX = rand.nextInt(100);
	    int randY = rand.nextInt(40);
	    Baller monster = new Baller();
	    if (terrain[randY][randX].getType() == "grass") {
		terrain[randY][randX].setCharacter(monster);
		terrain[randY][randX].setImage("m");
	    }
	    /*
	    int v=0;
	    while (v<4) {
		int o = randY;
		int p = randX;
		if (rand.nextInt(100) > 1) {
		    if (p-1 > 0) {
			terrain[o][p-1].setType("water");
			terrain[o][p-1].setImage("~");
			
		    }
		    if (p+1 < maxX-1) {
			terrain[o][p+1].setType("water");
			terrain[o][p+1].setImage("~");
			p++;
		    }
		    if (o-1 > 0) {
			terrain[o-1][p].setType("water");
			terrain[o-1][p].setImage("~");
		    }
		    if (o+1 < maxY-1) {
			terrain[o+1][p].setType("water");
			terrain[o+1][p].setImage("~");
		    }
		}
	    }
	    v++;
	    */
	}
	
	
	/*
	int x = 0;
	while (x < 5) {
	    for (int y=0;y<maxY-1;y++) {
		for (int z=0;z<maxX-1;z++) {
		    if (terrain[y][z].getType() == "water"  && 
			rand.nextInt(100) > 10) {  
			if (z-1 > 0) {
			    terrain[y][z-1].setType("water");
			    terrain[y][z-1].setImage("~");
			    
			}
			if (z+1 < maxX-1) {
			    terrain[y][z+1].setType("water");
			    terrain[y][z+1].setImage("~");
			    z++;
			}
			if (y-1 > 0) {
			    terrain[y-1][z].setType("water");
			    terrain[y-1][z].setImage("~");
			}
			if (y+1 < maxY-1) {
			    terrain[y+1][z].setType("water");
			    terrain[y+1][z].setImage("~");
			}
			
			
		    }
		}
	    }
	    x = x + 1;
	}
	*/
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
