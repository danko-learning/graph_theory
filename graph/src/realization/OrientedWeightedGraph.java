package realization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    public OrientedWeightedGraph(String wayToFile) {
        if (readGraphFromFile(wayToFile) != null) {
            setGraph(readGraphFromFile(wayToFile));
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
                        weight = Integer.parseInt(connect.substring(iName + 1, connect.length()));
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
                } else {
                    if (this.graph == null) {
                        this.isNormal = false;
                    }
                    err = true;
                    System.out.println("У связи: " + connect + " не указан вес!");
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
    public void setGraph(String wayToFile) {
        if (readGraphFromFile(wayToFile) != null) {
            setGraph(readGraphFromFile(wayToFile));
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

                    Iterator<String> it = this.graph.get(vertex).iterator();
                    while(it.hasNext()) {
                        String connect = it.next();
                        String edge = connect.substring(0, connect.indexOf("-"));
                        if (edge.equals(name)) {
                            it.remove();
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
                    if (!this.graph.containsKey(edge.substring(0, iName))) {
                        System.out.println("Попытка создать связь с несуществующей вершиной: " + vertex + "-" + edge + "!");
                        err = true;
                    }
                } catch (NumberFormatException numE) {
                    System.out.println("В качестве веса может быть указано только число!");
                    err = true;
                }
            }

            if (this.graph.containsKey(vertex) && !err) {
                if (!this.graph.get(vertex).contains(edge)) {
                    this.graph.get(vertex).add(edge);
                } else {
                    System.out.println("В графе уже есть вершина" + edge + "!");
                }
            }
        }
    }

    @Override
    public void delEdge(String vertex, String connect) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.get(vertex).contains(connect)) {
                    this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(connect));
                } else {
                    System.out.println("Попытка удалить несуществующую связь: " + vertex + "-" + connect + "!");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
}
