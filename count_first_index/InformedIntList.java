import java.util.Arrays;

public class InformedIntList {
    private IntList intlist;
    private int cou;
	private int lastString;
    
    public InformedIntList() {
        intlist = new IntList();
        cou = 0;
		lastString = -1;
    }
    
    public int getCou() {
        return cou;
    }   
    
    public void windCou() {
        cou++;
    }
    
	public int lastIns() {
		return lastString;
	}
	
	public int get(int x) {
        return intlist.get(x);
    }
    
    public int len() {
        return intlist.len();
    }
	
	public void push(int x, int StringNum) {
		intlist.push(x);
		lastString = StringNum;
	}
}