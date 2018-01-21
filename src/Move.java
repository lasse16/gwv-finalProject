/**
 * represenation of a possible move
 * @author Lasse
 *
 */
public class Move {
	//private SlidingTile _movedTile;
	private Direction _dir;
	
	public Move(Direction dir) {
		_dir = dir;
		//_movedTile = tile;
	}
	
	public String toString() {
		return  _dir.toString();
	}
}
