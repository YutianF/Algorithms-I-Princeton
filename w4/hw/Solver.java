package w4.hw;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private static class GameTreeNode implements Comparable<GameTreeNode> {
        private final Board board;
        private final GameTreeNode parent;
        private final boolean twin;

        private final int moves;
        private final int distance;
        private final int priority;
        
        public GameTreeNode(Board board, boolean twin) {
            this.board = board;
            this.parent = null;
            this.twin = twin;
            this.moves = 0;
            this.distance = board.manhattan();
            this.priority = this.moves + this.distance;
        }

        public GameTreeNode(Board board, GameTreeNode parent) {
            this.board = board;
            this.parent = parent;
            this.twin = parent.twin;
            this.moves = parent.moves + 1;
            this.distance = board.manhattan();
            this.priority = this.moves + this.distance;
        }

        /**
         * Using Manhattan() as a tie-breaker helped a lot.
         * Using Manhattan priority, then using Manhattan() to break the tie if two boards tie, and returning 0 if both measurements tie.
         */
        public int compareTo(GameTreeNode that) {
            if (this.priority == that.priority) {
                return Integer.compare(this.distance, that.distance);
            } else {
                return Integer.compare(this.priority, that.priority);
            }
        }

        public Board getBoard() {
            return this.board;
        }

        public GameTreeNode getParent() {
            return this.parent;
        }

        public boolean isTwin() {
            return this.twin;
        }

        public int getMoves() {
            return this.moves;
        }


    }


    private int moves;
    private boolean solvable;
    private Iterable<Board> solution;


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        cache(initial);

    }

    private void cache(Board initial) {
        MinPQ<GameTreeNode> pq = new MinPQ<>();

        pq.insert(new GameTreeNode(initial, false));
        pq.insert(new GameTreeNode(initial.twin(), true));

        GameTreeNode node = pq.delMin();
        Board b = node.getBoard();

        while (!b.isGoal()) {
            for (Board neighbor : node.getBoard().neighbors()) {
                if (node.getParent() == null || !neighbor.equals(node.getParent().getBoard())) {
                    pq.insert(new GameTreeNode(neighbor, node));
                }
            }
            node = pq.delMin();
            b = node.getBoard();
        }

        this.solvable = !node.isTwin();

        if (!this.solvable) {
            this.moves = -1;
            this.solution = null;
        } else {
            this.moves = node.getMoves();
            List<Board> list = new ArrayList<Board>();
            while (node != null) {
                list.add(0, node.getBoard());
                node = node.getParent();
            }
            this.solution = list;
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return this.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return this.solution;
    }

    // test client (see below) 
    public static void main(String[] args) {
        java.io.File file = new java.io.File(args[0]);

        // create initial board from file
        In in = new In(file);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                tiles[i][j] = in.readInt();
            }
                
        Board initial = new Board(tiles);
    
        // solve the puzzle
        Solver solver = new Solver(initial);
    
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}