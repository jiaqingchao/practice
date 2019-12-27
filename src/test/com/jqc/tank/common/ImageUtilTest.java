package com.jqc.tank.common;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageUtilTest {

    @Test
    public void rotateImage() {
        try {
            BufferedImage tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankL = rotateImage(tankL, 90);
            Assertions.assertNotNull(tankL);
        } catch (IOException e) {.jqc
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public BufferedImage rotateImage(final BufferedImage bufferedimage,
                                     final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}