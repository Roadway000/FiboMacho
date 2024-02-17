public class Fibonacci {
    public static long fiboIter(int n) {
        if (n < 2) {
            return n;
        }
        // 0,1,1,2,3,5,8,13,21,34,55...
        int x1 = 0;
        int x2 = 1;
        int xn = 1;
        for (int i = 2; i <= n; i++) {
            xn = x1 + x2;
            x1 = x2;
            x2 = xn;
        }
        return xn;
    }
    public static int fiboRecurs(int n) {
        if (n <= 1)
            return n;
        return fiboRecurs(n - 1) + fiboRecurs(n - 2);
    }
    /*  ------------------------------------------------------------------------------------------------------------------
        Hashmap is better for performance, Treemap is for iterating and memory saving.
     What would be the ideal data structure, or does it matter?
     I would suggest using neither and use an int[] instead, doubling the size if needed.
     1. Auto(un)boxing costs time.
     2. The insertion to the map costs time. It is specified as O(1), but the actual cost is noticeable.
     3. The doubling-strategy guarantees amortized cost for inserting n elements of O(n) (we cannot do better,
        adding n elements will always have a baseline cost of O(n), it is the same as the insert costs of ArrayList (docs.oracle.com)).
     Putting it all together leads to the following linear time algorithm for calculating the n-th Fibonacci-number:
    */
    public static final int INIT_CAPACITY = 8;
    private static int[] cache = new int[INIT_CAPACITY];
    static {
        cache[1] = 1;
    }
    private static int currentMax = 1;
    static int fiboCache(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n <= currentMax) {
            return cache[n];
        }
        if (n >= cache.length) {
            resizeCache(n);
        }
        for (int index = currentMax + 1; index <= n; ++index) {
            cache[index] = cache[index - 1] + cache[index - 2];
        }
        if (currentMax < n) {
            currentMax = n;
        }
        return cache[n];
    }
    private static int calculateNewCacheCapacity(int minCapacity) {
        int newSize = cache.length;
        while (newSize <= minCapacity) {
            newSize *= 2;
        }
        return newSize;
    }
    private static void resizeCache(int minCapacity) {
        int newCapacity = calculateNewCacheCapacity(minCapacity);
        int currentCapacity = cache.length;
        if (newCapacity < currentCapacity) {
            throw new IllegalArgumentException("new Capacity must be larger than the current capacity");
        }
        int[] newCache = new int[newCapacity];
        System.arraycopy(cache, 0, newCache, 0, currentCapacity);
        cache = newCache;
        System.out.println("newCapacity: "+newCapacity);
    }
    //------------------------------------------------------------------------------------------------------------------
    /*  This approach relies on the fact that if we n times multiply the matrix M = {{1,1},{1,0}} to itself
        (in other words calculate power(M, n)), then we get the (n+1)th Fibonacci number as the element
        at row and column (0, 0) in the resultant matrix.
        If n is even then k = n/2:
        Therefore Nth Fibonacci Number = F(n) = [2*F(k-1) + F(k)]*F(k)
        If n is odd then k = (n + 1)/2:
        Therefore Nth Fibonacci Number = F(n) = F(k)*F(k) + F(k-1)*F(k-1)
        --- This code is contributed by Rajat Mishra ---
     */
    static int fibOptima(int n) {
        int[][] f = new int[][] {{1,1},{1,0}};
        if (n==0)
            return 0;
        power(f,n - 1);
        return f[0][0];
    }
    static void power(int[][] f, int n)
    {
        if (n == 0 || n == 1)
            return;
        int[][] m = new int[][] {{1,1},{1,0}};

        power(f,n / 2);
        multiply(f,f);

        if (n % 2 != 0)
            multiply(f,m);
    }
    static void multiply(int[][] f, int[][] m)
    {
        int x = f[0][0] * m[0][0] + f[0][1] * m[1][0];
        int y = f[0][0] * m[0][1] + f[0][1] * m[1][1];
        int z = f[1][0] * m[0][0] + f[1][1] * m[1][0];
        int w = f[1][0] * m[0][1] + f[1][1] * m[1][1];

        f[0][0] = x;
        f[0][1] = y;
        f[1][0] = z;
        f[1][1] = w;
    }
}
