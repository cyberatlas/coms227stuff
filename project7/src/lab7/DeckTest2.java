
package lab7;

import java.util.Random;

/**
 * Computes which random seeds create specific "good" hands for standard 5-card
 * poker.
 * 
 */
public class DeckTest2
{
  public static void main(String[] args)
  {
    for (int i = 0; i < 6000; i++)
    {
      Random rand = new Random(i);
      Deck deck = new Deck(rand); // construct the deck using generator
      Card[] hand = deck.select(5);
      PokerHand poker = new PokerHand(hand);
      if (poker.toString() != "Nothing")
      {
        System.out.println("The key: " + i + ", is " + poker);
        poker.printHand();
      }
    }
  }
}