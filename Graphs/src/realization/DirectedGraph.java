package realization;

public abstract class DirectedGraph extends Graph {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2
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
            if (this.graph.containsKey(vertex)) {
                System.out.println(outVertexDegree(vertex));
            } else {
                System.out.println("В графе не содержится вершины " + vertex + " !");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    public abstract int inVertexDegree(String vertex);
    public void printInVertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                System.out.println(inVertexDegree(vertex));
            } else {
                System.out.println("В графе не содержится вершины " + vertex + " !");
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
        }
    }

    @Override
    public int vertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                return inVertexDegree(vertex) + outVertexDegree(vertex);
            } else {
                System.out.println("В графе не содержится вершины " + vertex + " !");
                return -1;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return -1;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2
}
