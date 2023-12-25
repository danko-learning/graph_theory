@Override
public int vertexDegree(String vertex) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertex)) {
            return inVertexDegree(vertex) + outVertexDegree(vertex);
        } else {
            System.out.println("В графе не содержится вершины " + vertex + " !");
            return -1;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return -1;}
}