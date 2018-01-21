/**
 * Implementation of a numbered Tile
 * 
 * @author Lasse Haffke, Mai Tam Do , Joschka Eickhoff
 *
 */
public class NumberTile implements SlidingTile {
	// The enwritten number
	private final int _number;

	public NumberTile(int number) {
		_number = number;
	}

	@Override
	public String toString() {
		return "" + _number;
	}

	/**
	 * return if two NumberTiles are equal
	 * 
	 * @param obj
	 *            the second tile
	 * @return true if their enwritten numbers are equal
	 */
	public boolean equals(NumberTile obj) {
		return _number == obj.getNumber();
	}

	/**
	 * returns the pictured number
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return _number;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NumberTile) {
			return equals((NumberTile) obj);
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return _number;
	}

}
