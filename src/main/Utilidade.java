package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utilidade {

    public BufferedImage scaleImage(BufferedImage original, int largura, int altura) {

        BufferedImage scaledImage = new BufferedImage(largura, altura, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, largura, altura, null);
        g2.dispose();

        return scaledImage;
    }
}
