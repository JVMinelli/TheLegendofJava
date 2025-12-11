package tile;

import main.PainelJogo;
import main.Utilidade;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    PainelJogo pj;
    public Tile[] tile;
    public int NumeroTileMapa[][][];

    public TileManager (PainelJogo pj){

        this.pj = pj;

        tile = new Tile[55];

        NumeroTileMapa = new int [pj.mapaMax][pj.mundoMaxCol][pj.mundoMaxLinha];

        getTileImage();
        carregaMapa("/maps/MAPAMUNDI.txt", 0);
        carregaMapa("/maps/DESERTO.txt", 1);
        carregaMapa("/maps/indoor01.txt", 2);
        carregaMapa("/maps/PANTANO.txt", 3);
        carregaMapa("/maps/CASTELO.txt", 4);
    }

    public void getTileImage() {

            setup(3, "03grass", false);
            setup(7, "07wall", true);
            setup(2, "02water", true);
            setup(5, "05sand", false);
            setup(4, "04earth", false);
            setup(6, "06tree", true);
            setup(1, "01lvlDoor", false);
            setup(8, "08Casa-branca", true);
            setup(9, "09Casa-verde", true);
            setup(10, "10Casa-azul", true);
            setup(11, "11Casa-amarela", false);
            setup(12, "12ponte", false);
            setup(13, "13loja", true);
            setup(14, "14escola", true);
            setup(15, "15Casa-amarela-costas", true);
            setup(16, "16Casa-azul-costas", true);
            setup(17, "17Casa-branca-costas", true);
            setup(18, "18Casa-verde-costas", true);
            setup(19, "19igreja", true);
            setup(20,"20chaoMadeira", false);
            setup(21, "21areiadeserto", false);
            setup(22, "22cactoredondo", true);
            setup(23, "23cacto2", true);
            setup(24, "24cacto", true);
            setup(25, "25cactoredondo2", true);
            setup(26, "26coqueiro-baixo-grama", true);
            setup(27, "27coqueiro-cima-areia", true);
            setup(28, "28coqueiro-baixo-areia", true);
            setup(29, "29coqueiro-cima-grama", true);
            setup(30, "30grama-pantano", false);
            setup(31, "31tronco-pantano", true);
            setup(32, "32arvore-pantano", true);
            setup(33, "33cerca-costas", true);
            setup(34, "34cerca-frente", true);
            setup(35, "35cerca", true);
            setup(36, "36castelo0", true);
            setup(37, "37castelo1", true);
            setup(38, "38castelo2", true);
            setup(39, "39castelo3", true);
            setup(40, "40castelo4", true);
            setup(41, "41castelo5", true);
            setup(42, "42castelo6", false);
            setup(43, "43castelo7", true);
    }
    public void setup(int index, String caminhoImagem, boolean colisao) {

        Utilidade UT = new Utilidade();

        try {
            tile[index] = new Tile();
            tile[index].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/" + caminhoImagem + ".png"));
            tile[index].imagem = UT.scaleImage(tile[index].imagem, pj.tamanhoTile,pj.tamanhoTile);
            tile[index].colisao = colisao;

        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void carregaMapa(String Mapa, int mapa){

        try{
            InputStream is = getClass().getResourceAsStream(Mapa);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int coluna=0;
            int linha=0;

            while(coluna < pj.mundoMaxCol && linha < pj.mundoMaxLinha){

                String linhaLer = br.readLine();

                while(coluna < pj.mundoMaxCol){

                    String numeros[] = linhaLer.split(" ");

                    int num = Integer.parseInt(numeros[coluna]);

                    NumeroTileMapa[mapa][coluna][linha]= num;
                    coluna++;
                }
                if (coluna==pj.mundoMaxCol){
                    coluna=0;
                    linha++;
                }
            }
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){

        int mundoColuna=0;
        int mundoLinha =0;


        while(mundoColuna<pj.mundoMaxCol && mundoLinha < pj.mundoMaxLinha){

            int numeroTile = NumeroTileMapa[pj.mapaAtual][mundoColuna][mundoLinha];

            int mundoX=mundoColuna *pj.tamanhoTile;
            int mundoY=mundoLinha*pj.tamanhoTile;
            int telaX=mundoX-pj.jogador.mundox + pj.jogador.telax;
            int telaY=mundoY-pj.jogador.mundoy + pj.jogador.telay;
            if (mundoX + pj.tamanhoTile > pj.jogador.mundox - pj.jogador.telax &&
                    mundoX < pj.jogador.mundox + pj.larguraTela - pj.jogador.telax &&
                    mundoY + pj.tamanhoTile > pj.jogador.mundoy - pj.jogador.telay &&
                    mundoY < pj.jogador.mundoy + pj.alturaTela - pj.jogador.telay) {

                g2.drawImage(tile[numeroTile].imagem,telaX,telaY,null);
            }
            mundoColuna++;

            if (mundoColuna == pj.mundoMaxCol){
                mundoColuna=0;
                mundoLinha++;
            }
        }
    }
}
