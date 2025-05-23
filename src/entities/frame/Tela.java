package entities.frame;
import entities.Cobra;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tela extends JFrame {

    private static final int LARGURA_TELA = 500;
    private static final int ALTURA_TELA = 500;
    private final Cobra cobra;

    public Tela(Cobra cobra) {

        setTitle("TELA");
        setSize(LARGURA_TELA, ALTURA_TELA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());

        JLabel fundo = new JLabel(new ImageIcon("images/fundo.png"));
        fundo.setSize(1000, 1000);
        setContentPane(fundo);

        JButton botao = new JButton("Jogar");
        botao.setSize(LARGURA_TELA / 5, ALTURA_TELA / 15);

        botao.setBounds((LARGURA_TELA - botao.getWidth()) / 2, (ALTURA_TELA - botao.getHeight()) / 2, botao.getWidth(), botao.getHeight());

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("AulaJFrame/src/fontes/PressStart2P-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 24f);
            botao.setFont(font);
        } catch (FontFormatException | IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            // Se der erro, usa uma fonte padrão
            botao.setFont(new Font("SansSerif", Font.PLAIN, 24));
        }

        PainelJogo painel = new PainelJogo();
        setContentPane(painel);

        this.cobra = cobra;
        setVisible(true);
        }
        private static class PainelJogo extends JPanel{
            private Cobra cobra;
            private final Image fundo;
            public PainelJogo(){
                fundo = new ImageIcon("images/fundo.png").getImage();
            }
            @Override
            protected void paintComponent(Graphics gr) {

                gr.drawImage(fundo, 0,0, getWidth(),getHeight(),this );

                gr.setColor(Color.GREEN);
                for (Point p : cobra.getCorpo()) {
                    gr.fillRect(p.x * 20, p.y * 20, 20, 20); // cada bloco 20x20 pixels
                }
        }

    }
}
