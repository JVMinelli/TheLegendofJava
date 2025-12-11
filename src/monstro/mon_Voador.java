package monstro;

import Entidades.Entidade;
import main.PainelJogo;
import objts.OBJ_chave;

import java.util.Random;

public class mon_Voador extends Entidade {

    PainelJogo pj;

    public mon_Voador(PainelJogo pj) {
        super(pj);
        this.pj = pj;

        tipo = 2;
        nome = "Voador";
        Vel = 1;
        vidaMax = 10;
        vida = vidaMax;

        areaSolida.x = 3;
        areaSolida.y = 10;
        areaSolida.width = 42;
        areaSolida.height = 30;
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;

        getImage();
    }

    public void getImage() {
        cima1 = setup("/monster/voadorCostas1", pj.tamanhoTile, pj.tamanhoTile);
        cima2 = setup("/monster/voadorCosta2", pj.tamanhoTile, pj.tamanhoTile);
        baixo1 = setup("/monster/voadorFrente1", pj.tamanhoTile, pj.tamanhoTile);
        baixo2 = setup("/monster/voadorFrente2", pj.tamanhoTile, pj.tamanhoTile);
        esquerda1 = setup("/monster/voadorEsq1", pj.tamanhoTile, pj.tamanhoTile);
        esquerda2 = setup("/monster/voadorEsq2", pj.tamanhoTile, pj.tamanhoTile);
        direita1 = setup("/monster/voadorDir1", pj.tamanhoTile, pj.tamanhoTile);
        direita2 = setup("/monster/voadorDir2", pj.tamanhoTile, pj.tamanhoTile);
    }

    public void setAcao() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direção = "cima";
            }
            if (i > 25 && i <= 50) {
                direção = "baixo";
            }
            if (i > 50 && i <= 75) {
                direção = "esquerda";
            }
            if (i > 75 && i <= 100) {
                direção = "direita";
            }
            actionLockCounter = 0;
        }

    }

    public void ReacaoDano() {

    }

}

