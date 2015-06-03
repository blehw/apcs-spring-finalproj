void setup(){
  
  
  size(1024 , 768, P2D);
  
}

class Dude{
  int x;
  int y;
  public Dude(int a, int b){
     x = a;
     y = b;
  }
  public void display(){
     ellipse(x,y,80,80);
  }
}

void draw(){
  Dude d = new Dude(60,60);
  d.display();
}
