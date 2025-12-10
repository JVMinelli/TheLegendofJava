package Entidades;

import main.PainelJogo;

public class NPCdeserto2 extends Entidade{

    public NPCdeserto2(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Este calor está fazendo minha cabeça girar.../nComo eu gostaria de uma água gelada...";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCdeserto2", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}
