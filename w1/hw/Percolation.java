package w1.hw;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * virtual top and bottom node will bring backwash problem
 * backwash problem: 有了一个虚拟底层位置，如果网格已经渗透了。这时判断其中一个位置（与底部相连，并且底部是打开的，与虚拟底层位置相连）是否满（即与虚拟顶层位置是相连的），那么不管这个位置是不是真的满，结果总会是满的。
 * 因为网格已经渗透了，那么虚拟顶层位置与虚拟底层位置总是相连的，而这个位置与虚拟顶层位置是相连的，这时候再判断它是不是满（与虚拟顶层位置相连），结果当然总是为真。
 * 
 **/ 
public class Percolation {
    private boolean[][] isOpen;
    private int nOpenSite;
    private final WeightedQuickUnionUF uf;
    private final int top, bottom;
    private final int nGrid; 

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n <= 0)  
            throw new IllegalArgumentException();
        else{
            this.isOpen = new boolean[n][n]; // initiate with default value - false
            this.uf = new WeightedQuickUnionUF(n*n+2);
            this.top = 0;
            this.bottom = n*n+1;
            this.nGrid = n;
            this.nOpenSite = 0;
        }
    }

    // return the index of UF for site (row, col)
    private int getIndex(int row, int col) {
        return (row-1)*this.nGrid+col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(row < 0 || row > this.nGrid || col <0 || col > this.nGrid) 
            throw new IllegalArgumentException();
        else{
            int node = getIndex(row, col);
            if (!isOpen(row, col)){
                this.isOpen[row-1][col-1] = true;
                this.nOpenSite += 1;
                if(row == 1) this.uf.union(this.top, node);
                if(row == this.nGrid) this.uf.union(node,this.bottom);
                if(row > 1 && isOpen(row-1,col)) this.uf.union(node, getIndex(row-1, col));
                if(col > 1 && isOpen(row,col-1)) this.uf.union(node, getIndex(row, col-1));
                if(row < this.nGrid && isOpen(row+1, col)) this.uf.union(node, getIndex(row+1, col));
                if(col < this.nGrid && isOpen(row, col+1)) this.uf.union(node, getIndex(row, col+1)); 
            }
        }

    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row < 0 || row > this.nGrid || col <0 || col > this.nGrid) 
            throw new IllegalArgumentException();
        else 
            return this.isOpen[row-1][col-1];

    }

    // is the site (row, col) full?
    // may display backwash problem
    public boolean isFull(int row, int col) {
        if(row < 0 || row > this.nGrid || col <0 || col > this.nGrid) 
            throw new IllegalArgumentException();
        else
            return this.uf.find(this.top) == this.uf.find(getIndex(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.nOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        
        return this.uf.find(this.top) == this.uf.find(this.bottom);

    }


    // test client (optional)
    public static void main(final String[] args) {
        System.out.println(args[0]);

    }
}