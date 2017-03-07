package mini1;


public class TallyNumber {

    //integer representation of the tally stuff
    private int intRep;
    //string representation of the tally stuff
    private String strRep;

    private int num5;
    private int num1;

    private int numSpace;

    public TallyNumber(int givenValue) {
        intRep = givenValue;
        num5 = givenValue / 5;
        num1 = givenValue % 5;
        //strRep = str5.repeat(num5) + str1.repeat(num1);
        strRep = (intRep !=0) ? repeat("*", num5) + repeat("|", num1) : "0";
    }


    public TallyNumber(String givenString) {

        strRep = (givenString);
        int sum =0;
        countSpaces();
        for (int i = 0; i < strRep.length(); i++) {
            if (strRep.charAt(i) == '|') {
                //intRep += 1;
                sum +=1;
            } else if (strRep.charAt(i) == '*') {
                //intRep += 5;
                sum+=5;
            } else if (strRep.charAt(i) == ' ') {
                intRep += sum * (int) Math.pow(10, numSpace);
                numSpace -=1;
            }
            else if (strRep.charAt(i) =='0'){
                sum += 0;
            }

            else if ((strRep.charAt(i) != '|') &&  (strRep.charAt(i) != '*' )&& (strRep.charAt(i) != ' ')){
                intRep =-1;
                strRep = "";
                sum = 0;
                break;
            }
        }
        intRep += sum;

    }

    public String getStringValue() {
        return strRep;
    }

    public int getIntValue() {
        return intRep;
    }

    public void add(TallyNumber other) {

    }

    public void combine(TallyNumber other) {

    }

    public void normalize() {

    }
    //found this method, allows me to make a string that has a character repeated, some number of times
    private static String repeat(String str, int times){
        return new String(new char[times]).replace("\0", str);
    }
    private void countSpaces(){
        for (int i =0; i <strRep.length(); i++) {
            if (strRep.charAt(i) == ' '){
                numSpace +=1;
            }
        }
    }
}

