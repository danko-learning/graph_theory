package realization;

import java.util.ArrayList;
import java.util.List;

public abstract class DirecredGraph extends Graph{

    public List<String> outVertices(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                return new ArrayList<String>(this.graph.get(vertex));
            } else {
                System.out.println("В графе не содержится вершины " + vertex + " !");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    public void printOutVertices(String vertex) {
        if (this.isNormal) {
            this.outVertices(vertex);
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public int outVertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                return this.graph.get(vertex).size();
            } else {
                System.out.println("В графе не содержится вершины " + vertex + " !");
                return -1;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return -1;
        }
    }
    public void printOutVertexDegree(String vertex) {
        if (this.isNormal) {
            this.outVertexDegree(vertex);
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public abstract int inVertexDegree(String vertex);
    public void printInVertexDegree(String vertex) {
        if (this.isNormal) {
            this.inVertexDegree(vertex);
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    @Override
    public int vertexDegree(String vertex) {
        if (this.isNormal) {
            return inVertexDegree(vertex) + outVertexDegree(vertex);
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
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public List<String> outBiggerIn() {
        if (this.isNormal) {
            List<String> oBI = new ArrayList<>();
            for (String key : this.graph.keySet()) {
                if (outVertexDegree(key) > inVertexDegree(key)) {
                    oBI.add(key);
                }
            }
            return oBI;
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    public void printOutBiggerIn() {
        if (this.isNormal) {
            System.out.println("\nВершины, степень исхода которых больше степени захода:");
            for (String vertex : outBiggerIn()) {
                System.out.print(vertex + " ");
            }
            System.out.println("\n");
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public List<String> vertexesThemBiggerOut(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                List<String> vertTBO = new ArrayList<>();
                int border = outVertexDegree(vertex);

                for (String key : this.graph.keySet()) {
                    if (outVertexDegree(key) > border) {
                        vertTBO.add(key);
                    }
                }
                return vertTBO;
            } else {
                System.out.println("В графе не содержится вершины " + vertex + "!");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    public void printVertexesThemBiggerOut(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                System.out.println("\nВершины, степень исхода которых больше, чем у вершины " + vertex + ":");
                for (String vert : vertexesThemBiggerOut(vertex)) {
                    System.out.print(vert + " ");
                }
                System.out.println("\n");
            } else {
                System.out.println("В графе нет вершины " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public List<String> vertexesThemSmallerIn(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                List<String> vertTSI = new ArrayList<>();
                int border = inVertexDegree(vertex);

                for (String key : this.graph.keySet()) {
                    if (inVertexDegree(key) < border) {
                        vertTSI.add(key);
                    }
                }
                return vertTSI;
            } else {
                System.out.println("В графе не содержится вершины " + vertex + "!");
                return null;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return null;
        }
    }
    public void printVertexesThemSmallerIn(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                System.out.println("\nВершины, степень исхода которых больше, чем у вершины " + vertex + ":");
                for (String vert : vertexesThemSmallerIn(vertex)) {
                    System.out.print(vert + " ");
                }
                System.out.println("\n");
            } else {
                System.out.println("В графе нет вершины " + vertex + "!");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }
}
