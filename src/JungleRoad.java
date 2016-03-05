import java.util.ArrayList;
import java.util.Scanner;

public class JungleRoad {
	public HashTable<String, ArrayList<TNode>> table = new HashTable<String, ArrayList<TNode>>();
	public ArrayList<TNode> pq = new ArrayList<TNode>();
	public ArrayList<String> keys = new ArrayList<String>();

	public void makeGraph(){
		Scanner scanner = new Scanner(System.in);
		int numV = scanner.nextInt();
		for(int i = 0; i < numV; i ++){
			String vertex = scanner.next();
			if(!table.containsKey(vertex)){
				table.put(vertex, new ArrayList<TNode>());
				keys.add(vertex);
			}
			int connections = scanner.nextInt();
			for(int j = 0; j < connections; j ++){
				String v = scanner.next();
				int distance = scanner.nextInt();
				table.get(vertex).add(new TNode(vertex, distance, v));
				if(!table.containsKey(v)){
					table.put(v, new ArrayList<TNode>());
					keys.add(vertex);
				}
				table.get(v).add(new TNode(v, distance, vertex));
			}
		}
	}

	public void makePQ(){
		for(String key : keys){
			pq.add(new TNode(key, 10000, null));
		}
		pq.get(0).distance = 0;
	}

	public void calcMinDist(){
		int sum = 0;
		while(!pq.isEmpty()){
			//find min
			int min = 10001;
			int mindex = 0;
			for(int  i = 0; i < pq.size(); i++){
				if(pq.get(i).distance < min){
					min = pq.get(i).distance;
					mindex = i;
				}
			}
			TNode node = pq.remove(mindex);
			sum += node.distance;
			ArrayList<TNode> list = table.get(node.vertex);
			for(TNode n : list){
				String v = n.parent;
				for(int i = 0; i < pq.size(); i++){
					if(pq.get(i).vertex.equals(v)){
						pq.get(i).distance = n.distance;
						pq.get(i).parent = node.vertex;
					}
				}
			}
		}
	}

	public static void main(String[] args){
	}
}
