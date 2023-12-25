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
                        matrix.get(vertex).put(_vertex, 99999);}
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
                            matrix.get(matrixVertex).put(edge, matrix.get(matrixVertex).get(vertex) + matrix.get(vertex).get(edge));}
                    }
                }
            }
            return matrix.get(start);
        } else {
            System.out.println("В графе нет вершины " + start + "!");
            return null;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}
public void printFloydsAlgorithm(String start, String vertexV1, String vertexV2) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            if (this.graph.containsKey(vertexV1)) {
                if (this.graph.containsKey(vertexV2)) {
                    HashMap<String, Integer> result = floydsAlgorithm(start);
                    if (result != null) {
                        for (String vertex : result.keySet()) {
                            if (vertex.equals(vertexV1) || vertex.equals(vertexV2)) {
                                System.out.println(vertex + ": " + result.get(vertex));}
                        }}
                } else {
                    System.out.println("В графе нет вершины " + vertexV2 + "!");}
            } else {
                System.out.println("В графе нет вершины " + vertexV2 + "!");}
        } else {
            System.out.println("В графе нет вершины " + start + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}