package com.bluesky.windows;


import javax.swing.*;


public class App extends MainApp{

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.setResizable(true);
        mainApp.setVisible(true);
        mainApp.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }



}
