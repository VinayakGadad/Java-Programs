/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multipleclientchat;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Vinayak
 */
public class MultipleClientChat extends Frame implements Runnable
{
    // Text field for chat
  private JTextField jt = new JTextField();

  // Text field for name
  private JTextField jt1 = new JTextField("Your name");

  // Text area to display contents
  private JTextArea jtarea = new JTextArea();

  // Socket
  private Socket socket;

  // IO streams
  private DataOutputStream dataout;
  private DataInputStream datain;

  public static void main(String[] args) {
    new MultipleClientChat();
  }

  public MultipleClientChat() {
    // Panel p1 to hold the label and text field
    JPanel p1 = new JPanel();
    p1.setLayout(new BorderLayout());
    p1.add(new JLabel("Chat here"), BorderLayout.WEST);
    p1.add(jt, BorderLayout.CENTER);

    // Panel p2 to hold the label and name field
    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());
    p2.add(new JLabel("Name"), BorderLayout.WEST);
    p2.add(jt1, BorderLayout.CENTER);

    // Panel p to hold p1 and p2
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(p1, BorderLayout.SOUTH);
    p.add(p2, BorderLayout.NORTH);

    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(new JScrollPane(jtarea), BorderLayout.CENTER);

    jt.addActionListener(new ButtonListener()); // Register listener

    jtarea.setEditable(false); // Disable editing of chat area

    setTitle("Multi Chat Client");
    setSize(500, 300);
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Center the frame
    setVisible(true); // It is necessary to show the frame here!

    try {
        // Create a socket to connect to the server
        socket = new Socket("localhost", 8000);

        // Create an input stream to receive data from the server
        datain = new DataInputStream(socket.getInputStream());

        // Create an output stream to send data to the server
        dataout = new DataOutputStream(socket.getOutputStream());

        // Start a new thread for receiving messages
        new Thread(this).start();
    }
    catch (IOException ex) {
    	jtarea.append(ex.toString() + '\n');
    }
  }

  private class ButtonListener implements ActionListener {
    @Override 
    public void actionPerformed(ActionEvent e) {
      try {
        // Get the text from the text field
        String string = jt1.getText().trim() + ": " + jt.getText().trim();

        // Send the text to the server
        dataout.writeUTF(string);

        // Clear jtf
        jt.setText("");
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  // Gets mesages from other clients
  public void run(){
      try{
          while(true){
              // Get message
              String text = datain.readUTF();

              // Display to the text area
              jtarea.append(text + '\n');
          }
      } catch (IOException ex) {
          System.err.println(ex);
      }
  }
}
