package objts;

import Entidades.Entidade;
import main.PainelJogo;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_bau extends Entidade {

    PainelJogo pj;

    public OBJ_bau(PainelJogo pj){

        super(pj);

        this.pj=pj;

        nome="Bau";
        baixo1 = setup("/objs/chest", pj.tamanhoTile, pj.tamanhoTile);

    }
}
