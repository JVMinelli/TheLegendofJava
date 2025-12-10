package objts;

import Entidades.Entidade;
import main.PainelJogo;

public class OBJ_cristaldourado extends Entidade {

    PainelJogo pj;
    public OBJ_cristaldourado(PainelJogo pj){

        super(pj);

        this.pj=pj;

        nome="Cristal-Dourado";
        imagem = setup("/objs/cristal-dourado", pj.tamanhoTile, pj.tamanhoTile);
        baixo1=imagem;
    }
}