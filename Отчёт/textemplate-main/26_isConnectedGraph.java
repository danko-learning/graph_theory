public boolean IsConnectedGraph() {
    if (this.isNormal) {
        for (String vertex : this.graph.keySet()) {
            if (DFS(vertex).containsAll(this.graph.keySet())) {
                return true;}
        }
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
    return false;
}
public void printIsConnectedGraph() {
    if (this.isNormal) {
        System.out.println(IsConnectedGraph());
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}