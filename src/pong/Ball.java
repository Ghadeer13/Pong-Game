
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class Ball extends Rectangle{
    	
	Random random;
	int xVelocity;
	int yVelocity;
	int Speed = 3;
	
	Ball(int x, int y, int width, int height){
            super(x,y,width,height);
		random = new Random(); //تتحرك الكوره بشكل عشوائي
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0) // if direction is 0 the ball go left if its 1 go right//
			randomXDirection--;
		setXDirection(randomXDirection*Speed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*Speed);
            
            
        }
        //the direction of ball
        public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
        
        }
        public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
        }
         public void move() {
         
		x += xVelocity;
		y += yVelocity;
         }
         //prameter graphics g
    public void draw(Graphics g)  {
    g.setColor(Color.white); 
    g.fillOval(x, y, height, width);
    }


}