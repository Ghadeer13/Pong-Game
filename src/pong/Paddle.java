
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

//paddle is subclass of rectangle superclass
public class Paddle  extends Rectangle{
    int id;
    int yVelocity;
    int speed = 10;
 
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
            super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
           this.id=id;
            
            
	}
        
        //the direction of paddle up and down//
	public void keyPressed(KeyEvent e){
            switch(id) {
		case 1: //paddle1
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
			}
                
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		case 2: //paddle2
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;       
            }
            
            
        }
        //for player 1 click w,s and player2 the up and down keyboard
    public void keyReleased(KeyEvent e){
         switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) { //click w keyboard 
				setYDirection(0);// change speed to 0 cause oif we dont change it we will move forever//
			}
                
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
			}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;       
            }
    
    
    }
    public void setYDirection(int yDirection){
    	yVelocity = yDirection; //يتحرك لفوق وتحت  فما يحتاج اكس
    }
    public void move() {
    y= y + yVelocity;
    }
    public void draw(Graphics g) {
    	if(id==1)
			g.setColor(Color.pink);
		else
			g.setColor(Color.gray);
		g.fillRect(x, y, width, height);
    
    
    }
    
}