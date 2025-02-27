import java.util.*;

public class Dijkstras {
    static int getMinVertex(int[] minDistances, boolean[] visited) {
        int minValue = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < minDistances.length; i++) {
            if (!visited[i] && minDistances[i] < minValue) {
                minValue = minDistances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static int getMinPath(int[][] graph, int src, int dst) {
        int v = graph.length;
        int[] minDistances = new int[v];
        boolean[] visited = new boolean[v];
        
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        minDistances[src] = 0;
        
        for (int count = 0; count < v - 1; count++) {
            int node = getMinVertex(minDistances, visited);
            if (node == -1) break;
            visited[node] = true;
            
            for (int i = 0; i < v; i++) {
                if (graph[node][i] != Integer.MAX_VALUE && !visited[i]) {
                    int newDist = minDistances[node] + graph[node][i];
                    if (newDist < minDistances[i]) {
                        minDistances[i] = newDist;
                    }
                }
            }
        }
        
        return minDistances[dst] == Integer.MAX_VALUE ? -1 : minDistances[dst];
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v = scan.nextInt();
        System.out.print("Enter the number of edges: ");
        int e = scan.nextInt();
        
        int[][] graph = new int[v][v];
        for (int i = 0; i < v; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        
        System.out.println("Enter the source, destination, and weight of edges (1-based index):");
        for (int i = 0; i < e; i++) {
            int s = scan.nextInt() - 1;
            int d = scan.nextInt() - 1;
            int w = scan.nextInt();
            graph[s][d] = w;
            graph[d][s] = w;
        }
        
        System.out.print("Enter the source vertex (1-based index): ");
        int src = scan.nextInt() - 1;
        System.out.print("Enter the destination vertex (1-based index): ");
        int dst = scan.nextInt() - 1;
        
        int minPath = getMinPath(graph, src, dst);
        if (minPath == -1)
            System.out.println("No path found.");
        else
            System.out.println("The shortest path distance is: " + minPath);
        
        scan.close();
    }
}
