package realization;

import java.util.HashMap;
import java.util.List;

public class OrientedUnweightedGraph extends DirecredGraph{

    public OrientedUnweightedGraph(HashMap<String, List<String>> graph) {
        setGraph(graph);
    }

    public OrientedUnweightedGraph(OrientedUnweightedGraph oUGraph) {
        if (oUGraph.getStatus()) {
            this.graph = new HashMap<>(oUGraph.getGraph());
        } else {
            System.out.println("Переданный вами в конструктор граф был некорректно создан, пресоздайте его!");
        }
    }

    public boolean getStatus() {
        return this.isNormal;
    }

    @Override
    public void setGraph(HashMap<String, List<String>> graph) {
        for (String key : graph.keySet()) {
            for (String connect : graph.get(key)) {
                int iName = connect.indexOf("-");
                if (iName != -1)
                {
                    String edge = connect.substring(0, iName);
                    double weight;
                    try {
                        weight = Integer.parseInt(connect.substring(iName, connect.length()));
                    } catch (NumberFormatException numE) {
                        System.out.println("В качестве веса может быть указано только число!");
                    } finally {
                        this.isNormal = false;
                    }
                    if (!graph.containsKey(edge) && this.isNormal) {
                        this.isNormal = false;
                        System.out.println("Указана связь с несуществующей вершиной: " + key + "-" + edge + "!");
                        break;
                    }
                }
            }
            if (!this.isNormal) {
                break;
            }
        }

        if (this.isNormal) {
            this.graph = new HashMap<>(graph);
        }
    }

    @Override
    public void addVertex(String name, List<String> edges) {

    }

    @Override
    public void delVertex(String name) {

    }

    @Override
    public Vertex getVertex(String name) {
        return null;
    }

    @Override
    public void addEdge(String vertex, String edge) {

    }

    @Override
    public void delEdge(String vertex, String edge) {

    }
}
