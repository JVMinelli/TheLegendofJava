package Entidades;

import main.PainelJogo;

public class NPCpantano extends Entidade{
    public NPCpantano(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "O monstro nesse castelo possui o cristal azul./nBoa sorte Herói!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/npcpantano1", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}


