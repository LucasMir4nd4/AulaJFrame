package entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cobra extends JPanel {

    private final ArrayList<Point> corpo;
    private String direcao = "Direita";

    public Cobra(){
        corpo = new ArrayList<>();
        corpo.add(new Point(5, 5));  // cabeça
        corpo.add(new Point(4, 5));  // corpo
        corpo.add(new Point(3, 5));  // cauda
    }

    public void mover(){
        Point cabeca = corpo.getFirst();
        Point nova = new Point(cabeca);

        switch (direcao){
            case "Cima": nova.y -= 1;break;
            case "Baixo": nova.y += 1;break;
            case "Direita": nova.x += 1;break;
            case "Esquerda": nova.x -= 1;break;
        }

        corpo.addFirst(nova);   // adiciona nova cabeça
        corpo.removeLast(); // remove a cauda
    }

    public ArrayList<Point> getCorpo() {
        return corpo;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
}
