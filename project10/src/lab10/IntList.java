package lab10;

/**
 * A growable collection of ints.
 */
public class IntList {

   /**
    * The list of integers. It may contain more elements than are actually
    * filled.
    */
   protected int[] list;
   
   /**
    * The actual number of elements in list that contain a value.
    */
   protected int size;
   
   /**
    * Create a new, empty list of integers.  We create spots for 10 numbers,
    * and we'll grow the array if these all fill up.
    */
   public IntList() {
      list = new int[10];
      size = 0;
   }
   
   /**
    * Get the number of items in the list.
    * @return Number of items in list.
    */
   public int size() {
      return size;
   }
   
   /**
    * Get the item at position i
    *
    * @param i
    * Index of item to retrieve.
    *
    * @return
    * Item at position i
    *
    * @throws ArrayIndexOutOfBoundsException
    * If position invalid.
    */
   public int get(int i) {
      if (i >= size) {
         throw new ArrayIndexOutOfBoundsException();
      }
      return list[i];
   }
   
   /**
    * Add an int to the end of the list. The list is grown automatically if
    * its current capacity has been reached.
    *
    * @param newItem
    * Int to add.
    */
   public void add(int newItem) {
      if (size == list.length) {
         grow();
      }
      list[size] = newItem;
      ++size;
   }
   
   /**
    * Double the capacity of the array. Automatically creates a new
    * array twice the size of the current and copies all existing
    * list data over.
    */
   private void grow() {
      int[] tmpList = new int[list.length * 2];
      for (int i = 0; i < size; ++i) {
         tmpList[i] = list[i];
      }
      list = tmpList;
   }
   
   /**
    * Find the maximum int in the list.
    *
    * @return
    * Maximum value in list.
    */
   public int getMaximum() {
      int max = list[0];
      for (int i = 1; i < size; ++i) {
         if (list[i] > max) {
            max = list[i];
         }
      }
      return max;
   }
   
   /**
    * Find the minimum int in the list.
    *
    * @return
    * Minimum value in list.
    */
   public int getMinimum() {
      int min = list[0];
      for (int i = 1; i < size; ++i) {
         if (list[i] < min) {
            min = list[i];
         }
      }
      return min;
   }
   
   /**
    * Get a string representation of the list of the form
    * [val0, val1, val2, ...].
    *
    * @return
    * Comma-separated list.
    */
   public String toString() {
      String s = "[";
      if (size > 0) {
         s += list[0];
         for (int i = 1; i < size; ++i) {
            s += ", " + list[i];
         }
      }
      s += "]";
      return s;
   }
}