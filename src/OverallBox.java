import java.util.ArrayList;
import java.util.List;

/**
 * the representaion of the puzzle box containing all the sliding tiles
 * 
 * @author Lasse
 *
 */
public class OverallBox {
	private SlidingTile[][] _puzzleBox;
	private int BlankZeile;
	private int BlankSpalte;

	/**
	 * intializing the puzzle box
	 */
	public OverallBox() {
		// The box [zeile][spalte]
		_puzzleBox = new SlidingTile[4][4];
		fillPuzzleBox();
	}

	/**
	 * filling the puzzle box with the numbers from 1 to 15, and a blank tile in the
	 * goal order.
	 */
	private void fillPuzzleBox() {
		for (int i = 0; i < 15; i++) {
			_puzzleBox[i / 4][i % 4] = new NumberTile(i + 1);
		}
		_puzzleBox[3][3] = new BlankTile();
		BlankSpalte = BlankZeile = 3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String t = "";
		for (SlidingTile[] st : _puzzleBox) {
			for (SlidingTile s : st) {
				t = t + s.toString() + "|";
			}
			t = t + "\n";
		}
		return t;
	}

	/**
	 * gets the object for the sliding tile
	 * 
	 * @param the
	 *            sliding tile, which equivalent is searched
	 * @return the saved sliding tile
	 */
	public SlidingTile findSlidingTile(SlidingTile obj) {
		for (SlidingTile[] st : _puzzleBox) {
			for (SlidingTile s : st) {
				if (s.equals(obj))
					return s;
			}
		}
		return null;
	}

	/**
	 * gets the object for the sliding tile with the number put in
	 * 
	 * @param obj
	 *            the number looked for
	 * @return the saved sliding tile
	 */
	public SlidingTile findSlidingTile(int obj) {
		for (SlidingTile[] st : _puzzleBox) {
			for (SlidingTile s : st) {
				if (s.equals(new NumberTile(obj)))
					return s;
			}
		}
		return null;
	}

	/**
	 * gets the position of the sliding tile FIXME sucht bis jetzt nur nach
	 * numberTile
	 * 
	 * @param obj
	 *            the number on the looked for tile
	 * @return int[] with the x and y position of the number
	 */
	public int[] findSlidingTilePosition(int obj) {
		if (containsNumber(obj)) {
			int spalte = 0;
			int zeile = 0;
			int[] position = new int[2];
			for (SlidingTile[] st : _puzzleBox) {
				position[0] = spalte;
				for (SlidingTile s : st) {
					position[1] = zeile;
					if (s.equals(new NumberTile(obj)))
						return position;
					zeile++;
				}
				spalte++;
			}
		}
		return null;
	}

	/**
	 * gets the position of the sliding tile FIXME sucht bis jetzt nur nach
	 * numberTile
	 * 
	 * @param obj
	 *            the looked for tile
	 * @return int[] with the x and y position of the number
	 */
	public int[] findSlidingTilePosition(SlidingTile obj) {
		if (containsNumber(obj)) {
			int spalte = 0;
			int zeile = 0;
			int[] position = new int[2];
			for (SlidingTile[] st : _puzzleBox) {
				position[0] = spalte;
				for (SlidingTile s : st) {
					position[1] = zeile;
					if (s.equals(obj))
						return position;
					zeile++;
				}
				spalte++;
			}
		}
		return null;
	}

	/**
	 * @param obj
	 * @return
	 */
	private boolean containsNumber(SlidingTile obj) {
		return findSlidingTile(obj) != null;
	}

	/**
	 * @param obj
	 * @return
	 */
	private boolean containsNumber(int obj) {
		return findSlidingTile(obj) != null;
	}

	/**
	 * FIXME über PArität lösen wie in https://de.wikipedia.org/wiki/15-Puzzle
	 * beschrieben
	 * 
	 * @return
	 */
	public boolean hasSolution() {
		return true;
	}


	/**
	 * Method for swapping with the upper tile (lower zeile-value)
	 * @return true if it swapped the tiles
	 */
	public boolean swapUp() {
		if (BlankZeile > 0) {
			SlidingTile temp = _puzzleBox[BlankZeile - 1][BlankSpalte];
			_puzzleBox[BlankZeile - 1][BlankSpalte] = new BlankTile();
			_puzzleBox[BlankZeile][BlankSpalte] = temp;
			BlankZeile -= 1;
			return true;
		}
		return false;
	}
	
	public boolean isGoal() {
		for (int i = 0; i < 15; i++) {
			if(_puzzleBox[i / 4][i % 4].getNumber() != i + 1) return false;
		}
		return true;
	}
	
	public int[] getBlankTilePosition() {
		int[] position = {BlankZeile,BlankSpalte};
		return  position;
	}
	
	public List<Move> getValidMoves(){
		List<Move> moves = new ArrayList<Move>();
		
		if (BlankZeile > 0) moves.add(new Move(Direction.UP));
		if (BlankZeile < _puzzleBox.length-1) moves.add(new Move(Direction.DOWN));
		if (BlankSpalte > 0) moves.add(new Move(Direction.LEFT));
		if (BlankSpalte < _puzzleBox[0].length-1) moves.add(new Move(Direction.RIGHT));
		
		return moves; 
	}
	


}
