package Entidades;

import main.PainelJogo;

public class NPCcidade1 extends Entidade{

    public NPCcidade1(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Há uma lenda que quem juntar 3 cristais salvará o mundo.../nEu acho que é bobagem, mas vai saber...";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCcidade1", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}
