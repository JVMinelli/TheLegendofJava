package Entidades;

import main.PainelJogo;

public class NPCcristaldeserto extends Entidade{

    public NPCcristaldeserto(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Parabéns!/nAqui está o cristal amarelo, para encontrar o terceiro cristal,/nsiga pela porta até o pântano!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCcristaldeserto", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}

