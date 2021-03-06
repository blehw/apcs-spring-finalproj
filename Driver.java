import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.util.*;
import java.io.*;

public class Driver extends Canvas implements Runnable{
    
    
    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 200;
    public static final int HEIGHT = 81;
    public static final int SCALE = 10;
    public static final String NAME = "Game";
    
    private JFrame frame;
    
    public boolean running = false;
    public int tickCount = 0;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    public InputHandler input;    

    private static final Terrain terrain = new Terrain();
    private int x = 10;
    private int y = 10;
    Baller baller = new Baller();
    private Random rand = new Random();
    private double nsPerTick = 7500000000D/60D;

    public Driver(){
	setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	
	frame = new JFrame(NAME);
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(new BorderLayout());
	
	frame.add(this, BorderLayout.CENTER);
	frame.pack();
	
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);	

	input = new InputHandler(this);
    }
    
    public synchronized void start() {
	running = true;
	new Thread(this).start();
    }
    
    public synchronized void stop() {
	running = false;
    }
    
    public void run() {	
	long lastTime = System.nanoTime();
	
	int frames = 0;
	int ticks = 0;

	long lastTimer = System.currentTimeMillis();
	double delta = 0;
	
	while (running){
	    long now = System.nanoTime();
	    delta += (now - lastTime) / nsPerTick;
	    lastTime = now;
	    boolean shouldRender = true;
	    while(delta >= 1)
		{
		    ticks++;
		    tick();
		    delta -= 1;
		    shouldRender = true;
		}	    
	    if (shouldRender)
		{
		    frames++;
		    render();
		}
	    
	    if(System.currentTimeMillis() - lastTimer >= 1000){
		lastTimer += 1000;
		System.out.println(ticks + " ticks, " + frames + " frames");
		frames = 0;
		ticks = 0;
	    }
	}
    }
    
    public void tick(){
     
	tickCount++;

	Tile[][] t = terrain.getTerrain();

	if (t[y][x].getCharacter().getID() == "enemy") {
	    JOptionPane.showMessageDialog(frame, "You dead, son.");
	    frame.dispose();
	}

	if (baller.getMana() < 0) {
	    baller.setMana(0);
	} 
		      
	baller.setMana(baller.getMana() + 1);
		    
	for (int y=0;y<terrain.getmaxY();y++) {
	    for (int x=0;x<terrain.getmaxX();x++) {
		if (t[y][x].getCharacter() != null) {
		    if (t[y][x].getCharacter().getID() == "enemy") {
			Enemy e = new Enemy();
			
			if (rand.nextInt(100) <50) {
			    int direction = rand.nextInt(4);
			    if (direction == 0 && y-1 > -1) {
			   	Random r = new Random();
			   	if(!(r.nextInt(8)==4 && e.getReplicate())){
			   		t[y][x].setCharacter(null);
			   	}
				
				t[y-1][x].setCharacter(e);
				y--;
			    }
			    if (direction == 1 && y+1  < terrain.getmaxY()) {
			    	Random r = new Random();
				if(!(r.nextInt(8)==4 && e.getReplicate())){
			   		t[y][x].setCharacter(null);
			   	}
				t[y+1][x].setCharacter(e);
				y++;
			    }
			    if (direction == 2 && x-1 > -1) {
			    	Random r = new Random();
				if(!(r.nextInt(8)==4 && e.getReplicate())){

			   		t[y][x].setCharacter(null);
			   	}
				t[y][x-1].setCharacter(e);
				x--;
			    }
			    if (direction == 3 && x+1 < terrain.getmaxX()) {
			    	Random r = new Random();
				if(!(r.nextInt(8)==4 && e.getReplicate())){
			   		t[y][x].setCharacter(null);
			   	}
				t[y][x+1].setCharacter(e);
				x++;
			    }
			}
		    }
		}
		
	    }
	}
	
	if (t[y][x].getType() == "water") {
	    nsPerTick = 15000000000D/60D;
	}
	if (t[y][x].getType() == "grass") {
	    nsPerTick = 7500000000D/60D;
	}


	if((input.up.isPressed() || input.left.isPressed() || input.right.isPressed() || input.down.isPressed()) && (input.space.isPressed())){
	    if(baller.getMana()>0){
		baller.setMana(baller.getMana()-2);
	    }
	    
	}

	if (input.up.isPressed()  && y > 0 && t[y-1][x].getType() != "water" && (t[y-1][x].getCharacter() == null || (input.space.isPressed() && t[y][x].getCharacter().getMove() == "magic"))){
	    t[y][x].getCharacter().setDirection("up");
	    t[y-1][x].setCharacter(t[y][x].getCharacter());
	    t[y][x].setCharacter(null);
	    y--;
	  
	}
	if (input.down.isPressed() && y+1 < terrain.getmaxY() && t[y+1][x].getType() != "water" && (t[y+1][x].getCharacter() == null ||(input.space.isPressed() && t[y][x].getCharacter().getMove() == "magic"))){
	    t[y][x].getCharacter().setDirection("down");
	    t[y+1][x].setCharacter(t[y][x].getCharacter());
	    t[y][x].setCharacter(null);
	    y++;
	   
	}
	if (input.left.isPressed()  && x > 0 && t[y][x-1].getType() != "water" && (t[y][x-1].getCharacter() == null || (input.space.isPressed() && t[y][x].getCharacter().getMove() == "magic"))){
	//if (input.left.isPressed() && x > 0 && t[y][x-1].getType() != "water" (t[y-1][x].getType() != "enemy" || input.space.isPressed())){
	    t[y][x].getCharacter().setDirection("left");
	    t[y][x-1].setCharacter(t[y][x].getCharacter());
	    t[y][x].setCharacter(null);
	    x--;
	  
	}
	if (input.right.isPressed()  && x+1 > 0 && t[y][x+1].getType() != "water" && (t[y][x+1].getCharacter() == null|| (input.space.isPressed() && t[y][x].getCharacter().getMove() == "magic"))){
	//if (input.right.isPressed() && x+1 < terrain.getmaxX() &&
	    //t[y][x+1].getType() != "water" (t[y-1][x].getType() != "enemy" || input.space.isPressed())){
	    t[y][x].getCharacter().setDirection("right");
	    t[y][x+1].setCharacter(t[y][x].getCharacter());
	    t[y][x].setCharacter(null);
	    x++;
	 
	}

	if (!input.space.isPressed()) {

	    if (input.up.isPressed() && y > 0 && t[y-1][x].getCharacter() != null) {
		t[y][x].setCharacter(null);
		y--;
	    }
	    
	    if (input.down.isPressed() && y+1 < terrain.getmaxY() && t[y+1][x].getCharacter() != null) {
		t[y][x].setCharacter(null);
		y++;
	    }
	    
	    if (input.left.isPressed() && x>0 && t[y][x-1].getCharacter() != null) {
		t[y][x].setCharacter(null);
		x--;
	    }
	    
	    if (input.right.isPressed() && x+1 < terrain.getmaxX() && t[y][x+1].getCharacter() != null) {
		t[y][x].setCharacter(null);
		x++;
	    }
	}

	if (input.space.isPressed()) {
		if(baller.getMana()>0){
			String direction = t[y][x].getCharacter().getDirection();
	    String move = t[y][x].getCharacter().getMove();
	    if (move == "magic") {
		t[y][x].getCharacter().setBallskillz(t[y][x].getCharacter().getBallskillz() - 10);
		if (direction == "up") {
		    t[y+1][x].setType("magic");
		}
		if (direction == "down") {
		    t[y-1][x].setType("magic");
		}
		if (direction == "right") {
		    t[y][x-1].setType("magic");
		}
		if (direction == "left") {
		    t[y][x+1].setType("magic");
		}
	    }
	    if (move == "freeze") {
		t[y][x].getCharacter().setBallskillz(t[y][x].getCharacter().getBallskillz() - 20);
		for (int i=y-2;i<y+2;i++) {
		    for (int k=x-2;k<x+2;k++) {
			t[i][k].setType("freeze");
			t[i][k].setImage("^");
		    }
		}
		for (int a=y-3;a<y+3;a++) {
		    for (int b=x-3;b<x+3;b++) {
			if (rand.nextInt(100) < 10) {
			    t[a][b].setType("freeze");
			    t[a][b].setImage("^");
			}
		    }
		}
		/*
		if (direction == "up") {
		    t[y+1][x].setType("freeze");
		}
		if (direction == "down") {
		    t[y-1][x].setType("freeze");
		}
		if (direction == "right") {
		    t[y][x-1].setType("freeze");
		}
		if (direction == "left") {
		    t[y][x+1].setType("freeze");
		}
		*/
	    }
		}
	    
	}
	if (input.z.isPressed()) {
	    t[y][x].getCharacter().setMove("magic");
	}
	if (input.x.isPressed()) {
	    t[y][x].getCharacter().setMove("freeze");
	}
    }
    
    public void render(){
	
	BufferStrategy bs = getBufferStrategy();
	if (bs == null){
	    createBufferStrategy(3);
	    return;
	}
	
	Graphics g = bs.getDrawGraphics();
	
	Tile[][] t = terrain.getTerrain();
	
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

	g.dispose();
	bs.show();

	for (int y=0;y<terrain.getmaxY();y++) {
	    for (int x=0;x<terrain.getmaxX();x++) {
		if (t[y][x].getType() == "grass") {
		    image.setRGB(x,y,Color.GREEN.getRGB());
		}
		if (t[y][x].getType() == "water") {
		    image.setRGB(x,y,Color.BLUE.getRGB());
		}
		if (t[y][x].getType() == "magic") {
		    image.setRGB(x,y,Color.ORANGE.getRGB());
		}
		if (t[y][x].getType() == "freeze") {
		    //Color lightblue = new Color(0,255,255);
		    image.setRGB(x,y,Color.CYAN.getRGB());
		}
		if (t[y][x].getType() == "mana") {
		    image.setRGB(x,y,Color.WHITE.getRGB());
		}
		if (t[y][x].getCharacter() != null) {
		    if (t[y][x].getCharacter().getID() == "baller") {
			image.setRGB(x,y,Color.RED.getRGB());
		    }
		    if (t[y][x].getCharacter().getID() == "enemy") {
			image.setRGB(x,y,Color.YELLOW.getRGB());
			if(t[y][x].getCharacter().getReplicate()){
				image.setRGB(x,y,Color.CYAN.getRGB());
			}
		    }
		    
		}
		/*
		if (t[y][x].getCharacter() == "monster") {
		    image.setRGB(x,y,Color.YELLOW.getRGB());
		}
		*/
	    }
	}
	baller.setX(x);
    baller.setY(y);
    }
    
    public static void main(String[] args){ 
	new Driver().start();	
    }
    
}
