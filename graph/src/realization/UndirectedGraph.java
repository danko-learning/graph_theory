package realization;

import java.io.IOError;
import java.util.HashMap;
import java.util.List;

public class UndirectedGraph extends Graph{

    public UndirectedGraph(HashMap<String, List<String>> graph){
        setGraph(graph);
    }

    public UndirectedGraph(UndirectedGraph uGraph) {
        if (uGraph.getStatus()) {
            this.graph = new HashMap<>(uGraph.getGraph());
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
            for (String edge : graph.get(key)) {
                if (!graph.containsKey(edge)) {
                    this.isNormal = false;
                    System.out.println("Указана связь с несуществующей вершиной: " + key + "-" + edge + "!");
                    break;
                }
            }
            if (!this.isNormal) {
                break;
            }
        }

        if (this.isNormal) {
            this.graph = new HashMap<>(graph);

            for (String key : this.graph.keySet()) {
                for (String edge : this.graph.get(key)) {
                    if (!this.graph.get(edge).contains(key)) {
                        this.graph.get(edge).add(key);
                    }
                }
            }
        }
    }

    @Override
    public void addVertex(String name, List<String> edges) {
        if (this.isNormal) {
            if (!this.graph.containsKey(name)) {

                boolean err = false;

                for (String edge : graph.get(name)) {
                    if (!graph.containsKey(edge)) {
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
            }
            else {
                System.out.println("В графе уже есть вершина: " + name + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }

    }
    @Override
    public void delVertex(String name) {
        if (this.isNormal)
        {
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
    public Vertex getVertex(String name) {
        if (this.isNormal) {
            if (this.graph.containsKey(name)) {
                return new Vertex(name, this.graph.get(name));
            } else {
                System.out.println("В графе нет вершины: " + name + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
        return null;
    }

    @Override
    public void addEdge(String vertex, String edge) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.containsKey(edge)) {
                    this.graph.get(vertex).add(edge);
                    this.graph.get(edge).add(vertex);
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
                    this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(edge));
                    this.graph.get(edge).remove(this.graph.get(edge).indexOf(vertex));
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
