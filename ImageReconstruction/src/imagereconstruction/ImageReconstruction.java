/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagereconstruction;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author d.yigit
 */
public class ImageReconstruction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        EasyImage lowRef = new EasyImage( getImage("images\\SMALLREFc.bmp"));
        EasyImage hiRef = new EasyImage( getImage("images\\BIGREFc.bmp"));
        EasyImage low = new EasyImage( getImage("images\\SMALLc.bmp"));
        
        TextureMapper tmap = new TextureMapper(hiRef, lowRef);
        Reconstructor rec = new Reconstructor( low, tmap);
        
        rec.reconstruct();
        
        BufferedImage bi = rec.getBufferedImage();
        
        try 
        {
            File outputfile = new File("images\\RECONS_new.bmp");
            ImageIO.write(bi, "bmp", outputfile);
        } 
        catch (IOException e) 
        {
            System.out.println("The image was not saved.");
        }
        
    }
    
    public static BufferedImage getImage(String filename) 
    {
        // This time, you can use an InputStream to load
        try 
        {
            return ImageIO.read(new File(filename));
        } 
        catch (Exception e)
        {
            System.out.println("The image was not loaded.");
            //System.exit(1);
        }
        return null;
    }
    
}
