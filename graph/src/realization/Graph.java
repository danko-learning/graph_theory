package realization;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public abstract class Graph {

    protected boolean isNormal = true;
    protected HashMap<String, List<String>> graph;



    public abstract void setGraph(HashMap<String, List<String>> graph);
    public HashMap<String, List<String>> getGraph() {
        return new HashMap<String, List<String>>(this.graph);
    }

    public abstract void addVertex(String name, List<String> edges);
    public abstract void delVertex(String name);
    public abstract Vertex getVertex(String name);

    public abstract void addEdge(String vertex, String edge);
    public abstract void delEdge(String vertex, String edge);
}
