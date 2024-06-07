import java.util.*;

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

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");

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

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");

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

	public static void lg_test_succ(String n) {
		Graph g = new ListGraph();
		List<String> n1 = new LinkedList<>();

		n1.add("b");
		n1.add("c");
		n1.add("d");

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");

		assert g.addEdge("a", "b");
		assert g.hasEdge("a", "b");

		assert g.addEdge("a", "c");
		assert g.hasEdge("a", "c");

		assert g.addEdge("a", "d");
		assert g.hasEdge("a", "d");

		List<String> n2 = g.succ(n);
		assert n1.equals(n2);
	}

	public static void lg_test_pred(String n) {
		Graph g = new ListGraph();
		List<String> n1 = new LinkedList<>();

		n1.add("a");
		n1.add("b");
		n1.add("c");

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");

		assert g.addEdge("a", "d");
		assert g.hasEdge("a", "d");

		assert g.addEdge("b", "d");
		assert g.hasEdge("b", "d");

		assert g.addEdge("c", "d");
		assert g.hasEdge("c", "d");

		List<String> n2 = g.pred(n);
		assert n1.equals(n2);
	}

	public static void lg_test_union() {
		Graph g1 = new ListGraph();
		Graph g2 = new ListGraph();

		Set<String> n1 = new HashSet<>();
		List<String> n2 = new LinkedList<>();
		List<String> n3 = new LinkedList<>();

		n1.add("a");
		n1.add("b");
		n1.add("c");
		n1.add("d");
		n1.add("e");
		n1.add("f");

		n2.add("b");
		n2.add("c");
		n2.add("d");

		n3.add("b");
		n3.add("c");
		n3.add("e");

		assert g1.addNode("a");
		assert g1.addNode("b");
		assert g1.addNode("c");
		assert g1.addNode("d");

		assert g2.addNode("b");
		assert g2.addNode("c");
		assert g2.addNode("e");
		assert g2.addNode("f");

		assert g1.addEdge("a", "b");
		assert g1.addEdge("a", "c");
		assert g1.addEdge("a", "d");

		assert g2.addEdge("b", "f");
		assert g2.addEdge("c", "f");
		assert g2.addEdge("e", "f");

		Graph unionG = g1.union(g2);
		Set<String> unionN1 = new HashSet<>(unionG.nodes());
		List<String> unionN2 = unionG.succ("a");
		List<String> unionN3 = unionG.pred("f");

		assert n1.equals(unionN1);
		assert n2.equals(unionN2);
		assert n3.equals(unionN3);
	}

	public static void lg_test_subGraph() {
		Graph g = new ListGraph();

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");

		assert g.addEdge("a", "b");
		assert g.addEdge("a", "c");
		assert g.addEdge("c", "d");

		Set<String> subNodes = new HashSet<>();
		subNodes.add("a");
		subNodes.add("b");
		subNodes.add("c");
		subNodes.add("e");

		Graph subG = g.subGraph(subNodes);
		Set<String> n1 = new HashSet<>();
		List<String> n2 = new LinkedList<>();

		n1.add("a");
		n1.add("b");
		n1.add("c");

		n2.add("b");
		n2.add("c");

		Set<String> subGN = new HashSet<>(subG.nodes());
		assert n1.equals(subGN);
		assert n2.equals(subG.succ("a"));
	}

	public static void lg_test_connected() {
		Graph g = new ListGraph();

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");

		assert g.addEdge("a", "b");
		assert g.addEdge("a", "c");
		assert g.addEdge("c", "d");

		assert g.connected("a", "d");
		assert g.connected("a", "a");
		assert g.connected("c", "d");

		assert !g.connected("b", "d");
		assert !g.connected("d", "a");
	}

	/* ******************* */

	public static void eg_test_addEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		Edge e = new Edge("a", "b");

		assert g.addNode("a");
		assert g.addNode("b");

		assert eg.addEdge(e);
		assert eg.hasEdge(e);
	}

	public static void eg_test_hasNode() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		Edge e = new Edge("a", "b");
		assert !eg.hasNode("a");
		assert !eg.hasNode("b");

		assert g.addNode("a");
		assert g.addNode("b");
		eg.addEdge(e);
		assert eg.hasNode("a");
		assert eg.hasNode("b");
	}

	public static void eg_test_hasEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		Edge e = new Edge("a", "b");
		assert !eg.hasEdge(e);

		assert g.addNode("a");
		assert g.addNode("b");
		eg.addEdge(e);
		assert eg.hasEdge(e);
	}

	public static void eg_test_removeEdge() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		assert g.addNode("a");
		assert g.addNode("b");

		Edge e = new Edge("a", "b");
		eg.addEdge(e);
		assert eg.hasEdge(e);

		eg.removeEdge(e);
		assert !eg.hasEdge(e);
	}

	public static void eg_test_outEdges() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");

		Edge e1 = new Edge("a", "b");
		Edge e2 = new Edge("a", "c");

		eg.addEdge(e1);
		eg.addEdge(e2);

		List<Edge> outE = eg.outEdges("a");
		assert outE.contains(e1);
		assert outE.contains(e2);
	}

	public static void eg_test_inEdges() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");

		Edge e1 = new Edge("a", "b");
		Edge e2 = new Edge("c", "b");
		eg.addEdge(e1);
		eg.addEdge(e2);

		List<Edge> inE = eg.inEdges("b");
		assert inE.contains(e1);
		assert inE.contains(e2);
	}

	public static void eg_test_edges() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");

		Edge e1 = new Edge("a", "b");
		Edge e2 = new Edge("c", "d");
		eg.addEdge(e1);
		eg.addEdge(e2);

		List<Edge> edges = eg.edges();
		assert edges.contains(e1);
		assert edges.contains(e2);
	}

	public static void eg_test_union() {
		Graph g1 = new ListGraph();
		EdgeGraph eg1 = new EdgeGraphAdapter(g1);

		assert g1.addNode("a");
		assert g1.addNode("b");
		assert g1.addNode("c");

		eg1.addEdge(new Edge("a", "b"));
		eg1.addEdge(new Edge("a", "c"));

		Graph g2 = new ListGraph();
		EdgeGraph eg2 = new EdgeGraphAdapter(g2);

		assert g2.addNode("b");
		assert g2.addNode("c");
		assert g2.addNode("d");

		eg2.addEdge(new Edge("b", "d"));
		eg2.addEdge(new Edge("c", "d"));

		EdgeGraph unionEG = eg1.union(eg2);
		List<Edge> unionE = unionEG.edges();

		assert unionE.contains(new Edge("a", "b"));
		assert unionE.contains(new Edge("a", "c"));
		assert unionE.contains(new Edge("b", "d"));
		assert unionE.contains(new Edge("c", "d"));
	}

	public static void eg_test_hasPath() {
		Graph g = new ListGraph();
		EdgeGraph eg = new EdgeGraphAdapter(g);

		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");

		Edge e1 = new Edge("a", "b");
		Edge e2 = new Edge("b", "c");
		eg.addEdge(e1);
		eg.addEdge(e2);

		List<Edge> path = new LinkedList<>();
		path.add(e1);
		path.add(e2);

		assert eg.hasPath(path);

		path.add(new Edge("c", "d")); // this edge doesn't exist in graph
		assert !eg.hasPath(path);
	}


    public static void main(String[] args) {
		lg_test_addNode();
		lg_test_removeNode();
		lg_test_addEdge();
		lg_test_removeEdge();
		lg_test_nodes();
		lg_test_succ("a");
		lg_test_pred("d");
		lg_test_union();
		lg_test_subGraph();
		lg_test_connected();

		eg_test_addEdge();
		eg_test_hasNode();
		eg_test_hasEdge();
		eg_test_removeEdge();
		eg_test_outEdges();
		eg_test_inEdges();
		eg_test_edges();
		eg_test_union();
		eg_test_hasPath();
    }

}