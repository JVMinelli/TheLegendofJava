package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class inputTeclado implements KeyListener {

    PainelJogo pj;
    public boolean cima,baixo,esquerda,direita,enter;

    public inputTeclado(PainelJogo pj) {
        this.pj = pj;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (pj.gameState == pj.menuState) {
            menuState(code);
        }
        else if (pj.gameState == pj.playState){
            playState(code);
        }
        else if (pj.gameState == pj.pauseState) {
            pauseState(code);
        }
        else if (pj.gameState == pj.dialogoState) {
            dialogoState(code);
        }
        else if (pj.gameState == pj.gameOverState) {
            gameOverState(code);
        }
    }


    public void menuState( int code){
        if (code == KeyEvent.VK_W) {
            pj.iu.numComando--;
            if (pj.iu.numComando < 0) {
                pj.iu.numComando = 1;
            }
            pj.tocaEfeito(8);
        }
        if (code == KeyEvent.VK_S) {
            pj.iu.numComando++;
            if (pj.iu.numComando > 1) {
                pj.iu.numComando = 0;
            }
            pj.tocaEfeito(8);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (pj.iu.numComando == 0) {
                pj.gameState = pj.playState;
                pj.paraMusica();
                pj.tocaMusica(0);
            } else {
                System.exit(0);
            }
            pj.tocaEfeito(8);
        }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            cima=true;
        }
        if (code == KeyEvent.VK_A) {
            esquerda=true;
        }
        if (code == KeyEvent.VK_S) {
            baixo=true;
        }
        if (code == KeyEvent.VK_D) {
            direita=true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            pj.gameState = pj.pauseState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter = true;
        }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_ESCAPE) {
            pj.gameState = pj.playState;
        }
    }

    public void dialogoState(int code){
        if (code == KeyEvent.VK_ENTER) {
            pj.gameState = pj.playState;
        }
        pj.tocaEfeito(8);
    }

    public void gameOverState(int code) {
        if (code==KeyEvent.VK_S){
            pj.iu.numComando++;
            if (pj.iu.numComando > 1) {
                pj.iu.numComando = 0;
            }
            pj.tocaEfeito(8);
        }
        if(code==KeyEvent.VK_W){
            pj.iu.numComando--;
            if (pj.iu.numComando < 0) {
                pj.iu.numComando = 1;
            }
            pj.tocaEfeito(8);
        }
        if (code==KeyEvent.VK_ENTER){
            if(pj.iu.numComando==0){
                pj.gameState = pj.playState;
                pj.retry();
            }
            else if(pj.iu.numComando==1){
                pj.gameState = pj.menuState;
                pj.restart();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            cima=false;
        }
        if (code == KeyEvent.VK_A) {
            esquerda=false;
        }
        if (code == KeyEvent.VK_S) {
            baixo=false;
        }
        if (code == KeyEvent.VK_D) {
            direita=false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter=false;
        }
    }
}
