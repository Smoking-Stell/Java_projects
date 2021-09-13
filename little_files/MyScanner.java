import java.io.*;

public class MyScanner implements AutoCloseable{
    private final int endconst = 1024;
    private Reader scan;
    private char[] real;
    private int i;
    private int end;
    private String nex;
    private boolean endOfLine;
    private boolean checkTran;
    private boolean endOfFile;
    private Element allow;
    
    public MyScanner(String file, String ChrName) throws IOException {
        this(file, ChrName, c -> !Character.isWhitespace(c));
    }       
    
    public MyScanner(InputStream input, String ChrName) throws IOException {
        this(input, ChrName, c -> !Character.isWhitespace(c));
    }
    
    public MyScanner(String file, String ChrName, Element allowFromOut) throws IOException {
        this(new FileInputStream(file), ChrName, allowFromOut);
    }       
    
    public MyScanner(InputStream input, String ChrName, Element allowFromOut) throws IOException {
        this.scan = new InputStreamReader(input, ChrName);
        set(allowFromOut);
    }   
    
    private void set(Element allowFromOut) throws IOException {
        real = new char[endconst];
        end = endconst;
        i = endconst;
        endOfLine = false;
        endOfFile = false;
        checkTran = false;
        allow = allowFromOut;
        nex = null;
        newNex();
    }
    
    @FunctionalInterface
    public interface Element {
        boolean check(char c);
    }

    private boolean pullbuff() throws IOException {
        if (i == end) {
            end = scan.read(real, 0, endconst);
            i = 0;
        }
        if (end == -1) {
            endOfFile = true;
            scan.close();
            return false;
        }
        return true;
    }
    
    private boolean newNex() throws IOException {
        if (endOfLine || endOfFile) {
            return false;
        }       
        
        StringBuilder ans = new StringBuilder();
            
        while ((pullbuff()) && !allow.check(real[i])) {
            if (real[i] == '\r') {
                endOfLine = true;
                checkTran = true;
            } else {
                if (real[i] == '\n') {
                    if (!checkTran) {
                        endOfLine = true;
                    }
                } 
                checkTran = false;
            }
            i++;
            if (endOfLine) {
                return false;
            }
        }
        
        boolean flag = false;
        while (pullbuff() && allow.check(real[i])) {
            ans.append(real[i]);
            flag = true;
            i++;
        }
        if (flag) {
            nex = ans.toString();
            return true;
        } else {
            nex = null;
            return false;
        }
    }
    
    public String nextInLine() throws IOException{
        if (hasNextInLine()) {
            String out = nex;
            nex = null;
            newNex();
            return out;
        } else {
            return null;
        }
    }
    
    public void nextLine() throws IOException {
        endOfLine = false;
        newNex();
    }
    
    public boolean isEndOfFile() {
        return endOfFile;
    }
    
    public boolean hasNextInLine() {
        return (nex != null);
    }
    
    public int nextIntInLine() throws IOException, NumberFormatException {
        return Integer.parseInt(nextInLine());
    }
    
    @Override
    public void close() throws IOException {
        scan.close();
    }
}
