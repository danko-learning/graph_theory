@Override
public List<String> BFS(String vertex) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertex)) {
            List<String> visited = new ArrayList<>();
            Queue<String> q = new LinkedList<>();
            visited.add(vertex);
            q.add(vertex);
            while (!q.isEmpty()) {
                for (String connect : this.graph.get(q.peek())) {
                    String edge = connect.substring(0, connect.indexOf("-"));
                    if (!visited.contains(edge) && !q.contains(edge)) {
                        q.add(edge);}
                }
                visited.add(q.poll());}
            return visited;
        } else {
            System.out.println("В графе нет вершины " + vertex + "!");
            return null;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}
protected List<Pair> takeShortestPathFromUtoV (String vertexU, String vertexV) {
    List<Pair> visited = new ArrayList<Pair>();
    Queue<Pair> q = new LinkedList<Pair>();
    visited.add(new Pair(vertexU, ""));
    q.add(new Pair(vertexU, ""));
    while(!q.isEmpty()) {
        for (String connect : this.graph.get(q.peek().first)) {
            String edge = connect.substring(0, connect.indexOf("-"));
            boolean qFlag = true, vFlag = true;
            for (Pair pair : q) {
                if (pair.first.equals(edge)) {
                    qFlag = false;
                    break;}
            }
            for (Pair pair : visited) {
                if (pair.first.equals(edge)) {
                    vFlag = false;
                    break;}
            }
            if (qFlag && vFlag) {
                q.add(new Pair(edge, q.peek().first));}
        }
        visited.add(q.peek());
        if (q.poll().first.equals(vertexV)) {
            break;}
    }
    return visited;}
protected List<String> takeWay (String findVertex, List<String> way, List<Pair> visited) {
    String parent = "";
    for (Pair pair : visited) {
        if (pair.first.equals(findVertex)) {
            if (!pair.second.equals("")) {
                way.add(pair.second);}
            parent = pair.second;
            break;}
    }
    if (parent.equals("")) {
        return way;
    } else {
        return takeWay(parent, way, visited);}
}
public void printShortestPathFromUtoV (String vertexU, String vertexV) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertexU)) {
            if (this.graph.containsKey(vertexV)) {
                if (DFS(vertexU).contains(vertexV)) {
                    String result = "";
                    List<String> way = takeWay(vertexV, new ArrayList<>(), takeShortestPathFromUtoV(vertexU, vertexV));
                    result += vertexV;
                    for (String edge : way) {
                        result += ("-" + edge);}
                    System.out.println(new StringBuilder(result).reverse().toString());
                } else {
                    System.out.println("Между вершинами " + vertexU + " и " + vertexV + " нет пути!");}
            } else {
                System.out.println("В графе нет вершины " + vertexV + "!");}
        } else {
            System.out.println("В графе нет вершины " + vertexU + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}