package realization;

import java.io.IOError;
import java.util.HashMap;
import java.util.List;

public class UndirectedGraph extends Graph{

    public UndirectedGraph(HashMap<String, List<String>> graph){
        setGraph(graph);
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
        if (isNormal) {
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
        if (isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.containsKey(edge)) {
                    this.graph.get(vertex).add(edge);
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

        if (this.graph.containsKey(vertex)) {
            try {
                this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(edge));
            } catch(IndexOutOfBoundsException iOOBE) {
                System.out.println(iOOBE.getMessage());
            }
        } else {
            throw new IllegalArgumentException("В графе нет такой вершины!");
        }
    }
}