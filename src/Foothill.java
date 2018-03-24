import java.util.LinkedList;
import java.util.ListIterator;

//Adam Creeger
//CS1B- Assignment 8
//2018 Winter Quarter (Instructor: Dr. Baba Kofi Weusijana)

public class Foothill {
	public static void main(String[] args) {
		int k;
		int numCards = 10;

		LinkedList<Card> cardList = new LinkedList<Card>();
		Card cardIndex[] = new Card[numCards];

		// populate card index
		for (int i = 0; i < numCards; i++) {
			cardIndex[i] = generateRandomCard();

			// insert twice into card index
			insert(cardList, cardIndex[i]);
			insert(cardList, cardIndex[i]);
		}

		Card first = new Card('A', Card.Suit.spades);
		Card second = new Card('4', Card.Suit.hearts);
		Card third = new Card('T', Card.Suit.clubs);

		System.out.println("should all be 0:\n");
		System.out.println(first.compareTo(first));
		System.out.println(second.compareTo(second));
		System.out.println(third.compareTo(third));

		System.out.println("\nshould all be < 0:\n");
		System.out.println(second.compareTo(first));
		System.out.println(second.compareTo(third));
		System.out.println(third.compareTo(first));

		System.out.println("\nshould all be > 0:\n");
		System.out.println(first.compareTo(second));
		System.out.println(third.compareTo(second));
		System.out.println(first.compareTo(third));

		System.out.println("\nSome random cards:\n");
		for (k = 0; k < 50; k++) {
			System.out.print(generateRandomCard().toString() + "  ");
		}

		System.out.println(); // new line spacer
		System.out.println(); // new line spacer

		// display initial card list
		System.out.println("Initial card list: ");
		displayList(cardList);

		// remove about half of cards (duplicates of random generated cards will be
		// removed)

		for (int i = 0; i < numCards / 2; i++) {
			while (remove(cardList, cardIndex[i])) {
			}
		}

		System.out.println(); // new line spacer

		// display 'halved' card list
		System.out.println("'Halved' card list: ");
		displayList(cardList);

		System.out.println(); // new line spacer

		System.out.println("Removing all cards from card list: ");

		System.out.println(); // new line spacer

		// remove all cards from card list
		for (int i = 0; i < numCards; i++) {
			while (remove(cardList, cardIndex[i])) {
				System.out.println("Removed: " + cardIndex[i]);
			}
		}

		System.out.println(); // new line spacer
		System.out.println("RemoveAll check:"); 
		System.out.println(); // new line spacer
		
		for (int i = 0; i < numCards; i++) {
			if(removeAll(cardList, cardIndex[i])) {
				System.out.println("Card detected! Removing card: " + cardIndex[i]);
			}
			else
			{
				System.out.println("No card detected! " + cardIndex[i] + " has been removed!");
			}
		}
		System.out.println(); // new line spacer

		// display 'removed' card list
		System.out.println("'Removed' card list: ");

		displayList(cardList);
	}

	// "global" static Foothill methods
	static Card generateRandomCard() {
		// if firstTime = true, use clock to seed, else fixed seed for debugging
		Card.Suit suit;
		char val;

		int suitSelector, valSelector;

		// get random suit and value
		suitSelector = (int) (Math.random() * 4);
		valSelector = (int) (Math.random() * 13);

		// pick suit
		suit = turnIntIntoSuit(suitSelector);
		val = turnIntIntoVal(valSelector);

		return new Card(val, suit);
	}

	// note: this method not needed if we use int for suits instead of enum
	static Card.Suit turnIntIntoSuit(int k) {
		return Card.Suit.values()[k]; //
	}

	static char turnIntIntoVal(int k) {
		String legalVals = "23456789TJQKA";

		if (k < 0 | k >= legalVals.length())
			return '?';
		return legalVals.charAt(k);
	}

	static void displayList(LinkedList<Card> cardList) {

		ListIterator<Card> cardIterator;

		// initialize iterator
		cardIterator = cardList.listIterator();

		System.out.println("Displaying Card List: ");
		while (cardIterator.hasNext()) {
			System.out.println(cardIterator.next());
		}
		System.out.println("Completed Displaying Card List!");
	}

	static void insert(LinkedList<Card> cardList, Card c) {

		ListIterator<Card> cardIterator;

		// initialize iterator
		cardIterator = cardList.listIterator();

		Card nextCompare;

		while (cardIterator.hasNext()) {
			nextCompare = cardIterator.next();
			if (c.compareTo(nextCompare) < 0) {
				cardIterator.previous();
				break;
			}
		}
		cardIterator.add(c);
	}

	static boolean remove(LinkedList<Card> cardList, Card c) {

		ListIterator<Card> cardIterator;

		// initialize iterator
		cardIterator = cardList.listIterator();

		Card nextCompare;

		while (cardIterator.hasNext()) {
			nextCompare = cardIterator.next();
			if (c.compareTo(nextCompare) == 0) {
				cardIterator.remove();
				return true;
			}
		}
		return false;
	}

	static boolean removeAll(LinkedList<Card> cardList, Card c) {

		boolean hasRemovedAll = false;

		while (remove(cardList, c)) 
			hasRemovedAll = true;


		return hasRemovedAll;
	}
}