package realization;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.*;

public class DirectedWeightedGraph extends DirectedGraph{
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// based
    public DirectedWeightedGraph() {
        this.graph = new HashMap<String, List<String>>();
    }
    public DirectedWeightedGraph(HashMap<String, List<String>> graph){
        setGraph(graph);
    }
    public DirectedWeightedGraph(UndirectedGraph other) {
        if (other.isNormal) {
            this.graph = other.getGraph();
            this.isNormal = other.isNormal;
        } else {
            System.out.println("Переданный вами в конструктор граф был некорректно создан!");
        }
    }
    public DirectedWeightedGraph(String path) {
        setGraph(path);
    }

    @Override
    public void setGraph(HashMap<String, List<String>> graph) {
        boolean err = false;

        for (String vertex : graph.keySet()) {
            for (String connect : graph.get(vertex)) {
                int indexDash = connect.indexOf("-");

                if (indexDash != -1) {
                    if (connect.length() >= indexDash + 1) {
                        String edge = connect.substring(0, indexDash);

                        if (!graph.containsKey(edge)) {
                            if (this.graph == null) {
                                this.isNormal = false;
                            }
                            err = true;
                            System.out.println("Указана связь с несуществующей вершиной: " + vertex + "-" + connect + "!");
                            break;
                        }
                    } else {
                        err = true;
                        System.out.println("В связи " + connect + " не задан вес!");
                    }
                } else {
                    err = true;
                    System.out.println("В связи " + connect + " не задан вес!");
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
    public void addVertex(String vertex, List<String> connects) {
        if (this.isNormal) {
            if (!this.graph.containsKey(vertex)) {
                boolean err = false;

                for (String connect : connects) {
                    int indexDash = connect.indexOf("-");

                    if (indexDash != -1) {
                        if (connect.length() >= indexDash + 1) {
                            String edge = connect.substring(0, indexDash);

                            if (!graph.containsKey(edge)) {
                                err = true;
                                System.out.println("Указана связь с несуществующей вершиной: " + vertex + "-" + connect + "!");
                                break;
                            }
                        } else {
                            err = true;
                            System.out.println("В связи " + connect + " не задан вес!");
                        }
                    } else {
                        err = true;
                        System.out.println("В связи " + connect + " не задан вес!");
                    }
                }

                if (!err) {
                    this.graph.put(vertex, connects);
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
                    for (String connect : this.graph.get(graphVertex)) {
                        String edge = connect.substring(0, connect.indexOf("-"));

                        if (vertex.equals(edge)) {
                            this.graph.get(graphVertex).remove(this.graph.get(graphVertex).indexOf(connect));
                            break;
                        }
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
    public void addEdge(String vertex, String connect) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                int indexDash = connect.indexOf("-");

                if (indexDash != -1)
                {
                    if (connect.length() >= indexDash + 1)
                    {
                        String edge = connect.substring(0, indexDash);

                        if (this.graph.containsKey(edge)) {
                            if (!this.graph.get(vertex).contains(connect)) {
                                this.graph.get(vertex).add(connect);
                            } else {
                                System.out.println("У вершины " + vertex + " уже есть связь " + connect + "!");
                            }
                        } else {
                            System.out.println("Попытка создать связь с несуществующей вершиной: " + vertex + "-" + connect + "!");
                        }
                    } else {
                        System.out.println("В связи " + connect + " не задан вес!");
                    }
                } else {
                    System.out.println("В связи " + connect + " не задан вес!");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    @Override
    public void delEdge(String vertex, String connect) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                if (this.graph.get(vertex).contains(connect)) {
                    this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(connect));
                } else {
                    System.out.println("Попытка удалить несуществующую связь: " + vertex + "-" + connect + " !");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// based

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2
    @Override
    public int inVertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                int inDegree = 0;

                for (String key : this.graph.keySet()) {
                    if (key.equals(vertex)) {
                        continue;
                    } else {
                        for (String connect : this.graph.get(vertex)) {
                            if (connect.substring(0, connect.indexOf("-")).equals(vertex)) {
                                inDegree += 1;
                                break;
                            }
                        }
                    }
                }
                return inDegree;
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
                return -1;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return -1;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task3
    @Override
    public String fromUAndNotFromV(String vertexU, String vertexV) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertexU)) {
                if (this.graph.containsKey(vertexV)) {
                    List<String> uEdge = new ArrayList<>();
                    for (String conn : this.graph.get(vertexU)) {
                        String edge = conn.substring(0, conn.indexOf("-"));
                        uEdge.add(edge);
                    }

                    List<String> vEdge = new ArrayList<>();
                    for (String conn : this.graph.get(vertexV)) {
                        String edge = conn.substring(0, conn.indexOf("-"));
                        vEdge.add(edge);
                    }

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
                for (String connect : this.graph.get(s.pop())) {
                    String edge = connect.substring(0, connect.indexOf("-"));

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
                    for (String connect : this.graph.get(q.peek())) {
                        String edge = connect.substring(0, connect.indexOf("-"));

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
            for (String connect : this.graph.get(q.peek().first)) {
                String edge = connect.substring(0, connect.indexOf("-"));

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
        if (this.isNormal) {
            for (String vertex : this.graph.keySet()) {
                for (String connect : this.graph.get(vertex)) {
                    String wght = connect.substring(connect.indexOf("-") + 1, connect.length());
                    int weight = Integer.parseInt(wght);

                    if (weight < 0) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return true;
        }
    }

    @Override
    public HashMap<String, Integer> dijkstrasAlgorithm(String start) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                if (!this.findNegativeWeight()) {
                    HashMap<String, Integer> pathWeights = new HashMap<String, Integer>();
                    List<String> visited = new ArrayList<String>();

                    for (String vertex : this.graph.keySet()) {
                        if (vertex.equals(start)) {
                            pathWeights.put(vertex, 0);
                            continue;
                        }

                        pathWeights.put(vertex, Integer.MAX_VALUE);
                    }

                    visited.add(start);
                    for (String connect : this.graph.get(start)) {
                        int indexDash = connect.indexOf("-");
                        String edge = connect.substring(0, indexDash);
                        int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));

                        if (!edge.equals(visited)) {
                            pathWeights.put(edge, weight);
                        }
                    }

                    while (visited.size() != this.graph.keySet().size()) {
                        int minWeight = Integer.MAX_VALUE;
                        String currentVertex = "";

                        for (String vertex : pathWeights.keySet()) {
                            if (minWeight > pathWeights.get(vertex) && !visited.contains(vertex)) {
                                minWeight = pathWeights.get(vertex);
                                currentVertex = vertex;
                            }
                        }

                        if (currentVertex.equals("")) {
                            return pathWeights;
                        }

                        visited.add(currentVertex);
                        for (String connect : this.graph.get(currentVertex)) {
                            int indexDash = connect.indexOf("-");
                            String edge = connect.substring(0, indexDash);
                            int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));

                            if (minWeight + weight < pathWeights.get(edge)) {
                                pathWeights.put(edge, minWeight + weight);
                            }
                        }
                    }

                    return pathWeights;
                } else {
                    System.out.println("Алгоритм Дейкстры не может работать с отрицательными весами!");
                    return null;
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task8

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task9
    @Override
    public HashMap<String, Integer> fordBelmansAlgorithm(String start) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                HashMap<String, Integer> pathWeights = new HashMap<String, Integer>();
                HashMap<String, Integer> temp;
                int numberOfIteration = this.graph.keySet().size() - 1;

                for (String vertex : this.graph.keySet()) {
                    pathWeights.put(vertex, 99999);
                }
                pathWeights.put(start, 0);

                for (int i = 0; i < numberOfIteration; ++i) {
                    for (String vertex : this.graph.keySet()) {
                        for (String connect : this.graph.get(vertex)) {
                            int indexDash = connect.indexOf("-");
                            String edge = connect.substring(0, indexDash);
                            int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));

                            if (edge.equals(start)) {
                                continue;
                            }

                            if (pathWeights.get(edge) > (pathWeights.get(vertex) + weight)) {
                                pathWeights.put(edge, (pathWeights.get(vertex) + weight));
                            }
                        }
                    }
                }

                temp = new HashMap<String, Integer>(pathWeights);
                for (String vertex : this.graph.keySet()) {
                    for (String connect : this.graph.get(vertex)) {
                        int indexDash = connect.indexOf("-");
                        String edge = connect.substring(0, indexDash);
                        int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));

                        if (edge.equals(start)) {
                            continue;
                        }

                        if (temp.get(edge) > temp.get(vertex) + weight) {
                            temp.put(edge, pathWeights.get(vertex) + weight);
                        }
                    }
                }

                if (pathWeights.equals(temp)) {
                    return pathWeights;
                } else {
                    System.out.println("В графе присутствует отрицательный цикл!");
                    return null;
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task9

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task10
    public HashMap<String, Integer> floydsAlgorithm(String start) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                HashMap<String, HashMap<String, Integer>> matrix = new HashMap<String, HashMap<String, Integer>>();

                for (String vertex : this.graph.keySet()) {
                    matrix.put(vertex, new HashMap<String, Integer>());

                    for (String _vertex : this.graph.keySet()) {
                        if (vertex.equals(_vertex)) {
                            matrix.get(vertex).put(_vertex, 0);
                        } else {
                            matrix.get(vertex).put(_vertex, 99999);
                        }
                    }
                }

                for (String vertex : this.graph.keySet()) {
                    for (String connect : this.graph.get(vertex)) {
                        String edge = connect.substring(0, connect.indexOf("-"));
                        int weight = Integer.parseInt(connect.substring(connect.indexOf("-") + 1, connect.length()));

                        matrix.get(vertex).put(edge, weight);
                    }
                }

                for (String vertex : this.graph.keySet()) {
                    for (String matrixVertex : matrix.keySet()) {
                        for (String edge : matrix.get(matrixVertex).keySet()) {
                            if (matrix.get(matrixVertex).get(edge) >
                                    matrix.get(matrixVertex).get(vertex) + matrix.get(vertex).get(edge)) {
                                matrix.get(matrixVertex).put(edge, matrix.get(matrixVertex).get(vertex) + matrix.get(vertex).get(edge));
                            }
                        }
                    }
                }

                return matrix.get(start);
            } else {
                System.out.println("В графе нет вершины " + start + "!");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task10

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task11
    @Override
    public int findMaximumFlow(String start, String end) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                if (this.graph.containsKey(end)) {
                    int maximumFlow = 0;

                    HashMap<String, HashMap<String, Integer>> web = new HashMap<String, HashMap<String, Integer>>();

                    for (String vertex : this.graph.keySet()) {
                        web.put(vertex, new HashMap<String, Integer>());

                        for (String connection : this.graph.get(vertex)) {
                            int dash = connection.indexOf("-");
                            String edge = connection.substring(0, dash);
                            int weight = Integer.parseInt(connection.substring(dash + 1, connection.length()));

                            web.get(vertex).put(edge, weight);
                        }
                    }

                    while (true) {
                        HashMap<String, Pair_> way = new HashMap<String, Pair_>();
                        String currentVertex = start, oldVertex = start;
                        ArrayList<String> visited = new ArrayList<String>();

                        while (!currentVertex.equals(end)) {
                            HashMap<String, Integer> edges = web.get(currentVertex);
                            oldVertex = currentVertex;

                            int maximum = Integer.MIN_VALUE;
                            for (String edge : edges.keySet()) {
                                if (maximum < edges.get(edge) && !visited.contains(edge) && !way.containsKey(edge) && !edge.equals(start)) {
                                    maximum = edges.get(edge);
                                    currentVertex = edge;
                                }
                            }

                            if (oldVertex.equals(currentVertex)) {
                                if (oldVertex.equals(start)) {
                                    return maximumFlow;
                                } else {
                                    String temp = currentVertex;
                                    currentVertex = way.get(currentVertex).vertex;
                                    visited.add(temp);
                                    way.remove(temp);
                                    continue;
                                }
                            }

                            way.put(currentVertex, new Pair_(oldVertex, maximum));
                        }

                        int minimum = Integer.MAX_VALUE;
                        for (String edge : way.keySet()) {
                            if (minimum > way.get(edge).weight) {
                                minimum = way.get(edge).weight;
                            }
                        }

                        maximumFlow += minimum;

                        for (String edge : way.keySet()) {
                            int newWeight = web.get(way.get(edge).vertex).get(edge) - minimum;
                            web.get(way.get(edge).vertex).put(edge, newWeight);
                            if (newWeight == 0) {web.get(way.get(edge).vertex).remove(edge);}

                            if (web.get(edge).containsKey(way.get(edge).vertex)) {
                                newWeight = web.get(edge).get(way.get(edge).vertex) + minimum;
                                web.get(edge).put(way.get(edge).vertex, newWeight);
                            } else {
                                web.get(edge).put(way.get(edge).vertex, minimum);
                            }
                        }
                    }
                } else {
                    System.out.println("В графе нет вершины " + end + "!");
                    return -1;
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
                return -1;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return -1;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task11
}
