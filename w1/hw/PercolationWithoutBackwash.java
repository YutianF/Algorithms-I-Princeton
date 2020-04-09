package w1.hw;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationWithoutBackwash {
    private boolean[][] isOpen;
    private boolean[] isConnectTop, isConnectBottom; // 以(row, col) 为root的component是否connectTop/Bottom
    private boolean isPercolation;
    private int nOpenSite;
    private final WeightedQuickUnionUF uf;
    private final int nGrid; 


    // creates n-by-n grid, with all sites initially blocked
    public PercolationWithoutBackwash(int n) {
        if(n <= 0)  
            throw new IllegalArgumentException();
        else{
            this.isOpen = new boolean[n][n]; // initiate with default value - false
            this.isConnectTop = new boolean[n*n];
            this.isConnectBottom = new boolean[n*n];
            this.uf = new WeightedQuickUnionUF(n*n);
            this.nGrid = n;
            this.nOpenSite = 0;
            this.isPercolation = false;
        }
    }

    // return the index of UF for site (row, col)
    private int getIndex(int row, int col) {
        return (row-1)*this.nGrid+col-1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(row < 0 || row > this.nGrid || col <0 || col > this.nGrid) 
            throw new IllegalArgumentException();
        else{
            int node = getIndex(row, col);
            boolean top = false;
            boolean bottom = false; // 用作判断加入该结点后的component是否连接top/bottom 
            if (!isOpen(row, col)){
                this.isOpen[row-1][col-1] = true;
                this.nOpenSite += 1;
                if(row > 1 && isOpen(row-1,col)) {
                    if(this.isConnectTop[this.uf.find(getIndex(row-1, col))] || this.isConnectTop[this.uf.find(getIndex(row, col))])
                        top = true;
                    if(this.isConnectBottom[this.uf.find(getIndex(row-1, col))] || this.isConnectBottom[this.uf.find(getIndex(row, col))])
                        bottom = true;
                    this.uf.union(node, getIndex(row-1, col));
                }
                
                if(col > 1 && isOpen(row,col-1)) {
                    if(this.isConnectTop[this.uf.find(getIndex(row, col-1))] || this.isConnectTop[this.uf.find(getIndex(row, col))])
                        top = true;
                    if(this.isConnectBottom[this.uf.find(getIndex(row, col-1))] || this.isConnectBottom[this.uf.find(getIndex(row, col))])
                        bottom = true;
                    this.uf.union(node, getIndex(row, col-1));
                }
                if(row < this.nGrid && isOpen(row+1, col)) {
                    if(this.isConnectTop[this.uf.find(getIndex(row+1, col))] || this.isConnectTop[this.uf.find(getIndex(row, col))])
                        top = true;
                    if(this.isConnectBottom[this.uf.find(getIndex(row+1, col))] || this.isConnectBottom[this.uf.find(getIndex(row, col))])
                        bottom = true;
                    this.uf.union(node, getIndex(row+1, col));
                }
                if(col < this.nGrid && isOpen(row, col+1)) {
                    if(this.isConnectTop[this.uf.find(getIndex(row, col+1))] || this.isConnectTop[this.uf.find(getIndex(row, col))])
                        top = true;
                    if(this.isConnectBottom[this.uf.find(getIndex(row, col+1))] || this.isConnectBottom[this.uf.find(getIndex(row, col))])
                        bottom = true;
                    this.uf.union(node, getIndex(row, col+1)); 
                }
                if(col==1)  top = true;
                if(col==this.nGrid) bottom = true;
                this.isConnectTop[this.uf.find(getIndex(row, col))]=top;
                this.isConnectBottom[this.uf.find(getIndex(row, col))]=bottom;
                if(top && bottom)   this.isPercolation = true; // 如果该结点的component的root同时连接top/bottom, 浸润
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
            return this.isConnectTop[this.uf.find(getIndex(row, col))];

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.nOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        
        return this.isPercolation;

    }


    // test client (optional)
    public static void main(final String[] args) {
        System.out.println(args[0]);

    }
}