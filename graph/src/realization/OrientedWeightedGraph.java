package realization;

import java.util.HashMap;
import java.util.List;

public class OrientedWeightedGraph extends DirecredGraph{

    public OrientedWeightedGraph() {
        this.graph = new HashMap<>();
    }
    public OrientedWeightedGraph(HashMap<String, List<String>> graph) {
        setGraph(graph);
    }
    public OrientedWeightedGraph(OrientedWeightedGraph oWGraph) {
        if (oWGraph.getStatus()) {
            this.graph = new HashMap<>(oWGraph.getGraph());
        } else {
            System.out.println("Переданный вами в конструктор граф был некорректно создан, пресоздайте его!");
        }
    }

    @Override
    public void setGraph(HashMap<String, List<String>> graph) {
        boolean err = false;

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
                        if (this.graph == null) {
                            this.isNormal = false;
                        }
                        err = true;
                    }

                    if (!graph.containsKey(edge) || err) {
                        if (this.graph == null) {
                            this.isNormal = false;
                        }
                        err = true;
                        System.out.println("Указана связь с несуществующей вершиной: " + key + "-" + edge + "!");
                        break;
                    }
                }
            }
            if (err) {
                break;
            }
        }

        if (!err) {
            this.graph = new HashMap<>(graph);
        }
    }

    @Override
    public void addVertex(String name, List<String> edges) {
        if (this.isNormal) {
            if (!this.graph.containsKey(name))
            {
                boolean err = false;

                for (String connect : edges) {
                    int iName = connect.indexOf("-");

                    if (iName != -1) {
                        String edge = connect.substring(0, iName);
                        double weight;

                        try {
                            weight = Integer.parseInt(connect.substring(iName, connect.length()));
                        } catch (NumberFormatException numE) {
                            System.out.println("В качестве веса может быть указано только число!");
                            err = true;
                            break;
                        }

                        if (!this.graph.containsKey(edge)) {
                            err = true;
                            System.out.println("Указана связь с несуществующей вершиной: " + name + "-" + edge+ "!");
                            break;
                        }
                    }
                }

                if (!err) {
                    this.graph.put(name, edges);
                }
            } else {
                System.out.println("В графе уже есть вершина: " + name + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    @Override
    public void delVertex(String name) {
        if (this.isNormal) {
            if (this.graph.containsKey(name)) {
                this.graph.remove(name);

                for (String vertex : this.graph.keySet()) {
                    for (String connect : this.graph.get(vertex)) {
                        String edge = connect.substring(0, connect.indexOf("-"));

                        if (edge.equals(name)) {
                            this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(connect));
                        }
                    }
                }
            } else {
                System.out.println("В графе нет вершины: " + name + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    @Override
    public void addEdge(String vertex, String edge) {
        if (this.isNormal) {
            boolean err = false;

            int iName = edge.indexOf("-");
            if (iName != -1) {
                try {
                    String weight = edge.substring(iName, edge.length());
                } catch (NumberFormatException numE) {
                    System.out.println("В качестве веса может быть указано только число!");
                    err = true;
                }
            }

            if (this.graph.containsKey(vertex) && !err) {
                this.graph.get(vertex).add(edge);
            }
        }
    }

    @Override
    public void delEdge(String vertex, String edge) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.containsKey(edge)) {
                    this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(edge));
                } else {
                    System.out.println("Попытка удалить связь с несуществующей вершиной: " + vertex + "-" + edge + "!");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
}
