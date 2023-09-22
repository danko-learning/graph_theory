package realization;

import java.util.HashMap;
import java.util.List;

public class G {

    private String type;
    private UndirectedGraph uG;
    private OrientedUnWeightedGraph oUWG;
    private OrientedWeightedGraph oWG;

    public G(String type) {
        if (type.equals("undirected graph")) {
            this.type = type;
            uG = new UndirectedGraph();
            oUWG = null;
            oWG = null;
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
            oUWG = new OrientedUnWeightedGraph();
            uG = null;
            oWG = null;
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
            oWG = new OrientedWeightedGraph();
            uG = null;
            oUWG = null;
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }
    public G(String type, HashMap<String, List<String>> graph) {
        if (type.equals("undirected graph")) {
            this.type = type;
            this.uG = new UndirectedGraph(graph);
            oUWG = null;
            oWG = null;
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
            this.oUWG = new OrientedUnWeightedGraph(graph);
            uG = null;
            oWG = null;
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
            this.oWG = new OrientedWeightedGraph(graph);
            uG = null;
            oUWG = null;
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }
    public G(UndirectedGraph graph) {
        this.type = "undirected graph";
        this.uG = new UndirectedGraph(graph.getGraph());
        oUWG = null;
        oWG = null;
    }
    public G(OrientedUnWeightedGraph graph) {
        this.type = "oriented unweighted graph";
        this.oUWG = new OrientedUnWeightedGraph(graph.getGraph());
        uG = null;
        oWG = null;
    }
    public G(OrientedWeightedGraph graph) {
        this.type = "oriented unweighted graph";
        this.oUWG = new OrientedUnWeightedGraph(graph.getGraph());
        uG = null;
        oUWG = null;
    }

    public void setGraph(HashMap<String, List<String>> graph) {
        if (this.type.equals("undirected graph")) {
            this.uG.setGraph(graph);
        } else if (this.type.equals("oriented unweighted graph")) {
            this.oUWG.setGraph(graph);
        } else if (this.type.equals("oriented weighted graph")) {
            this.oWG.setGraph(graph);
        } else {
            System.out.println("Граф не был создан!");
        }
    }
    public void setGraph(UndirectedGraph graph) {
        if (this.type.equals("undirected graph")) {
            this.uG.setGraph(graph.getGraph());
        } else {
            System.out.println("Граф имеет другой тип или не был создан!");
        }
    }
    public void setGraph(OrientedUnWeightedGraph graph) {
        if (this.type.equals("oriented unweighted graph")) {
            this.oUWG.setGraph(graph.getGraph());
        } else {
            System.out.println("Граф имеет другой тип или не был создан!");
        }
    }
    public void setGraph(OrientedWeightedGraph graph) {
        if (this.type.equals("oriented weighted graph")) {
            this.oUWG.setGraph(graph.getGraph());
        } else {
            System.out.println("Граф имеет другой тип или не был создан!");
        }
    }

    public Graph getGraph() {
        if (this.type.equals("undirected graph") && this.uG != null) {
            return this.uG;
        } else if (this.type.equals("oriented unweighted graph") && this.oUWG != null) {
            return this.oUWG;
        } else if (this.type.equals("oriented weighted graph") && this.oWG != null) {
            return this.oWG;
        } else {
            System.out.println("Граф не создан!");
            return null;
        }
    }
}
