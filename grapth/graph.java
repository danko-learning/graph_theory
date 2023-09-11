import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Graph {

    private Map<String, List<String>> graph;

    public Graph(Map<String, List<String>> graph) {
        
        this.graph = new HashMap<String, List<String>>();
        setGraph(graph);
    }

    public abstract void setGraph(Map<String, List<String>> graph);
    public abstract HashMap<String, List<String>> getGraph();

    public abstract void setVertex(String vertex);
    public abstract void getVertex();

    public abstract void setBound();
    public abstract void getBound();
}

class UndirectedGraph extends Graph {

    public UndirectedGraph(Map<String, List<String>> graph) {
        super(graph);
    }
}


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