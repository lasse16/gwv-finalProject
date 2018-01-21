/**
 * 
 */

/**
 * @author Lasse
 *
 */
public class StartUp {

	/**Testet die Methoden 
	 */
	public static void main(String[] args) {
		OverallBox box = new OverallBox();
		System.out.println(box.hasSolution());
		System.out.print(box.toString());
		System.out.print(box.getValidMoves());
		box.applyMove(new Move(Direction.UP, box));
		System.out.println(box.isGoal());
		System.out.print(box.toString());
		System.out.print(box.getValidMoves());

	}

}
