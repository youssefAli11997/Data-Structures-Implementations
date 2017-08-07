/*
 - Used an adjacency list to represent the graph
 - Main method Included for testing
*/
public class Graph {
	
	private class AdjNode{
		private int source;
		private int destination;
		private int cost;
		private AdjNode next;
		
		public AdjNode(int src, int dest, int cost){
			this.source = src;
			this.destination = dest;
			this.cost = cost;
		}
		
		public AdjNode(int src, int dest){
			this(src, dest, 1);
		}
		
	}
	
	private class AdjList{
		private AdjNode head = null;
	}
	
	private int countOfNodes;
	private AdjList[] list;
	
	public Graph(int count){
		this.countOfNodes = count;
		list = new AdjList[count];
	}
	
	public void AddEdge(int src, int dest, int cost){
		AdjNode node = new AdjNode(src, dest, cost);
		node.next = list[src].head;
		list[src].head = node;
	}

	public void AddEdge(int src, int dest){
		AddEdge(src, dest, 1);
	}
	
	public void AddBiEdge(int src, int dest, int cost){
		AddEdge(src, dest, cost);
		AddEdge(dest, src, cost);
	}
	
	public void AddBiEdge(int src, int dest){
		AddBiEdge(src, dest, 1);
	}
	
	public void print(){
		for(int i = 0; i < countOfNodes; i++){
			AdjNode current = list[i].head;
			if(current != null){
				System.out.print("Vertex " + i+ " is connected to: ");
				while(current != null){
					System.out.print(current.destination +" ");
					current = current.next;
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args){
		// assuming to be unweighed graph
		Graph graph = new Graph(5);
		graph.AddEdge(1, 3);
		graph.AddBiEdge(1, 2);
		graph.AddBiEdge(3,4);
		graph.print();
	}
}
