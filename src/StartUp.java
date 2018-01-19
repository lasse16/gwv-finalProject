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
		System.out.println(box.isGoal());
		System.out.print(box.toString());
		box.swapUp();
		System.out.println(box.isGoal());
		System.out.print(box.toString());

	}

}
