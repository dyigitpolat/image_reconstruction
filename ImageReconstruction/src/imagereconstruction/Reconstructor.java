/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagereconstruction;

import java.awt.image.BufferedImage;

/**
 *
 * @author d.yigit
 */
public class Reconstructor 
{
    public TextureMapper tmap;
    public EasyImage lowRes;
    public EasyImage reconstructedImage;
    
    public Reconstructor( EasyImage low, TextureMapper textmap)
    {
        lowRes = low;
        tmap = textmap;
        
    }
    
    public void reconstruct()
    {
        
        reconstructedImage = new EasyImage(lowRes.height*2, lowRes.width*2, new int[ (lowRes.height*2)*(lowRes.width*2)*3]);
        
        for( int i = 1; i < lowRes.height - 1; i++)
        {
            for( int j = 1; j < lowRes.width - 1; j++)
            {
                setTextureAt( j, i, tmap.getClosestTexture( getPatternAt( j, i)));
            }
        }
        
    }
    
    public BufferedImage getBufferedImage()
    {
        BufferedImage bi = new BufferedImage(reconstructedImage.width, reconstructedImage.height, BufferedImage.TYPE_INT_RGB);
        
        for( int i = 0; i < reconstructedImage.height; i++)
        {
            for(int j = 0; j < reconstructedImage.width; j++)
            {
                int red = reconstructedImage.RGBarray[ i*reconstructedImage.width*3 + j*3 + 0];
                int green = reconstructedImage.RGBarray[ i*reconstructedImage.width*3 + j*3 + 1];
                int blue = reconstructedImage.RGBarray[ i*reconstructedImage.width*3 + j*3 + 2];
                
                int rgb = (red << 16) | (green << 8) | (blue);
                bi.setRGB(j, i, rgb);
            }
        }
        
        return bi;
    }
    
    //use 1x scale coordinates.
    private void setTextureAt( int x, int y, int[] texture)
    {
        //TODO::
        for( int i = 0; i < 2; i++)
        {
            for( int j = 0; j < 2; j++)
            {
                int index = reconstructedImage.indexOfRed(2*x+j, 2*y+i);
                reconstructedImage.RGBarray[ index + 0] = texture[ i*6 + j*3 + 0];
                reconstructedImage.RGBarray[ index + 1] = texture[ i*6 + j*3 + 1];
                reconstructedImage.RGBarray[ index + 2] = texture[ i*6 + j*3 + 2];
                
            }
        }
    }
    
    private int[] getPatternAt( int x, int y)
    {
        int[] pattern = new int[27];
        
        for( int i = -1; i < 2; i++)
        {
            for( int j = -1; j < 2; j++)
            {
                int index = lowRes.indexOfRed( x+j, y+i);
                pattern[(i+1)*9 + (j+1)*3 + 0] = lowRes.RGBarray[ index + 0];
                pattern[(i+1)*9 + (j+1)*3 + 1] = lowRes.RGBarray[ index + 1];
                pattern[(i+1)*9 + (j+1)*3 + 2] = lowRes.RGBarray[ index + 2];
            }
        }
        
        return pattern;
    }
    
}
