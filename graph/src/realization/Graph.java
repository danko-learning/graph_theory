package realization;

import org.w3c.dom.ls.LSException;

import java.io.*;
import java.util.*;


public abstract class Graph {

    protected boolean isNormal = true;
    protected HashMap<String, List<String>> graph;

    public boolean getStatus() {
        return this.isNormal;
    }

    protected HashMap<String, List<String>> readGraphFromFile (String wayToFile)
    {
        HashMap<String, List<String>> graph = new HashMap<String, List<String>>();

        File file = new File(wayToFile);
        FileReader fr;

        try {
            fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                int inedxColon = line.indexOf(":");
                if (inedxColon != -1) {
                    String vertex = line.substring(0, inedxColon);
                    String edges = line.substring(inedxColon + 1, line.length());
                    if (edges.length() > 0) {
                        graph.put(vertex, new ArrayList<>( Arrays.asList(edges.split(" "))));
                    } else {
                        graph.put(vertex, new ArrayList<>());
                    }
                    line = reader.readLine();
                } else {
                    System.out.println("Некорректно задан файл (не удалось найти имя вершины в строке \t" + line);
                    if (this.graph == null) {
                        this.isNormal = false;
                    }
                    return null;
                }
            }

            return graph;
        } catch (FileNotFoundException fn){
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
    public void setGraph(Graph graph) {
        setGraph(graph.getGraph());
    }
    public HashMap<String, List<String>> getGraph() {
        return new HashMap<String, List<String>>(this.graph);
    }

    public abstract void addVertex(String name, List<String> edges);
    public abstract void delVertex(String name);
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

    public abstract void addEdge(String vertex, String edge);
    public abstract void delEdge(String vertex, String edge);

    public void printGraph() {
        if (this.isNormal) {
            for (String key : this.graph.keySet()) {
                System.out.print(key + " : ");
                for (String edge : this.graph.get(key)) {
                    System.out.print(edge + " ");
                }
                System.out.println();
            }
        }
    }

    public abstract int vertexDegree(String vertex);
    public abstract void printVertexesDegree();

    public List<String> hangingVertices() {
        if (this.isNormal) {
            List<String> hangVertices = new ArrayList<>();
            for (String key : this.graph.keySet()) {
                if (vertexDegree(key) == 1) {
                    hangVertices.add(key);
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
                System.out.println("\nВисячие вершины графа:");

                for (String vertex : hangingVertices()) {
                    System.out.print(vertex + " ");
                }
                System.out.println("\n");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public List<String> isolatedVertices() {
        if (this.isNormal) {
            List<String> hangVertices = new ArrayList<>();
            for (String key : this.graph.keySet()) {
                if (vertexDegree(key) == 0) {
                    hangVertices.add(key);
                }
            }
            return hangVertices;
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    public void printIsolatedVertices() {
        if (this.isNormal) {
            System.out.println("\nВисячие вершины графа:");
            for (String vertex : isolatedVertices()) {
                System.out.print(vertex + " ");
            }
            System.out.println("\n");
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public abstract String fromUAndNotFromV(String u, String v);
    public void printFromUAndNotFromV(String u, String v) {
        if (this.isNormal) {
            String result = fromUAndNotFromV(u, v);
            if (!result.equals("")) {
                System.out.println(fromUAndNotFromV(u, v));
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public void delHangingVertexes() {
        if (this.isNormal) {
            if (!this.hangingVertices().isEmpty()) {
                for (String vertex: this.hangingVertices()) {
                    this.delVertex(vertex);
                }
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

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

    public boolean IsConnectedGraph() {
        if (this.isNormal) {
            boolean IsconnectedG = false;
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

    protected void iterShortestPathFromUtoV (String u, String v, List<String> path, List<String> visited)
    {
        List<String> currentPath = new ArrayList<>(path);

        if (this.graph.get(u).contains(v)) {
            if (!visited.contains(v)) {
                currentPath.add(v);
                visited.add(u);
                visited.add(v);
                System.out.println(currentPath);
            }
        } else {
            visited.add(u);
            boolean flag = true;
            for (String edge : this.graph.get(u)) {
                if (this.graph.get(edge).contains(v)) {
                    currentPath.add(edge);
                    visited.add(edge);
                    iterShortestPathFromUtoV(edge, v, currentPath, visited);
                    break;
                }
            }
            if (flag) {
                for (String edge : this.graph.get(u)) {
                    if (visited.contains(edge)) {
                        continue;
                    }
                    currentPath.add(edge);
                    iterShortestPathFromUtoV(edge, v, currentPath, visited);
                    currentPath.remove(edge);
                }
            }
        }
    }
    public void shortestPathFromUtoV (String u, String v) {
        if (this.isNormal) {
            if (this.graph.containsKey(u)) {
                if (this.graph.containsKey(v)) {
                    if (this.BFS(u).contains(v)) {
                        if (u.equals(v)) {
                            System.out.println(u + " " + v);
                        } else {
                            List<String> path = new ArrayList<>();
                            path.add(u);
                            List<String> visited = new ArrayList<>();
                            visited.add(u);
                            iterShortestPathFromUtoV(u, v, path, visited);
                        }
                    } else {
                        System.out.println("Пути из вершины " + u + " в вершину " + v + " не существует!");
                    }
                } else {
                    System.out.println("В графе нет вершины " + v + "!");
                }
            } else {
                System.out.println("В графе нет вершины " + u + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
}
