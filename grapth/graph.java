import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Graph {

    protected Map<String, List<String>> graph;

    public Graph(Map<String, List<String>> graph) {
        
        this.graph = new HashMap<String, List<String>>();
        setGraph(graph);
    }

    public abstract void setGraph(Map<String, List<String>> graph);
    public HashMap<String, List<String>> getGraph() {
        return new HashMap<String, List<String>>(this.graph);
    }

    public abstract void addVertex(String name, List<String> edges);
    public abstract void getVertex(String name);

    public abstract void setEdge();
    public abstract void getEdge();
}

class UndirectedGraph extends Graph {

    public UndirectedGraph(Map<String, List<String>> graph) {
        super(graph);
    }

    @Override
    public void setGraph(Map<String, List<String>> graph) {

        boolean err = false;

        for (String key : graph.keySet()) {

            for (String coms : graph.get(key)) {

                if (!graph.containsKey(coms)) { err = true; break; }
            }

            if (err) { break; }
        }

        if (!err) {
            this.graph = new HashMap<>(graph);
        } else {
            System.out.println("Error!");
        }
    }

    @Override
    public void addVertex(String name, List<String> edges) {

        if (!this.graph.containsKey(name)) {
            
            boolean err = false;
            for (String coms : edges) {
                if (!this.graph.containsKey(coms)) { err = true; break; }
            }

            if (!err) {
                this.graph.put(name, edges);
            } else {
                System.out.println("Error!");
            }
        }
    }
    @Override
    public void getVertex(String name) {
        
    }

    @Override
    public void setEdge() {

    }
    @Override
    public void getEdge() {

    }
}

/*
abstract class DirectedGraph extends Graph{

    public DirectedGraph(Map<String, List<String>> graph) {
        super(graph);
    }
}

class WeightedGraph extends DirectedGraph{

    public WeightedGraph(Map<String, List<String>> graph) {
        super(graph);
    }
}

class UnWeightedGraph extends DirectedGraph {

    public UnWeightedGraph(Map<String, List<String>> graph) {
        super(graph);
    }
}
*/