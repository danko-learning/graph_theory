@Override
public String fromUAndNotFromV(String vertexU, String vertexV) {
    if (this.isNormal) {
        if (this.graph.containsKey(vertexU)) {
            if (this.graph.containsKey(vertexV)) {
                List<String> uEdge = new ArrayList<>();
                for (String conn : this.graph.get(vertexU)) {
                    String edge = conn.substring(0, conn.indexOf("-"));
                    uEdge.add(edge);}
                List<String> vEdge = new ArrayList<>();
                for (String conn : this.graph.get(vertexV)) {
                    String edge = conn.substring(0, conn.indexOf("-"));
                    vEdge.add(edge);}
                uEdge.removeAll(vEdge);
                if(uEdge.isEmpty()) {
                    System.out.println("В графе нет подходящих вершин!");
                    return "";
                } else {
                    return uEdge.get(0);}
            } else {
                System.out.println("В графе нет вершины: " + vertexV + "!");
                return "";}
        } else {
            System.out.println("В графе нет вершины: " + vertexU + "!");
            return "";}
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");
        return "";}
}