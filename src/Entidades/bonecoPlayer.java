package Entidades;

import main.PainelJogo;

public class bonecoPlayer extends Entidade{

    public static final String nomeNpc = "boneco";

    public bonecoPlayer(PainelJogo pj) {
        super(pj);

        nome = nomeNpc;
        getImagemJogador();

    }
    public void getImagemJogador(){

        cima1 = setup("/player/cima1", pj.tamanhoTile, pj.tamanhoTile);
        cima2 = setup("/player/cima2", pj.tamanhoTile, pj.tamanhoTile);
        baixo1 = setup("/player/baixo1", pj.tamanhoTile, pj.tamanhoTile);
        baixo2 = setup("/player/baixo2", pj.tamanhoTile, pj.tamanhoTile);
        esquerda1 = setup("/player/esquerda1", pj.tamanhoTile, pj.tamanhoTile);
        esquerda2 = setup("/player/esquerda2", pj.tamanhoTile, pj.tamanhoTile);
        direita1 = setup("/player/direita1", pj.tamanhoTile, pj.tamanhoTile);
        direita2 = setup("/player/direita2", pj.tamanhoTile, pj.tamanhoTile);
    }
}
