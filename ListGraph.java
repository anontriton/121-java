import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

    public boolean addNode(String n) {
	     if (hasNode(n)) {
             return false;
         }

         nodes.put(n, new LinkedList<>());
         return true;
    }

    public boolean addEdge(String n1, String n2) {
	     if (!hasNode(n1)) {
             nodes.put(n1, new LinkedList<>());
         }
         if (!hasNode(n2)) {
             nodes.put(n2, new LinkedList<>());
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
        if (!nodes.containsKey(n1)) {
            return false;
        }
        LinkedList<String> list = nodes.get(n1);
        return list.contains(n2);
    }

    public boolean removeNode(String n) {
        if (hasNode(n)) {
            nodes.remove(n);
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
	     if (nodes.isEmpty()) {
             throw new RuntimeException("Graph is empty");
         }

         return new ArrayList<>(nodes.keySet());
    }

    public List<String> succ(String n) {
	     throw new UnsupportedOperationException();
    }

    public List<String> pred(String n) {
	     throw new UnsupportedOperationException();
    }

    public Graph union(Graph g) {
	     throw new UnsupportedOperationException();
    }

    public Graph subGraph(Set<String> nodes) {
	     throw new UnsupportedOperationException();
    }

    public boolean connected(String n1, String n2) {
	     throw new UnsupportedOperationException();
    }
}
