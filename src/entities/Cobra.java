package entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventObject;

public class Cobra extends JPanel {

    private final ArrayList<Point> corpo;
    private String direcao = "Direita";


    public Cobra(){
        corpo = new ArrayList<>();
        corpo.add(new Point(5, 5));  // cabeça
        corpo.add(new Point(4, 5));  // corpo
        corpo.add(new Point(3, 5));  // cauda

        setFocusable(true); // Permite que o painel receba foco
        addKeyListener(new Teclado()); // Agora seu teclado funciona de verdade!

    }

    public void mover(){
        Point cabeca = corpo.getFirst();
        Point nova = new Point(cabeca);
        Teclado teclado = new Teclado();

        switch (direcao){
            case "Cima": nova.y -= 1;break;
            case "Baixo": nova.y += 1;break;
            case "Direita": nova.x += 1;break;
            case "Esquerda": nova.x -= 1;break;
        }


        corpo.addFirst(nova);   // adiciona nova cabeça
        corpo.removeLast(); // remove a cauda
    }

    private class Teclado extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key){
                case KeyEvent.VK_LEFT:
                    if (!direcao.equals("Direita")){
                        setDirecao("Esquerda");
                        break;
                }
                case KeyEvent.VK_RIGHT:
                    if (!direcao.equals("Esquerda")){
                        setDirecao("Direita");
                        break;
                }
                case KeyEvent.VK_UP:
                    if (!direcao.equals("Baixo")){
                        setDirecao("Cima");
                        break;
                }
                case  KeyEvent.VK_DOWN:
                    if (!direcao.equals("Cima")){
                        setDirecao("Baixo");
                        break;
                    }
            }
        }
    }

    public ArrayList<Point> getCorpo() {
        return corpo;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
}
