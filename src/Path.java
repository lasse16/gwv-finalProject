import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implements a path as an array-list of moves
 * 
 * @author Lasse Haffke, Mai Tam Do,l Joschka Eickhoff
 *
 */

public class Path implements Iterable<Move>, Cloneable, Comparable<Path>{
	private List<Move> _path;



	
		public Path() {
			_path = new ArrayList<Move>();
		}

		/**
		 * returns the list of moves in the path
		 * 
		 * @return the list
		 */
		public List<Move> getPath() {
			return _path;
		}

		/**
		 * Adds a move to the path 
		 * 
		 * @param move the added move
		 */
		public void add(Move move) {
			_path.add(0, move);
		}

		@Override
		public String toString() {
			String rep = "";
			for (Move k : _path) {
				rep = rep + k.toString() + "-";
			}
			return rep;
		}

		/**
		 * checks two path for their identity
		 * 
		 * @param pfad the path 
		 *            
		 * @return true if they are equal
		 */
		public boolean equals(Path pfad) {
			for (int i = 0; i < _path.size(); ++i) {
				if (!pfad.getPath().get(i).equals(_path.get(i))) {
					return false;
				}

			}
			return true;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Path) {
				equals(obj);
			}
			return false;
		}

		/**
		 * returns the number of Moves in a path
		 * 
		 * @return number of moves
		 */
		public int length() {
			return _path.size();
		}

		/**
		 * returns true if a path contains a move
		 * 
		 * @param the checked for move
		 * @return true if the path contains the move
		 */
		public boolean contains(Move m) {
			return _path.contains(m);
		}

		/**
		 * Returns the last node of a path, unless it's empty then null
		 * 
		 * @return the node or null
		 */
		public Move getLast() {
			if(!_path.isEmpty()) return _path.get(0);
			else return null;			
		}
		
		@Override
		public Iterator<Move> iterator() {
			return _path.iterator();
		}

		@Override
		public Path clone() {
			Path copy = new Path();
			for(Move m: _path) {
				copy.add(m);
			}
			return copy;
		}

		@Override
		public int compareTo(Path o) {
			if (this.length() < o.length())
				return -1;
			else if (this.length() > o.length())
				return 1;
			else
				return 0;
		}
	}


