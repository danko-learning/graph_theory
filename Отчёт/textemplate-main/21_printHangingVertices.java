public void printHangingVertices() {
    if (this.isNormal) {
        if (hangingVertices().isEmpty()) {
            System.out.println("В графе нет таких вершин!");
        } else {
            System.out.println("Висячие вершины графа:");

            for (String vertex : hangingVertices()) {
                System.out.print(vertex + " ");
            }
            System.out.println();}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}