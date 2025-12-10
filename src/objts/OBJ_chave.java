package objts;

import Entidades.Entidade;
import main.PainelJogo;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_chave extends Entidade {

    PainelJogo pj;

    public OBJ_chave(PainelJogo pj){

        super(pj);

        this.pj=pj;

        nome="Chave";
        baixo1 = setup("/objs/key", pj.tamanhoTile, pj.tamanhoTile);
        imagem = setup("/objs/key",  pj.tamanhoTile, pj.tamanhoTile);

    }
}
