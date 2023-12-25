public List<String> hangingVertices() {
    if (this.isNormal) {
        List<String> hangVertices = new ArrayList<>();
        for (String vertex : this.graph.keySet()) {
            if (vertexDegree(vertex) == 1) {
                hangVertices.add(vertex);}
        }
        return hangVertices;
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}