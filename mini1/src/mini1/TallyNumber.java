package mini1;

public class TallyNumber {

    private int intRep;
    private String strRep;

    private int num5;
    private int num1;

    public TallyNumber(int givenValue)
    {
        intRep = givenValue;
        num5 = givenValue/5;
        num1 = givenValue%5;
        strRep = ('*' * num5)+ ('|' * num1);


    }

    public TallyNumber(String givenString)
    {
        strRep = givenString;
    }

    public String getStringValue()
    {
        return strRep;
    }

    public  int getIntValue()
    {
        return intRep;
    }

    public static void add(TallyNumber other)
    {

    }

    public static void combine(TallyNumber other)
    {

    }

    public void normalize()
    {

    }
}