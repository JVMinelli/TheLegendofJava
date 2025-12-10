package objts;


import Entidades.Entidade;
import main.PainelJogo;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_porta extends Entidade {

    PainelJogo pj;

    public OBJ_porta(PainelJogo pj){
        super(pj);

        this.pj=pj;

        nome="Porta";
        baixo1 = setup("/objs/door", pj.tamanhoTile, pj.tamanhoTile);
        colisao=true;
    }
}
