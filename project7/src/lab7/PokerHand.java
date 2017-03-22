package lab7;

/**
 * Creates a standard 5 card hand for poker, and identifies which type of hand
 * it is, e.g. straight, flush, or full-house.
 */
public class PokerHand {

    /**
     * The 5 cards comprising a hand.
     */
    private Card[] cards;

    /**
     * Creates a hand using the 5 cards passed in.
     */
    public PokerHand(Card[] cards) {
        this.setHand(cards);
    }

    /**
     * Sets the cards in the hand.
     *
     * @param cards a 5 card array. If not 5 cards, a RuntimeException is thrown.
     */
    public void setHand(Card[] cards) {
        if (cards.length == 5) {
            this.cards = cards;
        } else {
            throw new RuntimeException("Wrong number of cards. ");
        }
    }

    /**
     * Determines whether or not a hand is made up of cards from a single suit.
     * Should work with more than 4 suits, and any number of cards.
     *
     * @return true iff all the cards have the same suit
     */
    public boolean isFlush() {
        sortWithSuit();
        return cards[0].getSuit().equals(cards[cards.length - 1].getSuit());
    }

    /**
     * Determines whether or not a hand is made up of consecutively increasing
     * ranks. E.g. 2, 3, 4, 5, 6. Should work with more than 4 suits, and any
     * number of cards.
     *
     * @return true iff we have a straight
     */
    public boolean isStraight() {
        sortNoSuit();
        // TODO Finish this method
        //assuming sorted without regards for suits
        for(int i =1; i<cards.length; i++){
            if ((cards[i].getRank() - cards[i-1].getRank()) !=1){
                return false;
            }
        }
        return true;
    }

    /**
     * Looks if we have a pair and a three of a kind in the hand.
     *
     * @return true iff we have a full house.
     */
    public boolean isFullHouse() {
        sortNoSuit();
        // TODO Finish this method
        if ((cards[0].getRank() == cards[1].getRank())&&(cards[3].getRank() == cards[4].getRank())&&((cards[2].getRank() == cards[0].getRank()) || (cards[2].getRank()==cards[4].getRank()))){
            return true;
        }

        return false;
    }

    /**
     * Sorts the cards by their suit and then their rank.
     */
    private void sortWithSuit() {
        for (int i = 0; i < cards.length - 1; ++i) {
            // Step 1: find the "minimal" element to the right of position i
            int indexOfMin = findIndexOfMin(i);

            // Step 2: swap the minimal element into position i
            swap(i, indexOfMin);
        }
    }

    /**
     * Returns the index of the minimal element to the right of position 'start',
     * where cards are ordered first by suit, and within the same suit, by rank.
     *
     * @param start first index at which to look
     * @return index of the smallest element to the right of 'start'
     */
    private int findIndexOfMin(int start) {
        int indexOfMin = start;
        for (int j = start + 1; j < cards.length; ++j) {
            // look at the two cards, the minimal one we've found,
            // and the one at the current position j
            Card min = cards[indexOfMin];
            Card current = cards[j];

            // if current has a lower suit than the current min, then it should come
            // first
            if (current.getSuit().compareTo(min.getSuit()) < 0) {
                indexOfMin = j;
            } else if (current.getSuit().equals(min.getSuit())) {
                // suits are the same, so compare ranks
                if (current.getRank() < min.getRank()) {
                    indexOfMin = j;
                }
            }
            // otherwise, current does not come before min, so ignore it
        }
        return indexOfMin;
    }

    /**
     * Sorts the cards according to their rank, ignoring their suits.
     */
    private void sortNoSuit() {
        // TODO Finish this method.
        //use bubble sort
        Card temp = null;
        for(int i =0;  i<cards.length; i++){
            for(int j =1; j<(cards.length-  i);j++){
                if (cards[j-1].getRank() > cards[j].getRank()){
                    //might be worth using the swap function I wrote
                    temp = cards[j-1];
                    cards[j-1] = cards[j];
                    cards[j]= temp;
                }
            }
        }
    }

    /**
     * Swaps the cards i and j in the hand.
     *
     * @param i the first card to swap
     * @param j the card to swap with
     */
    private void swap(int i, int j) {
        Card c = cards[i];
        cards[i] = cards[j];
        cards[j] = c;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (isFullHouse()) {
            return "a Full House!";
        } else if (isFlush()) {
            if (isStraight()) {
                return "a Straight Flush!";
            } else {
                return "a Flush!";
            }
        } else if (isStraight()) {
            return "a Straight!";
        }
        return "Nothing";
    }

    /**
     * Prints out the suit and rank of each card in the hand in order.
     */
    public void printHand() {
        for (int i = 0; i < 5; i++) {
            System.out.print(cards[i].getSuit() + ":" + cards[i].getRank() + ", ");
        }
        System.out.println();
    }

}