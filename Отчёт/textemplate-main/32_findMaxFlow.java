public void printMaximumFlow(String start, String end) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            if (this.graph.containsKey(start)) {
                System.out.println(findMaximumFlow(start, end));
            } else {
                System.out.println("В графе нет вершины " + end + "!");}
        } else {
            System.out.println("В графе нет вершины " + start + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}
@Override
public int findMaximumFlow(String start, String end) {
    if (this.isNormal) {
        if (this.graph.containsKey(start)) {
            if (this.graph.containsKey(end)) {
                int maximumFlow = 0;
                HashMap<String, HashMap<String, Integer>> web = new HashMap<String, HashMap<String, Integer>>();
                for (String vertex : this.graph.keySet()) {
                    web.put(vertex, new HashMap<String, Integer>());
                    for (String connection : this.graph.get(vertex)) {
                        int dash = connection.indexOf("-");
                        String edge = connection.substring(0, dash);
                        int weight = Integer.parseInt(connection.substring(dash + 1, connection.length()));
                        web.get(vertex).put(edge, weight);
                    }
                }
                while (true) {
                    HashMap<String, Pair_> way = new HashMap<String, Pair_>();
                    String currentVertex = start, oldVertex = start;
                    ArrayList<String> visited = new ArrayList<String>();
                    while (!currentVertex.equals(end)) {
                        HashMap<String, Integer> edges = web.get(currentVertex);
                        oldVertex = currentVertex;
                        int maximum = Integer.MIN_VALUE;
                        for (String edge : edges.keySet()) {
                            if (maximum < edges.get(edge) && !visited.contains(edge) && !way.containsKey(edge) && !edge.equals(start)) {
                                maximum = edges.get(edge);
                                currentVertex = edge;}
                        }
                        if (oldVertex.equals(currentVertex)) {
                            if (oldVertex.equals(start)) {
                                return maximumFlow;
                            } else {
                                String temp = currentVertex;
                                currentVertex = way.get(currentVertex).vertex;
                                visited.add(temp);
                                way.remove(temp);
                                continue;}
                        }
                        way.put(currentVertex, new Pair_(oldVertex, maximum));
                    }
                    int minimum = Integer.MAX_VALUE;
                    for (String edge : way.keySet()) {
                        if (minimum > way.get(edge).weight) {
                            minimum = way.get(edge).weight;}
                    }
                    maximumFlow += minimum;
                    for (String edge : way.keySet()) {
                        int newWeight = web.get(way.get(edge).vertex).get(edge) - minimum;
                        web.get(way.get(edge).vertex).put(edge, newWeight);
                        if (newWeight == 0) {web.get(way.get(edge).vertex).remove(edge);}

                        if (web.get(edge).containsKey(way.get(edge).vertex)) {
                            newWeight = web.get(edge).get(way.get(edge).vertex) + minimum;
                            web.get(edge).put(way.get(edge).vertex, newWeight);
                        } else {
                            web.get(edge).put(way.get(edge).vertex, minimum);}
                    }
                }
            } else {
                System.out.println("В графе нет вершины " + end + "!");
                return -1;}
        } else {
            System.out.println("В графе нет вершины " + start + "!");
            return -1;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return -1;}
}