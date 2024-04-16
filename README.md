<img src="Screenshot 2024-04-15 at 22.50.16.png"></img>

/******************************************************************************
 *  Name:    Henry Langmack 
 *
 *  Hours to complete assignment (optional): 3
 *
 ******************************************************************************/

Programming Project: Percolation


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 *****************************************************************************/

I used a bit set for each root node of the disjoint set data structure (unionfind) which stores whether the root node is connected to the top and bottom. I XOR the bit set when merging nodes, meaning when a bottom cell is merged with a root node that has a top flag set, there is a bottom and top flag set, and since it has both we can set percolates = true and therefore the system percolates. This also prevents backwash.

/******************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for values values of n and T when implementing
 *  Percolation.java with QuickFindUF.java.
 *
 *  To do so, fill in the two tables below. Each table must have at least
 *  4 data points, ranging in time from around 0.1 seconds to around
 *  60 seconds. Do not include data points that takes less than 0.1 seconds.
 *****************************************************************************/

(keep T constant) t = 500

 n          time (seconds)
------------------------------
100          0.745
200          1.198
350          2.905
750          14.405
1000         29.560       


(keep n constant) n = 500

 T          time (seconds)
------------------------------
100         1.486
250         3.055
1000        10.859
2000        21.980
5000        53.104



/******************************************************************************
 *  Using the empirical data from the above two tables, give a formula 
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 T^1.5
 *
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient to two significant digits.
 *
 *****************************************************************************/

running time (in seconds) as a function of n and T:

~ (0.0000399928537328n^2 − 0.012639896398n + 1.95223002575)/500 * T

for relatively small values of N.

/**********************************************************************
 *  How much memory (in bytes) does a Percolation object (which uses
 *  WeightedQuickUnionUF.java) use to store an n-by-n grid? Use the
 *  64-bit memory cost model from Section 1.4 of the textbook and use
 *  tilde notation to simplify your answer. Briefly justify your
 *  answers.
 *
 *  Include the memory for all referenced objects (deep memory).
 **********************************************************************/

int is 4 bytes

Percolation:
WeightedQuickUnionPathCompressionUF: two arrays of size n, 4 * 2 * n = 8n
Grid storage: 4*n*n = 4n^2
Two ints and a boolean as instance variables: 8 + 1 = 9

TOTAL: 4n^2+8n+9 bytes




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

None that I know of - probably could be more memory efficient since i'm not using all cells of the grid for root storage - could just be booleans and then use a hashmap to store bottom/top
details


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

Didn't recieve any help other than some forum articles explaning unionfind (not related to this
assignment)

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

Backwash - had to think about the data I was storing in terms of roots, decided to use a bitset
as all you need is to store the groups of cells and their shared state, not individual state.
For any cell, you can look up the root in near linear time with the UnionFind implementation
I am using with weights the graph and uses path compression.


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/


I really enjoyed this assignment!
