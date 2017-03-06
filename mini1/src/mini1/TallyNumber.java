package mini1;

public class TallyNumber {

	private int intRep;
	private String strRep;

	public TallyNumber(int givenValue){	
		intRep = givenValue;
	}
	public TallyNumber(String givenString){
		strRep = givenString;
	}

	public static String getStringValue(){}

	public  static int getIntValue(){}

	public static void add(TallyNumber other){}

	public static void combine(TallyNumber other){}

	public void normalize(){}
}