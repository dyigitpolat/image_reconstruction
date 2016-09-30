/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagereconstruction;

/**
 *
 * @author d.yigit
 */
public class Kernel 
{
    public int[] keys;
    public int[] data;
    
    //kernel around the pixel x,y
    public Kernel(int[] raster, int x, int y, int width, int height)
    {
        keys = new int[27];
        data = new int[12];
        for( int i = -1; i < 2; i++)
        {
            for( int j = -1; j < 2; j++)
            {
                keys[ (i+1)*3*3 + (j+1)*3 + 0] = raster[ (y+i)*width*3 + (x+j)*3 + 0 ];
                keys[ (i+1)*3*3 + (j+1)*3 + 1] = raster[ (y+i)*width*3 + (x+j)*3 + 1 ];
                keys[ (i+1)*3*3 + (j+1)*3 + 2] = raster[ (y+i)*width*3 + (x+j)*3 + 2 ];
            }
        }
    }
    
    public void setData( int[] raster, int x, int y, int width, int height)
    {
        //TODO: higher quality version.
        for( int i = 0; i < 2; i++)
        {
            for( int j = 0; j < 2; j++)
            {
                data[ i*2*3 + j*3 + 0] = raster[ (y+i)*width*3 + (x+j)*3 + 0 ];
                data[ i*2*3 + j*3 + 1] = raster[ (y+i)*width*3 + (x+j)*3 + 1 ];
                data[ i*2*3 + j*3 + 2] = raster[ (y+i)*width*3 + (x+j)*3 + 2 ];
            }
        }
    }
    
    public double distance( Kernel k)
    {
        double distance = 0.0;
        for( int i = 0; i < 27; i++)
        {
            distance += ( ( keys[i] - k.keys[i])*( keys[i] - k.keys[i]));
        }
        
        distance = Math.sqrt(distance);
        return distance;
    }
    
}
