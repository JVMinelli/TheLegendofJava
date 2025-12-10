package Entidades;

import main.PainelJogo;

public class NPCcidade3 extends Entidade{

        public NPCcidade3(PainelJogo pj) {
            super(pj);

            direção = "baixo";

            getImagem();
            setDialogo();
        }
        public void setDialogo() {
            dialogos[0] = "Monstros me dão tanto medo.../nAinda bem que você está aqui Herói";
        }
        public void getImagem() {
            baixo1 = setup("/npc/NPCcidade3", pj.tamanhoTile, pj.tamanhoTile);
        }
        public void fala() {
            super.fala();
        }
}
