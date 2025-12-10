package Entidades;

import main.PainelJogo;

public class NPCoasis extends Entidade{

    public NPCoasis(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();
        setDialogo();
    }
    public void setDialogo() {
        dialogos[0] = "Não acredito!/n Quem diria que em um deserto tão intenso,/neu acharia um oásis!";
    }
    public void getImagem() {
        baixo1 = setup("/npc/NPCoasis", pj.tamanhoTile, pj.tamanhoTile);
    }
    public void fala() {
        super.fala();
    }
}
