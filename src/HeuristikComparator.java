import java.util.Comparator;

public class HeuristikComparator implements Comparator<Path> {

	SlidingTile[][] _feld;

	public HeuristikComparator(OverallBox feld) {
		_feld = feld.getFeld();
	}

	@Override
	public int compare(Path o1, Path o2) {
		int hValueP1 = getHeuristicValue(o1);
		int hValueP2 = getHeuristicValue(o2);
		int fValue1 = hValueP1 + o1.length();
		int fValue2 = hValueP2 + o2.length();

		return fValue1 - fValue2;
	}

	private int getHeuristicValue(Path o1) {
		return (getLinearConflicts() * 2) + manhattanDistance();
	}

	private int getLinearConflicts() {
		return getHorizontalConflicts() + getVerticalConflicts();
	}

	private int getHorizontalConflicts() {
		int conflicts = 0;
		for (int i = 0; i < _feld.length; i++) {
			short max = -1;
			for (int j = 0; j < _feld[0].length; j++) {
				SlidingTile current = _feld[i][j];
				if ((current.getNumber() != 0) && current.getNumber() -1 / _feld.length == i) {
					if (current.getNumber() > max) {
						max = (short) current.getNumber();
					} else {
						conflicts++;
					}
				}
			}
		}

		return conflicts;
	}

	private int getVerticalConflicts() {
		int conflicts = 0;
		
			
			for (int j = 0; j < _feld[0].length; j++) {
				short max = -1;
				for (int i = 0; i < _feld.length; i++) {
				SlidingTile current = _feld[i][j];
				if ((current.getNumber() != 0) && current.getNumber() -1 / _feld.length == j) {
					if (current.getNumber() > max) {
						max = (short) current.getNumber();
					} else {
						conflicts++;
					}
				}
			}
		}

		return conflicts;
	}
	

	private int manhattanDistance() {
		int manhattanDistance = 0;
		for (int i = 0; i < _feld.length; i++) {
			for (int j = 0; j < _feld[0].length; j++) {
				SlidingTile current = _feld[i][j];
				int properNumber = (i * j) + j + 1;
				if (!(current.getNumber() == properNumber) && !current.equals(new BlankTile())) {
					manhattanDistance += Math.abs(current.getNumber() - properNumber);
				}
			}
		}
		return manhattanDistance;
	}

}
