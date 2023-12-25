public HashMap<String, List<String>> getGraph() {
    if (this.isNormal) {
        return new HashMap<String, List<String>>(this.graph);
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}