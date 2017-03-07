package mini1;

/**
 * Created by ruski on 3/6/2017.
 */
public class TallyNumberTest {
    public static void main(String[] args){

        TallyNumber t =  new TallyNumber("*************");
        System.out.println(t.getIntValue());
        System.out.println(t.getStringValue());
        TallyNumber t2 = new TallyNumber("** ||");
        System.out.println(t2.getStringValue());
        System.out.println(t2.getIntValue());
    }
}
