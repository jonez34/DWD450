/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiloconverter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hjones2368
 */
public class KiloConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        JFrame myFrame = new JFrame();
        myFrame.setVisible(true);
        myFrame.setSize(200, 300);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel myPanel = new JPanel();
        myFrame.add(myPanel);
        JButton btn = new JButton("Nooo");
        btn.setText("Hello");
        myPanel.add(btn);
        JTextField txt = new JTextField(15);
        txt.setText("jhkjkhklh");
        myPanel.add(txt);
    
    }
    
}
