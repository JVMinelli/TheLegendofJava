package objts;

import Entidades.Entidade;
import main.PainelJogo;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_cogumelo extends Entidade {

    PainelJogo pj;

    public OBJ_cogumelo(PainelJogo pj){

        super(pj);

        this.pj=pj;

        nome="Cogumelo";
        baixo1 = setup("/objs/mushroom", pj.tamanhoTile, pj.tamanhoTile);
    }
}
