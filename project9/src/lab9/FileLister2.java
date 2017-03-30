package lab9;
import java.io.File;

public class FileLister2
{
  public static void main(String[] args)
  {
    // Choose the directory you want to list.
    // If running in Eclipse, "." will just be the current project directory.
    // Use ".." to list the whole workspace directory, or enter a path to
    // some other directory.
    String rootDirectory = "."; 

    listAllFiles(new File(rootDirectory));
  }
  
  /**
   * Lists all files and directories below f
   * @param f
   *   a given file or directory
   */
  public static void listAllFiles(File f)
  {
    // start off at level 0
    listAllFilesRec(f, 0);
  }
  
  /**
   * Helper method lists all files and directories below f, using the given
   * level to indent
   */
  private static void listAllFilesRec(File f, int level)
  {
    // First, indent two spaces for each level
    System.out.print(makeSpaces(2 * level));
    
    if (!f.isDirectory())
    {
      // Base case: f is a file, so just print its name
      System.out.println(f.getName());
    }
    else
    {
      // General case: f is a directory, so recursively list the 
      // files and subdirectories it contains
      System.out.println("+ " + f.getName());
      File[] files = f.listFiles();
      for (int i = 0; i < files.length; ++i)
      {
        listAllFilesRec(files[i], level + 1);
      }
    }
  }

  /**
   * Returns a string with the given number of spaces.
   */
  private static String makeSpaces(int numSpaces)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numSpaces; ++i)
    {
      sb.append(" ");
    }
    return sb.toString();
  }
  
}