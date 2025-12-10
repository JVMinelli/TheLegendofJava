package main;

import Entidades.Entidade;

public class checaColisao {

    PainelJogo pj;

    public checaColisao(PainelJogo pj) {
        this.pj = pj;
    }

    public void checaTile(Entidade entidade) {
        int entidadeEsquerdaMundoX = entidade.mundox + entidade.areaSolida.x;
        int entidadeDireitaMundoX = entidade.mundox + entidade.areaSolida.x + entidade.areaSolida.width;
        int entidadeTopoMundoY = entidade.mundoy + entidade.areaSolida.y;
        int entidadeBaixoMundoY = entidade.mundoy + entidade.areaSolida.y + entidade.areaSolida.height;

        int entidadeEsquerdaColuna = entidadeEsquerdaMundoX / pj.tamanhoTile;
        int entidadeDireitaColuna = entidadeDireitaMundoX / pj.tamanhoTile;
        int entidadeTopoLinha = entidadeTopoMundoY / pj.tamanhoTile;
        int entidadeBaixoLinha = entidadeBaixoMundoY / pj.tamanhoTile;

        int tile1, tile2;

        switch (entidade.direção) {
            case "cima":

                entidadeTopoLinha = (entidadeTopoMundoY - entidade.Vel) / pj.tamanhoTile;
                tile1 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeEsquerdaColuna][entidadeTopoLinha];
                tile2 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeDireitaColuna][entidadeTopoLinha];
                if (pj.TM.tile[tile1].colisao == true || pj.TM.tile[tile2].colisao == true) {
                    entidade.colisaoLigada = true;
                }

                break;
            case "baixo":
                entidadeBaixoLinha = (entidadeBaixoMundoY + entidade.Vel) / pj.tamanhoTile;
                tile1 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeEsquerdaColuna][entidadeBaixoLinha];
                tile2 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeDireitaColuna][entidadeBaixoLinha];
                if (pj.TM.tile[tile1].colisao == true || pj.TM.tile[tile2].colisao == true) {
                    entidade.colisaoLigada = true;
                }
                break;
            case "esquerda":
                entidadeEsquerdaColuna = (entidadeEsquerdaMundoX - entidade.Vel) / pj.tamanhoTile;
                tile1 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeEsquerdaColuna][entidadeTopoLinha];
                tile2 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeEsquerdaColuna][entidadeBaixoLinha];
                if (pj.TM.tile[tile1].colisao == true || pj.TM.tile[tile2].colisao == true) {
                    entidade.colisaoLigada = true;
                }
                break;
            case "direita":
                entidadeDireitaColuna = (entidadeDireitaMundoX + entidade.Vel) / pj.tamanhoTile;
                tile1 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeDireitaColuna][entidadeTopoLinha];
                tile2 = pj.TM.NumeroTileMapa[pj.mapaAtual][entidadeDireitaColuna][entidadeBaixoLinha];
                if (pj.TM.tile[tile1].colisao == true || pj.TM.tile[tile2].colisao == true) {
                    entidade.colisaoLigada = true;
                }
                break;
        }

    }

    public int checaObjeto(Entidade entidade, boolean Jogador) {

        int index = 999;

        for (int i = 0; i < pj.obj[1].length; i++) {
            if (pj.obj[pj.mapaAtual][i] != null && pj.obj[pj.mapaAtual][i].areaSolida != null) {
                entidade.areaSolida.x = entidade.mundox + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoy + entidade.areaSolida.y;
                pj.obj[pj.mapaAtual][i].areaSolida.x = pj.obj[pj.mapaAtual][i].mundox + pj.obj[pj.mapaAtual][i].areaSolida.x;
                pj.obj[pj.mapaAtual][i].areaSolida.y = pj.obj[pj.mapaAtual][i].mundoy + pj.obj[pj.mapaAtual][i].areaSolida.y;

                switch (entidade.direção) {
                    case "cima":
                        entidade.areaSolida.y -= entidade.Vel;
                        break;

                    case "baixo":
                        entidade.areaSolida.y += entidade.Vel;
                        break;

                    case "esquerda":
                        entidade.areaSolida.x -= entidade.Vel;
                        break;

                    case "direita":
                        entidade.areaSolida.x += entidade.Vel;
                        break;
                }
                if (entidade.areaSolida.intersects(pj.obj[pj.mapaAtual][i].areaSolida)) {
                    if (pj.obj[pj.mapaAtual][i].colisao == true) {
                        entidade.colisaoLigada = true;
                    }
                    if (Jogador == true) {
                        index = i;
                    }
                }
                entidade.areaSolida.x = entidade.areaSolidaDefaultX;
                entidade.areaSolida.y = entidade.areaSolidaDefaultY;
                pj.obj[pj.mapaAtual][i].areaSolida.x = pj.obj[pj.mapaAtual][i].areaSolidaDefaultX;
                pj.obj[pj.mapaAtual][i].areaSolida.y = pj.obj[pj.mapaAtual][i].areaSolidaDefaultY;
            }
        }

        return index;
    }

    public int checaEntidade(Entidade entidade, Entidade[][] alvo) {
        int index = 999;

        for (int i = 0; i < alvo[1].length; i++) {
            if (alvo[pj.mapaAtual][i] != null && alvo[pj.mapaAtual][i].areaSolida != null) {
                entidade.areaSolida.x = entidade.mundox + entidade.areaSolida.x;
                entidade.areaSolida.y = entidade.mundoy + entidade.areaSolida.y;
                alvo[pj.mapaAtual][i].areaSolida.x = alvo[pj.mapaAtual][i].mundox + alvo[pj.mapaAtual][i].areaSolida.x;
                alvo[pj.mapaAtual][i].areaSolida.y = alvo[pj.mapaAtual][i].mundoy + alvo[pj.mapaAtual][i].areaSolida.y;

                switch (entidade.direção) {
                    case "cima":
                        entidade.areaSolida.y -= entidade.Vel;
                        break;

                    case "baixo":
                        entidade.areaSolida.y += entidade.Vel;
                        break;

                    case "esquerda":
                        entidade.areaSolida.x -= entidade.Vel;
                        break;

                    case "direita":
                        entidade.areaSolida.x += entidade.Vel;
                        break;
                }
                if (entidade.areaSolida.intersects(alvo[pj.mapaAtual][i].areaSolida)) {
                    if(alvo[pj.mapaAtual][i] != entidade){
                        entidade.colisaoLigada = true;
                        index = i;
                    }

                }
                entidade.areaSolida.x = entidade.areaSolidaDefaultX;
                entidade.areaSolida.y = entidade.areaSolidaDefaultY;
                alvo[pj.mapaAtual][i].areaSolida.x = alvo[pj.mapaAtual][i].areaSolidaDefaultX;
                alvo[pj.mapaAtual][i].areaSolida.y = alvo[pj.mapaAtual][i].areaSolidaDefaultY;
            }
        }
    return index;
    }
    public boolean checaPlayer(Entidade entidade) {

        boolean contato=false;

        entidade.areaSolida.x = entidade.mundox + entidade.areaSolida.x;
        entidade.areaSolida.y = entidade.mundoy + entidade.areaSolida.y;
        pj.jogador.areaSolida.x = pj.jogador.mundox + pj.jogador.areaSolida.x;
        pj.jogador.areaSolida.y = pj.jogador.mundoy + pj.jogador.areaSolida.y;

        switch (entidade.direção) {
            case "cima":
                entidade.areaSolida.y -= entidade.Vel;
                break;

            case "baixo":
                entidade.areaSolida.y += entidade.Vel;
                break;

            case "esquerda":
                entidade.areaSolida.x -= entidade.Vel;
                break;

            case "direita":
                entidade.areaSolida.x += entidade.Vel;
                break;
        }
        if (entidade.areaSolida.intersects(pj.jogador.areaSolida)) {
            entidade.colisaoLigada = true;
            contato=true;


        }
        entidade.areaSolida.x = entidade.areaSolidaDefaultX;
        entidade.areaSolida.y = entidade.areaSolidaDefaultY;
        pj.jogador.areaSolida.x = pj.jogador.areaSolidaDefaultX;
        pj.jogador.areaSolida.y = pj.jogador.areaSolidaDefaultY;

        return contato;
    }
}
