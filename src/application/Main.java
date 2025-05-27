package application;

import entities.Cobra;
import entities.Direcao;
import entities.Fruta;
import entities.frame.Tela;



public class Main {
    public static void main(String[] args) {


        Tela  tela = new Tela(new Cobra(Direcao.DIREITA), new Fruta(Tela.TAMANHO_BLOCO  ));



    }
}