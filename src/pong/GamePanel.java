 
package pong;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000; // static final int mean means is that it's an integer that is constant for all instances of a certain class at all times.we had multiple instance of Game panel and they share just 1 variable//
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //its 5 dividd by 9// خاص بعرض وطول النافذه
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);//تحديد البعد
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;  
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	
    
    
    
    GamePanel(){
        newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);//pass the width and hight
		this.setFocusable(true); 
		this.addKeyListener(new AL());//take arg AL and its inner class يبحث عنAL وينفذها// 
		this.setPreferredSize(SCREEN_SIZE);
                
                
		gameThread = new Thread(this);
		gameThread.start();
    
    }
    public void newBall(){
      random = new Random(); 
      // want ball to start in center of window//
	ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    
    
    }
    public void newPaddles(){
        //تحكم بسرعه المضرب
    paddle1 = new Paddle(0,(GAME_HEIGHT/2)-
            (PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)
                        -(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	
 

    }
    public void paint(Graphics g){
    	image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics); //call draw method to draw all the components and pass the graphics//
		g.drawImage(image,0,0,this);  //pass the image as the coordnite where x,y is 0//
    
    
    
    } //to baint method on screen//
    public void draw(Graphics g){
    	paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    
    
    
    } 
    public void move() {
    //استدعى الموف ميثود بكلاس البدل والبل
		paddle1.move();
		paddle2.move();
		ball.move();
    
    }
    public void checkCollision(){
        	//bounce ball off top & bottom window edges
		if(ball.y <=0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
                //bounce ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
                	if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}

       //هنا يبغا المضرب بحدود النافذه ولا يروح فوق
	//stops paddles at window edges
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
    //give a player 1 point and creates new paddles & ball
		if(ball.x <=0) {
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
		}
	
    }
    public void run() {
        //the run of the game
    //game loop
        long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
                        	if(delta >=1) {
				move(); //move all components 
				checkCollision();//then check the collision
				repaint();
				delta--;
                 
                                }
                }
    }
    
    
    
   // inner classe called AL short for action listioner that contain key pressed and relased
    //from awt.event*
    public class AL extends KeyAdapter{ 
    public void keyPressed(KeyEvent e){ //يتم إستدعاءها عنما يقوم المستخدم بالنقر على أي زر من لوحة المفاتيح و قبل أن يرفع إصبعه عنه.
     paddle1.keyPressed(e);
        paddle2.keyPressed(e);
    }
    public void keyReleased(KeyEvent e){ //يتم إستدعاءها بعد أن يقوم المستخدم بإزالة إصبعه عن الزر الذي نقر عليه من لوحة المفاتيح.
    paddle1.keyReleased(e);
   paddle2.keyReleased(e);
    
    }
 
    
    }
}