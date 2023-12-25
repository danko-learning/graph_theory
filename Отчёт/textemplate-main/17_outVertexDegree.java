public int outVertexDegree(String vertex) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertex)) {
            return this.graph.get(vertex).size();
        } else {
            System.out.println("В графе не содержится вершины " + vertex + " !");
            return -1;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return -1;}
}