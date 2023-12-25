@Override
public void addEdge(String vertex, String connect) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertex)) {
            int indexDash = connect.indexOf("-");
            if (indexDash != -1) {
                if (connect.length() >= indexDash + 1) {
                    String edge = connect.substring(0, indexDash);
                    if (this.graph.containsKey(edge)) {
                        if (!this.graph.get(vertex).contains(connect)) {
                            String weight = connect.substring(connect.indexOf("-"), connect.length());
                            this.graph.get(vertex).add(connect);
                            this.graph.get(edge).add(vertex + weight);
                        } else {
                            System.out.println("У вершины " + vertex + " уже есть связь " + connect + "!");}
                    } else {
                        System.out.println("Попытка создать связь с несуществующей вершиной: " + vertex + "-" + connect + "!");}
                } else {
                    System.out.println("В связи " + connect + " не задан вес!");}
            } else {
                System.out.println("В связи " + connect + " не задан вес!");}
        } else {
            System.out.println("В графе нет вершины: " + vertex + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}