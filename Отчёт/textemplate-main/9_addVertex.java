@Override
public void addVertex(String vertex, List<String> connects) {
    if (this.isNormal) {
        if (!this.graph.containsKey(vertex)) {
            boolean err = false;
            for (String connect : connects) {
                int indexDash = connect.indexOf("-");
                if (indexDash != -1) {
                    if (connect.length() >= indexDash + 1) {
                        String edge = connect.substring(0, indexDash);
                        if (!graph.containsKey(edge)) {
                            err = true;
                            System.out.println("Указана связь с несуществующей вершиной: " + vertex + "-" + connect + "!");
                            break;}
                    } else {
                        err = true;
                        System.out.println("В связи " + connect + " не задан вес!");}
                } else {
                    err = true;
                    System.out.println("В связи " + connect + " не задан вес!");}
            }
            if (!err) {
                for (String connect : connects) {
                    String edge = connect.substring(0, connect.indexOf("-"));
                    String weight = connect.substring(connect.indexOf("-"), connect.length());

                    this.graph.get(edge).add(vertex + weight);}
                this.graph.put(vertex, connects);}
        } else {
            System.out.println("В графе уже есть вершина: " + vertex + "!");}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}