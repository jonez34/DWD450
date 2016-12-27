/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author hjones2368
 */
public class userGUI extends Data {

    JTextField userName, password;
    JButton submit;
    JPanel panel;
    JFrame frame;

    public String userGUIController() {
        JPanel panel = new JPanel();
        panel = new JPanel();
        frame = new JFrame();
        userName = new JTextField(20);
        password = new JTextField(20);
        submit = new JButton("Sign In");

        submit.addActionListener(new BListener());
        panel.add(userName);
        panel.add(password);
        panel.add(submit);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return "";
    }

    private class BListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                boolean flag = false;
                try {
                    flag = userController(userName.getText(),password.getText());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(userGUI.class.getName());
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "Wrong username and password");
                } else {
                    pNames = userName.getText();
                }

            }
        }
    }

}
