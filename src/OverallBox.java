
public class OverallBox {
	private SlidingTile[][] _puzzleBox;
	
	public OverallBox() {
		_puzzleBox = new SlidingTile[4][4];
		fillPuzzleBox();
	}

	private void fillPuzzleBox() {
		for(int i = 0;i<15;i++) {
			_puzzleBox[i%4][i/4] = new NumberTile(i+1); 
		}
		_puzzleBox[4][4] = new BlankTile();	
	}
	
	@Override
	public String toString() {
		String t = "";
		for(SlidingTile[] st : _puzzleBox) {
			for(SlidingTile s  : st) {
				t = t + s.toString();
			}	
		}
		return t;
	}
}
