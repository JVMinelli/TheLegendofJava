package monstro;

import Entidades.Entidade;
import main.PainelJogo;
import objts.OBJ_chave;
import objts.OBJ_cristalazul;

import java.util.Random;

public class mon_slimeBravoCristal extends Entidade {
    PainelJogo pj;

    public mon_slimeBravoCristal(PainelJogo pj) {
        super(pj);
        this.pj = pj;

        tipo = 2;
        nome = "SlimeBravoCristal";
        Vel = 1;
        vidaMax = 20;
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
        cima1 = setup("/monster/slimeBravoCristalC", pj.tamanhoTile, pj.tamanhoTile);
        cima2 = setup("/monster/slimeBravoCristalCP", pj.tamanhoTile, pj.tamanhoTile);
        baixo1 = setup("/monster/slimeBravoCristalF", pj.tamanhoTile, pj.tamanhoTile);
        baixo2 = setup("/monster/slimeBravoCristalFP", pj.tamanhoTile, pj.tamanhoTile);
        esquerda1 = setup("/monster/slimeBravoCristalE", pj.tamanhoTile, pj.tamanhoTile);
        esquerda2 = setup("/monster/slimeBravoCristalEP", pj.tamanhoTile, pj.tamanhoTile);
        direita1 = setup("/monster/slimeBravoCristalD", pj.tamanhoTile, pj.tamanhoTile);
        direita2 = setup("/monster/slimeBravoCristalDP", pj.tamanhoTile, pj.tamanhoTile);
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
        actionLockCounter = 0;
        direção = pj.jogador.direção;
    }

    public void checaDrop(){

            dropaItem(new OBJ_cristalazul(pj));

    }
}

