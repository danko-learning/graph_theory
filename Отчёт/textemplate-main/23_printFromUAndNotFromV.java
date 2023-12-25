public void printFromUAndNotFromV(String vertexU, String vertexV) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertexU)) {
            if (this.graph.containsKey(vertexV)) {
                String result = fromUAndNotFromV(vertexU, vertexV);
                if (!result.equals("")) {
                    System.out.print("Это вершина: ");
                    System.out.println(result);}
            } else {
                System.out.println("В графе нет вершины: " + vertexV + "!");}
        } else {
            System.out.println("В графе нет вершины: " + vertexU + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}