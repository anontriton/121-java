import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

    private Graph g;

    public EdgeGraphAdapter(Graph g) { this.g = g; }

    public boolean addEdge(Edge e) {
        if (!g.hasNode(e.getSrc())) { g.addNode(e.getSrc()); }
        if (!g.hasNode(e.getDst())) { g.addNode(e.getDst()); }

        return g.addEdge(e.getSrc(), e.getDst());
    }

    public boolean hasNode(String n) {
        return g.hasNode(n);
    }

    public boolean hasEdge(Edge e) {
        return g.hasEdge(e.getSrc(), e.getDst());
    }

    public boolean removeEdge(Edge e) {
        return g.removeEdge(e.getSrc(), e.getDst());
    }

    public List<Edge> outEdges(String n) {
        List<String> successors = g.succ(n);
        List<Edge> outEdges = new LinkedList<>();

        for (String s : successors) {
            outEdges.add(new Edge(n, s));
        }

        return outEdges;
    }

    public List<Edge> inEdges(String n) {
        List<String> predecessors = g.pred(n);
        List<Edge> inEdges = new LinkedList<>();

        for (String pred : predecessors) {
            inEdges.add(new Edge(pred, n));
        }

        return inEdges;
    }

    public List<Edge> edges() {
        List<Edge> e = new LinkedList<>();

        for (String node : g.nodes()) {
            for (String s : g.succ(node)) {
                e.add(new Edge(node, s));
            }
        }

        return e;
    }

    public EdgeGraph union(EdgeGraph g) {
        Graph g1 = new ListGraph();
        Graph g2 = new ListGraph();

        // add nodes
        for (Edge e : this.edges()) {
            g1.addNode(e.getSrc());
            g1.addNode(e.getDst());
        }
        for (Edge e : g.edges()) {
            g2.addNode(e.getSrc());
            g2.addNode(e.getDst());
        }

        // add edges
        for (Edge e : this.edges()) {
            g1.addEdge(e.getSrc(), e.getDst());
        }
        for (Edge e : g.edges()) {
            g2.addEdge(e.getSrc(), e.getDst());
        }

        Graph unionG = g1.union(g2);
        return new EdgeGraphAdapter(unionG);
    }

    public boolean hasPath(List<Edge> e) {
        if (e.isEmpty()) { return true; }

        for (int i = 0; i < e.size() - 1; i++) {
            Edge curr = e.get(i);
            Edge next = e.get(i + 1);
            if (!curr.getDst().equals(next.getSrc())) {
                throw new BadPath();
            }
        }

        for (Edge edge : e) {
            if (!hasEdge(edge)) { return false; }
        }

        return true;
    }
}
