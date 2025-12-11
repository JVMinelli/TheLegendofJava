package objts;

import Entidades.Entidade;
import main.PainelJogo;

public class OBJ_cristalazul extends Entidade {

    public static final String nomeObj = "Cristal-Azul";

    public OBJ_cristalazul(PainelJogo pj){

        super(pj);
        this.pj = pj;

        nome=nomeObj;
        imagem = setup("/objs/cristal-azul", pj.tamanhoTile, pj.tamanhoTile);
        baixo1=imagem;


    }

}
