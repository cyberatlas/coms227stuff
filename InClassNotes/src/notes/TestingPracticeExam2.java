package notes;

/**
 * Created by Ruski on 4/1/2017.
 */
public class TestingPracticeExam2 {



    private static double average(double []array){
        double count = 0;
        for (int i =0; i <array.length;i++){
            count += array[i];
        }
        return (count/array.length);
    }

    public static void main(String[] args){
        double[] a1 = {5.0,10.0,1.0};
        System.out.print(average(a1));
    }
}