package notes;

import java.util.Arrays;

/**
 * Created by Ruski on 3/27/2017.
 */
public class MergeSort {

    public static void merge(int [] v1, int[] v2, int[] result){
        //Write a loop that fills up the result array
        int i1=0, i2=0;
        for (int i=0; i<result.length; ++i){
            //compare v1[i1] and v2[i2]
            if (i2 == v2.length || (i1<v1.length && v1[i1]<v2[i2])){
                result[i] = v1[i1];
                i1++;
            }
            else{
                result[i] = v2[i2];
                i2++;
            }
        }
    }
    public static void mergeSort(int [] v){
        //base case
        if (v.length ==1)
            return;

        //divide v into 2 halves
        int mid = v.length/2;
        int[] firstHalf  = Arrays.copyOfRange(v,0,mid);
        int [] secondHalf = Arrays.copyOfRange(v,mid,v.length);

        //sort arrays firstHalf and secondHalf
        mergeSort(firstHalf);
        mergeSort(secondHalf);

        //merge firstHalf and secondHalf into V
        merge(firstHalf,secondHalf,v);

    }
}
