import java.util.ArrayList;
import java.util.List;

public class Bucket {
	int bucketSize=3;
	List<Pair> records = new ArrayList<Pair>();
	int localDepth=1;
	int id;
	
	Bucket(int id) {
		this.id = id;
	}
	boolean isFull() {
		if(records.size()>=bucketSize) {
			return true;
		}
		return false;
	}
	
	void put(int key,int value) {
		Pair p=new Pair(key,value);
		records.add(p);
	}
	
	int get(int key) {
		int val=-1;
		for(Pair p:records) {
			if(p.getKey()==key) val=p.getVal();
		}
		return val;
	}
	
}
