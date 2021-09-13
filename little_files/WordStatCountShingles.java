import java.util.*;
import java.io.*;

public class WordStatCountShingles {
    private static final MScanner.Element Symbol = c -> (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION);
    
    public static class Par {
        private int cou;
        private String tri;
        
        public Par(int cou, String tri) {
            this.cou = cou;
            this.tri = tri;
        }
    }
    
    public static void main(String[] args) {
        String inp = args[0];
        String outp = args[1];
        
        Map <String, Integer> map = new LinkedHashMap <String, Integer>();
        
        try {
            
            MScanner in = new MScanner(inp, "utf8", Symbol);
            while (!in.isEndOfFile()) {
                while (in.hasNextInLine()) {
                    String word = in.nextInLine();
                    word = word.toLowerCase();

                    for (int i = 0; i < word.length() - 2; i++) {
                        String trio = word.substring(i, i + 3);
                        int t = map.getOrDefault(trio, 0) + 1;
                        map.put(trio, t);
                    }
                }
                in.nextLine();
            }
            
            Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(outp),
                    "utf8"
                )
            );
            try {
                Par[] ans = new Par[map.size()];
                int n = 0;
                        
                for (Map.Entry<String, Integer> x: map.entrySet()) {
                    String key = x.getKey();
                    int value = x.getValue();
                    
                    ans[n] = new Par(value, key);
                    n++;
                }
                
                Arrays.sort(ans, Comparator.comparingInt(Par -> Par.cou));
                
                for (int i = 0; i < n; i++) {
                    writer.write(ans[i].tri + " " + ans[i].cou);
                    writer.write("\r\n");
                }
            } finally {
                writer.close();
            }
            
        } catch(IOException e) {
            System.out.println("Input file not found");
        }
        
    }
}


