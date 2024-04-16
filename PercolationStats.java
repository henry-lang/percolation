public class PercolationStats {
    private int T;
    private double[] thresholds;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        this.T = T;
        this.thresholds = new double[T];

        for (int t = 0; t < T; t++) {
            Percolation p = new Percolation(N);

            while (!p.percolates()) {
                int row = StdRandom.uniformInt(N); // Adding 1 to convert to 1-indexing
                int col = StdRandom.uniformInt(N); // Adding 1 to convert to 1-indexing

                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }

            thresholds[t] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PercolationStats <grid size> <number of experiments>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLow() + ", " + stats.confidenceHigh() + "]");
    }
}