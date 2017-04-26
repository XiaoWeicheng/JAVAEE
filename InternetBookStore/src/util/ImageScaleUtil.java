package util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageScaleUtil {
	
	public static void scale(File srcImageFile,int height,File smallImage){
		
        try{
            BufferedImage src = ImageIO.read(srcImageFile);
            int width = (int)(height*src.getWidth()/(double)src.getHeight());
            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            ImageIO.write(tag, "JPEG", smallImage);// 输出到文件流
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
