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
public class EasyImage 
{

    public int[] RGBarray;
    
    public int width;
    public int height;
    
    public EasyImage( BufferedImage img)
    {
        RGBarray = new int[img.getWidth() * img.getHeight() * 3];
        width = img.getWidth();
        height = img.getHeight();
        
        for( int i = 0; i < height; i++)
        {
            for( int j = 0; j < width; j++)
            {
                int rgb = img.getRGB(j, i);
                RGBarray[i*width*3 + j*3 + 0] = ((rgb >> 16) & 0xFF);
                RGBarray[i*width*3 + j*3 + 1] = ((rgb >> 8) & 0xFF);
                RGBarray[i*width*3 + j*3 + 2] = (rgb & 0xFF);
            }
        }
    }
    
    public EasyImage( int h, int w, int[] RGBarray)
    {
        this.RGBarray = RGBarray;
        height = h;
        width = w;
    }
    
    public int indexOfRed( int x, int y)
    {
        return y*width*3 + x*3;
    }
    
    /*
    * just for test never never never use.
    */
    public void printImg()
    {
        for( int i = 0; i < height; i++)
        {
            for( int j = 0; j < width; j++)
            {
                System.out.print(":" + RGBarray[i*width*3 + j*3 + 0]);
                System.out.print(":" + RGBarray[i*width*3 + j*3 + 1]);
                System.out.print(":" + RGBarray[i*width*3 + j*3 + 2]);
                System.out.print(" , \t\t");
            }
            System.out.println();
        }
    }
    
    
}
