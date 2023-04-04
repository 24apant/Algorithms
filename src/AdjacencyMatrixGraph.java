public class AdjacencyMatrixGraph {
    private boolean[][] edges;

    // Create a new graph with this number of vertices
    public AdjacencyMatrixGraph(int numVertices) {
        this.edges = new boolean[numVertices][numVertices];
    }

    // Add an addEdge method that is given 2 vertices
    // and creates an edge between both vertices
    public void addEdge(int vertexA, int vertexB) {
        // YOUR CODE HERE
        edges[vertexA][vertexB] = true;
        edges[vertexB][vertexA] = true;
    }

    // Add a hasEdge method that is given 2 vertices and
    // checks if there an edge between the 2 vertices
    public boolean hasEdge(int vertexA, int vertexB) {
        // YOUR CODE HERE
        return edges[vertexA][vertexB];
    }

    // Add a removeEdge method that is given 2 vertices
    // and removes the edge between the 2 vertices
    public void removeEdge(int vertexA, int vertexB) {
        // YOUR CODE HERE
        edges[vertexA][vertexB] = false;
        edges[vertexB][vertexA] = false;
    }

    // Add a addVertex method that adds a new vertex to
    // the graph by creating a new array
    public void addVertex() {
        // YOUR CODE HERE
        boolean[][] n = new boolean[edges.length+1][edges.length+1];
        // copy over edges to n and replace
        for (int i = 0; i< edges.length;i++){
            System.arraycopy(edges[i], 0, n[i], 0, edges[i].length);
        }
        edges = n;
    }

    public void printGraph() {
        System.out.println("Vertex | Adjacent Vertices");
        System.out.println("--------------------------");

        for (int i = 0; i < edges.length; i++) {
            System.out.print(i + "      | ");

            for (int j = 0; j < edges[0].length; j++) {
                if (edges[i][j] == true) {
                    System.out.print(j + " ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        // --------------------------
        // Test 1: Add Edges
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 1: Add Edges");
        System.out.println("Expected:");
        System.out.println("Vertex | Adjacent Vertices\n" +
                "--------------------------\n" +
                "0      | 1 4 \n" +
                "1      | 0 2 3 4 \n" +
                "2      | 1 3 \n" +
                "3      | 1 2 4 \n" +
                "4      | 0 1 3 ");

        System.out.println("\nGot:");

        AdjacencyMatrixGraph graph1 = new AdjacencyMatrixGraph(5);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 4);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 3);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 4);

        graph1.printGraph();
        System.out.println();


        // --------------------------
        // Test 2: Remove edge
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 2: Remove edge");
        System.out.println("Expected:");
        System.out.println("Vertex | Adjacent Vertices\n" +
                "--------------------------\n" +
                "0      | \n" +
                "1      | 2 3 4 \n" +
                "2      | 1 3 \n" +
                "3      | 1 2 4 \n" +
                "4      | 1 3 ");

        System.out.println("\nGot:");

        // Remove edge tests
        graph1.removeEdge(0, 1);
        graph1.removeEdge(0, 4);
        graph1.printGraph();

        // --------------------------
        // Test 3: Add vertex
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 3: Add vertex");
        System.out.println("Expected:");
        System.out.println("Vertex | Adjacent Vertices\n" +
                "--------------------------\n" +
                "0      | 5 \n" +
                "1      | 2 3 4 \n" +
                "2      | 1 3 \n" +
                "3      | 1 2 4 5 \n" +
                "4      | 1 3 \n" +
                "5      | 0 3 ");

        System.out.println("\nGot:");

        // Add a vertex
        graph1.addVertex();

        // Add some edges to it
        graph1.addEdge(0, 5);
        graph1.addEdge(3, 5);
        graph1.printGraph();
    }
}
