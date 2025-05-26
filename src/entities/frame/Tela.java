package entities.frame;

import entities.Cobra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Tela extends JFrame {

    private static final int LARGURA_TELA = 600;
    private static final int ALTURA_TELA = 600;

    public Tela(Cobra cobra) {

        //CONFIGURAÇÕES DA TELA
        setTitle("TELA");
        setSize(LARGURA_TELA, ALTURA_TELA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/images/icon.png");
        setIconImage(icon.getImage());


        PainelJogo painel = new PainelJogo(cobra);
        setContentPane(painel);


        setVisible(true);
        }
        private static class PainelJogo extends JPanel implements KeyListener {
            private final Cobra cobra;
            private final Image fundo;
            private final JButton botao;


            public PainelJogo(Cobra cobra ){

                this.cobra = cobra;
                fundo = new ImageIcon("src/images/fundo.png").getImage();

                setLayout(null);

                botao = new JButton("Jogar");
                botao.setSize(LARGURA_TELA / 5, ALTURA_TELA / 15);

                botao.setBounds((LARGURA_TELA - botao.getWidth()) / 2, (ALTURA_TELA - botao.getHeight()) / 2, botao.getWidth(), botao.getHeight());

                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/font/PressStart2P-Regular.ttf"))
                            .deriveFont(Font.PLAIN, 24f);
                    botao.setFont(font);
                } catch (FontFormatException | IOException e) {
                    System.out.println("ERROR: " + e.getMessage());
                    // Se der erro, usa uma fonte padrão
                    botao.setFont(new Font("SansSerif", Font.PLAIN, 24));
                }

                botao.addActionListener(e -> iniciarJogo());
                add(botao);

                setFocusable(true);
                requestFocus();
                addKeyListener(this);

            }

            private void iniciarJogo(){
                remove(botao);
                revalidate();
                repaint();

                Timer timer = new Timer(150, e -> {
                    cobra.mover();
                    repaint();
                });

                timer.start();

                requestFocusInWindow();
            }
            @Override
            protected void paintComponent(Graphics gr) {

                super.paintComponent(gr);

                gr.drawImage(fundo, 0,0, getWidth(),getHeight(),this );

                gr.setColor(Color.GREEN);
                for (Point p : cobra.getCorpo()) {
                    gr.fillRect(p.x * 20, p.y * 20, 20, 20); // cada bloco 20x20 pixels
                }
        }


            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                    switch (key){
                        case KeyEvent.VK_LEFT:
                            if (!cobra.getDirecao().equals("Direita")){
                                cobra.setDirecao("Esquerda");
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (!cobra.getDirecao().equals("Esquerda")){
                                cobra.setDirecao("Direita");
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (!cobra.getDirecao().equals("Baixo")){
                                cobra.setDirecao("Cima");
                            }
                            break;
                        case  KeyEvent.VK_DOWN:
                            if (!cobra.getDirecao().equals("Cima")){
                                cobra.setDirecao("Baixo");
                            }
                            break;
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
    }

}

