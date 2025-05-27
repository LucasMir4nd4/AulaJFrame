package entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cobra extends JPanel {

    private final ArrayList<Point> corpo;
    private Direcao direcao = Direcao.DIREITA;


    public Cobra(Direcao direcao){
        this.direcao = Direcao.DIREITA;
        corpo = new ArrayList<>();
        corpo.add(new Point(5, 5));  // cabeça
        corpo.add(new Point(4, 5));  // corpo
        corpo.add(new Point(3, 5));  // cauda

        setFocusable(true); // Permite que o painel receba foco


    }

    public void mover(){
        Point cabeca = corpo.getFirst();
        Point nova = new Point(cabeca);

        switch (direcao){
            case CIMA: nova.y -= 1;break;
            case BAIXO: nova.y += 1;break;
            case DIREITA: nova.x += 1;break;
            case ESQUERDA: nova.x -= 1;break;
        }


        corpo.addFirst(nova);   // adiciona nova cabeça
        corpo.removeLast(); // remove a cauda
    }



    public ArrayList<Point> getCorpo() {
        return corpo;
    }
    public Point getCabeca(){
        return corpo.getFirst();
    }

    public void crescer(){
        Point cauda = corpo.get(corpo.size() - 1);
        corpo.add(new Point(cauda));
    }
    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public Direcao getDirecao() {
        return direcao != null? direcao : Direcao.DIREITA;
    }
}
