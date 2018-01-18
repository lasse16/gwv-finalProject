/**
 * Implementation of a numbered Tile
 * @author Lasse
 *
 */
public class NumberTile implements SlidingTile {
	private final int _number;

	public NumberTile(int number) {
		_number = number;
	}

	@Override
	public String toString() {
		return "" + _number;
	}

	public boolean equals(NumberTile obj) {
		return _number == obj.getNumber();
	}
	/**
	 * returns the pictured number
	 * @return the number
	 */
	private int getNumber() {
		return _number;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NumberTile) {
			return equals((NumberTile) obj);	
		}else return false;			
	}
	
	@Override 
	public int hashCode() {
		return _number;
	}
	
}
