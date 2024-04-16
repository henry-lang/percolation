public class Percolation {
    private static final int FLAG_OPEN = 1;
    private static final int FLAG_TOP = 2;
    private static final int FLAG_BOTTOM = 4;

    private int N;
    private int[] cells;
    private int openSites = 0;
    private boolean percolates = false;

    private WeightedQuickUnionPathCompressionUF unionFind;

    private int cellIndex(int row, int col) {
        return row * N + col;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        this.N = N;
        cells = new int[N * N];
        unionFind = new WeightedQuickUnionPathCompressionUF(N * N);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int newStatus = FLAG_OPEN;
            if (row == 0) {
                newStatus |= FLAG_TOP;
            }
            if (row == N - 1) {
                newStatus |= FLAG_BOTTOM;
            }

            if (col > 0 && isOpen(row, col - 1)) {
                newStatus |= cells[unionFind.find(cellIndex(row, col - 1))];
                unionFind.union(cellIndex(row, col), cellIndex(row, col - 1));
            }
            if (col < N - 1 && isOpen(row, col + 1)) {
                newStatus |= cells[unionFind.find(cellIndex(row, col + 1))];
                unionFind.union(cellIndex(row, col), cellIndex(row, col + 1));
            }
            if (row > 0 && isOpen(row - 1, col)) {
                newStatus |= cells[unionFind.find(cellIndex(row - 1, col))];
                unionFind.union(cellIndex(row, col), cellIndex(row - 1, col));
            }
            if (row < N - 1 && isOpen(row + 1, col)) {
                newStatus |= cells[unionFind.find(cellIndex(row + 1, col))];
                unionFind.union(cellIndex(row, col), cellIndex(row + 1, col));
            }

            if ((newStatus & FLAG_TOP) != 0 && (newStatus & FLAG_BOTTOM) != 0) {
                percolates = true;
            }
            cells[cellIndex(row, col)] = newStatus;
            cells[unionFind.find(cellIndex(row, col))] = newStatus;
            openSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return (cells[cellIndex(row, col)] & FLAG_OPEN) != 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return (cells[unionFind.find(cellIndex(row, col))] & FLAG_TOP) != 0;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    public static void main(String[] args) {
        testOpen();
        testIsFull();
        testNumberOfOpenSites();
        testPercolates();
    }

    private static void testOpen() {
        Percolation percolation = new Percolation(3);
        System.out.println("Testing open method...");
        System.out.println("Is (0, 0) open before opening? " + percolation.isOpen(0, 0));
        percolation.open(0, 0);
        System.out.println("Is (0, 0) open after opening? " + percolation.isOpen(0, 0));
    }

    private static void testIsFull() {
        Percolation percolation = new Percolation(3);
        System.out.println("Testing isFull method...");
        System.out.println("Is (0, 0) full before opening? " + percolation.isFull(0, 0));
        percolation.open(0, 0);
        System.out.println("Is (0, 0) full after opening? " + percolation.isFull(0, 0));
        System.out.println();

        System.out.println("Is (1, 0) full before opening? " + percolation.isFull(1, 0));
        percolation.open(1, 0);
        System.out.println("Is (1, 0) full after opening? " + percolation.isFull(1, 0));
        System.out.println();

        System.out.println("Is (1, 2) full before opening? " + percolation.isFull(1, 2));
        percolation.open(1, 2);
        System.out.println("Is (1, 2) full after opening? " + percolation.isFull(1, 2));
        System.out.println();
    }

    private static void testNumberOfOpenSites() {
        Percolation percolation = new Percolation(3);
        System.out.println("Testing numberOfOpenSites method...");
        System.out.println("Number of open sites before opening: " + percolation.numberOfOpenSites());
        percolation.open(0, 0);
        System.out.println("Number of open sites after opening: " + percolation.numberOfOpenSites());
    }

    private static void testPercolates() {
        Percolation percolation = new Percolation(3);
        System.out.println("Testing percolates method...");
        System.out.println("Does the system percolate before opening? " + percolation.percolates());
        percolation.open(0, 0);
        percolation.open(1, 0);
        percolation.open(2, 0);
        System.out.println("Does the system percolate after opening? " + percolation.percolates());
    }
}
