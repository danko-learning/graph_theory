package realization;

import java.util.*;

public class UndirectedUnweightedGraph extends UndirectedGraph {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// based
    public UndirectedUnweightedGraph() {
        this.graph = new HashMap<String, List<String>>();
    }
    public UndirectedUnweightedGraph(HashMap<String, List<String>> graph){
        setGraph(graph);
    }
    public UndirectedUnweightedGraph(UndirectedGraph other) {
        if (other.isNormal) {
            this.graph = other.getGraph();
            this.isNormal = other.isNormal;
        } else {
            System.out.println("Переданный вами в конструктор граф был некорректно создан!");
        }
    }
    public UndirectedUnweightedGraph(String path) {
        setGraph(path);
    }

    @Override
    public void setGraph(HashMap<String, List<String>> graph) {
        boolean err = false;

        for (String vertex : graph.keySet()) {
            for (String edge : graph.get(vertex)) {
                if (!graph.containsKey(edge)) {
                    if (this.graph == null) {
                        this.isNormal = false;
                    }
                    err = true;
                    System.out.println("Указана связь с несуществующей вершиной: " + vertex + "-" + edge + "!");
                    break;
                }
            }

            if (err) {
                break;
            }
        }

        if (!err) {
            HashMap<String, List<String>> newGraph = new HashMap<String, List<String>>(graph);

            for (String vertex : newGraph.keySet()) {
                for (String edge : newGraph.get(vertex)) {
                    if (!newGraph.get(edge).contains(vertex)) {
                        newGraph.get(edge).add(vertex);
                    }
                }
            }

            this.graph = newGraph;
        }
    }

    @Override
    public void addVertex(String vertex, List<String> edges) {
        if (this.isNormal) {
            if (!this.graph.containsKey(vertex)) {
                boolean err = false;

                for (String edge : edges) {
                    if (!this.graph.containsKey(edge)) {
                        err = true;
                        System.out.println("Указана связь с несуществующей вершиной: " + vertex + "-" + edge + "!");
                        break;
                    }
                }

                if (!err) {
                    for (String edge : edges) {
                        this.graph.get(edge).add(vertex);
                    }

                    this.graph.put(vertex, edges);
                }
            }
            else {
                System.out.println("В графе уже есть вершина: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    @Override
    public void delVertex(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                this.graph.remove(vertex);

                for (String graphVertex : this.graph.keySet()) {
                    if (this.graph.get(graphVertex).contains(vertex)) {
                        this.graph.get(graphVertex).remove(this.graph.get(graphVertex).indexOf(vertex));
                    }
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
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
                        this.graph.get(vertex).add(edge);
                        this.graph.get(edge).add(vertex);
                    } else {
                        System.out.println("У вершины " + vertex + " уже есть связь " + edge + "!");
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
                if (this.graph.get(vertex).contains(edge)) {
                    this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(edge));
                    this.graph.get(edge).remove(this.graph.get(edge).indexOf(vertex));
                } else {
                    System.out.println("Попытка удалить несуществующую связь: " + vertex + "-" + edge + " !");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// based

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task3
    @Override
    public String fromUAndNotFromV(String vertexU, String vertexV) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertexU))
            {
                if (this.graph.containsKey(vertexV)) {
                    List<String> uEdge = new ArrayList<>(this.graph.get(vertexU));
                    List<String> vEdge = new ArrayList<>(this.graph.get(vertexV));

                    uEdge.removeAll(vEdge);

                    if(uEdge.isEmpty()) {
                        System.out.println("В графе нет подходящих вершин!");
                        return "";
                    } else {
                        return uEdge.get(0);
                    }
                } else {
                    System.out.println("В графе нет вершины: " + vertexV + "!");
                    return "";
                }
            } else {
                System.out.println("В графе нет вершины: " + vertexU + "!");
                return "";
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return "";
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task3

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task5
    @Override
    public List<String> DFS(String vertex) {
        if (this.isNormal) {
            List<String> visited = new ArrayList<>();

            Stack<String> s = new Stack<>();
            s.push(vertex);

            while (!s.isEmpty()) {
                if (!visited.contains(s.peek())) {
                    visited.add(s.peek());
                }
                for (String edge : this.graph.get(s.pop())) {
                    if (!visited.contains(edge) && !s.contains(edge)) {
                        s.push(edge);
                    }
                }
            }

            return visited;
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task5

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task6
    @Override
    public List<String> BFS(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                List<String> visited = new ArrayList<>();
                Queue<String> q = new LinkedList<>();

                visited.add(vertex);
                q.add(vertex);
                while (!q.isEmpty()) {
                    for (String edge : this.graph.get(q.peek())) {
                        if (!visited.contains(edge) && !q.contains(edge)) {
                            q.add(edge);
                        }
                    }
                    visited.add(q.poll());
                }

                return visited;
            } else {
                System.out.println("В графе нет вершины " + vertex + "!");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }

    protected List<Pair> takeShortestPathFromUtoV (String vertexU, String vertexV) {
        List<Pair> visited = new ArrayList<Pair>();
        Queue<Pair> q = new LinkedList<Pair>();
        visited.add(new Pair(vertexU, ""));
        q.add(new Pair(vertexU, ""));

        while(!q.isEmpty()) {
            for (String edge : this.graph.get(q.peek().first)) {
                boolean qFlag = true, vFlag = true;
                for (Pair pair : q) {
                    if (pair.first.equals(edge)) {
                        qFlag = false;
                        break;
                    }
                }

                for (Pair pair : visited) {
                    if (pair.first.equals(edge)) {
                        vFlag = false;
                        break;
                    }
                }

                if (qFlag && vFlag) {
                    q.add(new Pair(edge, q.peek().first));
                }
            }
            visited.add(q.peek());
            if (q.poll().first.equals(vertexV)) {
                break;
            }
        }
        return visited;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task6

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task7
    @Override
    public List<String> DFS(String vertex, HashMap<String, List<String>> graph) {
        System.out.println("Для данного типа графа эта операция не поддерживается!");
        return null;
    }

    @Override
    public HashMap<String, List<String>> krascalsAlgorithm () {
        System.out.println("Для данного типа графа эта операция не поддерживается!");
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task7

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task8
    @Override
    protected boolean findNegativeWeight() {
        System.out.println("Данный тип графа не поддерживает алгоритм Дейкстры!");
        return true;
    }

    @Override
    public HashMap<String, Integer> dijkstrasAlgorithm(String start) {
        System.out.println("Данный тип графа не поддерживает алгоритм Дейкстры!");
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task8

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task9
    @Override
    public HashMap<String, Integer> fordBelmansAlgorithm(String start) {
        System.out.println("Данный тип графа не поддерживает алгоритм Форда-Белмана!");
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task9

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task10
    @Override
    public HashMap<String, Integer> floydsAlgorithm(String start) {
        System.out.println("Данный тип графа не поддерживает алгоритм Флойда!");
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task10
}
