package application;

import entities.Cobra;
import entities.frame.Tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        Cobra cobra = new Cobra();
        Tela tela = new Tela(cobra);

        Timer timer = new Timer(150, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cobra.mover();     // atualiza a posição
                tela.repaint();    // redesenha na tela
            }
        });
        timer.start();
    }
}