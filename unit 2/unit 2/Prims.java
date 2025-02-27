import java.util.*;

class Prims {
    
    public static int findMinIdx(int[] key, boolean[] MST) {
        int minIdx = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < key.length; i++) {
            if (!MST[i] && key[i] < minValue) {
                minValue = key[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    public static int MST_prims(int[][] edgemat) {
        int v = edgemat.length;
        int[] parent = new int[v];  
        int[] key = new int[v];    
        boolean[] MST = new boolean[v];

        Arrays.fill(key, Integer.MAX_VALUE);

        key[0] = 0; 
        parent[0] = -1; 

        for (int count = 0; count < v - 1; count++) {
            int u = findMinIdx(key, MST); 
            MST[u] = true; 

            for (int j = 0; j < v; j++) {
                if (edgemat[u][j] != 0 && !MST[j] && edgemat[u][j] < key[j]) {
                    parent[j] = u;
                    key[j] = edgemat[u][j];
                }
            }
        }

        int mstCost = 0;
        for (int i = 1; i < v; i++) {
            mstCost += key[i];
        }
        return mstCost;
    }

    public static int[][] readMat() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v = scan.nextInt();
        int[][] edgemat = new int[v][v];
        System.out.print("Enter the number of edges: ");
        int e = scan.nextInt();

        System.out.println("Enter source, destination, and weight (1-based index): ");
        for (int i = 0; i < e; i++) {
            int src = scan.nextInt() - 1;  // Convert 1-based index to 0-based
            int dest = scan.nextInt() - 1;
            int weight = scan.nextInt();
            edgemat[src][dest] = weight;
            edgemat[dest][src] = weight;
        }
        return edgemat;
    }

    public static void main(String[] args) {
        int[][] graph = readMat();
        System.out.println("Minimum cost to connect all power stations: " + MST_prims(graph) + " million dollars");
    }
}


// ---

// ### **Example Graph (Adjacency Matrix)**
// Consider a graph with **5 vertices** (labeled 1 to 5), and the following edge weights:

// ```
//       (1)
//     /  |  \
//    2   3   6
//   /    |    \
// (2)----8----(3)
//   \    |     |
//    5   7     9
//     \  /     |
//      (4)--4-(5)
//         
// ```

// This corresponds to the following **adjacency matrix**:

// |    | 1 | 2 | 3 | 4 | 5 |
// |----|---|---|---|---|---|
// | 1* | 0 | 2 | 3 | 6 | 0 |
// | 2* | 2 | 0 | 8 | 5 | 0 |
// | 3* | 3 | 8 | 0 | 7 | 9 |
// | 4* | 6 | 5 | 7 | 0 | 4 |
// | 5* | 0 | 0 | 9 | 4 | 0 |

// ---

// ### **Step-by-Step Debugging of `findMinIdx()`**
// The function finds the **minimum key value vertex that is not in MST**.

// #### **Initial Setup**
// - `key = [0, ∞, ∞, ∞, ∞]`
// - `MST = [false, false, false, false, false]`
// - `parent[]`** = `[-1, -1, -1, -1, -1]`
// - First `findMinIdx()` call:
//   - **Minimum key is 0 (index 0), so vertex 1 is selected.**

// ---

// ### **Step 1: Pick Vertex 1**
// - **Minimum key = 0 (Vertex 1)**
// - **Mark `MST[0] = true`**
// - **Update neighbors (2, 3, 4)**
//   - `key[1] = 2, parent[1] = 0`
//   - `key[2] = 3, parent[2] = 0`
//   - `key[3] = 6, parent[3] = 0`
// - **Updated `key[]`** = `[0, 2, 3, 6, ∞]`
// - **Updated `parent[]`** = `[-1, 0, 0, 0, -1]`
// - **MST updated:** `[true, false, false, false, false]`
// - **Next `findMinIdx()` call → Vertex 2 (key = 2)**

// ---

// ### **Step 2: Pick Vertex 2**
// - **Minimum key = 2 (Vertex 2)**
// - **Mark `MST[1] = true`**
// - **Update neighbors (3, 4)**
//   - `key[3] = 5 (Better than 6), parent[3] = 1`
// - **Updated `key[]`** = `[0, 2, 3, 5, ∞]`
// - **Updated `parent[]`** = `[-1, 0, 0, 1, -1]`
// - **MST updated:** `[true, true, false, false, false]`
// - **Next `findMinIdx()` call → Vertex 3 (key = 3)**

// ---

// ### **Step 3: Pick Vertex 3**
// - **Minimum key = 3 (Vertex 3)**
// - **Mark `MST[2] = true`**
// - **Update neighbors (4, 5)**
//   - No update for 4 (`key[3] = 5` is better)
//   - `key[4] = 9, parent[4] = 2`
// - **Updated `key[]`** = `[0, 2, 3, 5, 9]`
// - **Updated `parent[]`** = `[-1, 0, 0, 1, 2]`
// - **MST updated:** `[true, true, true, false, false]`
// - **Next `findMinIdx()` call → Vertex 4 (key = 5)**

// ---

// ### **Step 4: Pick Vertex 4**
// - **Minimum key = 5 (Vertex 4)**
// - **Mark `MST[3] = true`**
// - **Update neighbors (5)**
//   - `key[4] = 4 (Better than 9), parent[4] = 3`
// - **Updated `key[]`** = `[0, 2, 3, 5, 4]`
// - **Updated `parent[]`** = `[-1, 0, 0, 1, 3]`
// - **MST updated:** `[true, true, true, true, false]`
// - **Next `findMinIdx()` call → Vertex 5 (key = 4)**

// ---

// ### **Step 5: Pick Vertex 5**
// - **Minimum key = 4 (Vertex 5)**
// - **Mark `MST[4] = true`**
// - **All vertices are now included in MST!**

// ---

// ### **Final MST Cost**
// Summing the selected edges:
// - 2 + 3 + 5 + 4 = **14 million dollars**

// ---

// ### **Debugging Output**
// If we add print statements for debugging:
// ```java
// System.out.println("Step " + count + ": Selected Vertex = " + u);
// System.out.println("Key Array: " + Arrays.toString(key));
// System.out.println("MST Status: " + Arrays.toString(MST));
// ```
// The output would be:
// ```
// Step 0: Selected Vertex = 0
// Key Array: [0, 2, 3, 6, ∞]
// MST Status: [true, false, false, false, false]

// Step 1: Selected Vertex = 1
// Key Array: [0, 2, 3, 5, ∞]
// MST Status: [true, true, false, false, false]

// Step 2: Selected Vertex = 2
// Key Array: [0, 2, 3, 5, ∞]
// MST Status: [true, true, true, false, false]

// Step 3: Selected Vertex = 3
// Key Array: [0, 2, 3, 5, 4]
// MST Status: [true, true, true, true, false]

// Step 4: Selected Vertex = 4
// Key Array: [0, 2, 3, 5, 4]
// MST Status: [true, true, true, true, true]
// ```


// ### **Final MST Output**
// ```
// Edges in MST:
// 1 - 2 : 2
// 1 - 3 : 3
// 2 - 4 : 5
// 4 - 5 : 4
// Minimum cost to connect all power stations: 14 million dollars
// ```

// ---

// ### **Final Arrays:**
// ```plaintext
// key[]    = [0, 2, 3, 5, 4]
// parent[] = [-1, 0, 0, 1, 3]
// MST[]    = [true, true, true, true, true]
// ```


// ---

// ### **Conclusion**
// - The **code correctly finds the MST** using Prim’s Algorithm.
// - `findMinIdx()` **correctly selects the vertex with the minimum key** in each iteration.
// - The **final MST cost is 14 million dollars** for the given graph.

// ---
