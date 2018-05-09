package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
    public void paintComponent(Graphics g){  
        super.paintComponent(g);  
        Image image = new ImageIcon("you.jpg").getImage();  
        int x = 0;
		int y = 0;
		g.drawImage(image, x, y, this);
//		g.drawImage(image, this.getX(), this.getY(), this.getWidth(),this.getHeight(),this); 
//		if (x>this.getWidth() && y>this.getHeight()) 
//			return;
//		if (x>this.getHeight()) {
//			x = 0;
//			y += this.getHeight();
//			
//		}
//		else {
//			x += this.getWidth();
//		}
		
    }

}
