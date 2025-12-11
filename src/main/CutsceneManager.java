package main;

import Entidades.bonecoPlayer;
import monstro.mon_Boss;
import objts.OBJ_porta;

import java.awt.*;

public class CutsceneManager {

    PainelJogo pj;
    Graphics2D g2;
    public int numCena;
    public int faseCena;
    int contador = 0;
    float alpha = 0f;
    int y;

    public final int NA = 0;
    public final int boss = 1;
    public final int ending = 2;

    public CutsceneManager(PainelJogo pj) {
        this.pj = pj;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (numCena) {
            case boss:
                bossFinal();
                break;
            case ending:
                ending();
                break;
        }
    }

    public void bossFinal() {

        if (faseCena == 0) {
            pj.lutaBoss = true;

            for (int i = 0; i < pj.obj[1].length; i++) {

                if (pj.obj[pj.mapaAtual][i] == null) {
                    pj.obj[pj.mapaAtual][i] = new OBJ_porta(pj);
                    pj.obj[pj.mapaAtual][i].mundox = pj.tamanhoTile * 26;
                    pj.obj[pj.mapaAtual][i].mundoy = pj.tamanhoTile * 30;
                    pj.obj[pj.mapaAtual][i].temp = true;
                    break;
                }
            }
            for (int i = 0; i < pj.npc[1].length; i++) {

                if (pj.npc[pj.mapaAtual][i] == null) {
                    pj.npc[pj.mapaAtual][i] = new bonecoPlayer(pj);
                    pj.npc[pj.mapaAtual][i].mundox = pj.jogador.mundox;
                    pj.npc[pj.mapaAtual][i].mundoy = pj.jogador.mundoy;
                    pj.npc[pj.mapaAtual][i].direção = pj.jogador.direção;
                    pj.npc[pj.mapaAtual][i].temp = true;
                    break;
                }
            }
            pj.jogador.desenhar = false;

            faseCena++;
        }
        if (faseCena == 1) {

            pj.jogador.mundoy -= 2;

            if (pj.jogador.mundoy < pj.tamanhoTile * 10) {
                faseCena++;
            }
        }
        if (faseCena == 2) {

            for (int i = 0; i < pj.monstro[1].length; i++) {

                if (pj.monstro[pj.mapaAtual][i] != null && pj.monstro[pj.mapaAtual][i].nome == mon_Boss.nomeMon) {

                    pj.monstro[pj.mapaAtual][i].dormir = false;
                    faseCena++;

                    break;
                }
            }
            for (int i = 0; i < pj.npc[1].length; i++) {


                if (pj.npc[pj.mapaAtual][i] != null && pj.npc[pj.mapaAtual][i].nome == bonecoPlayer.nomeNpc) {

                    pj.jogador.mundox = pj.npc[pj.mapaAtual][i].mundox;
                    pj.jogador.mundoy = pj.npc[pj.mapaAtual][i].mundoy;
                    faseCena++;
                    pj.npc[pj.mapaAtual][i] = null;
                    break;
                }
        }

            pj.jogador.desenhar = true;

            pj.monstro[pj.mapaAtual][3].dormir = false;
            numCena = NA;
            faseCena = 0;
        }
    }
    public void ending() {
        if (faseCena == 0) {
            pj.paraMusica();

            faseCena++;
        }
        if (faseCena == 1) {
            pj.tocaEfeito(4);
            faseCena++;
        }
        if (faseCena == 2) {

            if (counterReached(300) == true) {
                faseCena++;
            }
        }
        if (faseCena == 3) {
            alpha += 0.005f;
            if (alpha >1f) {
                alpha = 1f;
            }
            desenhaBackgroundPreto(alpha);

            if (alpha == 1f) {
                alpha = 0;
                faseCena++;
            }
        }
        if (faseCena == 4) {
            desenhaBackgroundPreto(1f);

            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }


            String texto = "Após a ardua jornada de encontrar os 3 cristais, \n"
                    + "você retorna à cidade, onde acontece uma grande festa em sua homenagem, e, \n"
                    + "com a ajuda do mago,\n"
                    + "você usa os cristais para fazer todos os monstros desaparecerem.";
            drawString(alpha, 20f, 200, texto, 70);

            if (counterReached(600) == true) {
                faseCena++;
            }
        }
        if (faseCena == 5) {

            desenhaBackgroundPreto(1f);

            drawString(1f,40f,pj.alturaTela/2, "The Legend of Java", 40);

            if (counterReached(480) == true) {
                faseCena++;
            }
        }
        if (faseCena == 6) {
            desenhaBackgroundPreto(1f);

            drawString(1f,40f,pj.alturaTela/2, "Obrigado por jogar!", 40);
        }
    }
    public boolean counterReached(int target) {
        boolean counterReached = false;

        contador++;
        if (contador > target) {
            counterReached = true;
            contador = 0;
        }
        return  counterReached;
    }
    public void desenhaBackgroundPreto(float alpha) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0, pj.larguraTela, pj.alturaTela);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String texto, int lineHeight) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line: texto.split("\n")) {
            FontMetrics fm = g2.getFontMetrics();
            int larguraTexto = fm.stringWidth(line);
            int x = (pj.larguraTela - larguraTexto) / 2;

            g2.drawString(line, x,y);
            y+= lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}

