package main;

import java.awt.*;

public class Eventos {

    PainelJogo pj;
    RetanguloEvento retanguloEvento[][][];

    int eventoAnteriorX, eventoAnteriorY;
    boolean podeTriggarEvento = true;

    public Eventos(PainelJogo pj) {
        this.pj=pj;

            retanguloEvento = new RetanguloEvento[pj.mapaMax][pj.mundoMaxCol][pj.mundoMaxLinha];


            int mapa = 0;
            int coluna = 0;
            int linha = 0;
            while(mapa < pj.mapaMax &&coluna < pj.mundoMaxCol && linha < pj.mundoMaxLinha) {

                retanguloEvento[mapa][coluna][linha] = new RetanguloEvento();
                retanguloEvento[mapa][coluna][linha].x=23;
                retanguloEvento[mapa][coluna][linha].y=23;
                retanguloEvento[mapa][coluna][linha].width=2;
                retanguloEvento[mapa][coluna][linha].height=2;
                retanguloEvento[mapa][coluna][linha].retanguloEventoX = retanguloEvento[mapa][coluna][linha].x;
                retanguloEvento[mapa][coluna][linha].retanguloEventoY = retanguloEvento[mapa][coluna][linha].y;

                coluna++;
                if(coluna==pj.mundoMaxCol) {
                    coluna = 0;
                    linha++;

                    if (linha==pj.mundoMaxLinha) {
                        linha=0;
                        mapa++;
                    }
                }
            }
    }

    public void checaEvento() {

        int distanciaX = Math.abs(pj.jogador.mundox - eventoAnteriorX);
        int distanciaY = Math.abs(pj.jogador.mundoy - eventoAnteriorY);
        int distancia = Math.max(distanciaX,distanciaY);
        if (distancia > pj.tamanhoTile) {
            podeTriggarEvento = true;
        }

        if (podeTriggarEvento == true) {

            if(hit(0,40,0,"any") == true) {teleport(1,19,48);}
            if(hit(1,19,49, "any") == true) {teleport(0,40,1);}

            if(hit(0,37,2,"any") == true) {teleport(2,22,27);}
            if(hit(2,22,28, "any") == true) {teleport(0,37,2);}

            if(hit(1,32,0,"any") == true) {teleport(3,23,48);}
            if(hit(3,23,49, "any") == true) {teleport(1,32,1);}

            if(hit(3,23,36,"any") == true || hit(3,24,36,"any")) {teleport(4,26,48);}
            if(hit(4,26,49, "any") == true) {teleport(3,23,37);}

            if(hit(1,29,20,"cima") == true || hit(1,30,20,"cima") || hit(1,31,20,"cima") || hit(1,32,20,"cima") || hit(1,33,20,"cima")
            || hit(1,34,20,"cima") || hit(1,28,19,"direita") || hit(1,28,18,"direita") || hit(1,28,17,"direita") || hit(1,28,16,"direita") || hit(1,28,15,"direita")
            || hit(1,29,14,"baixo") || hit(1,30,14,"baixo") || hit(1,31,14,"baixo") || hit(1,32,14,"baixo") || hit(1,33,14,"baixo") || hit(1,34,14,"baixo")
            || hit(1,35,15,"esquerda") || hit(1,35,16,"esquerda") || hit(1,35,17,"esquerda") || hit(1,35,18,"esquerda") || hit(1,35,19,"esquerda"))
            {piscinaCura(pj.dialogoState);}
        }

    }

    public boolean hit(int mapa, int coluna, int linha, String reqDirection) {

        boolean hit = false;

        if (mapa == pj.mapaAtual) {

            pj.jogador.areaSolida.x=pj.jogador.mundox + pj.jogador.areaSolida.x;
            pj.jogador.areaSolida.y=pj.jogador.mundoy + pj.jogador.areaSolida.y;
            retanguloEvento[mapa][coluna][linha].x = coluna*pj.tamanhoTile + retanguloEvento[mapa][coluna][linha].x;
            retanguloEvento[mapa][coluna][linha].y = linha*pj.tamanhoTile + retanguloEvento[mapa][coluna][linha].y;

            if(pj.jogador.areaSolida.intersects(retanguloEvento[mapa][coluna][linha]) && !retanguloEvento[mapa][coluna][linha].eventoFeito) {
                if (pj.jogador.direção.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    eventoAnteriorX = pj.jogador.mundox;
                    eventoAnteriorY = pj.jogador.mundoy;
                }
            }

            pj.jogador.areaSolida.x = pj.jogador.areaSolidaDefaultX;
            pj.jogador.areaSolida.y = pj.jogador.areaSolidaDefaultY;
            retanguloEvento[mapa][coluna][linha].x = retanguloEvento[mapa][coluna][linha].retanguloEventoX;
            retanguloEvento[mapa][coluna][linha].y = retanguloEvento[mapa][coluna][linha].retanguloEventoY;
        }


        return hit;
    }
    public void teleport(int mapa, int coluna,int linha){

        pj.mapaAtual = mapa;
        pj.jogador.mundox = pj.tamanhoTile * coluna;
        pj.jogador.mundoy = pj.tamanhoTile * linha;
        eventoAnteriorX = pj.jogador.mundox;
        eventoAnteriorY = pj.jogador.mundoy;
        podeTriggarEvento = false;
    }
    public void piscinaCura(int gameState) {

        if (pj.inputT.enter == true) {
            pj.gameState = gameState;
            pj.iu.dialogoAtual = "Você bebe a água do oásis e se sente refrescado!";
            pj.jogador.vida = pj.jogador.vidaMax;
        }

    }


}
