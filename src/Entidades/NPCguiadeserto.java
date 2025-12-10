package Entidades;

import main.PainelJogo;

public class NPCguiadeserto extends Entidade{

    public NPCguiadeserto(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Uau Herói! Você conseguiu o cristal azul!/nMais dois e você salva o mundo!/nBoa sorte Herói, estamos com você!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCguiadeserto", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}
