import java.util.ArrayList;
import java.util.Collections;
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

	private List<Watcher> _watchers = new ArrayList<Watcher>();

	/**
	 * intializing the puzzle box
	 */
	public OverallBox() {
		// The box [zeile][spalte]
		_puzzleBox = new SlidingTile[4][4];
		fillPuzzleBox();
	}
	
	public OverallBox(SlidingTile[][] box) {
		// The box [zeile][spalte]
		_puzzleBox = new SlidingTile[4][4];
		_puzzleBox = box;
		int[] bPosition = findBlankPosition();
		BlankSpalte = bPosition[0];
		BlankZeile = bPosition[1];
	}
	
	public OverallBox(int [] box) {
		_puzzleBox = new SlidingTile[4][4];
		for(int i =0;i<_puzzleBox.length;i++) {
			for(int j = 0;j < _puzzleBox[0].length;j++) {
				_puzzleBox[j][i] = new NumberTile(box[i*j+i]);
			}
		}
		_puzzleBox[_puzzleBox.length-1][_puzzleBox[0].length-1] = new BlankTile();
		BlankSpalte = BlankZeile = 3;
	}
	
	private int[] findBlankPosition() {
		int spalte = 0;
		int zeile = 0;
		int[] position = new int[2];
		for (SlidingTile[] st : _puzzleBox) {
			position[0] = spalte;
			for (SlidingTile s : st) {
				position[1] = zeile;
				if (s.equals(new BlankTile()))
					return position;
				zeile++;
			}
			spalte++;
		}
		return position;
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
	 * FIXME test https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
	 * @return
	 */
	public boolean hasSolution() {
		int parity = 0;
		int gridWidth = _puzzleBox.length;
		int row = 0; // the current row we are on
		int blankRow = 0; // the row with the blank tile
		int[] puzzle = new int[16];

		for (int i = 0; i < _puzzleBox.length; i++) {
			for (int j = 0; j < _puzzleBox[0].length; j++) {
				puzzle[i * j + i] = _puzzleBox[i][j].getNumber();
			}
		}
		for (int k = 0; k < puzzle.length; k++) {
			if (k % gridWidth == 0) { // advance to next row
				row++;
			}
			if (puzzle[k] == 0) { // the blank tile
				blankRow = row; // save the row on which encountered
				continue;
			}
			for (int j = k + 1; j < _puzzleBox.length; j++) {
				if (puzzle[k] > puzzle[j] && puzzle[j] != 0) {
					parity++;
				}
			}
		}

		if (gridWidth % 2 == 0) { // even grid
			if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
				return parity % 2 == 0;
			} else { // blank on even row; counting from bottom
				return parity % 2 != 0;
			}
		} else { // odd grid
			return parity % 2 == 0;
		}
	}

	/**
	 * checks if the box is in the goal state by checking the number on each tile
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
	public OverallBox applyMove(Move move) {
		if (isValidMove(move)) {
			SlidingTile temp;
			
			switch (move.getDirection()) {
			case DOWN:
				temp = _puzzleBox[BlankZeile + 1][BlankSpalte];
				_puzzleBox[BlankZeile + 1][BlankSpalte] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankZeile += 1;
				notifyWatchers();
				return new OverallBox(_puzzleBox);
			case LEFT:
				temp = _puzzleBox[BlankZeile][BlankSpalte - 1];
				_puzzleBox[BlankZeile][BlankSpalte - 1] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankSpalte -= 1;
				notifyWatchers();
				return new OverallBox(_puzzleBox);

			case RIGHT:
				temp = _puzzleBox[BlankZeile][BlankSpalte + 1];
				_puzzleBox[BlankZeile][BlankSpalte + 1] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankSpalte += 1;
				notifyWatchers();
				return new OverallBox(_puzzleBox);

			case UP:
				temp = _puzzleBox[BlankZeile - 1][BlankSpalte];
				_puzzleBox[BlankZeile - 1][BlankSpalte] = new BlankTile();
				_puzzleBox[BlankZeile][BlankSpalte] = temp;
				BlankZeile -= 1;
				notifyWatchers();
				new OverallBox(_puzzleBox);

			default:
				return null;
			}
		}
		
		return null;
	}

	private void notifyWatchers() {
		for(Watcher w: _watchers) {
			w.update();
		}
		
	}

	/**
	 * allows for a list of moves to be executed
	 * @param moves the moves to be executed
	 * @return true if all moves succeeded
	 */
	public boolean applyMultipleMoves(List<Move> moves) {
		for (Move m : moves) {
			boolean finished = applyMove(m) != null;
			if (!finished)
				return false;
		}
		return true;
	}

	/**
	 * checks if a move is valid by looking for it in the getValidMoves
	 * @param m the move to be checked for
	 * @return true if it is a valid move
	 */
	public boolean isValidMove(Move m) {
		return getValidMoves().contains(m);
	}

	public SlidingTile[][] getFeld() {
		return _puzzleBox;
	}
	
	/**
	 * returns the tile at the position defined by 1 integer
	 * @param position
	 * @return
	 */
	public SlidingTile getTileByPosition(int position) {
		return _puzzleBox[position/_puzzleBox.length][position%_puzzleBox[0].length];
	}

	public void addWatcher(Watcher watcher) {
		_watchers.add(watcher);	
	}

	public OverallBox shuffle() {
		for(int i= 0; i<10;i++) {
			List<Move> moves = getValidMoves();
			Collections.shuffle(moves);
			applyMove(moves.get(0));
		}
		notifyWatchers();
		return this;
	}

}
