package application;

import entities.Cobra;
import entities.frame.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        Cobra cobra = new Cobra();
        new Tela(cobra);



    }
}