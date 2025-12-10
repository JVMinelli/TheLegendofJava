package Entidades;

import main.PainelJogo;
import main.Utilidade;
import main.inputTeclado;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Entidade{

    PainelJogo pj;
    inputTeclado iT;

    public final int telax;
    public final int telay;
    public boolean temCristalAzul = false;
    public boolean temCristalDourado = false;
    public boolean temCristalVermelho = false;

    public int pegaChave = 0; //indice de quantas chaves vai pegar



    public Jogador(PainelJogo pj, inputTeclado iT) {
        super(pj);

        this.pj = pj;
        this.iT = iT;

        telax = pj.larguraTela/2 - (pj.tamanhoTile/2);
        telay = pj.alturaTela/2 - (pj.tamanhoTile/2);

        areaSolida = new Rectangle();
        areaSolida.x=8;
        areaSolida.y=16;
        areaSolidaDefaultX= areaSolida.x;
        areaSolidaDefaultY= areaSolida.y;
        areaSolida.width=32;
        areaSolida.height=32;

        areaAtaque.width = 36;
        areaAtaque.height = 36;

        defineValoresPadrao();
        getImagemJogador();
        getImagemAtaqueJogador();
    }
    public void defineValoresPadrao(){

        mundox=pj.tamanhoTile*20;
        mundoy=pj.tamanhoTile*43;
        Vel=4;
        direção = "baixo";

        vidaMax = 6;
        vida = vidaMax;
    }
    public void getImagemJogador(){

        cima1 = setup("/player/cima1", pj.tamanhoTile, pj.tamanhoTile);
        cima2 = setup("/player/cima2", pj.tamanhoTile, pj.tamanhoTile);
        baixo1 = setup("/player/baixo1", pj.tamanhoTile, pj.tamanhoTile);
        baixo2 = setup("/player/baixo2", pj.tamanhoTile, pj.tamanhoTile);
        esquerda1 = setup("/player/esquerda1", pj.tamanhoTile, pj.tamanhoTile);
        esquerda2 = setup("/player/esquerda2", pj.tamanhoTile, pj.tamanhoTile);
        direita1 = setup("/player/direita1", pj.tamanhoTile, pj.tamanhoTile);
        direita2 = setup("/player/direita2", pj.tamanhoTile, pj.tamanhoTile);
    }

    public void getImagemAtaqueJogador(){
        cimaAtaque = setup("/player/cimaAtaque", pj.tamanhoTile, pj.tamanhoTile*2);
        cima1Ataque = setup("/player/cima1Ataque", pj.tamanhoTile, pj.tamanhoTile*2);
        baixoAtaque = setup("/player/baixoAtaque", pj.tamanhoTile, pj.tamanhoTile*2);
        baixo1Ataque = setup("/player/baixo1Ataque", pj.tamanhoTile, pj.tamanhoTile*2);
        esquerdaAtaque = setup("/player/esquerdaAtaque", pj.tamanhoTile*2, pj.tamanhoTile);
        esquerda1Ataque = setup("/player/esquerda1Ataque", pj.tamanhoTile*2, pj.tamanhoTile);
        direitaAtaque = setup("/player/direitaAtaque", pj.tamanhoTile*2, pj.tamanhoTile);
        direita1Ataque = setup("/player/direita1Ataque", pj.tamanhoTile*2, pj.tamanhoTile);
    }

    public BufferedImage setup(String caminhoImagem) {

        Utilidade UT = new Utilidade();
        BufferedImage imagem = null;

        try {
            imagem = ImageIO.read(getClass().getResourceAsStream("/player/" + caminhoImagem +".png"));
            imagem = UT.scaleImage(imagem, pj.tamanhoTile, pj.tamanhoTile);

        }catch(IOException e) {
            e.printStackTrace();
        }
        return imagem;
    }

    public void atualiza() {

        if (iT.enter) {
            if (!atacando) {
                atacando = true;
                contaSprite = 0;
            }
        }

        if (atacando) {
            atacando();
        }

        else if (iT.cima || iT.baixo || iT.esquerda || iT.direita) {

            if (iT.cima){
                direção = "cima";
            }
            if (iT.baixo){
                direção = "baixo";
            }
            if (iT.esquerda){
                direção = "esquerda";
            }
            if (iT.direita){
                direção = "direita";
            }

            colisaoLigada = false;
            pj.cColisao.checaTile(this);

            int objIndex = pj.cColisao.checaObjeto(this,true);
            pegarObjeto(objIndex);

            int npcIndex = pj.cColisao.checaEntidade(this,pj.npc);
            interageNPC(npcIndex);

            int monstroIndex = pj.cColisao.checaEntidade(this,pj.monstro);
            contatoMonstro(monstroIndex);

            pj.Eventos.checaEvento();

            if(!colisaoLigada) {
                switch(direção) {
                    case "cima":     mundoy -= Vel; break;
                    case "baixo":    mundoy += Vel; break;
                    case "esquerda": mundox -= Vel; break;
                    case "direita":  mundox += Vel; break;
                }
            }

            contaSprite++;
            if (contaSprite > 10) {
                numSprite = (numSprite == 1) ? 2 : 1;
                contaSprite = 0;
            }
        }

        if (invencivel == true) {
            invencivelCounter++;
            if (invencivelCounter > 60){
                invencivel = false;
                invencivelCounter = 0;
            }
        }
        if(vida <= 0){
            pj.gameState = pj.gameOverState;
            pj.tocaEfeito(9);
        }
    }

    public void setDefaultPositions(){
        mundox=pj.tamanhoTile*20;
        mundoy=pj.tamanhoTile*42;
        direção = "baixo";
    }

    public void restoreLife(){
        vida = vidaMax;
        invencivel = false;
    }

    public void atacando() {
        contaSprite++;
        if (contaSprite <= 5) {
            numSprite=1;
        }
        if (contaSprite >5 && contaSprite <= 25) {
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

            int monsterIndex = pj.cColisao.checaEntidade(this, pj.monstro);
            danoMonstro(monsterIndex);

            mundox=mundoXAtual;
            mundoy=mundoYAtual;
            areaSolida.width = areaSolidaWidth;
            areaSolida.height = areaSolidaHeight;
        }

        if (contaSprite >25){
            numSprite = 1;
            contaSprite = 0;
            atacando = false;
        }
    }

    public void pegarObjeto(int i) {
        // 999 é o "nenhum objeto"
        if (i != 999) {


            String objetoNome = pj.obj[pj.mapaAtual][i].nome;

            switch (objetoNome) {



                case "Chave":
                    pj.tocaEfeito(1);
                    pegaChave++;
                    pj.obj[pj.mapaAtual][i] = null;
                    pj.iu.mostraMensagem("Você pegou uma chave!");
                    break;

                case "Cristal-Dourado":
                    pj.paraMusica();     // pausa a música atual
                    pj.tocaEfeito(4);    // toca o efeito do cristal dourado

                    temCristalDourado = true;
                    pj.obj[pj.mapaAtual][i] = null;

                    // Espera 4 segundos em outra thread e volta a música
                    new Thread(() -> {
                        try {
                            Thread.sleep(4000); // 4 segundos
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        pj.tocaMusica(0);
                    }).start();

                    break;

                case "Cristal-Vermelho":
                    pj.tocaEfeito(4);
                    temCristalVermelho = true;
                    pj.obj[pj.mapaAtual][i] = null;
                    break;

                case "Cristal-Azul":
                    pj.tocaEfeito(4);
                    temCristalAzul = true;
                    pj.obj[pj.mapaAtual][i] = null;
                    break;

                case "Porta":
                    if (pegaChave > 0) {
                        pj.tocaEfeito(3);
                        pj.obj[pj.mapaAtual][i] = null;
                        pegaChave--;
                        pj.iu.mostraMensagem("Você abriu uma porta!");
                    } else {
                        pj.iu.mostraMensagem("Você precisa de uma chave!");
                    }
                    break;

                case "Bota":
                    pj.tocaEfeito(2);
                    Vel += 3;
                    pj.obj[pj.mapaAtual][i] = null;
                    pj.iu.mostraMensagem("Boost de velocidade recebido!");
                    break;

                case "Cogumelo":
                    pj.tocaEfeito(5);
                    Vel -= 3;
                    pj.obj[pj.mapaAtual][i] = null;
                    pj.iu.mostraMensagem("Velocidade reduzida!");
                    break;

                case "Bau":
                    pj.paraMusica();
                    pj.tocaEfeito(4);
                    break;
            }
        }
    }

    public void interageNPC(int i) {
        if (i !=999) {
            pj.gameState = pj.dialogoState;
            pj.npc[pj.mapaAtual][i].fala();
        }
        else {
        }
    }

    public void contatoMonstro(int i) {
        if(i!=999){
            if(invencivel == false)
            {
                pj.tocaEfeito(6);
                vida-=1;
                invencivel = true;
            }
        }
    }

    public void danoMonstro(int i) {
        if(i!=999){
            if(pj.monstro[pj.mapaAtual][i].invencivel==false){
                pj.tocaEfeito(6);
                pj.monstro[pj.mapaAtual][i].vida -= 1;
                pj.monstro[pj.mapaAtual][i].invencivel = true;
                pj.monstro[pj.mapaAtual][i].ReacaoDano();

                if(pj.monstro[pj.mapaAtual][i].vida<=0){
                    pj.monstro[pj.mapaAtual][i].morto = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempTelaX =telax;
        int tempTelaY = telay;

        switch(direção) {
            case "cima":
                if (atacando == false) {
                    if(numSprite == 1){
                        image = cima1;
                    }
                    if(numSprite == 2){
                        image = cima2;
                    }
                }
                if (atacando == true) {
                    tempTelaY = telay - pj.tamanhoTile;
                    if(numSprite == 1){
                        image = cima1Ataque;
                    }
                    if(numSprite == 2){
                        image = cimaAtaque;

                    }
                }
                break;

            case "baixo":
                if(atacando == false) {
                    if(numSprite == 1){
                        image = baixo1;
                    }
                    if(numSprite == 2){
                        image = baixo2;
                    }
                }
                if(atacando==true){
                    if(numSprite == 1){
                        image = baixo1Ataque;
                    }
                    if(numSprite == 2){
                        image = baixoAtaque;

                    }
                }
                break;

            case "esquerda":
                if (atacando == false) {
                    if(numSprite == 1){
                        image = esquerda1;
                    }
                    if(numSprite == 2){
                        image = esquerda2;
                    }
                }
                if (atacando == true){
                    tempTelaX = telax - pj.tamanhoTile;
                    if(numSprite == 1){
                        image = esquerda1Ataque;
                    }
                    if(numSprite == 2){
                        image = esquerdaAtaque;

                    }
                }
                break;
            case "direita":
                if (atacando == false) {
                    if(numSprite == 1){
                        image = direita1;
                    }
                    if(numSprite == 2){
                        image = direita2;
                    }
                }
                if (atacando == true){
                    if(numSprite == 1){
                        image = direita1Ataque;
                    }
                    if(numSprite == 2){
                        image = direitaAtaque;

                    }
                }
                break;
        }

        if (invencivel == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, tempTelaX, tempTelaY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("invencivel:"+invencivelCounter, 10, 400);

    }
}
