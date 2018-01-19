
public class BlankTile implements SlidingTile {

	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public boolean equals(Object obj) {
			return obj instanceof BlankTile;					
	}
	
	@Override 
	public int hashCode() {
		return 0;
	}

	@Override
	public int getNumber() {
		return 0;
	}
}
