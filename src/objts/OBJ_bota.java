package objts;

import Entidades.Entidade;
import main.PainelJogo;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_bota extends Entidade {

    PainelJogo pj;

    public OBJ_bota(PainelJogo pj) {

        super(pj);

        this.pj = pj;

        nome = "Bota";
        baixo1 = setup("/objs/boots", pj.tamanhoTile, pj.tamanhoTile);

    }
}
