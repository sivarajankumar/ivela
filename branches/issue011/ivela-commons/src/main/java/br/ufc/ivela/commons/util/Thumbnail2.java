/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * 
 */
public class Thumbnail2 {

    public static void processSquareThumbnail(String fileIn, String fileOut, Integer thumbnailSide, String sQuality) throws Exception {
        // load image from INFILE
        Image image = Toolkit.getDefaultToolkit().getImage(fileIn);
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForID(0);
        
        // determine thumbnail size from WIDTH and HEIGHT
        Integer initialWidth = image.getWidth(null);
        Integer initialHeight = image.getHeight(null);
        
        // draw a temp image to crop resizing the original image depending on the bigger dimension of the thumbnail image
        Integer tempWidth;
        Integer tempHeight;
        if (initialWidth >= initialHeight) {
            tempWidth = (int) Math.ceil(thumbnailSide * ((float)initialWidth / (float)initialHeight));
            tempHeight = thumbnailSide;
        }else{
            tempWidth = thumbnailSide;
            tempHeight = (int) Math.ceil(thumbnailSide * ((float)initialHeight / (float)initialWidth));
        }
        
        BufferedImage tempImage = new BufferedImage(tempWidth, tempHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D tempGraphics2D = tempImage.createGraphics();
        tempGraphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        tempGraphics2D.drawImage(image, 0, 0, tempWidth, tempHeight, null);
        
        // set the start points to crop
        Integer cropX = (tempWidth != thumbnailSide) ? Math.abs((tempWidth - thumbnailSide) / 2) : 0;
        Integer cropY = (tempHeight != thumbnailSide) ? Math.abs((tempHeight - thumbnailSide) / 2) : 0;
        
        // crop the temp image to get only a central part of the image according to the thumbnail dimension
        BufferedImage thumbnailImage = new BufferedImage(thumbnailSide, thumbnailSide, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbnailImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(tempImage, 0, 0, thumbnailSide, thumbnailSide, cropX, cropY, (cropX + thumbnailSide), (cropY + thumbnailSide), null);
        
        // save thumbnail image to OUTFILE
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileOut));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbnailImage);
        int quality = Integer.parseInt(sQuality);
        quality = Math.max(0, Math.min(quality, 100));
        param.setQuality((float) quality / 100.0f, false);
        //encoder.setJPEGEncodeParam(param);
        encoder.encode(thumbnailImage);
        out.close();
    }
    
    public static void process(String fileIn, String fileOut, String tWidth, String tHeight, String sQuality) throws Exception {
        // load image from INFILE
        Image image = Toolkit.getDefaultToolkit().getImage(fileIn);
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForID(0);
        
        // determine thumbnail size from WIDTH and HEIGHT
        int thumbWidth = Integer.parseInt(tWidth);
        int thumbHeight = Integer.parseInt(tHeight);
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }
        
        // draw original image to thumbnail image object and
        // scale it to the new size on-the-fly
        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
        
        // save thumbnail image to OUTFILE
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileOut));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
        int quality = Integer.parseInt(sQuality);
        quality = Math.max(0, Math.min(quality, 100));
        param.setQuality((float) quality / 100.0f, false);
       // encoder.setJPEGEncodeParam(param);
        encoder.encode(thumbImage);
        out.close();
    }

    public static void main(String[] args) {
        String fileIn = "/home/leoomoreira/tux14.jpg";
        String fileOut = "/home/leoomoreira/tux15.jpg";
        try {
            Thumbnail2.processSquareThumbnail(fileIn, fileOut, 80, "80");
            //Thumbnail2.process(fileIn, fileOut, "80", "80", "50");
        } catch (Exception ex) {
            Logger.getLogger(Thumbnail2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
