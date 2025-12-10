package main;

import Entidades.Entidade;
import Entidades.Jogador;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class  PainelJogo extends JPanel implements Runnable{

    //Configurações da tela
    final int tamanhoTileOriginal = 16;
    final int escala = 3;

    public final int tamanhoTile = tamanhoTileOriginal * escala;
    public int tamanhoMaximoCol = 16;
    public int tamanhoMaximoLin = 12;
    public final int mapaMax = 10;
    public int mapaAtual = 0;

    public int larguraTela = tamanhoTile * tamanhoMaximoCol;
    public int alturaTela = tamanhoTile * tamanhoMaximoLin;

    public final int mundoMaxLinha=50;
    public final int mundoMaxCol=50;

    TileManager TM = new TileManager(this);
    public inputTeclado inputT = new inputTeclado(this);
    Som musica = new Som();
    Som efeito = new Som();

    public checaColisao cColisao = new checaColisao(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public IU iu= new IU(this);
    public Eventos Eventos = new Eventos(this);
    Thread threadJogo;

    public Jogador jogador = new Jogador(this,inputT);

    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogoState = 3;
    public final int gameOverState = 4;

    //FAZER EM 2D O ARRAY PARA OS PROXS OBJETOS TAMBEM/NPS/MONSTROS
    public Entidade obj[][]=new Entidade[mapaMax][10];
    public Entidade npc[][] = new Entidade[mapaMax][10];
    public Entidade monstro[][] = new Entidade[mapaMax][20];

    ArrayList<Entidade> listaEntidade = new ArrayList<>();

    int FPS = 60;

    public PainelJogo() {

        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(inputT);
        this.setFocusable(true);
    }

    public void configJogo(){
        aSetter.setObjeto();
        aSetter.setNPC();
        aSetter.setMonstro();
        tocaMusica(0);
        gameState = menuState;
    }

    public void retry(){
        jogador.setDefaultPositions();
        jogador.restoreLife();
        aSetter.setNPC();
        aSetter.setMonstro();
    }

    public void restart(){
        jogador.defineValoresPadrao();
        jogador.setDefaultPositions();
        aSetter.setObjeto();
        aSetter.setNPC();
        aSetter.setMonstro();
    }

    public void comecaThread() {

        threadJogo = new Thread(this);
        threadJogo.start();
    }

    public void atualiza() {

        if (gameState == playState) {
            jogador.atualiza();
            for (int i = 0; i < monstro[1].length; i++) {
                if (monstro[mapaAtual][i] != null) {
                    if (monstro[mapaAtual][i].vivo == true && monstro[mapaAtual][i].morto == false) {
                        monstro[mapaAtual][i].atualiza();
                    }
                    if (monstro[mapaAtual][i].vivo == false) {
                        monstro[mapaAtual][i].checaDrop();
                        monstro[mapaAtual][i] = null;
                    }
                }

            }
            if (gameState == pauseState) ;

        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (gameState == menuState) {
            iu.draw(g2);
        }
        else{
            //TILE
            TM.draw(g2);

            listaEntidade.add(jogador);

            for (int i = 0; i<obj[1].length; i++) {
                if (obj[mapaAtual][i] != null) {
                    listaEntidade.add(obj[mapaAtual][i]);
                }
            }
            for (int i = 0; i < npc[1].length; i++) {
                if(npc[mapaAtual][i] != null) {
                    listaEntidade.add(npc[mapaAtual][i]);
                }
            }
            for (int i = 0; i < monstro[1].length; i++) {
                if(monstro[mapaAtual][i] != null) {
                    listaEntidade.add(monstro[mapaAtual][i]);
                }
            }

            Collections.sort(listaEntidade, new Comparator<Entidade>() {
                @Override
                public int compare(Entidade e1, Entidade e2) {

                    int resultado = Integer.compare(e1.mundoy,e2.mundoy);
                    return resultado;
                }
            });

            for (int i=0; i <listaEntidade.size(); i++) {
                listaEntidade.get(i).draw(g2);
            }

            listaEntidade.clear();

            //interface do usuario
            iu.draw(g2);

            g2.dispose();
        }


    }
    public void tocaMusica(int i){
        musica.setFile(i);
        musica.toca();
        musica.loop();
    }

    public void paraMusica(){
        musica.stop();
    }

    public void tocaEfeito(int i){
        efeito.setFile(i);
        efeito.toca();
    }


    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime () + drawInterval;
        while(threadJogo!=null){


            atualiza();

            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime=0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
