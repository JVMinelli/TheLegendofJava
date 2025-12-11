package monstro;

import Entidades.Entidade;
import main.PainelJogo;
import objts.OBJ_chave;
import objts.OBJ_cristalazul;

import java.util.Random;

public class mon_Boss extends Entidade {

    PainelJogo pj;
    public static final String nomeMon = "Golem";

    public mon_Boss(PainelJogo pj) {
        super(pj);

        this.pj=pj;

        tipo = 2;
        nome = "Boss";
        Vel = 2;
        vidaMax=10;
        vida=vidaMax;
        boss = true;
        dormir = true;

        int tamanho = pj.tamanhoTile*5;

        areaSolida.x = 48;
        areaSolida.y = 48;
        areaSolida.width = tamanho - 48*2;
        areaSolida.height = tamanho - 48;
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;
        areaAtaque.width = 170;
        areaAtaque.height = 170;
        duracaoAcao1 = 45;
        duracaoAcao2 = 85;

        getImage();
        getImagemAtaque();
        setDialogo();
    }
    public void getImage() {

        int i = 5;
        cima1 = setup("/monster/golem-cima-1", pj.tamanhoTile*i, pj.tamanhoTile*i);
        cima2 = setup("/monster/golem-cima-2", pj.tamanhoTile*i, pj.tamanhoTile*i);
        baixo1 = setup("/monster/golem-frente-1", pj.tamanhoTile*i, pj.tamanhoTile*i);
        baixo2 = setup("/monster/golem-frente-2", pj.tamanhoTile*i, pj.tamanhoTile*i);
        esquerda1 = setup("/monster/golem-esquerda-1", pj.tamanhoTile*i, pj.tamanhoTile*i);
        esquerda2 = setup("/monster/golem-esquerda-2", pj.tamanhoTile*i, pj.tamanhoTile*i);
        direita1 = setup("/monster/golem-direita-1", pj.tamanhoTile*i, pj.tamanhoTile*i);
        direita2 = setup("/monster/golem-direita-2", pj.tamanhoTile*i, pj.tamanhoTile*i);
    }
    public void getImagemAtaque() {

        int i = 5;
        cimaAtaque = setup("/monster/golem-cima-atacando-1", pj.tamanhoTile*i, pj.tamanhoTile*i*2);
        cima1Ataque = setup("/monster/golem-cima-atacando-2", pj.tamanhoTile*i, pj.tamanhoTile*i*2);
        baixoAtaque = setup("/monster/golem-parado-2", pj.tamanhoTile*i, pj.tamanhoTile*i*2);
        baixo1Ataque = setup("/monster/golem-parado-2", pj.tamanhoTile*i, pj.tamanhoTile*i*2);
        esquerdaAtaque = setup("/monster/golem-esquerda-atacando-1", pj.tamanhoTile*i*2, pj.tamanhoTile*i);
        esquerda1Ataque = setup("/monster/golem-esquerda-atacando-2", pj.tamanhoTile*i*2, pj.tamanhoTile*i);
        direitaAtaque = setup("/monster/golem-direita-atacando-1", pj.tamanhoTile*i*2, pj.tamanhoTile*i);
        direita1Ataque = setup("/monster/golem-direita-atacando-2", pj.tamanhoTile*i*2, pj.tamanhoTile*i);
    }
    public void setDialogo() {

        dialogos[0] = "Você nunca terá o cristal azul Herói!";
    }
    public void setAcao() {
        actionLockCounter++;

        int dx = getDistanciaX(pj.jogador) / pj.tamanhoTile;
        int dy = getDistanciaY(pj.jogador) / pj.tamanhoTile;
        int distancia = dx + dy;


        if (distancia <= 8){
            seguePlayer(80);
        }


        else if (actionLockCounter == 120) {
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
        if (atacando == false) {
            checaAtaqueouNao(25, pj.tamanhoTile*10, pj.tamanhoTile*5);
        }

    }
    public void checaDrop(){

        dropaItem(new OBJ_cristalazul(pj));

    }
}
