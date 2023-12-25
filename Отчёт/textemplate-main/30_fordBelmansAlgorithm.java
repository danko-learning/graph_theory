@Override
public HashMap<String, Integer> fordBelmansAlgorithm(String start) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            HashMap<String, Integer> pathWeights = new HashMap<String, Integer>();
            HashMap<String, Integer> temp;
            int numberOfIteration = this.graph.keySet().size() - 1;
            for (String vertex : this.graph.keySet()) {
                pathWeights.put(vertex, 99999);
            }
            pathWeights.put(start, 0);
            for (int i = 0; i < numberOfIteration; ++i) {
                for (String vertex : this.graph.keySet()) {
                    for (String connect : this.graph.get(vertex)) {
                        int indexDash = connect.indexOf("-");
                        String edge = connect.substring(0, indexDash);
                        int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));
                        if (edge.equals(start)) {
                            continue;}
                        if (pathWeights.get(edge) > (pathWeights.get(vertex) + weight)) {
                            pathWeights.put(edge, (pathWeights.get(vertex) + weight));}
                    }
                }
            }
            temp = new HashMap<String, Integer>(pathWeights);
            for (String vertex : this.graph.keySet()) {
                for (String connect : this.graph.get(vertex)) {
                    int indexDash = connect.indexOf("-");
                    String edge = connect.substring(0, indexDash);
                    int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));
                    if (edge.equals(start)) {
                        continue;}
                    if (temp.get(edge) > temp.get(vertex) + weight) {
                        temp.put(edge, pathWeights.get(vertex) + weight);}
                }
            }
            if (pathWeights.equals(temp)) {
                return pathWeights;
            } else {
                System.out.println("В графе присутствует отрицательный цикл!");
                return null;}
        } else {
            System.out.println("В графе нет вершины " + start + "!");
            return null;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}
public void printFordBelmansAlgorithm(String start, String vertexV1, String vertexV2) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            if (this.graph.containsKey(vertexV1)) {
                if (this.graph.containsKey(vertexV2)) {
                    HashMap<String, Integer> result = fordBelmansAlgorithm(start);
                    if (result != null) {
                        for (String vertex : result.keySet()) {
                            if (vertex.equals(vertexV1) || vertex.equals(vertexV2)) {
                                System.out.println(vertex + ": " + result.get(vertex));}
                        }
                    }
                } else {
                    System.out.println("В графе нет вершины " + vertexV2 + "!");}
            } else {
                System.out.println("В графе нет вершины " + vertexV2 + "!");}
        } else {
            System.out.println("В графе нет вершины " + start + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}