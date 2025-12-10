package Entidades;

import main.PainelJogo;

public class NPCmago extends Entidade{

    public NPCmago(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Herói! Que bom que acordou!/nA cidade foi tomada por monstros e precisa da sua ajuda!/nSiga o caminho para entrar na cidade.";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCmago", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}
