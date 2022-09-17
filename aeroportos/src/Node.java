import java.util.*;

class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public Node() {}

    // Constructor 2
    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}