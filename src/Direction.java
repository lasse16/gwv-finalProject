/**
 * The possible Directions of a tile swap
 * @author Lasse Haffke, Mai TAm Do, Joschka Eickhoff
 *
 */
public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;
	
	public static Direction getOpposite(Direction dir) {
		switch(dir) {
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case UP:
			return DOWN;
		default:
			return null;
		}
	}
}
