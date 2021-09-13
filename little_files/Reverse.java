import java.util.Arrays;
import java.io.*;

public class Reverse {
    private static final MScanner.Element Symbol = c -> Character.isDigit(c) || c == '-';
    
    public static void main(String[] args) {
        final int N = 5;
        int m = 0, t = 0;
        int[][] matr = new int[N + 1][];
        int st[] = new int[N + 1];
        
        try {
            MScanner in = new MScanner(System.in, "utf8", Symbol);
            matr[0] = new int[N];
            st[0] = 0;
            
            while (!in.isEndOfFile()) {
                while (in.hasNextInLine()) {
                    int x = in.nextIntInLine();
                    if (st[m] >= matr[m].length) {
                        matr[m] = Arrays.copyOf(matr[m], matr[m].length * 2);
                    }
                    matr[m][st[m]] = x;
                    st[m]++;
                }
                
                m++;
                if (m == matr.length) {
                    matr = Arrays.copyOf(matr, m * 2);
                    st = Arrays.copyOf(st, m * 2);
                }
                matr[m] = new int[N];
                st[m] = 0;
                
                in.nextLine();
            }
            
            for (int i = m - 1; i >= 0; i--) {
                for (int j = st[i] - 1; j >= 0; j--) {
                    System.out.print(matr[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }catch (IOException e) {
            System.out.println("Input problem: " + e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("Lost Number: " + e.getMessage());
        }
    }
}