import java.util.Arrays;

/**
 * represenation of a possible move
 * @author Lasse
 *
 */
public class Move {
	private int[] _blankPosition;
	private Direction _dir;
	private OverallBox _box;
	
	public Move(Direction dir, OverallBox box) {
		_dir = dir;
		_box = box;
		_blankPosition = box.getBlankTilePosition();
	}
	
	public String toString() {
		return  _dir.toString();
	}
	
	public Direction getDirection() {
		return _dir;
	}
	

	
	private int[] getBlankPosition() {
		return _blankPosition;
	}

	public boolean equals(Move m) {
		return _dir.equals(m.getDirection()) && Arrays.equals(_blankPosition, m.getBlankPosition());
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Move) {
			Move m = (Move) obj;
			return equals(m);
		}
		else return false;
	}
}
