import java.util.*;

public class grafo {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;

    private int V;
    List<List<Node>> adj;

    public grafo(int V){
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    //Algoritimo de Dijkstra
    public void dijkstra(List<List<Node> > adj, int src){
        this.adj = adj;

        //Todos os nos começam com dist máxima.
        for(int i = 0; i < V; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(src, 0));

        dist[src] = 0;
        while(settled.size() != V){
            if(pq.isEmpty()){
                return;
            }

            int u = pq.remove().node;
            if(settled.contains(u)){
                continue;
            }

            settled.add(u);
            e_Neighbours(u);
        }
    }

    //metodo para os vizinhos
    private void e_Neighbours(int u){
        int edgeDistance = -1;
        int newDistance = -1;

        for(int i= 0; i<adj.get(u).size(); i++){
            Node v = adj.get(u).get(i);

            if(!settled.contains(v.node)){
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                if(newDistance < dist[v.node]){
                    dist[v.node] = newDistance;
                }

                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public static void main(String arg[])
    {
        int V = 5;
        int source = 0;
        // Adjacency list representation of the
        // connected edges by declaring List class object
        // Declaring object of type List<Node>
        List<List<Node> > adj
                = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the GFG(dpq) graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculating the single source shortest path
        grafo dpq = new grafo(V);
        dpq.dijkstra(adj, source);

        // Printing the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");

        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }

}

