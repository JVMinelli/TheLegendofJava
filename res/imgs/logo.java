package imgs;

import Entidades.Entidade;
import main.PainelJogo;

public class logo extends Entidade {

    public logo(PainelJogo pj) {
        super(pj);

        direção = "baixo";

        getImagem();

    }

    public void getImagem() {
        baixo1 = setup("/player/logo.png", pj.tamanhoTile, pj.tamanhoTile);
    }
}
