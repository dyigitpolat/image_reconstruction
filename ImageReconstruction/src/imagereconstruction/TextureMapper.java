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
public class TextureMapper 
{
    public EasyImage highRes;
    public EasyImage lowRes;
    
    public TextureMapper(EasyImage high, EasyImage low)
    {
        highRes = high;
        lowRes = low;
    }
    
    public int[] getClosestTexture( int[] pattern)
    {
        //TODO:
        double minDistance = Double.POSITIVE_INFINITY;
        int[] closestTexture = new int[12];
        
        for( int i = 1; i < lowRes.height - 1; i++)
        {
            for( int j = 1; j < lowRes.width - 1; j++)
            {
                double curDist = getDistance( pattern, getPatternAt(j, i));
                if( curDist < minDistance)
                {
                    minDistance = curDist;
                    closestTexture = getTextureAt( j, i);
                }
            }
        }
        
        return closestTexture;
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
    
    
    // TODO: (int[] arr1, int[] arr2, int offset1, int offset2, int width)
    // pattern: offset + (j*width) + i 
    // too much work here omg :( but will save hundreds of cycles believe me.
    private double getDistance( int[] pattern1, int[] pattern2)
    {
        double distance = 0.0;
        
        for( int i = 0; i < pattern1.length; i++)
        {
            distance += (pattern1[i] - pattern2[i]) * (pattern1[i] - pattern2[i]);
        }
        
        return Math.sqrt(distance);
                
    }
    
    // too much overhead we got here man, shit. 
    // we should never do such things.
    private int[] getTextureAt( int x, int y)
    {
        int[] texture = new int[12];
        
        for( int i = 0; i < 2; i++)
        {
            for( int j = 0; j < 2; j++)
            {
                int index = highRes.indexOfRed(2*(x+j), 2*(y+i));
                texture[ i*6 + j*3 + 0] = highRes.RGBarray[ index + 0];
                texture[ i*6 + j*3 + 1] = highRes.RGBarray[ index + 1];
                texture[ i*6 + j*3 + 2] = highRes.RGBarray[ index + 2];
            }
        }
        
        return texture;
    }
}
