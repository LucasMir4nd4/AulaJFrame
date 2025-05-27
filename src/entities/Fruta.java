package entities;

import entities.frame.Tela;

import java.awt.*;
import java.util.Random;

public class Fruta {
    private Point fruta;
    private final int tamanhoBloco;
    private final Random aleatorio;


    public Fruta( int tamanhoBloco) {
        this.tamanhoBloco = tamanhoBloco;
        this.aleatorio = new Random();
        gerarNovaFruta();
    }


    public void gerarNovaFruta(){
        int x = aleatorio.nextInt(Tela.NUM_BLOCOS);
        int y = aleatorio.nextInt(Tela.NUM_BLOCOS);

        fruta = new Point(x,y);
    }

    public Point getFruta() {
        return fruta;
    }
    public void desenharFruta(Graphics gr){
        gr.setColor(Color.RED);
        gr.fillOval(fruta.x *Tela.TAMANHO_BLOCO, fruta.y*Tela.TAMANHO_BLOCO, Tela.TAMANHO_BLOCO, Tela.TAMANHO_BLOCO);
    }
}
