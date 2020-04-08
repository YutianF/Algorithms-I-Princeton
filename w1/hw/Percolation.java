package w1.hw;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] isOpen;
    private final WeightedQuickUnionUF uf;
    private final int top, bottom;
    private final int nGrid; 

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        this.isOpen = new boolean[n][n]; //initiate with default value - false
        this.uf = new WeightedQuickUnionUF(n*n+2);
        this.top = 0;
        this.bottom = n*n+1;
        this.nGrid = n;
    }

    //return the index of UF for site (row, col)
    private int getIndex(int row, int col){
        return (row-1)*this.nGrid+col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        int node = getIndex(row, col);
        if(!isOpen(row, col)){
            this.isOpen[row-1][col-1] = true;
            if(row==1) this.uf.union(this.top, node);
            if(row==this.nGrid) this.uf.union(node,this.bottom);
            if(row>1 && isOpen(row-1,col)) this.uf.union(node, getIndex(row-1, col));
            if(col>1 && isOpen(row,col-1)) this.uf.union(node, getIndex(row, col-1));
            if(row<this.nGrid && isOpen(row+1, col)) this.uf.union(node, getIndex(row+1, col));
            if(col<this.nGrid && isOpen(row, col+1)) this.uf.union(node, getIndex(row, col+1)); 
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        return this.isOpen[row-1][col-1];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return this.uf.find(this.top) == this.uf.find(getIndex(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        int num=0;
        for(int row=1; row<=this.nGrid; row++){
            for(int col=1; col<=this.nGrid; col++){
                if(isOpen(row, col)) num++;
            }
        }
        return num;

    }

    // does the system percolate?
    public boolean percolates(){
        
        return this.uf.find(this.top) == this.uf.find(this.bottom);

    }


    // test client (optional)
    public static void main(final String[] args){
        System.out.println(args[0]);

    }
}