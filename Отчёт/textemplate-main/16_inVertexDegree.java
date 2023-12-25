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
                            break;}
                    }
                }
            }
            return inDegree;
        } else {
            System.out.println("В графе нет вершины: " + vertex + "!");
            return -1;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return -1;}
}