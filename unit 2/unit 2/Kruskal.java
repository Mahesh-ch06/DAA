import java.util.*;  // Import required classes

// 👇 Block 1: Edge Representation
class Edge {
    int src, dest, weight; // Stores source, destination, and weight of an edge

    // Constructor to initialize edge
    Edge(int s, int d, int w) {
        this.src = s;
        this.dest = d;
        this.weight = w;
    }
}

// 👇 Block 2: Main Kruskal Algorithm Class
public class Kruskal {
    
    // 📌 Block 3: Find Parent of a Node (Cycle Detection)
    static int findParent(int node, int[] parent) {
        while (parent[node] != node) {
            node = parent[node];  // Move up in the parent array
        }
        return node;
    }

    // 📌 Block 4: Union Operation (Merge Two Components)
    static void union(int u, int v, int[] parent) {
        int parentU = findParent(u, parent);  // Find root of component U
        int parentV = findParent(v, parent);  // Find root of component V
        parent[parentU] = parentV;  // Merge both components
    }

    // 📌 Block 5: Kruskal's Algorithm Implementation
    static void kruskalMST(int vertices, List<Edge> edges) {
        // Step 1: Sort edges by weight using Bubble Sort (simplest sorting method)
        for (int i = 0; i < edges.size() - 1; i++) {
            for (int j = 0; j < edges.size() - i - 1; j++) {
                if (edges.get(j).weight > edges.get(j + 1).weight) {
                    // Swap edges[j] and edges[j+1] if they are out of order
                    Edge temp = edges.get(j);
                    edges.set(j, edges.get(j + 1));
                    edges.set(j + 1, temp);
                }
            }
        }

        // Step 2: Initialize Parent Array (Each node is its own parent initially)
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) parent[i] = i;

        // Step 3: Process Edges and Build MST
        List<Edge> mst = new ArrayList<>();  // Stores MST edges
        int mstCost = 0;  // Stores total weight of MST

        for (Edge edge : edges) {
            int srcParent = findParent(edge.src, parent);
            int destParent = findParent(edge.dest, parent);

            if (srcParent != destParent) {  // Only add edge if it does not form a cycle
                mst.add(edge);
                mstCost += edge.weight;
                union(edge.src, edge.dest, parent);
            }

            if (mst.size() == vertices - 1) break; // Stop when MST has V-1 edges
        }

        // 📌 Block 6: Print the Result (Edges in MST and Total Cost)
        System.out.println("Edges in MST:");
        for (Edge edge : mst) {
            System.out.println((edge.src + 1) + " - " + (edge.dest + 1) + " : " + edge.weight);
        }
        System.out.println("Total Cost of MST: " + mstCost);
    }

    // 📌 Block 7: Taking Input from User and Calling Kruskal's Algorithm
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v = scan.nextInt();
        System.out.print("Enter the number of edges: ");
        int e = scan.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter source, destination, and weight (1-based index): ");
        for (int i = 0; i < e; i++) {
            int src = scan.nextInt() - 1;  // Convert 1-based index to 0-based
            int dest = scan.nextInt() - 1;
            int weight = scan.nextInt();
            edges.add(new Edge(src, dest, weight));
        }

        kruskalMST(v, edges);  // Call Kruskal's Algorithm

        scan.close();
    }
}
