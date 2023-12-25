@Override
public List<String> DFS(String vertex) {
    if (this.isNormal) {
        List<String> visited = new ArrayList<>();
        Stack<String> s = new Stack<>();
        s.push(vertex);
        while (!s.isEmpty()) {
            if (!visited.contains(s.peek())) {
                visited.add(s.peek());}
            for (String connect : this.graph.get(s.pop())) {
                String edge = connect.substring(0, connect.indexOf("-"));
                if (!visited.contains(edge) && !s.contains(edge)) {
                    s.push(edge);}
            }
        }
        return visited;
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}