package entities.frame;

import entities.Cobra;
import entities.Direcao;
import entities.Fruta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

public class Tela extends JFrame {

    public static final int LARGURA_TELA = 600;
    public static final int ALTURA_TELA = 600;
    public static final int NUM_BLOCOS = 20;
    public  static  final int TAMANHO_BLOCO = LARGURA_TELA/NUM_BLOCOS;



    public Tela(Cobra cobra, Fruta fruta) {



        //CONFIGURAÇÕES DA TELA
        setTitle("Snake Game");
        PainelJogo painel = new PainelJogo(cobra,fruta);
        setContentPane(painel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/images/icon.png");
        setIconImage(icon.getImage());




        setVisible(true);
        }
        private static class PainelJogo extends JPanel implements KeyListener {
            private final Cobra cobra;
            private final Fruta fruta;
            private final Image fundo;
            private final JButton botao;
            private boolean rodando = false;
            boolean podeMudarDirecao = true;
            private final Timer timer;


            public PainelJogo(Cobra cobra, Fruta fruta){

                this.cobra = cobra;
                this.fruta = fruta;

                setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));


                timer = new Timer(150, _ -> {
                    if (rodando){
                        cobra.mover();
                        podeMudarDirecao = true;
                        Point cabeca = cobra.getCabeca();
                        Point posFruta = fruta.getFruta();

                        if (cabeca.equals(posFruta)){

                            System.out.println("Comeu a Fruta");
                            cobra.crescer();
                            fruta.gerarNovaFruta();
                        }
                        repaint();
                    }
                });

                fundo = new ImageIcon("src/images/fundo.png").getImage();

                setLayout(null);

                //DIFINE O BOTÂO
                ImageIcon imagemBotao = new ImageIcon ("src/images/ImagemBotao1.png");
                botao = new JButton("Jogar", imagemBotao);

                add(botao);

                //DEFINE AS PROPRIEDADES DO BOTÃO
                botao.setHorizontalTextPosition(SwingConstants.CENTER); // texto centralizado na horizontal
                botao.setVerticalTextPosition(SwingConstants.CENTER);   // texto centralizado na vertical
                botao.setContentAreaFilled(false);  // remove fundo padrão do botão
                botao.setSize(200,70);
                botao.setFont(carregarFont());
                botao.setBorderPainted(false);
                botao.setForeground(Color.WHITE);



                botao.addActionListener(_ -> iniciarJogo());

                SwingUtilities.invokeLater(()->{
                    int x = ((LARGURA_TELA - botao.getWidth())/2)-6;
                    int y = ((ALTURA_TELA - botao.getHeight())/2)-6;
                    botao.setBounds(x, y, botao.getWidth(), botao.getHeight());

                });

                requestFocus();
                addKeyListener(this);

                setFocusable(true);
                SwingUtilities.invokeLater(this::requestFocusInWindow);

            }

            private void iniciarJogo(){

                remove(botao);
                rodando = true;
                timer.start();
                revalidate();
                repaint();

                requestFocusInWindow();
            }
            @Override
            public void paintComponent(Graphics gr) {
                super.paintComponent(gr);
                gr.drawImage(fundo, 0,0, getWidth(),getHeight(),this );

                if (rodando){
                    fruta.desenharFruta(gr);
                    //PINTA A COBRA
                    gr.setColor(Color.BLACK);
                    for (Point p : cobra.getCorpo()) {
                        gr.fillRect(p.x * TAMANHO_BLOCO, p.y * TAMANHO_BLOCO, TAMANHO_BLOCO, TAMANHO_BLOCO); // cada bloco 20x20 pixels
                    }

                }

        }


            @Override
            public void keyPressed(KeyEvent e) {
                Direcao direcaoAtual = cobra.getDirecao();
                Direcao novaDirecao = null;


                int key = e.getKeyCode();
                    //SETAS
                    switch (key){
                        case KeyEvent.VK_A , KeyEvent.VK_LEFT-> novaDirecao = Direcao.ESQUERDA;
                        case KeyEvent.VK_D , KeyEvent.VK_RIGHT-> novaDirecao = Direcao.DIREITA;
                        case KeyEvent.VK_W , KeyEvent.VK_UP-> novaDirecao = Direcao.CIMA;
                        case KeyEvent.VK_S , KeyEvent.VK_DOWN-> novaDirecao = Direcao.BAIXO;
                    }
                    if (podeMudarDirecao && novaDirecao != null && novaDirecao != direcaoAtual.oposta() ){
                        cobra.setDirecao(novaDirecao);
                        podeMudarDirecao = false;
                    }
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
            // CONFIGURAÇÕES DA FONTE
            private Font carregarFont(){
                try (InputStream fontStream = getClass().getResourceAsStream("/font/PressStart2P-Regular.ttf")) {
                    if (fontStream != null) {
                        return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, 24f);
                    } else {
                        System.out.println(" Fonte não encontrada.");
                    }
                } catch (FontFormatException | IOException e) {
                    System.out.println("Erro ao carregar a fonte: " + e.getMessage());
                }
                return new Font("SansSerif", Font.PLAIN, 24);

            }
    }

}

