package com.example.sprint_1;

import javax.swing.*;

import java.awt.event.*;

import java.awt.*;

public class profile_Page extends JFrame{

    private String selectedItem; // holds profile selection

    private JComboBox<String> dropdown; // makes a dropdown menu

    private JPanel panel; // makes a section within the window

    private JLabel messageLabel; // un-editable message from programmer

    private JButton selectButton; // button to start the event listener

    public profile_Page(){

        //create the frame

        JFrame window = new JFrame("Profile "); 

        window.setSize(350, 500);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create panel

        JPanel panel = new JPanel();

        //create label to list instructions

        messageLabel = new JLabel("Please select your profile");

        //create button

        selectButton = new JButton("Next");

        selectButton.addActionListener(new SelectButtonListener());

        //add all components to the panel 

        

        //create Array for profiles

        String[] options = {"", "Profile 1", "Profile 2"};

        //create dropdown

        dropdown = new JComboBox<>(options);

        //add action listener

        dropdown.addActionListener(new DropListener());

        //add dropdown to panel
        
        panel.add(messageLabel);
        panel.add(dropdown);
        panel.add(selectButton);

        //add panel to frame
        
        window.add(panel);

        //make frame visible

        window.setVisible(true);

    }

        //Store what profile was chosen from the dropdown menu
    private class DropListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            selectedItem = (String) dropdown.getSelectedItem();

            System.out.println(selectedItem);
        }
    }

    //handle event when Next button is clicked
    private class SelectButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            switch(selectedItem){
                case "Profile 1":
                    //input case in which Profile 1 is selected
                    //temporary test print
                    System.out.println("Profile 1 was selected");
                    JOptionPane.showMessageDialog(null, "Profile 1 selected");
                    return;
                case "Profile 2":
                    //input case in which Profile 2 is selected
                    JOptionPane.showMessageDialog(null, "Profile 2 selected");
                    return;
                case "":
                    JOptionPane.showMessageDialog(null, "Error: must select a profile");
                    return;
                    
            }
        }

    } 
    //main method
    public static void main(String[] args){

        new profile_Page();
    }


}

