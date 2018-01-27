import java.util.PriorityQueue;

public class Searcher {
	PriorityQueue<Path> _frontier ;
	private OverallBox _feld;

	public Searcher(OverallBox box) {
		_feld = box;
		_frontier = new PriorityQueue<Path>(new HeuristikComparator(_feld));
	}
	
	public Path startSearch() {
		_frontier.add(new Path());
		
		while(!_frontier.isEmpty()) {
			Path current = _frontier.poll();
			_feld.applyMove(current.getLast());
			
			if(_feld.isGoal()) return current;
			else {
				for(Move m : _feld.getValidMoves()) {
					Path nextMove = current.clone();
					nextMove.add(m);
					_frontier.add(nextMove);					
				}
			}
		}
		
		return null;
	}
}
