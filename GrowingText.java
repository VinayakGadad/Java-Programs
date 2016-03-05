/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;



import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

public class GrowingText extends JApplet implements Runnable {
private final JLabel lable = new JLabel("CS532-C", JLabel.CENTER);

  public GrowingText() {
            Component add = add(lable);
    new Thread(this).start();
  }

  @Override 
  public void run() {
    try {
      while (true) {
          
        if (lable.getText() == null){
        	Font f= lable.getFont();
        	
          lable.setText("CS532-C");
         
        int w= f.getSize();
          
        if(w==lable.getFont().getSize()){
        	
        	lable.setFont(new Font("Arial", Font.BOLD, w+2));
        	int second=lable.getFont().getSize();
        	if(second>=52){
        		lable.setFont(new Font("Arial", Font.BOLD, second-42));
        	}
        }            
    	}
        else{
        	  lable.setText(null);	
        }
        Thread.sleep(1000);
      }
    }
    catch (InterruptedException w) {
    }
  }

 
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Growing Text");
        frame.add(new GrowingText());
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setVisible(true);
      }
    });
  }
}

