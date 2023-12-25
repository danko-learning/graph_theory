public void printGraph() {
    if (this.isNormal) {
        for (String vertex : this.graph.keySet()) {
            System.out.print(vertex + " : ");
            for (String edge : this.graph.get(vertex)) {
                System.out.print(edge + " ");}
            System.out.println();}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}