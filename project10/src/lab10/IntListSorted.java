package lab10;

import lab10.IntList;

public class IntListSorted extends IntList {

    public IntListSorted() {
        super();
    }

    /**
     * Adds the new item not at end of the list, but at the correct spot so that
     * the list stays sorted.
     *
     * @param newItem the item to add.
     */
    public void add(int newItem) {
        // Ask the superclass to insert the item at the end of the list.
        super.add(newItem);

        // Now, look at the item right before the new item. Is it greater than
        // the new item? If so, swap the items. Keep doing this until we either
        // see an item before the new item that is not greater or we hit the
        // front of the list.
        int tmp;
        for (int i = size - 1; i > 0 && list[i - 1] > list[i]; --i) {
            tmp = list[i];
            list[i] = list[i - 1];
            list[i - 1] = tmp;
        }
    }
    public int getMedian(){
        return list[list.length/2];
    }
    public int getMaximum(){
        return list[size()-1];
    }
    public int getMinimum(){
        return list[0];
    }
}