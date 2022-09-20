import java.util.*;

class grafo {
    public double dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    List<List<Node> > adj;

    public grafo(int V)
    {
        this.V = V;
        dist = new double[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    public void dijkstra(List<List<Node> > adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        pq.add(new Node(src, 0));

        dist[src] = 0.0;

        while (settled.size() != V) {
            if (pq.isEmpty()){
                return;
            }
            int u = pq.remove().node;

            if (settled.contains(u)) {
                continue;
            }

            settled.add(u);

            e_Neighbours(u);
        }
    }

    private void e_Neighbours(int u)
    {

        double edgeDistance = -1.0;
        double newDistance = -1.0;

        for (int i = 0; i < adj.get(u).size()-1; i++) {
            Node v = adj.get(u).get(i);

            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance;
                }
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }
}

