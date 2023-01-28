
package pong;

import java.awt.Color;
import javax.swing.JFrame;
public class GameFrame extends JFrame{

	GamePanel panel;

    GameFrame(){ 
    //انشاء الايطار 
		panel = new GamePanel();
		this.add(panel);     // frame في الـ panel  هنا أضاف الكائن
		this.setTitle("Pong Game"); 
                this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
                this.setBackground(Color.black);
    
    
    
    
    }
}
