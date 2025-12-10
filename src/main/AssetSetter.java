package main;

import Entidades.*;
import monstro.mon_slimeBravoChave;
import monstro.mon_slimeBravoCristal;
import objts.*;
import monstro.mon_slimeverde;

public class AssetSetter {
    PainelJogo PJ;

    public AssetSetter(PainelJogo PJ) {
        this.PJ = PJ;
    }

    public void setObjeto() {
        int numMapa = 0;
        PJ.obj[numMapa][0] = new OBJ_chave(PJ);
        PJ.obj[numMapa][0].mundox = 19 * PJ.tamanhoTile;
        PJ.obj[numMapa][0].mundoy = 21 * PJ.tamanhoTile;

        PJ.obj[numMapa][1] = new OBJ_chave(PJ);
        PJ.obj[numMapa][1].mundox = 47 * PJ.tamanhoTile;
        PJ.obj[numMapa][1].mundoy = 25 * PJ.tamanhoTile;

        PJ.obj[numMapa][3] = new OBJ_porta(PJ);
        PJ.obj[numMapa][3].mundox = 40 * PJ.tamanhoTile;
        PJ.obj[numMapa][3].mundoy = 2 * PJ.tamanhoTile;

        PJ.obj[numMapa][4] = new OBJ_bota(PJ);
        PJ.obj[numMapa][4].mundox = 44 * PJ.tamanhoTile;
        PJ.obj[numMapa][4].mundoy = 27 * PJ.tamanhoTile;

        PJ.obj[numMapa][5] = new OBJ_cogumelo(PJ);
        PJ.obj[numMapa][5].mundox = 46 * PJ.tamanhoTile;
        PJ.obj[numMapa][5].mundoy = 24 * PJ.tamanhoTile;

        PJ.obj[numMapa][6] = new OBJ_fogueira(PJ);
        PJ.obj[numMapa][6].mundox = 20 * PJ.tamanhoTile;
        PJ.obj[numMapa][6].mundoy = 42 * PJ.tamanhoTile;

        numMapa++;

        PJ.obj[numMapa][0] = new OBJ_cristaldourado(PJ);
        PJ.obj[numMapa][0].mundox = 26 * PJ.tamanhoTile;
        PJ.obj[numMapa][0].mundoy = 1 * PJ.tamanhoTile;
    }

    public void setNPC() {
        int numMapa = 0;
        PJ.npc[numMapa][0] = new NPCmago(PJ);
        PJ.npc[numMapa][0].mundox = PJ.tamanhoTile*19;
        PJ.npc[numMapa][0].mundoy = PJ.tamanhoTile*33;

        PJ.npc[numMapa][1] = new NPCguia(PJ);
        PJ.npc[numMapa][1].mundox = PJ.tamanhoTile*38;
        PJ.npc[numMapa][1].mundoy = PJ.tamanhoTile*2;

        PJ.npc[numMapa][2] = new NPCcidade1(PJ);
        PJ.npc[numMapa][2].mundox = PJ.tamanhoTile*7;
        PJ.npc[numMapa][2].mundoy = PJ.tamanhoTile*8;

        PJ.npc[numMapa][3] = new NPCcidade2(PJ);
        PJ.npc[numMapa][3].mundox = PJ.tamanhoTile*18;
        PJ.npc[numMapa][3].mundoy = PJ.tamanhoTile*2;

        PJ.npc[numMapa][4] = new NPCcidade3(PJ);
        PJ.npc[numMapa][4].mundox = PJ.tamanhoTile*10;
        PJ.npc[numMapa][4].mundoy = PJ.tamanhoTile*21;

        PJ.npc[numMapa][5] = new NPCcidade3(PJ);
        PJ.npc[numMapa][5].mundox = PJ.tamanhoTile*10;
        PJ.npc[numMapa][5].mundoy = PJ.tamanhoTile*21;

        numMapa++;

        PJ.npc[numMapa][0] = new NPCdeserto1(PJ);
        PJ.npc[numMapa][0].mundox = PJ.tamanhoTile*22;
        PJ.npc[numMapa][0].mundoy = PJ.tamanhoTile*21;

        PJ.npc[numMapa][1] = new NPCoasis(PJ);
        PJ.npc[numMapa][1].mundox = PJ.tamanhoTile*36;
        PJ.npc[numMapa][1].mundoy = PJ.tamanhoTile*14;

        PJ.npc[numMapa][2] = new NPCdeserto2(PJ);
        PJ.npc[numMapa][2].mundox = PJ.tamanhoTile*3;
        PJ.npc[numMapa][2].mundoy = PJ.tamanhoTile*47;

        PJ.npc[numMapa][3] = new NPCguiadeserto(PJ);
        PJ.npc[numMapa][3].mundox = PJ.tamanhoTile*22;
        PJ.npc[numMapa][3].mundoy = PJ.tamanhoTile*44;

        PJ.npc[numMapa][4] = new NPCcristaldeserto(PJ);
        PJ.npc[numMapa][4].mundox = PJ.tamanhoTile*25;
        PJ.npc[numMapa][4].mundoy = PJ.tamanhoTile*1;
    }

    public void setMonstro() {
        int numMapa = 0;
        PJ.monstro[numMapa][0] = new mon_slimeverde(PJ);
        PJ.monstro[numMapa][0].mundox = PJ.tamanhoTile*3;
        PJ.monstro[numMapa][0].mundoy = PJ.tamanhoTile*15;
        PJ.monstro[numMapa][1] = new mon_slimeverde(PJ);
        PJ.monstro[numMapa][1].mundox = PJ.tamanhoTile*15;
        PJ.monstro[numMapa][1].mundoy = PJ.tamanhoTile*14;
        PJ.monstro[numMapa][2] = new mon_slimeverde(PJ);
        PJ.monstro[numMapa][2].mundox = PJ.tamanhoTile*44;
        PJ.monstro[numMapa][2].mundoy = PJ.tamanhoTile*14;
        PJ.monstro[numMapa][3] = new mon_slimeverde(PJ);
        PJ.monstro[numMapa][3].mundox = PJ.tamanhoTile*31;
        PJ.monstro[numMapa][3].mundoy = PJ.tamanhoTile*1;
        numMapa++;

        numMapa++;
        PJ.monstro[numMapa][0] = new mon_slimeBravoCristal(PJ);
        PJ.monstro[numMapa][0].mundox = PJ.tamanhoTile*21;
        PJ.monstro[numMapa][0].mundoy = PJ.tamanhoTile*18;

        PJ.monstro[numMapa][1] = new mon_slimeBravoChave(PJ);
        PJ.monstro[numMapa][1].mundox = PJ.tamanhoTile*23;
        PJ.monstro[numMapa][1].mundoy = PJ.tamanhoTile*18;
    }
}

