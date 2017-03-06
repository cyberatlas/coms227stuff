package mini1;

import com.sun.deploy.util.StringUtils;

public class TallyNumber {

    //integer representation of the tally stuff
    private int intRep;
    //string representation of the tally stuff
    private String strRep;

    private int num5;
    private int num1;

    private String str5 = "*";
    private String str1 = "|";

    public TallyNumber(int givenValue) {
        intRep = givenValue;
        num5 = givenValue / 5;
        num1 = givenValue % 5;
        strRep = repeat(str5, num5) + repeat(str1, num1);
    }


    public TallyNumber(String givenString) {
        strRep = givenString;
        int len = strRep.length(); //Find the length of the string so we can iterate through a loop and count the characters in it


        for (int i =0; i < len; i++){

        }
    }

    public String getStringValue() {
        return strRep;
    }

    public int getIntValue() {
        return intRep;
    }

    public static void add(TallyNumber other) {

    }

    public static void combine(TallyNumber other) {

    }

    public void normalize() {

    }
}

