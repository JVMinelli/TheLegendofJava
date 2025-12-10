package Entidades;

import main.PainelJogo;

public class NPCdeserto1 extends Entidade{

    public NPCdeserto1(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Meu Deus/nO sol está de matar calango!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCdeserto", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}

