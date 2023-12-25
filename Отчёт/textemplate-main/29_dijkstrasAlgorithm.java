@Override
public HashMap<String, Integer> dijkstrasAlgorithm(String start) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            if (!this.findNegativeWeight()) {
                HashMap<String, Integer> pathWeights = new HashMap<String, Integer>();
                List<String> visited = new ArrayList<String>();
                for (String vertex : this.graph.keySet()) {
                    if (vertex.equals(start)) {
                        pathWeights.put(vertex, 0);
                        continue;}
                    pathWeights.put(vertex, Integer.MAX_VALUE);
                }
                visited.add(start);
                for (String connect : this.graph.get(start)) {
                    int indexDash = connect.indexOf("-");
                    String edge = connect.substring(0, indexDash);
                    int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));
                    if (!edge.equals(visited)) {
                        pathWeights.put(edge, weight);}
                }
                while (visited.size() != this.graph.keySet().size()) {
                    int minWeight = Integer.MAX_VALUE;
                    String currentVertex = "";
                    for (String vertex : pathWeights.keySet()) {
                        if (minWeight > pathWeights.get(vertex) && !visited.contains(vertex)) {
                            minWeight = pathWeights.get(vertex);
                            currentVertex = vertex;}
                    }
                    if (currentVertex.equals("")) {
                        return pathWeights;}
                    visited.add(currentVertex);
                    for (String connect : this.graph.get(currentVertex)) {
                        int indexDash = connect.indexOf("-");
                        String edge = connect.substring(0, indexDash);
                        int weight = Integer.parseInt(connect.substring(indexDash + 1, connect.length()));
                        if (minWeight + weight < pathWeights.get(edge)) {
                            pathWeights.put(edge, minWeight + weight);}
                    }
                }
                return pathWeights;
            } else {
                System.out.println("Алгоритм Дейкстры не может работать с отрицательными весами!");
                return null;}
        } else {
            System.out.println("В графе нет вершины " + start + "!");
            return null;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}
public void printDijkstrasAlgorithm(String start) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            HashMap<String, Integer> result = dijkstrasAlgorithm(start);
            if (result != null) {
                for (String vertex : result.keySet()) {
                    System.out.println(vertex + ": " + result.get(vertex));
                }}
        } else {
            System.out.println("В графе нет вершины " + start + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}