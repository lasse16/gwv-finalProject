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
		
		System.out.print(box.toString());
		System.out.println(box.getValidMoves());
		box.applyMove(new Move(Direction.UP, box));
		box.applyMove(new Move(Direction.UP, box));
		box.applyMove(new Move(Direction.DOWN, box));
		System.out.print(box.toString());
		
		Searcher test = new Searcher(box);
		System.out.println(test.startSearch());
		
		System.out.print(box.toString());

	}

}
