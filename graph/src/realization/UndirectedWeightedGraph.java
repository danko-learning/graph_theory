package realization;

import java.util.HashMap;
import java.util.List;

public class UndirectedWeightedGraph extends UndirectedGraph {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////// based
    public UndirectedWeightedGraph() {
        this.graph = new HashMap<>();
    }
    public UndirectedWeightedGraph(HashMap<String, List<String>> graph){
        setGraph(graph);
    }
    public UndirectedWeightedGraph(UndirectedUnweightedGraph uGraph) {
        if (uGraph.getStatus()) {
            this.graph = new HashMap<>(uGraph.getGraph());
            this.isNormal = true;
        } else {
            this.isNormal = false;
            System.out.println("Переданный вами в конструктор граф был некорректно создан, пресоздайте его!");
        }
    }
    public UndirectedWeightedGraph(String wayToFile) {
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
            this.graph = new HashMap<String, List<String>>(graph);

            for (String key : this.graph.keySet()) {
                for (String connect : this.graph.get(key)) {
                    String edge = connect.substring(0, connect.indexOf("-"));

                    if (!this.graph.get(edge).contains(key)) {
                        this.graph.get(edge).add(key);
                    }
                }
            }

            this.isNormal = true;
        }
    }
    public void setGraph(UndirectedUnweightedGraph uUW) {
        if (uUW.getStatus()) {
            this.graph = uUW.getGraph();
            this.isNormal = true;
        }
        else {
            if (this.graph == null) {
                this.isNormal = false;
            }
        }
    }

    @Override
    public void addVertex(String name, List<String> edges) {
        if (this.isNormal) {
            if (!this.graph.containsKey(name)) {

                boolean err = false;

                for (String connect : edges) {
                    int iName = connect.indexOf("-");

                    if (iName != -1)
                    {
                        String edge = connect.substring(0, iName);

                        if (!this.graph.containsKey(edge)) {
                            err = true;
                            System.out.println("Указана связь с несуществующей вершиной: " + name + "-" + edge+ "!");
                            break;
                        }
                    } else {
                        System.out.println("У связи: " + connect + " не указан вес!");
                    }
                }

                if (!err) {
                    this.graph.put(name, edges);

                    for (String edge : edges) {
                        if (!this.graph.get(edge).contains(name))
                        {
                            this.graph.get(edge).add(name);
                        }
                    }
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

                for (String key : this.graph.keySet()) {
                    if (this.graph.get(key).contains(name)) {
                        this.graph.get(key).remove(this.graph.get(key).indexOf(name));
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
    public void addEdge(String vertex, String connect) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                int iName = connect.indexOf("-");

                if (iName != -1) {
                    String edge = connect.substring(0, iName);

                    if (this.graph.containsKey(edge)) {
                        if (!this.graph.get(vertex).contains(connect)) {
                            this.graph.get(vertex).add(connect);
                            this.graph.get(edge).add(vertex + connect.substring(iName + 1, connect.length()));
                        } else {
                            System.out.println("В графе уже есть вершина" + edge + "!");
                        }
                    } else {
                        System.out.println("Попытка создать связь с несуществующей вершиной: " + vertex + "-" + edge + "!");
                    }
                } else {
                    System.out.println("Некорректная связь " + connect + "!");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    @Override
    public void delEdge(String vertex, String edge) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.containsKey(edge)) {
                    if (this.graph.get(vertex).contains(edge)) {
                        this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(edge));
                        this.graph.get(edge).remove(this.graph.get(edge).indexOf(vertex));
                    } else {
                        System.out.println("Попытка удалить несуществующую связь!");
                    }

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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////// based
}
