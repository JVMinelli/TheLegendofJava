package Entidades;

import main.PainelJogo;

public class NPCcidade2 extends Entidade{

    public NPCcidade2(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Herói! Que bom que você está aqui, estava/ntão assustado!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCcidade2", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}
