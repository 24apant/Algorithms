import java.util.*;

public class Graph {
    HashMap<String, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    // Creates a new vertex with the given label
    // and adds it to the graph
    public void addVertex(String label) {
        vertices.put(label, new Vertex(label));
    }

    // Adds an edge between the vertices with the given
    // labels to the graph
    public void addEdge(String label1, String label2) {
        // YOUR CODE HERE
        // get the two vertices and add a neighbor
        vertices.get(label1).neighbors.add(vertices.get(label2));
        vertices.get(label2).neighbors.add(vertices.get(label1));
    }

    // Checks to see if there is an edges between the
    // vertices with the given labels
    public boolean hasEdge(String label1, String label2) {
        // YOUR CODE HERE

        return vertices.get(label1).neighbors.contains(vertices.get(label2));
    }

    // Removes the given vertex and all of its edges from
    // the graph
    public void removeVertex(String label) {
        for (String k: vertices.keySet()){
            if (!k.equals(label)){
                Vertex v = vertices.get(k);
                v.neighbors.remove(vertices.get(label));
            }
        }
        vertices.remove(label);
    }

    // Removes the edge between the given vertices
    public void removeEdge(String label1, String label2) {
        // YOUR CODE HERE
        vertices.get(label1).neighbors.remove(vertices.get(label2));
        vertices.get(label2).neighbors.remove(vertices.get(label1));
    }

    public boolean depthFirstSearch(String s, String t) {
        HashSet<Vertex> explored = new HashSet<>();
        Deque<Vertex> stack = new LinkedList<>();
        stack.push(vertices.get(s));
        while(!(stack.isEmpty())){
            Vertex v = stack.pop();
            if (!(explored.contains(v))){
                explored.add(v);
                System.out.println(v.label);
                for(Vertex n: v.neighbors){
                    if (n.label.equals(t)){
                        return true;
                    }
                    stack.push(n);
                }
            }
        }
        return false;
    }

    public String breadthFirstSearchWithPath(String s, String t) {
        //This version of BFS doesn't need to print out the nodes as it visits them.
        // However, it will need to keep track of the path from the starting node to each other node.
        // You may find it helpful to use a HashMap to keep track of all of these paths,
        // or you can add a "prev" variable to the Vertex class.
        // Whenever a node is added to the queue, you should be able to add the path to that
        // node to this HashMap as well.
        HashMap<Vertex, String> map = new HashMap<>();
        HashSet<Vertex> explored = new HashSet<>();
        Deque<Vertex> d = new LinkedList<>();
        map.put(vertices.get(s), s);
        d.offer(vertices.get(s));
        while(!(d.isEmpty())) {
            // go to each neighbor
            Vertex v = d.poll();
            for (Vertex m : v.neighbors) {
                if(!explored.contains(m)){
                    explored.add(m);
                    //System.out.println(m.label);
                    String path = map.get(v) + m.label;
                    //System.out.println(path);
                    map.put(m, path);
                    if (m.label.equals(t)) {
                        return map.get(m);
                    }
                    d.add(m);
                }

            }
        }
        return "no path";
    }




    public void printGraph() {
        int longest = 7;
        for (String str: vertices.keySet()) {
            longest = Math.max(longest, str.length() + 1);
        }

        String line = "Vertex ";
        for (int i = 7; i < longest; i++)
            line += " ";
        int leftLength = line.length();
        line += "| Adjacent Vertices";
        System.out.println(line);

        for (int i = 0; i < line.length(); i++)
        {
            System.out.print("-");
        }
        System.out.println();

        for (String str: vertices.keySet()) {
            Vertex v1 = vertices.get(str);

            for (int i = str.length(); i < leftLength; i++) {
                str += " ";
            }
            System.out.print(str + "| ");

            for (int i = 0; i < v1.neighbors.size()-1; i++) {
                System.out.print(v1.neighbors.get(i).label + ", ");
            }

            if (!v1.neighbors.isEmpty()) {
                System.out.print(v1.neighbors.get(v1.neighbors.size()-1).label);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        // --------------------------
        // Test 1: Add Vertices
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 1: Add Vertices");
        System.out.println("Expected:");
        System.out.println("Vertex        | Adjacent Vertices\n" +
                "---------------------------------\n" +
                "San Francisco | \n" +
                "New York      | \n" +
                "Chicago       | \n" +
                "London        | ");

        System.out.println("\nGot:");

        Graph graph = new Graph();

        graph.addVertex("London");
        graph.addVertex("New York");
        graph.addVertex("San Francisco");
        graph.addVertex("Chicago");
        graph.printGraph();
        System.out.println();

        // --------------------------
        // Test 2: Add Edges
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 2: Add Edges");
        System.out.println("Expected:");
        System.out.println("Vertex        | Adjacent Vertices\n" +
                "---------------------------------\n" +
                "San Francisco | New York, Chicago\n" +
                "New York      | London, Chicago, San Francisco\n" +
                "Chicago       | New York, San Francisco\n" +
                "London        | New York");

        System.out.println("\nGot:");

        graph.addEdge("London", "New York");
        graph.addEdge("New York", "Chicago");
        graph.addEdge("San Francisco", "New York");
        graph.addEdge("San Francisco", "Chicago");
        graph.printGraph();
        System.out.println();

        // --------------------------
        // Test 3: Has Edges
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 3: Has Edges");
        System.out.println("Expected:");
        System.out.println("true");
        System.out.println("true");
        System.out.println("false");


        System.out.println("\nGot:");

        System.out.println(graph.hasEdge("London", "New York"));
        System.out.println(graph.hasEdge("New York", "Chicago"));
        System.out.println(graph.hasEdge("London", "San Francisco"));

        // --------------------------
        // Test 4: Remove edge
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 4: Remove edge");
        System.out.println("Expected:");
        System.out.println("Vertex        | Adjacent Vertices\n" +
                "---------------------------------\n" +
                "San Francisco | New York, Chicago\n" +
                "New York      | London, San Francisco\n" +
                "Chicago       | San Francisco\n" +
                "London        | New York");


        System.out.println("\nGot:");
        graph.removeEdge("New York", "Chicago");
        graph.printGraph();
        System.out.println();

        // --------------------------
        // Test 5: Remove vertex
        // --------------------------
        System.out.println("-------------------");
        System.out.println("Test 5: Remove vertex");
        System.out.println("Expected:");
        System.out.println("Vertex        | Adjacent Vertices\n" +
                "---------------------------------\n" +
                "San Francisco | New York, Chicago\n" +
                "New York      | San Francisco\n" +
                "Chicago       | San Francisco");

        System.out.println("\nGot:");
        graph.removeVertex("London");
        graph.printGraph();
        System.out.println();


        // --------------------------
// Test 6: DFS True
// --------------------------
        System.out.println("-------------------");
        System.out.println("Test 6: DFS True");
        System.out.println("Expected:");
        System.out.println("A\n" +
                "B\n" +
                "E\n" +
                "F\n" +
                "C\n" +
                "true");

        System.out.println("\nGot:");

        Graph graph2 = new Graph();
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addVertex("F");
        graph2.addVertex("G");
        graph2.addVertex("H");
        graph2.addVertex("I");
        graph2.addEdge("A", "D");
        graph2.addEdge("A", "C");
        graph2.addEdge("A", "B");
        graph2.addEdge("D", "I");
        graph2.addEdge("D", "H");
        graph2.addEdge("C", "G");
        graph2.addEdge("B", "F");
        graph2.addEdge("B", "E");

        System.out.println(graph2.depthFirstSearch("A", "G"));
        System.out.println();

// --------------------------
// Test 7: DFS False
// --------------------------
        System.out.println("-------------------");
        System.out.println("Test 7: DFS False");
        System.out.println("Expected:");
        System.out.println("A\n" +
                "B\n" +
                "E\n" +
                "F\n" +
                "C\n" +
                "G\n" +
                "D\n" +
                "H\n" +
                "I\n" +
                "false");

        System.out.println("\nGot:");

        System.out.println(graph2.depthFirstSearch("A", "Z"));
        System.out.println();

        // --------------------------
// Test 10: BFS Pathfinding
// --------------------------
        System.out.println("-------------------");
        System.out.println("Test 10: BFS Pathfinding");
        System.out.println("Expected:");
        System.out.println("ACB\n" +
                "AC\n" +
                "AD\n" +
                "AE\n" +
                "AEF\n" +
                "AIG\n" +
                "AIH\n" +
                "AI\n" +
                "AJ\n" +
                "no path");

        System.out.println("\nGot:");

        Graph graph4 = new Graph();
        graph4.addVertex("A");
        graph4.addVertex("B");
        graph4.addVertex("C");
        graph4.addVertex("D");
        graph4.addVertex("E");
        graph4.addVertex("F");
        graph4.addVertex("G");
        graph4.addVertex("H");
        graph4.addVertex("I");
        graph4.addVertex("J");


        graph4.addEdge("A", "C");
        graph4.addEdge("A", "D");
        graph4.addEdge("A", "E");
        graph4.addEdge("A", "I");
        graph4.addEdge("A", "J");
        graph4.addEdge("B", "C");
        graph4.addEdge("B", "H");
        graph4.addEdge("B", "I");
        graph4.addEdge("D", "I");
        graph4.addEdge("D", "E");
        graph4.addEdge("E", "F");
        graph4.addEdge("F", "G");
        graph4.addEdge("F", "J");
        graph4.addEdge("G", "H");
        graph4.addEdge("G", "I");
        graph4.addEdge("G", "J");
        graph4.addEdge("H", "I");
        graph4.addEdge("H", "J");
        graph4.addEdge("I", "J");

        System.out.println(graph4.breadthFirstSearchWithPath("A", "B"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "C"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "D"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "E"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "F"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "G"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "H"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "I"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "J"));
        System.out.println(graph4.breadthFirstSearchWithPath("A", "Z"));



    }
}

class Vertex {
    String label;
    LinkedList<Vertex> neighbors;

    public Vertex(String label) {
        this.label = label;
        this.neighbors = new LinkedList<>();
    }
}


