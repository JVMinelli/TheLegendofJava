package Entidades;

import main.PainelJogo;
import main.Utilidade;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Entidade {

    public PainelJogo pj;

    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;
    public BufferedImage cimaAtaque, baixoAtaque, esquerdaAtaque, direitaAtaque, cima1Ataque, baixo1Ataque, esquerda1Ataque, direita1Ataque;
    public BufferedImage imagem, imagem2, imagem3;
    public Rectangle areaSolida;
    public Rectangle areaAtaque = new Rectangle(0,0,0,0);
    public int areaSolidaDefaultX, areaSolidaDefaultY;
    public boolean colisao = false;

    String dialogos[] = new String[20];

    //estado
    public int mundox,mundoy;
    public String direção = "baixo";
    public int numSprite = 1;
    int indexDialogo = 0;
    public boolean colisaoLigada = false;
    public boolean invencivel = false;
    public boolean atacando = false;
    public boolean vivo = true;
    public boolean morto = false;
    boolean barraVidaOn = false;

    //contadores
    public int contaSprite = 0;
    public int actionLockCounter = 0;
    public int invencivelCounter=0;
    public int morrerCounter=0;
    public int barraVidaCounter=0;

    //atributos do personagem
    public String nome;
    public int vidaMax;
    public int vida;
    public int tipo;
    public int Vel;
    public int duracaoAcao1;
    public int duracaoAcao2;


    public Entidade (PainelJogo pj){
        this.pj = pj;

        areaSolida = new Rectangle(0,0,pj.tamanhoTile,pj.tamanhoTile);
        areaSolidaDefaultX = areaSolida.x;
        areaSolidaDefaultY = areaSolida.y;
    }

    public void setAcao(){
    }

    public void ReacaoDano(){
        invencivel = true;
    }
    public void atacando() {
        contaSprite++;
        if (contaSprite <= duracaoAcao1) {
            numSprite=1;
        }
        if (contaSprite >duracaoAcao1 && contaSprite <= duracaoAcao2) {
            numSprite = 2;

            int mundoXAtual = mundox;
            int mundoYAtual = mundoy;
            int areaSolidaWidth = areaSolida.width;
            int areaSolidaHeight = areaSolida.height;

            switch(direção) {
                case "cima":
                    mundoy-=areaAtaque.height;
                    break;
                case "baixo":
                    mundoy+=areaAtaque.height;
                    break;
                case "esquerda":
                    mundox-=areaAtaque.width;
                    break;
                case "direita":
                    mundox+=areaAtaque.width;
                    break;
            }
            areaSolida.width = areaAtaque.width;
            areaSolida.height = areaAtaque.height;

            if (tipo == 2) {


            }
            else {
                int monsterIndex = pj.cColisao.checaEntidade(this, pj.monstro);
                pj.jogador.danoMonstro(monsterIndex);
            }



            mundox=mundoXAtual;
            mundoy=mundoYAtual;
            areaSolida.width = areaSolidaWidth;
            areaSolida.height = areaSolidaHeight;
        }

        if (contaSprite >duracaoAcao2){
            numSprite = 1;
            contaSprite = 0;
            atacando = false;
        }
    }

    public int getCentroX() {
        int centroX = mundox + cima1.getWidth()/2;
        return centroX;
    }
    public int getCentroY() {
        int centroY = mundoy + cima1.getHeight()/2;
        return centroY;
    }
    public int getDistanciaX(Entidade alvo) {
        int distanciaX = Math.abs(getCentroX() - alvo.getCentroX());
        return distanciaX;
    }
    public int getDistanciaY(Entidade alvo) {
        int distanciaY = Math.abs(getCentroY() - alvo.getCentroY());
        return distanciaY;
    }


    public void checaAtaqueouNao(int rate, int reto, int horizontal) {
        boolean alvoPerto = false;
        int DisX = getDistanciaX(pj.jogador);
        int DisY = getDistanciaY(pj.jogador);

        switch (direção) {
            case "cima":
                if (pj.jogador.getCentroY() < getCentroY() && DisY < reto && DisX < horizontal) {
                    alvoPerto = true;
                }
                break;
            case "baixo":
                if (pj.jogador.getCentroY() > getCentroY() && DisY < reto && DisX < horizontal) {
                    alvoPerto = true;
                }
                break;
            case "esquerda":
                if (pj.jogador.getCentroX() < getCentroX() && DisX < reto && DisY < horizontal) {
                    alvoPerto = true;
                }
                break;
            case "direita":
                if (pj.jogador.getCentroX() > getCentroX() && DisX < reto && DisY < horizontal) {
                    alvoPerto = true;
                }
                break;
            }

            if (alvoPerto == true) {
                int i = new Random().nextInt(rate);
                if (i==0) {
                    atacando = true;
                    numSprite = 0;
                    contaSprite = 0;
                }
            }

    }

    public void checaDrop(){

    }
    public void dropaItem(Entidade dropouItem){
        for (int i = 0; i<pj.obj[1].length;i++){
            if (pj.obj[pj.mapaAtual][i]==null) {
                pj.obj[pj.mapaAtual][i] = dropouItem;
                pj.obj[pj.mapaAtual][i].mundox = mundox;
                pj.obj[pj.mapaAtual][i].mundoy = mundoy;
                break;
            }
        }
    }

    public void atualiza(){
        setAcao();
        colisaoLigada = false;
        pj.cColisao.checaTile(this);
        pj.cColisao.checaObjeto(this, false);
        pj.cColisao.checaEntidade(this, pj.npc);
        pj.cColisao.checaEntidade(this,pj.monstro);
        boolean contato = pj.cColisao.checaPlayer(this);

        if(this.tipo == 2 && contato == true) {
            if (pj.jogador.invencivel == false) {
                pj.tocaEfeito(6);
                pj.jogador.vida -= 1;
                pj.jogador.invencivel = true;
            }
        }
        if (atacando==true) {
            atacando();
        }

        else if(colisaoLigada == false) {

            switch(direção) {
                case "cima":
                    mundoy -= Vel;
                    break;
                case "baixo":
                    mundoy += Vel;
                    break;
                case "esquerda":
                    mundox -= Vel;
                    break;
                case "direita":
                    mundox += Vel;
                    break;
            }
            contaSprite++;
            if (contaSprite > 10) {
                if (numSprite == 1) {
                    numSprite = 2;
                }
                else if (numSprite == 2) {
                    numSprite = 1;
                }
                contaSprite = 0;
            }
        }


        if (invencivel == true) {
            invencivelCounter++;
            if (invencivelCounter > 40){
                invencivel = false;
                invencivelCounter = 0;
            }
        }
    }

    public void fala() {
        if (dialogos[indexDialogo] == null) {
            indexDialogo = 0;
        }
        pj.iu.dialogoAtual = dialogos[indexDialogo];
        indexDialogo++;
    }

    public void seguePlayer(int intervalo) {

        actionLockCounter++;

        if (actionLockCounter > intervalo) {
            if (getDistanciaX(pj.jogador) > getDistanciaY(pj.jogador)) {
                if (pj.jogador.getCentroX() < getCentroX()) {
                    direção = "esquerda";
                }
                else {
                    direção = "direita";
                }

            }
            else if (getDistanciaX(pj.jogador) < getDistanciaY(pj.jogador)) {
                if (pj.jogador.getCentroY() < getCentroY()) {
                    direção = "cima";
                }
                else {
                    direção = "baixo";
                }
            }
            actionLockCounter = 0;
        }
    }


    public BufferedImage setup(String imagePath, int width, int height) {
        Utilidade UT = new Utilidade();
        BufferedImage imagem = null;

        try {
            imagem = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            imagem = UT.scaleImage(imagem, width, height);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return imagem;
    }

    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;
        int telaX = mundox - pj.jogador.mundox + pj.jogador.telax;
        int telaY = mundoy - pj.jogador.mundoy + pj.jogador.telay;

        if (mundox + pj.tamanhoTile*5 > pj.jogador.mundox - pj.jogador.telax &&
                mundox - pj.tamanhoTile < pj.jogador.mundox + pj.jogador.telax &&
                mundoy + pj.tamanhoTile*5 > pj.jogador.mundoy - pj.jogador.telay &&
                mundoy - pj.tamanhoTile < pj.jogador.mundoy + pj.jogador.telay) {
            int tempTelaX =telaX;
            int tempTelaY = telaY;

            switch(direção) {
                case "cima":
                    if (atacando == false) {
                        if(numSprite == 1){
                            imagem = cima1;
                        }
                        if(numSprite == 2){
                            imagem = cima2;
                        }
                    }
                    if (atacando == true) {
                        tempTelaY = telaY - cima1.getHeight();
                        if(numSprite == 1){
                            imagem = cimaAtaque;
                        }
                        if(numSprite == 2){
                            imagem = cima1Ataque;

                        }
                    }
                    break;

                case "baixo":
                    if(atacando == false) {
                        if(numSprite == 1){
                            imagem = baixo1;
                        }
                        if(numSprite == 2){
                            imagem = baixo2;
                        }
                    }
                    if(atacando==true){
                        if(numSprite == 1){
                            imagem = baixoAtaque;
                        }
                        if(numSprite == 2){
                            imagem = baixo1Ataque;

                        }
                    }
                    break;

                case "esquerda":
                    if (atacando == false) {
                        if(numSprite == 1){
                            imagem = esquerda1;
                        }
                        if(numSprite == 2){
                            imagem = esquerda2;
                        }
                    }
                    if (atacando == true){
                        tempTelaX = telaX - esquerda1.getWidth();
                        if(numSprite == 1){
                            imagem = esquerdaAtaque;
                        }
                        if(numSprite == 2){
                            imagem = esquerda1Ataque;

                        }
                    }
                    break;
                case "direita":
                    if (atacando == false) {
                        if(numSprite == 1){
                            imagem = direita1;
                        }
                        if(numSprite == 2){
                            imagem = direita2;
                        }
                    }
                    if (atacando == true){
                        if(numSprite == 1){
                            imagem = direitaAtaque;
                        }
                        if(numSprite == 2){
                            imagem = direita1Ataque;

                        }
                    }
                    break;
            }
            // Barra de vida monstros   
            if (this.tipo == 2 && barraVidaOn == true) {
                double umaEscala = (double) pj.tamanhoTile / vidaMax;
                double BarraVida = umaEscala * vida;
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(telaX - 1, telaY - 16, pj.tamanhoTile + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(telaX, telaY - 15, (int) BarraVida, 10);
                barraVidaCounter++;
                if (barraVidaCounter > 600) {
                    barraVidaOn = false;
                    barraVidaCounter = 0;
                }
            }

                if (this.tipo == 2) {
                    if (invencivel == true) {
                        barraVidaOn = true;
                        barraVidaCounter = 0;
                        mudarAlpha(g2, 0.4f);
                    }
                    if (morto == true) {
                        mortoAnimacao(g2);
                    }
                }
                g2.drawImage(imagem, tempTelaX, tempTelaY, null);

                mudarAlpha(g2, 1f);
            }
        }
    public void mortoAnimacao(Graphics2D g2) {
        morrerCounter++;
        int i=5;
        if (morrerCounter <= i) {
                mudarAlpha(g2, 0f);
        }
        if (morrerCounter > i && morrerCounter <= i*2) {
                mudarAlpha(g2, 1f);
        }
        if (morrerCounter > i*2 && morrerCounter <= i*3) {
                mudarAlpha(g2, 0f);
        }
        if (morrerCounter > i*3 && morrerCounter <= i*4) {
                mudarAlpha(g2, 1f);
        }
        if (morrerCounter > i*4 && morrerCounter <= i*5) {
                mudarAlpha(g2, 0f);
        }
        if (morrerCounter > i*5 && morrerCounter <= i*6) {
                mudarAlpha(g2, 1f);
        }
        if (morrerCounter > i*6 && morrerCounter <= i*7) {
                mudarAlpha(g2, 0f);
            }
        if (morrerCounter > i*7 && morrerCounter <= i*8) {
                mudarAlpha(g2, 1f);
        }
        if (morrerCounter > i*8) {
            morto = false;
            vivo = false;
        }
    }

    public void mudarAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}


