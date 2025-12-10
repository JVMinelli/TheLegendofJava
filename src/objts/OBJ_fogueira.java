package objts;

import Entidades.Entidade;
import main.PainelJogo;

public class OBJ_fogueira extends Entidade{

    PainelJogo pj;


        public OBJ_fogueira(PainelJogo pj) {
            super(pj);

            this.pj = pj;

            nome = "fogueira";
            baixo1 = setup("/objs/fogueira", pj.tamanhoTile, pj.tamanhoTile);
            colisao = true;
        }
    }


