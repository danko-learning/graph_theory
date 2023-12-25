package realization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UndirectedUnweightedGraph extends UndirectedGraph{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////// based
    public UndirectedUnweightedGraph() {
        this.graph = new HashMap<>();
    }
    public UndirectedUnweightedGraph(HashMap<String, List<String>> graph){
        setGraph(graph);
    }
    public UndirectedUnweightedGraph(UndirectedUnweightedGraph uGraph) {
        if (uGraph.getStatus()) {
            this.graph = new HashMap<>(uGraph.getGraph());
            this.isNormal = true;
        } else {
            this.isNormal = false;
            System.out.println("Переданный вами в конструктор граф был некорректно создан, пресоздайте его!");
        }
    }
    public UndirectedUnweightedGraph(String wayToFile) {
        if (readGraphFromFile(wayToFile) != null) {
            setGraph(readGraphFromFile(wayToFile));
        }
    }

    @Override
    public void setGraph(HashMap<String, List<String>> graph) {
        boolean err = false;
        for (String key : graph.keySet()) {
            for (String edge : graph.get(key)) {
                if (!graph.containsKey(edge)) {
                    if (this.graph == null) {
                        this.isNormal = false;
                    }
                    err = true;
                    System.out.println("Указана связь с несуществующей вершиной: " + key + "-" + edge + "!");
                    break;
                }
            }
            if (err) {
                break;
            }
        }

        if (!err) {
            this.graph = new HashMap<String, List<String>>(graph);

            for (String key : this.graph.keySet()) {
                for (String edge : this.graph.get(key)) {
                    if (!this.graph.get(edge).contains(key)) {
                        this.graph.get(edge).add(key);
                    }
                }
            }
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

                for (String edge : edges) {
                    if (!this.graph.containsKey(edge)) {
                        err = true;
                        System.out.println("Указана связь с несуществующей вершиной: " + name + "-" + edge+ "!");
                        break;
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
    public void addEdge(String vertex, String edge) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.containsKey(edge)) {
                    if (!this.graph.get(vertex).contains(edge)) {
                        if (!this.graph.get(vertex).contains(edge)) {
                            this.graph.get(vertex).add(edge);
                            this.graph.get(edge).add(vertex);
                        } else {
                            System.out.println("В графе уже есть вершина" + edge + "!");
                        }
                    } else {
                        System.out.println("В графе уже есть вершина" + edge + "!");
                    }
                } else {
                    System.out.println("Попытка создать связь с несуществующей вершиной: " + vertex + "-" + edge + "!");
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
    @Override
    public int vertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                return this.graph.get(vertex).size();
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
                return -1;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return -1;
        }
    }
    @Override
    public void printVertexesDegree() {
        if (this.isNormal) {
            System.out.println("\nСтепени вершины графа:");
            for (String key : this.graph.keySet()) {
                System.out.println(key + " - " + vertexDegree(key));
            }
            System.out.println();
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    @Override
    public String fromUAndNotFromV(String u, String v) {
        if (this.isNormal) {
            if (this.graph.containsKey(u))
            {
                if (this.graph.containsKey(v)) {
                    List<String> uEdge = new ArrayList<>(this.graph.get(u));
                    List<String> vEdge = new ArrayList<>(this.graph.get(v));

                    uEdge.removeAll(vEdge);

                    if(uEdge.isEmpty()) {
                        System.out.println("В графе нет подходящих вершин!");
                        return "";
                    } else {
                        return uEdge.get(0);
                    }
                } else {
                    System.out.println("В графе нет вершины: " + v + "!");
                    return "";
                }
            } else {
                System.out.println("В графе нет вершины: " + u + "!");
                return "";
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return "";
        }
    }
}
