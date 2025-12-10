package objts;

import Entidades.Entidade;
import main.PainelJogo;

public class OBJ_cristalvermelho extends Entidade {

    public OBJ_cristalvermelho(PainelJogo pj) {
        super(pj);

        this.pj = pj;


        tipo = 3;
        nome = "Cristal-Vermelho";
        imagem = setup("/objs/cristal-vermelho", pj.tamanhoTile, pj.tamanhoTile);
        baixo1=imagem;
    }
}
