public UndirectedUnweightedGraph(UndirectedGraph other) {
        if (other.isNormal) {
            this.graph = other.getGraph();
            this.isNormal = other.isNormal;
        } else {
            System.out.println("Переданный вами в конструктор граф был некорректно создан!");}
}