import java.util.Arrays;

public class IntList {
    private final int N = 5;
    private int size;
    private int n;
    private int[] a;
    
    public IntList() {
        a = new int[N];
        size = 5;//NOTE: use N
        n = 0;
    }
    
    public void push(int x) {
        if (size == n + 1) {
            a = Arrays.copyOf(a, size * 2);
            size = size * 2;
        }
        a[n] = x;
        n++;
    }
    
    public int get(int x) {
        return a[x];
    }
    
    public int len() {
        return n;
    }
}