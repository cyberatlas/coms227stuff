package lab2;
/**
 * Models an atom
 * 
 * @author 
 * Alex Stevenson
 *
 */
public class Atom {
	/**The number of protons in the atom*/
	private int protons;
	/**The number of neutrons in the atom*/
	private int neutrons;
	/**The number of electrons in the given atom*/
	private int electrons;
	
	/**
	 * The constructor makes the atom object 
	 * @param givenProtons
	 * the number of protons to start
	 * @param givenNeutrons
	 * the number of electrons to start
	 * @param givenElectrons
	 * the number of electrons to start
	 */
public Atom(int givenProtons, int givenNeutrons,int givenElectrons)
{
	protons = givenProtons; 
	neutrons = givenNeutrons; 
	electrons = givenElectrons;
	
}
/**Returns the atomic mass
 * @return 
 * the number of protons plus the neutrons
 */
public int getAtomicMass(){
	return protons + neutrons;
}
/**
 * Calculates the atomic charge
 * @return
 * done by finding the difference between the number of protons and electrons
 */
public int getAtomicCharge(){
	return protons - electrons; 
}
/**
 * The decay of the atom
 * Subtracts 2 protons and 2 neutrons
 */
public void decay(){
	protons = protons - 2;
	neutrons = neutrons- 2;
}

}



