@Override
public void delEdge(String vertex, String connect) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertex)) {
            if (this.graph.get(vertex).contains(connect)) {
                String edge = connect.substring(0, connect.indexOf("-"));
                String weight = connect.substring(connect.indexOf("-"), connect.length());
                this.graph.get(vertex).remove(this.graph.get(vertex).indexOf(connect));
                this.graph.get(edge).remove(this.graph.get(edge).indexOf(vertex + weight));
            } else {
                System.out.println("Попытка удалить несуществующую связь: " + vertex + "-" + connect + " !");}
        } else {
            System.out.println("В графе нет вершины: " + vertex + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}