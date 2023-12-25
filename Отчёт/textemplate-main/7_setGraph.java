@Override
public void setGraph(HashMap<String, List<String>> graph) {
    boolean err = false;
    for (String vertex : graph.keySet()) {
        for (String connect : graph.get(vertex)) {
            int indexDash = connect.indexOf("-");
            if (indexDash != -1) {
                if (connect.length() >= indexDash + 1) {
                    String edge = connect.substring(0, indexDash);

                    if (!graph.containsKey(edge)) {
                        if (this.graph == null) {
                            this.isNormal = false;
                        }
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
        if (err) {
            break;}
    }
    if (!err) {
        this.graph = new HashMap<>(graph);}
}