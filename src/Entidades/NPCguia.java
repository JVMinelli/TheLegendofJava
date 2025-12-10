package Entidades;

import main.PainelJogo;

public class NPCguia extends Entidade{

    public NPCguia(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Herói!/nAcho que alguns slimes entraram na casa do Mago!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/jonas", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}