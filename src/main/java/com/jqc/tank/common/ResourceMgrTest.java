package com.jqc.tank.common;

import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;


class ResourceMgrTest {
    @Test
    void test(){
        try {
            BufferedImage image = ImageIO.read(ResourceMgrTest.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            assertNotNull(image);
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        new ResourceMgrTest().test();
    }


}