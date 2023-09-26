package realization;

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
}
