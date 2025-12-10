package main;

import Entidades.Entidade;
import Entidades.NPCmago;
import objts.OBJ_Coracao;
import Entidades.Jogador;
import objts.OBJ_cristalazul;
import objts.OBJ_cristaldourado;
import objts.OBJ_cristalvermelho;

import java.awt.*;
import java.awt.image.BufferedImage;

//essa classe lida com toda a interface do usuario, tipo para mostrar dialogo, icone de item, etc
public class IU {

    Graphics2D g2;
    PainelJogo pj;
    Font arial_40B, arial_30;
    BufferedImage coracao0,coracao1,coracao2;
    BufferedImage cristalAzulimg, cristalDouradoimg, cristalVermelhoimg;
    public boolean mensagemOn = false;
    public String mensagem = "";
    int tempoMensagem = 0;
    public boolean encerraJogo = false;

    public String dialogoAtual = "";
    public int numComando= 0;

    public IU(PainelJogo pj) {
        this.pj = pj;

        arial_40B = new Font("Arial", Font.BOLD, 40);
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        //OBJ_chave chave = new OBJ_chave(pj);
        //chaveImagem = chave.imagem;

        Entidade coracao = new OBJ_Coracao(pj);
        coracao0 = coracao.imagem;
        coracao1 = coracao.imagem2;
        coracao2 = coracao.imagem3;

        Entidade cristalAzul = new OBJ_cristalazul(pj);
        cristalAzulimg = cristalAzul.imagem;

        Entidade cristalDourado = new OBJ_cristaldourado(pj);
        cristalDouradoimg = cristalDourado.imagem;

        Entidade cristalVermelho = new OBJ_cristalvermelho(pj);
        cristalVermelhoimg = cristalVermelho.imagem;
    }

    public void mostraMensagem(String texto) {
        mensagem = texto;
        mensagemOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40B);
        g2.setColor(Color.white);

        if (pj.gameState==pj.menuState) {
            desenhaTelaMenu();
        }

        if (pj.gameState == pj.playState) {
            drawVida();
            drawCristais();
        }
        if (pj.gameState == pj.pauseState) {
            desenhaTelaPause();
        }
        if (pj.gameState == pj.dialogoState) {
            drawVida();
            drawCristais();
            desenhaTelaDialogo();
        }
        if(pj.gameState == pj.gameOverState){
            desenhaTelaGameOver();
        }
    }
    public void drawCristais() {

        int x = pj.tamanhoTile / 2;
        int y = pj.tamanhoTile / 2 + pj.tamanhoTile + 4;

        // Cristal azul
        if (pj.jogador.temCristalAzul) {
            g2.drawImage(cristalAzulimg, x, y, pj.tamanhoTile, pj.tamanhoTile, null);
            x += pj.tamanhoTile + 4;
        }

        // Cristal dourado
        if (pj.jogador.temCristalDourado) {
            g2.drawImage(cristalDouradoimg, x, y, pj.tamanhoTile, pj.tamanhoTile, null);
            x += pj.tamanhoTile + 4;
        }

        // Cristal vermelho
        if (pj.jogador.temCristalVermelho) {
            g2.drawImage(cristalVermelhoimg, x, y, pj.tamanhoTile, pj.tamanhoTile, null);
        }
    }

    public void drawVida() {

        int x = pj.tamanhoTile/2;
        int y = pj.tamanhoTile/2;
        int i = 0;

        while (i <pj.jogador.vidaMax/2) {
            g2.drawImage(coracao2,x,y,null);
            i++;
            x+= pj.tamanhoTile;
        }

        x = pj.tamanhoTile/2;
        y = pj.tamanhoTile/2;
        i = 0;

        while (i < pj.jogador.vida) {
            g2.drawImage(coracao1,x,y,null);
            i++;
            if (i < pj.jogador.vida) {
                g2.drawImage(coracao0,x,y,null);
            }
            i++;
            x += pj.tamanhoTile;
        }
    }
    public void desenhaTelaMenu() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String texto = "The Legend Of Java";
        String instrucao = "Use W e S para controlar a opção, clique em Enter para selecionar";
        int x = getXparaTextoCentrado(texto);
        int y= pj.tamanhoTile*3;

        g2.setColor(Color.gray);
        g2.drawString(texto,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(texto, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.drawString(instrucao,x+80, y+45);

        x = pj.larguraTela/2 - (pj.tamanhoTile*2)/2;
        y += pj.tamanhoTile*2;

        g2.drawImage(pj.jogador.setup("logo"),x,y,pj.tamanhoTile*2,pj.tamanhoTile*2,null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,35F));

        texto = "Novo jogo";
        x = getXparaTextoCentrado(texto);
        y += pj.tamanhoTile*3.5;
        g2.drawString(texto,x,y);
        if(numComando == 0) {
            g2.drawString(">", x-pj.tamanhoTile,y);
        }

        texto = "Sair";
        x = getXparaTextoCentrado(texto);
        y += pj.tamanhoTile;
        g2.drawString(texto,x,y);
        if(numComando == 1) {
            g2.drawString(">", x-pj.tamanhoTile,y);
        }
    }
    public void  desenhaTelaPause() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String texto = "PAUSADO";
        int x = getXparaTextoCentrado(texto);

        int y = pj.alturaTela/2;

        g2.drawString(texto, x, y);
    }

    public void desenhaTelaDialogo() {
        int x = pj.tamanhoTile*2;
        int y = pj.tamanhoTile/2;
        int largura = pj.larguraTela - (pj.tamanhoTile*4);
        int altura = pj.tamanhoTile*5;

        desenhaSubTela(x,y,largura,altura);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20));
        x += 30;
        y += pj.tamanhoTile;

        for (String linha: dialogoAtual.split("/n")) {
            g2.drawString(linha,x,y);
            y+=40;
        }
    }

    public void desenhaSubTela(int x, int y, int largura, int altura) {

        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,largura,altura,35,35);

        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x,y,largura,altura,35,35);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,15));
        g2.drawString("Clique em Enter para continuar", x+325, y+225);

    }

    public int getXparaTextoCentrado(String texto) {
        int comprimento = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x=pj.larguraTela/2 - comprimento/2;
        return x;
    }

    public void desenhaTelaGameOver(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,pj.larguraTela,pj.alturaTela);

        int x;
        int y;
        String texto;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        texto = "GAME OVER";

        //sombra
        g2.setColor(Color.black);
        x = getXparaTextoCentrado(texto);
        y = pj.tamanhoTile*4;
        g2.drawString(texto,x,y);
        //texto
        g2.setColor(Color.white);
        g2.drawString(texto, x-4,y-4);

        //tentar de novo
        g2.setFont(g2.getFont().deriveFont(30f));
        texto = "Tentar novamente";
        x = getXparaTextoCentrado(texto);
        y+=pj.tamanhoTile*4;
        g2.drawString(texto,x,y);
        if (numComando == 0) {
            g2.drawString(">", x-40,y);
        }

        //de volta ao titulo
        texto = "Sair do jogo";
        x = getXparaTextoCentrado(texto);
        y+=55;
        g2.drawString(texto,x,y);
        if (numComando == 1) {
            g2.drawString(">", x-40,y);
        }
    }
}
