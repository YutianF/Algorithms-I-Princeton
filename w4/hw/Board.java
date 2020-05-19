/**
 * reference: https://zhuanlan.zhihu.com/p/119281710
 */
package w4.hw;

import java.util.Iterator;
import java.util.NoSuchElementException;
// import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.In;


public class Board {

    private final int[][] grids;
    private final int size;
    private int blkRow, blkCol;
    

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.grids = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.grids[i][j] = tiles[i][j];
                if (this.grids[i][j] == 0) {
                    this.blkRow = i;
                    this.blkCol = j;
                }
            }
        }
    }
                                           
    // string representation of this board
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.size);
        res.append("\n");

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                res.append(grids[i][j]);
                res.append(" ");
            }
            res.append("\n");
        }
        return res.toString();

    }

    // board dimension n
    public int dimension() {
        return this.size;
    }

    private int target(int i, int j) {
        if (i == this.size - 1 && j == this.size - 1)
            return 0;
        else 
            return i * this.size + j + 1;
    }

    // number of tiles out of place
    public int hamming() {
        int outOfPlace = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grids[i][j] == 0) continue;
                else if (this.grids[i][j] != target(i, j)) {
                    outOfPlace++;
                }
            }
        }

        return outOfPlace;

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int mhtDis = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grids[i][j] == 0) continue;
                else if (this.grids[i][j] != target(i, j)) {
                    int targetRow = (grids[i][j] -1) / this.size;
                    int targetCol = grids[i][j] % this.size - 1;
                    targetCol = (targetCol + this.size) % this.size; // avoid -1
                    // System.out.printf("%d %d %d\n", grids[i][j], targetRow, targetCol);
                    mhtDis += Math.abs(targetRow - i) + Math.abs(targetCol - j);
                }
            }
        }
        return mhtDis;

    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0 ? true : false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        // The equals() method is inherited from java.lang.Object, so it must obey all of Java’s requirements.
        if (y == null) return false;
        if (this == y) return true;
        if (this.getClass() != y.getClass()) return false;
        
        Board that = (Board) y;
        if (this.size != that.size)
            return false;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grids[i][j] != that.grids[i][j])  
                    return false;
            }
        }
        return true;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new NeighborsIterable();
    }

    private class NeighborsIterable implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return new NeighborsIterator();
        }
        
    }

    // 将 (x1, y1) 与 (x2, y2) 交换
    private int[][] neighborGrids(int x1, int y1, int x2, int y2) {
        int[][] nbgrids = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                nbgrids[i][j] = this.grids[i][j];
            }
        }
        swap(nbgrids, x1, y1, x2, y2);
        return nbgrids;
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
    }

    private class NeighborsIterator implements Iterator<Board> {
        private Board[] neighbors;
        private int neighborNum;
        private int i = 0;

        
        public NeighborsIterator() {
            generateNeighbors();
        }

        private void generateNeighbors() {
            if ( (blkRow == 0 || blkRow == size - 1) 
                    && (blkCol == 0 || blkCol == size - 1) ) {
                neighborNum = 2;
            } else if ( blkRow == 0 || blkRow == size - 1
                        || blkCol == 0 || blkCol == size - 1 ) {
                neighborNum = 3;
            } else {
                neighborNum = 4;
            }

            neighbors = new Board[neighborNum];

            int idx = 0;
            if (blkRow - 1 >= 0)
                neighbors[idx++] = new Board(neighborGrids(blkRow - 1, blkCol, blkRow, blkCol));
            if (blkRow + 1 < size)
                neighbors[idx++] = new Board(neighborGrids(blkRow + 1, blkCol, blkRow, blkCol));
            if (blkCol - 1 >= 0)
                neighbors[idx++] = new Board(neighborGrids(blkRow, blkCol - 1, blkRow, blkCol));
            if (blkCol + 1 < size)
                neighbors[idx++] = new Board(neighborGrids(blkRow, blkCol + 1, blkRow, blkCol));

        }

        public boolean hasNext(){
            return i < neighborNum;

        }

        public void remove() {
            throw new UnsupportedOperationException();

        }

        public Board next() {
            if (!hasNext()) throw new NoSuchElementException();
            return neighbors[i++];
        }

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int x1, x2, y1, y2;
        x1 = x2 = y1 = y2 = 0;
        for (int i = 0; i < this.size * this.size - 1; i++) {
            x1 = i / this.size;
            y1 = i % this.size;
            x2 = (i + 1) / this.size;
            y2 = (i + 1) % this.size;

            if (!( (x1 == x2 && y1 == y2) || (x1 == blkRow && y1 == blkCol) || (x2 == blkRow && y2 == blkCol) ))
                break;

        }
        return new Board(neighborGrids(x1, y1, x2, y2));


    }

    // unit testing (not graded)
    public static void main(String[] args) {
        java.io.File file = new java.io.File(args[0]);
        // System.out.println(file.getAbsolutePath());
        // create initial board from file
        In in = new In(file);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                tiles[i][j] = in.readInt();
            }
         
            
        Board initial = new Board(tiles);

        // System.out.println(initial);
        // System.out.println(initial.twin());

        // System.out.println(initial.hamming());
        System.out.println(initial.manhattan());

        // Iterable<Board> nbs = initial.neighbors();
        // for (Board b : nbs) {
        //     System.out.println(b);
        // }
        
    
    }

}