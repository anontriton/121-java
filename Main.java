import java.util.HashSet;
import java.util.Set;

public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)

	public static void lg_test_addNode() {
		Graph g = new ListGraph();

		assert g.addNode("a");
		assert g.hasNode("a");

		assert !g.hasNode("b");
    }

	public static void lg_test_removeNode() {
		Graph g = new ListGraph();

		assert g.addNode("a");
		assert g.removeNode("a");

		assert g.addNode("b");
		assert g.removeNode("b");

		assert !g.removeNode("a");
		assert !g.removeNode("b");
		assert !g.removeNode("c");
	}

	public static void lg_test_addEdge() {
		Graph g = new ListGraph();

		assert g.addEdge("a", "b");
		assert g.hasEdge("a", "b");

		assert g.addEdge("a", "c");
		assert g.hasEdge("a", "c");

		assert g.addEdge("b", "c");
		assert g.hasEdge("b", "c");

		assert !g.hasEdge("b", "d");
		assert !g.hasEdge("c", "d");
		assert !g.hasEdge("c", "e");
	}

	public static void lg_test_removeEdge() {
		Graph g = new ListGraph();

		assert g.addEdge("a", "b");
		assert g.removeEdge("a", "b");

		assert g.addEdge("a", "c");
		assert g.removeEdge("a", "c");

		assert !g.removeEdge("b", "c");
		assert !g.removeEdge("b", "d");
	}

	public static void lg_test_nodes() {
		Graph g = new ListGraph();
		Set<String> n1 = new HashSet<>();

		n1.add("a");
		n1.add("b");
		n1.add("c");
		n1.add("d");
		n1.add("e");
		n1.add("f");
		n1.add("g");

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");
		assert g.addNode("e");
		assert g.addNode("f");
		assert g.addNode("g");

		Set<String> n2 = new HashSet<>(g.nodes());
		assert n1.equals(n2);

		assert g.addNode("h");
		Set<String> n3 = new HashSet<>(g.nodes());
		assert !n1.equals(n3);
	}

    /*
	public static void test_addEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge e = new Edge("a", "b");
		assert eg.addEdge(e);
		assert eg.hasEdge(e);
    }
     */

    public static void main(String[] args) {
		lg_test_addNode();
		lg_test_removeNode();
		lg_test_addEdge();
		lg_test_removeEdge();
		lg_test_nodes();



		//test_addEdge();
    }

}