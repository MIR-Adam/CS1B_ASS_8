class Card {
	// suit enum for houses
	public enum Suit {
		clubs, diamonds, hearts, spades
	}

	// private member values
	private char value;
	private Suit suit;
	private boolean errorFlag;

	// private static final member values
	private static final char DEFAULT_VALUE = 'A';
	private static final Suit DEFAULT_SUIT = Card.Suit.spades;

	// accessors
	public Suit getSuit() {
		return suit;
	}

	public char getValue() {
		return value;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}

	// Default Constructor
	public Card() {
		value = DEFAULT_VALUE;
		suit = DEFAULT_SUIT;
		errorFlag = false;
	}

	// Constructor Overloader
	public Card(char value, Suit suit) {
		set(value, suit);
	}

	// boolean if/else check for valid parameters, if false it will trigger an
	// errorFlag
	public boolean set(char value, Suit suit) {
		if (isValid(value, suit)) {
			errorFlag = false;
			this.value = value;
			this.suit = suit;
			return true;
		} else {
			errorFlag = true;
			this.value = value;
			this.suit = suit;
			return false;
		}
	}

	// private boolean checks for ErrorFlag of Char value
	private static boolean isValid(char value, Suit suit) {
		// check for illegal characters
		if (!Character.isLetterOrDigit(value))
			return false;
		// improper value check
		value = Character.toUpperCase(value);
		if (value != 'A' && value != 'J' && value != 'Q' && value != 'K' && value != 'T'
				&& (value > '9' || value < '2')) {
			return false;
		} else {
			return true;
		}
	}

	// toString stringizer
	public String toString() {
		if (errorFlag == true)
			return "ERRORFLAG: INVALID";

		String returnVal = value + " of ";
		if (suit == Card.Suit.clubs) {
			returnVal += "clubs";
		}
		if (suit == Card.Suit.diamonds) {
			returnVal += "diamonds";
		}
		if (suit == Card.Suit.hearts) {
			returnVal += "hearts";
		}
		if (suit == Card.Suit.spades) {
			returnVal += "spades";
		}
		return returnVal;
	}

	// boolean check returns true if the card is equal to the same
	public boolean equals(Card card) {
		if (this.suit == card.suit && this.errorFlag == card.errorFlag && this.value == card.value)
			return true;

		return false;
	}

	// to existing members, add:

	// for sort
	protected static char[] valueRanks = { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A' };
	protected static Suit[] suitRanks = { Suit.clubs, Suit.diamonds, Suit.hearts, Suit.spades };
	protected static final int NUM_VALS = 13;

	// sort member methods
	public int compareTo(Card other) {
		if (this.value == other.value)
			return (getSuitRank(this.suit) - getSuitRank(other.suit));

		return (getValueRank(this.value) - getValueRank(other.value));
	}

	public static int getSuitRank(Suit st) {
		int k;

		for (k = 0; k < 4; k++)
			if (suitRanks[k] == st)
				return k;

		// should not happen
		return 0;
	}

	public static int getValueRank(char val) {
		int k;

		for (k = 0; k < NUM_VALS; k++)
			if (valueRanks[k] == val)
				return k;

		// should not happen
		return 0;
	}
}
