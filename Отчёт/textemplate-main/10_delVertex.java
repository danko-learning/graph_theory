@Override
public void delVertex(String vertex) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertex)) {
            this.graph.remove(vertex);
            for (String graphVertex : this.graph.keySet()) {
                for (String connect : this.graph.get(graphVertex)) {
                    String edge = connect.substring(0, connect.indexOf("-"));
                    if (vertex.equals(edge)) {
                        this.graph.get(graphVertex).remove(this.graph.get(graphVertex).indexOf(connect));
                        break;}
                }
            }
        } else {
            System.out.println("В графе нет вершины: " + vertex + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}