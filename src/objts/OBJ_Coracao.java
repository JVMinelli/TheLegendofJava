package objts;

import Entidades.Entidade;
import main.PainelJogo;

public class OBJ_Coracao extends Entidade {

    public OBJ_Coracao(PainelJogo pj) {
        super(pj);

        nome = "Coracao";
        imagem = setup("/objs/coracao0", pj.tamanhoTile, pj.tamanhoTile);
        imagem2 = setup("/objs/coracao1",  pj.tamanhoTile, pj.tamanhoTile);
        imagem3 = setup("/objs/coracao2", pj.tamanhoTile, pj.tamanhoTile);
    }
}
