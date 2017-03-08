package mini1;


public class TallyNumber {

    //integer representation of the tally stuff
    private int intRep;
    //string representation of the tally stuff
    private String strRep;

    private int num5;
    private int num1;
    
    private boolean b1 = false;
    private boolean b2 = false;
    
    private int numSpace;

    public TallyNumber(int givenValue) {
        intRep = givenValue;
        strRep = convertToString(intRep);//sets the string representation equal to the return of the convertT
        b2 = true;
    }


    public TallyNumber(String givenString) {

        strRep = givenString;
        convertToInt(strRep);
        b1 = true;
    }
    private String convertToString(int givenValue){
        //defining holdString as a temporary String and then returns that String 
    	
    	String holdString = "";
    	num5 = givenValue / 5;
        num1 = givenValue % 5;
        //strRep = str5.repeat(num5) + str1.repeat(num1);
        holdString = (intRep !=0) ? repeat("*", num5) + repeat("|", num1) : "0";
        return holdString;
    }
    private int convertToInt(String str){
    	int sum =0;
        numSpace = countSpaces(strRep);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                //intRep += 1;
                sum +=1;
            } else if (str.charAt(i) == '*') {
                //intRep += 5;
                sum+=5;
            } else if (str.charAt(i) == ' ') {
                intRep += sum * (int) Math.pow(10, numSpace);
                numSpace -=1;
                sum = 0;
            }
            else if (str.charAt(i) =='0'){
                sum += 0;
            }

            else if ((str.charAt(i) != '|') &&  (str.charAt(i) != '*' )&& (str.charAt(i) != ' ')){
                intRep =-1;
                strRep = "";
                sum = 0;
                break;
            }
        }
        intRep = (strRep == "") ? -1: intRep + sum ;
        return intRep;
        //return intRep;
    }

    public String getStringValue() {
        if(b1 == false){
    	normalize();
    	return strRep;
        }
        else return strRep;
    	
    }

    public int getIntValue() {
        return intRep;
    }

    public void add(TallyNumber other) {
    	intRep += other.getIntValue();
    }

    public void combine(TallyNumber other) {
    	/*b1 = true;
        String[] s1 = getStringValue().split(" ");
        String[] s2 = other.getStringValue().split(" ");
        String combine = "";
        int i = 0;
        if(s1.length > s2.length) {
            for (i = 0; i < s1.length - s2.length; i++) combine += s1[i] + " ";
            for(int j=0;j<s2.length;j++) combine += s1[i+j] + s2[j] + " ";
        }
        else {
            for (i = 0; i < s2.length - s1.length; i++) combine += s2[i] + " ";
            for(int j=0;j<s1.length;j++) combine +=  s1[j] + s2[i+j] + " ";
        }
        strRep = combine.substring(0, combine.length()-1);*/
    	getStringValue();
    	strRep = strRep + other.getStringValue();
    }

    public void normalize() {
    	//just a dummy string that we can add values to
    	
    	if (b1 == false){
    	
    	int intRep2 = getIntValue(); //another int to hold the value of intRep
		String int2String = Integer.toString(intRep2);//stores the integer as a string
		int atVal = 0;//stores the current value of the int in the String
		String normie = "";//Stores the normalized string
		
    	if (getIntValue() > 0){
    	    		
    		for(int i =0; i <int2String.length(); i++){
    		atVal = (int) int2String.charAt(i) - '0';
    		if (atVal == 0) {normie += "0";}
    		normie += repeat("*", (atVal/5))+ repeat("|", (atVal%5))+ " ";
    	}
    			  		
    	}
    	if (getIntValue() == 0){
    		normie = "0" + " ";
    	}
    	strRep = normie.trim();
   }
    }
    //found this method, allows me to make a string that has a character repeated, some number of times
    private static String repeat(String str, int times){
        return new String(new char[times]).replace("\0", str);
    }
    private int countSpaces(String str){
       int space= 0;
    	for (int i =0; i <str.length(); i++) {
            if (str.charAt(i) == ' '){
                space +=1;
            }
        }
        return space;
    }
}

