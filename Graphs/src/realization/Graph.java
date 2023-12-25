package realization;

import org.w3c.dom.ls.LSException;

import java.io.*;
import java.util.*;

public abstract class Graph {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// based
    protected boolean isNormal = true;
    
    protected HashMap<String, List<String>> graph;

    protected HashMap<String, List<String>> readGraphFromFile(String path) {
        HashMap<String, List<String>> graph = new HashMap<String, List<String>>();

        File file = new File(path);
        FileReader fr;

        try {
            fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                int inedxColon = line.indexOf(":");

                if (inedxColon != -1) {
                    String vertex = line.substring(0, inedxColon);

                    if (line.length() != inedxColon + 1) {
                        String edges = line.substring(inedxColon + 1, line.length());
                        graph.put(vertex, new ArrayList<>(Arrays.asList(edges.split(" "))));
                    } else {
                        graph.put(vertex, new ArrayList<>());
                    }

                    line = reader.readLine();
                } else {
                    System.out.println("Некорректно задана запись в файле: " + line);

                    if (this.graph == null) {
                        this.isNormal = false;
                    }

                    return null;
                }
            }

            return graph;
        } catch (FileNotFoundException fn) {
            System.out.println("Не получилось прочитать файл!");

            if (this.graph == null) {
                this.isNormal = false;
            }

            return null;
        } catch (IOException io) {
            System.out.println("Не удалось прочитать строку");

            if (this.graph == null) {
                this.isNormal = false;
            }

            return null;
        }
    }

    public abstract void setGraph(HashMap<String, List<String>> graph);
    public void setGraph(String path) {
        if (readGraphFromFile(path) != null) {
            setGraph(readGraphFromFile(path));
        } else {
            System.out.println("Не удалось считать граф с файла!");

            if (this.graph == null) {
                this.isNormal = false;
            }
        }
    }
    public HashMap<String, List<String>> getGraph() {
        if (this.isNormal) {
            return new HashMap<String, List<String>>(this.graph);
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");

            return null;
        }
    }

    public abstract void addVertex(String vertex, List<String> edges);
    public abstract void delVertex(String vertex);
    public Vertex getVertex(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                return new Vertex(vertex, this.graph.get(vertex));
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }

        return null;
    }

    public abstract void addEdge(String vertex, String edge);
    public abstract void delEdge(String vertex, String edge);

    public void printGraph() {
        if (this.isNormal) {
            for (String vertex : this.graph.keySet()) {
                System.out.print(vertex + " : ");
                for (String edge : this.graph.get(vertex)) {
                    System.out.print(edge + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// based

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2
    public abstract int vertexDegree(String vertex);
    public void printVertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)){
                System.out.println(vertexDegree(vertex));
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public List<String> hangingVertices() {
        if (this.isNormal) {
            List<String> hangVertices = new ArrayList<>();
            for (String vertex : this.graph.keySet()) {
                if (vertexDegree(vertex) == 1) {
                    hangVertices.add(vertex);
                }
            }
            return hangVertices;
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    public void printHangingVertices() {
        if (this.isNormal) {
            if (hangingVertices().isEmpty()) {
                System.out.println("В графе нет таких вершин!");
            } else {
                System.out.println("Висячие вершины графа:");

                for (String vertex : hangingVertices()) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task3
    public abstract String fromUAndNotFromV(String vertexU, String vertexV);
    public void printFromUAndNotFromV(String vertexU, String vertexV) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertexU)) {
                if (this.graph.containsKey(vertexV)) {
                    String result = fromUAndNotFromV(vertexU, vertexV);

                    if (!result.equals("")) {
                        System.out.print("Это вершина: ");
                        System.out.println(result);
                    }
                } else {
                    System.out.println("В графе нет вершины: " + vertexV + "!");
                }
            } else {
                System.out.println("В графе нет вершины: " + vertexU + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task3

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task4
    public void delHangingVertexes() {
        if (this.isNormal) {
            if (!this.hangingVertices().isEmpty()) {
                for (String vertex: this.hangingVertices()) {
                    delVertex(vertex);
                }
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task4

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task5
    public abstract List<String> DFS(String vertex);

    public boolean IsConnectedGraph() {
        if (this.isNormal) {
            for (String vertex : this.graph.keySet()) {
                if (DFS(vertex).containsAll(this.graph.keySet())) {
                    return true;
                }
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
        return false;
    }
    public void printIsConnectedGraph() {
        if (this.isNormal) {
            System.out.println(IsConnectedGraph());
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task5

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task6
    public abstract List<String> BFS(String vertex);

    protected List<String> takeWay (String findVertex, List<String> way, List<Pair> visited) {
        String parent = "";

        for (Pair pair : visited) {
            if (pair.first.equals(findVertex)) {
                if (!pair.second.equals("")) {
                    way.add(pair.second);
                }
                parent = pair.second;
                break;
            }
        }

        if (parent.equals("")) {
            return way;
        } else {
            return takeWay(parent, way, visited);
        }
    }
    protected abstract List<Pair> takeShortestPathFromUtoV (String vertexU, String vertexV);
    public void printShortestPathFromUtoV (String vertexU, String vertexV) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertexU)) {
                if (this.graph.containsKey(vertexV)) {
                    if (DFS(vertexU).contains(vertexV)) {
                        String result = "";
                        List<String> way = takeWay(vertexV, new ArrayList<>(), takeShortestPathFromUtoV(vertexU, vertexV));

                        result += vertexV;
                        for (String edge : way) {
                            result += ("-" + edge);
                        }

                        System.out.println(new StringBuilder(result).reverse().toString());
                    } else {
                        System.out.println("Между вершинами " + vertexU + " и " + vertexV + " нет пути!");
                    }
                } else {
                    System.out.println("В графе нет вершины " + vertexV + "!");
                }
            } else {
                System.out.println("В графе нет вершины " + vertexU + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task6

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task7
    protected abstract List<String> DFS(String vertex, HashMap<String, List<String>> graph);

    protected boolean IsConnectedGraph(HashMap<String, List<String>> graph) {
        for (String vertex : graph.keySet()) {
            if (DFS(vertex, graph).containsAll(graph.keySet())) {
                return true;
            }
        }

        return false;
    }

    public abstract HashMap<String, List<String>> krascalsAlgorithm();

    public void printKrascalsAlgorithm() {
        if (this.isNormal) {
            HashMap<String, List<String>> graph = krascalsAlgorithm();

            if (graph != null) {
                for (String vertex : graph.keySet()) {
                    System.out.print(vertex + " : ");
                    for (String edge : graph.get(vertex)) {
                        System.out.print(edge + " ");
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task7

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task8
    protected abstract boolean findNegativeWeight();

    public abstract HashMap<String, Integer> dijkstrasAlgorithm(String start);
    public void printDijkstrasAlgorithm(String start) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                HashMap<String, Integer> result = dijkstrasAlgorithm(start);

                if (result != null) {
                    for (String vertex : result.keySet()) {
                        System.out.println(vertex + ": " + result.get(vertex));
                    }
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task8

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task9

    public abstract HashMap<String, Integer> fordBelmansAlgorithm(String start);
    public void printFordBelmansAlgorithm(String start, String vertexV1, String vertexV2) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                if (this.graph.containsKey(vertexV1)) {
                    if (this.graph.containsKey(vertexV2)) {
                        HashMap<String, Integer> result = fordBelmansAlgorithm(start);

                        if (result != null) {
                            for (String vertex : result.keySet()) {
                                if (vertex.equals(vertexV1) || vertex.equals(vertexV2)) {
                                    System.out.println(vertex + ": " + result.get(vertex));
                                }
                            }
                        }
                    } else {
                        System.out.println("В графе нет вершины " + vertexV2 + "!");
                    }
                } else {
                    System.out.println("В графе нет вершины " + vertexV2 + "!");
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task9

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task10
    public abstract HashMap<String, Integer> floydsAlgorithm(String start);
    public void printFloydsAlgorithm(String start, String vertexV1, String vertexV2) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                if (this.graph.containsKey(vertexV1)) {
                    if (this.graph.containsKey(vertexV2)) {
                        HashMap<String, Integer> result = floydsAlgorithm(start);

                        if (result != null) {
                            for (String vertex : result.keySet()) {
                                if (vertex.equals(vertexV1) || vertex.equals(vertexV2)) {
                                    System.out.println(vertex + ": " + result.get(vertex));
                                }
                            }
                        }
                    } else {
                        System.out.println("В графе нет вершины " + vertexV2 + "!");
                    }
                } else {
                    System.out.println("В графе нет вершины " + vertexV2 + "!");
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task10

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task11
    public abstract int findMaximumFlow(String start, String end);
    public void printMaximumFlow(String start, String end) {
        if (this.isNormal) {
            if (this.graph.containsKey(start)) {
                if (this.graph.containsKey(start)) {
                    System.out.println(findMaximumFlow(start, end));
                } else {
                    System.out.println("В графе нет вершины " + end + "!");
                }
            } else {
                System.out.println("В графе нет вершины " + start + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task11

}

