@Override
public HashMap<String, List<String>> krascalsAlgorithm () {
    if (this.isNormal) {
        if (this.IsConnectedGraph()) {
            UndirectedWeightedGraph temp = new UndirectedWeightedGraph(this.getGraph());
            UndirectedWeightedGraph res = new UndirectedWeightedGraph();
            for (String vertex : temp.getGraph().keySet()) {
                res.addVertex(vertex, new ArrayList<String>());
            }
            while (!res.IsConnectedGraph()) {
                int min = Integer.MAX_VALUE;
                String cnnct = "";
                String vrtxt = "";
                String edg = "";
                for (String vertex : temp.getGraph().keySet()) {
                    for (String connect : temp.getGraph().get(vertex)) {
                        String edge = connect.substring(0, connect.indexOf("-"));
                        String weight = connect.substring(connect.indexOf("-") + 1, connect.length());
                        int wght = Integer.parseInt(weight);
                        if (min >= wght) {
                            min = wght;
                            cnnct = connect;
                            vrtxt = vertex;
                            edg = edge;}
                    }
                }
                if (!DFS(vrtxt, res.getGraph()).contains(edg)) {
                    res.addEdge(vrtxt, cnnct);}
                temp.delEdge(vrtxt, cnnct);
            }
            return res.getGraph();
        } else {
            System.out.println("Остов может быть только у связного графа!");
            return null;}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return null;}
}
public void printKrascalsAlgorithm() {
    if (this.isNormal) {
        HashMap<String, List<String>> graph = krascalsAlgorithm();
        if (graph != null) {
            for (String vertex : graph.keySet()) {
                System.out.print(vertex + " : ");
                for (String edge : graph.get(vertex)) {
                    System.out.print(edge + " ");
                }
                System.out.println();
            }
        }
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}