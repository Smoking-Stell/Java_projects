import java.util.*;
import java.io.*;

public class WordStatCountFirstIndex {  
    private static final MyScanner.Element Symbol = c -> Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    
    private static final Comparator <Map.Entry<String, InformedIntList>> compar = new Comparator<Map.Entry<String, InformedIntList>>(){
        public int compare(Map.Entry<String, InformedIntList> x, Map.Entry<String, InformedIntList> y) {
            return x.getValue().getCou() - y.getValue().getCou();
        }
    };
    
    public static void main(String[] args) {
        int couit = 0;
		int numString = 0;

        Map <String, InformedIntList > map = new LinkedHashMap <String, InformedIntList>();
        
        String inp = args[0];
        String outp = args[1];
        try {
            MyScanner in = new MyScanner(inp, "utf8", Symbol);
            
            while (!in.isEndOfFile()) {
                while (in.hasNextInLine()) {
                    couit++;
                    String key = in.nextInLine();
                    key = key.toLowerCase();
                    
                    InformedIntList t = map.get(key);
                    if (t == null) {//NOTE: use default
                        t = new InformedIntList();
                    }
                    t.windCou();
                    if (t.lastIns() != numString) {
                        t.push(couit, numString);
                        map.put(key, t);
                    }

                }
                couit = 0;
				numString++;
                in.nextLine();
            }

            Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(outp),
                    "utf8"
                )
            );
            try {               
                ArrayList<Map.Entry<String, InformedIntList>> ans = new ArrayList<Map.Entry<String, InformedIntList>>();
            
                for (Map.Entry<String, InformedIntList> x: map.entrySet()) {
                    ans.add(x);
                }
                Collections.sort(ans, compar);
                
                for (int i = 0; i < ans.size(); i++) {
                    InformedIntList out = (ans.get(i)).getValue();
                    
                    writer.write(ans.get(i).getKey() + " " + out.getCou());
                    for (int j = 0; j < out.len(); j++) {
                        writer.write(" " + out.get(j));
                    }
                    writer.write("\r\n");
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Input file not found");
        }
    }
}