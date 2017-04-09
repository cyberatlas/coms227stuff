package examples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import api.ITransform;
import mini2.GridUtil;
import mini2.SmoothingTransform;

/**
 * Applies a transform to an image and writes the resulting file.
 */
public class ImageTest
{
  public static void main(String[] args) throws IOException
  {
    // name of image file in project directory, needs to be jpg, gif, or png
    //String filename = "clover_non_square.jpg";
    String filename = "clover_really_small.jpg";
    
    // type of output file, can be jpg, gif, or png
    String type = "jpg";
    
    // name of output file
    String outFilename = filename.substring(0, filename.lastIndexOf('.')) + "_TRANSFORMED." + type;

    // read the file if possible, may throw IOException
    BufferedImage img = ImageIO.read(new File(filename));
    
    // transformation to use, average a 5x5 neighborhood
    ITransform transform = new SmoothingTransform(2);
        
    // apply the transformation separately to the red, green, and blue components
    int[][] result = applyToRGB(img, transform);
    
    // copy the resulting values into a new BufferedImage
    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int row = 0; row < height; row += 1)
    {
      for (int col = 0; col < width; col += 1)
      {
        // column is "x", row is "y"
        newImage.setRGB(col, row, result[row][col]);
      }
    }
    
    // write the image to output file
    ImageIO.write(newImage, type, new File(outFilename));
  }

  /**
   * Apply the given transformation to an image, applying it
   * separately to the red, green, and blue components and
   * merging the results.
   * @param img
   * @param transform
   * @return
   */
  private static int[][] applyToRGB(BufferedImage img, ITransform transform)
  {
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] temp = new int[height][width];
    int[][] result = new int[height][width];

    // Do this three times, for red, green, and blue
    // index is shift amount: 0 for blue, 8 for green, 16 for red
    for (int shift = 0; shift <= 16; shift += 8)
    {
      // copy one of the color components into temp
      for (int row = 0; row < height; row += 1)
      {
        for (int col = 0; col < width; col += 1)
        {
          // column is "x", row is "y"
          int pixel = img.getRGB(col, row);
          
          // pull the red, green or blue byte from the pixel value
          int colorVal = (pixel >> shift) & 0xff;
          temp[row][col] = colorVal;
        }
      }

      // apply the transformation to temp and update temp
      temp = GridUtil.applyAll(temp, transform);

      // put the transformed value into result, merging the 
      // red, green, or blue with a shift and bitwise-or operation
      for (int row = 0; row < height; row += 1)
      {
        for (int col = 0; col < width; col += 1)
        {
          result[row][col] |= (temp[row][col] << shift);
        }
      }
    }

    return result;
  }
  
}
