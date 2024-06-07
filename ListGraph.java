import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

    public boolean addNode(String n) {
	     if (hasNode(n)) { return false; }
         nodes.put(n, new LinkedList<>());
         return true;
    }

    public boolean addEdge(String n1, String n2) {
        if (!hasNode(n1) || !hasNode(n2)) {
            throw new NoSuchElementException();
        }

        LinkedList<String> list = nodes.get(n1);
        if (!list.contains(n2)) {
            list.add(n2);
            return true;
        }
        return false;
    }

    public boolean hasNode(String n) {
        return nodes.containsKey(n);
    }

    public boolean hasEdge(String n1, String n2) {
        if (!nodes.containsKey(n1)) { return false; }
        LinkedList<String> list = nodes.get(n1);
        return list.contains(n2);
    }

    public boolean removeNode(String n) {
        if (hasNode(n)) {
            nodes.remove(n);
            // remove all edges to this node
            for (String node : nodes.keySet()) {
                nodes.get(node).remove(n);
            }
            return true;
        }
        return false;
    }

    public boolean removeEdge(String n1, String n2) {
        if (hasEdge(n1, n2)) {
            LinkedList<String> list = nodes.get(n1);
            list.remove(n2);
            return true;
        }
        return false;
    }

    public List<String> nodes() {
	     if (nodes.isEmpty()) { throw new RuntimeException("graph is empty"); }
         return new LinkedList<>(nodes.keySet());
    }

    public List<String> succ(String n) {
        if (!nodes.containsKey(n)) { throw new NoSuchElementException(); }
        return nodes.get(n);
    }

    public List<String> pred(String n) {
        if (!nodes.containsKey(n)) {
            throw new NoSuchElementException();
        }

        List<String> predecessors = new LinkedList<>();
        for (String node : nodes.keySet()) {
            if (nodes.get(node).contains(n)) {
                predecessors.add(node);
            }
        }

        return predecessors;
    }

    public Graph union(Graph g) {
        Graph newG = new ListGraph();

        // add nodes from the current graph to new graph
        for (String node : this.nodes()) {
            newG.addNode(node);
        }

        // add nodes from graph g to new graph
        for (String node : g.nodes()) {
            newG.addNode(node);
        }

        // add edges from current graph to new graph
        for (String node : this.nodes()) {
            for (String successor : this.succ(node)) {
                newG.addEdge(node, successor);
            }
        }

        // add edges from graph g to new graph
        for (String node : g.nodes()) {
            for (String successor : g.succ(node)) {
                newG.addEdge(node, successor);
            }
        }

        return newG;
    }

    public Graph subGraph(Set<String> nodes) {
        Graph subG = new ListGraph();

        // add nodes to new subgraph
        for (String node : nodes) {
            if (this.hasNode(node)) {
                subG.addNode(node);
            }
        }

        // add edges for nodes in new subgraph if they exist
        for (String node : nodes) {
            if (this.hasNode(node)) {
                for (String successor : this.succ(node)) {
                    if (nodes.contains(successor)) {
                        subG.addEdge(node, successor);
                    }
                }
            }
        }

        return subG;
    }

    public boolean connected(String n1, String n2) {
        if (!hasNode(n1) || !hasNode(n2)) {
            throw new NoSuchElementException();
        }

        if (n1.equals(n2)) { return true; }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(n1);

        // use BFS
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(n2)) { return true; }
            visited.add(curr);
            for (String neighbor : succ(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }
}
