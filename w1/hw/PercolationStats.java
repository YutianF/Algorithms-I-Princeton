package w1.hw;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] fractions; // opensite fraction in each experiment
    private final int nTrials;
    private final int nGrid;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) 
            throw new IllegalArgumentException();
        else {
            this.fractions = new double[trials];
            this.nTrials = trials;
            this.nGrid = n;
    
            startTrial();
        }

    }

    private void startTrial() {
        for (int i = 0; i < this.nTrials; i++) {
            PercolationWithoutBackwash model = new PercolationWithoutBackwash(this.nGrid);
            inner:
            while (true) {
                int row = StdRandom.uniform(this.nGrid) + 1;
                int col = StdRandom.uniform(this.nGrid) + 1;
                // choose a site uniformly among all blocked sites
                if (model.isOpen(row, col)) continue inner;
                else {
                    
                    model.open(row, col);
                    if (model.percolates()) {
                        fractions[i] = ((double) model.numberOfOpenSites())/(this.nGrid*this.nGrid);
                        break inner;
                    }

                }

            }
            // System.out.printf("%d/%d round - fraction:%f\n",i+1, this.nTrials, fractions[i]);

        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
         return mean()-1.96*stddev()/Math.sqrt(this.nTrials);

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+1.96*stddev()/Math.sqrt(this.nTrials);

    }

   // test client (see below)
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats ps = new PercolationStats(n, trials);
       StdOut.printf("mean \t= %f\n", ps.mean());
       StdOut.printf("stddev \t= %f\n", ps.stddev());
       StdOut.printf("95%% confidence interval \t= [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
   }

}