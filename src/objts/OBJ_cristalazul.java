package objts;

import Entidades.Entidade;
import main.PainelJogo;

public class OBJ_cristalazul extends Entidade {

    public OBJ_cristalazul(PainelJogo pj){

        super(pj);
        this.pj = pj;

        nome="Cristal-Azul";
        imagem = setup("/objs/cristal-azul", pj.tamanhoTile, pj.tamanhoTile);
        baixo1=imagem;
    }
}
