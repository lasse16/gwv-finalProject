import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Searcher {
	PriorityQueue<Path> _frontier;
	private OverallBox _feld;
	private boolean firstTry;

	public Searcher(OverallBox box) {
		_feld = box;
		_frontier = new PriorityQueue<Path>(new HeuristikComparator(_feld));
		firstTry = true;
	}

	public Path startSearch() {
		_frontier.add(new Path());

		while (!_frontier.isEmpty()) {
			Path current = _frontier.poll();

			_feld.applyMove(current.getLast());

			if (_feld.isGoal())
				return current;
			else {
				List<Move> candidates = _feld.getValidMoves();
				Collections.shuffle(candidates);
				
				for (Move m : candidates) {
					// TODO CycleCheck
					if (!CycleCheck(m , current.getLast())) {

						Path nextMove = current.clone();
						nextMove.add(m);
						_frontier.add(nextMove);
					}
				}
			}
			System.out.println(_frontier);
		}

		return null;		
	}

	/**
	 * checks if the next move is the opposite of the first
	 * returns true if they are opposites
	 */
	private boolean CycleCheck(Move m, Move last) {
		if(last == null) {
			return false;
		}else {
			return Direction.getOpposite(m.getDirection()).equals(last.getDirection());
		}
	}
}
