/*    
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: ProfileAction.java                                                                  #
# Document: Profile Action                                                                  # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# ??-???-2008 - Leonardo Oliveira Moreira         - XXXXXX - Initial Version                #
# 30-JUN-2009 - otofuji (Instituto Eldorado)      - 000010 - General Fixes                  #
#############################################################################################
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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;


public class Thumbnail2 {

    public static void processSquareThumbnail(String fileIn, String fileOut,
            Integer thumbnailSide, String sQuality) throws Exception {
        BufferedImage source = ImageIO.read(new File(fileIn));
        int widerSide = source.getHeight() > source.getWidth() ? source
                .getHeight() : source.getWidth();
        if (widerSide <= thumbnailSide) {
            // No Need to Resize.
            return;
        }
        double transformation = (double) thumbnailSide / widerSide;
        BufferedImage thumbnail = new BufferedImage(
                (int) (source.getWidth() * transformation), (int) (source
                        .getHeight() * transformation),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = thumbnail.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance(transformation,
                transformation);
        g.drawRenderedImage(source, at);
        ImageIO.write(thumbnail, "JPG", new File(fileOut));
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
