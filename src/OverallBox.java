import java.util.ArrayList;
import java.util.List;

/**
 * the representaion of the puzzle box containing all the sliding tiles
 * 
 * @author Lasse Haffke, Mai Tam Do , Joschka Eickhoff
 *
 */
public class OverallBox {
	// the two dimensional array containing all the sliding tiles
	private SlidingTile[][] _puzzleBox;

	// position of the blank tile
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
	 * checks for the number
	 * 
	 * @param obj
	 *            the looked for tile and number
	 * @return true if it contains the number
	 */
	private boolean containsNumber(SlidingTile obj) {
		return findSlidingTile(obj) != null;
	}

	/**
	*@see this.containsNumber
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
		int numberMisplacedPairs = 0;

		List<SlidingTile> horizontalAlignment = new ArrayList<SlidingTile>();
		for (SlidingTile[] st : _puzzleBox) {
			for (SlidingTile s : st) {
				horizontalAlignment.add(s);
			}
		}

		// TODO hier look for unsorted pairs
		// https://zeroturnaround.com/rebellabs/java-8-explained-applying-lambdas-to-java-collections/

		return ((numberMisplacedPairs + BlankZeile) & 1) == 0;
	}

	/**
	 * checks if the box is in the goal state by checking the numer on each tile
	 * @return true if in goal state
	 */
	public boolean isGoal() {
		for (int i = 0; i < 15; i++) {
			if (_puzzleBox[i / 4][i % 4].getNumber() != i + 1)
				return false;
		}
		return true;
	}

	/**
	 * gets an array with the line and column of the blank tile
	 * @return the position of the blank tile
	 */
	public int[] getBlankTilePosition() {
		int[] position = { BlankZeile, BlankSpalte };
		return position;
	}

	/**
	 * returns the moves, validated by the position of the blank tile
	 * meaning no edge cases
	 * @return  a list of possible directions
	 */
	public List<Move> getValidMoves() {
		List<Move> moves = new ArrayList<Move>();

		if (BlankZeile > 0)
			moves.add(new Move(Direction.UP, this));
		if (BlankZeile < _puzzleBox.length - 1)
			moves.add(new Move(Direction.DOWN, this));
		if (BlankSpalte > 0)
			moves.add(new Move(Direction.LEFT, this));
		if (BlankSpalte < _puzzleBox[0].length - 1)
			moves.add(new Move(Direction.RIGHT, this));

		return moves;
	}

	/**
	 * executes a move, if it is valid.
	 * @param move the move to be executed
	 * @return true if valid and successful
	 */
	public boolean applyMove(Move move) {
		if (isValidMove(move)) {
			SlidingTile temp;
			switch (move.getDirection()) {
			case DOWN:
				temp = _puzzleBox[BlankZeile + 1][BlankSpalte];
				_puzzleBox[BlankZeile + 1][BlankSpalte] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankZeile += 1;
				return true;
			case LEFT:
				temp = _puzzleBox[BlankZeile][BlankSpalte - 1];
				_puzzleBox[BlankZeile][BlankSpalte - 1] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankSpalte -= 1;
				return true;

			case RIGHT:
				temp = _puzzleBox[BlankZeile][BlankSpalte + 1];
				_puzzleBox[BlankZeile][BlankSpalte + 1] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankSpalte += 1;
				return true;

			case UP:
				temp = _puzzleBox[BlankZeile - 1][BlankSpalte];
				_puzzleBox[BlankZeile - 1][BlankSpalte] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankZeile -= 1;
				return true;

			default:
				return false;
			}
		}

		return false;
	}

	/**
	 * allows for a list of moves to be executed
	 * @param moves the moves to be executed
	 * @return true if all moves succeeded
	 */
	public boolean applyMultipleMoves(List<Move> moves) {
		for (Move m : moves) {
			boolean finished = applyMove(m);
			if (!finished)
				return false;
		}
		return true;
	}

	/**
	 * checks if a move isvalid by looking for it in the getValidMoves
	 * @param m the move to be checked for
	 * @return true if it is a valid move
	 */
	public boolean isValidMove(Move m) {
		return getValidMoves().contains(m);
	}

}
