package lab5;

public class Basketball
{
	private double diameter;
	private boolean isInflated;
	
  public Basketball(double givenDiameter)
  {
	  diameter = givenDiameter;
	  isInflated = false;
  }

  public boolean isDribbleable()
  {
    return isInflated;
  }

  public double getDiameter()
  {
    return diameter;
  }

  public double getCircumference()
  {
    return 0;
  }

  public void inflate()
  {
	  isInflated = true;
  }
}