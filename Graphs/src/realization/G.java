package realization;

import java.util.HashMap;
import java.util.List;

public class G {
    private String type;
    private UndirectedUnweightedGraph uU;
    private UndirectedWeightedGraph uW;
    private DirectedUnweightedGraph oU;
    private DirectedWeightedGraph oW;

    public G(){}
    public G(String type) {
        if (type.equals("uU")) {
            this.type = type;
            uU = new UndirectedUnweightedGraph();
            uW = null;
            oU = null;
            oW = null;
        } else if (type.equals("uW")) {
            this.type = type;
            uU = null;
            uW = new UndirectedWeightedGraph();
            oU = null;
            oW = null;
        } else if (type.equals("oU")) {
            this.type = type;
            uU = null;
            uW = null;
            oU = new DirectedUnweightedGraph();
            oW = null;
        } else if (type.equals("oW")) {
            this.type = type;
            uU = null;
            uW = null;
            oU = null;
            oW = new DirectedWeightedGraph();
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }

    public String getType() {
        return this.type;
    }

    public void setGraph(HashMap<String, List<String>> graph) {
        if (this.type.equals("uU")) {
            this.uU.setGraph(graph);
        } else if (this.type.equals("uW")) {
            this.uW.setGraph(graph);
        } else if (this.type.equals("oU")) {
            this.oU.setGraph(graph);
        } else if (this.type.equals("oW")) {
            this.oW.setGraph(graph);
        }else {
            System.out.println("Граф не был создан!");
        }
    }
    public void setGraph(String path) {
        if (this.type.equals("uU")) {
            this.uU.setGraph(path);
        } else if (this.type.equals("uW")) {
            this.uW.setGraph(path);
        } else if (this.type.equals("oU")) {
            this.oU.setGraph(path);
        } else if (this.type.equals("oW")) {
            this.oW.setGraph(path);
        }else {
            System.out.println("Граф не был создан!");
        }
    }

    public HashMap<String, List<String>> getList() {
        if (this.type.equals("uU")) {
            return this.uU.getGraph();
        } else if (this.type.equals("uW")) {
            return this.uW.getGraph();
        } else if (this.type.equals("oU")) {
            return this.oU.getGraph();
        } else if (this.type.equals("oW")) {
            return this.oW.getGraph();
        }else {
            System.out.println("Граф не был создан!");
            return null;
        }
    }

    public Graph getGraph() {
        if (this.type.equals("uU")) {
            return this.uU;
        } else if (this.type.equals("uW")) {
            return this.uW;
        } else if (this.type.equals("oU")) {
            return this.oU;
        } else if (this.type.equals("oW")) {
            return this.oW;
        }else {
            System.out.println("Граф не был создан!");
            return null;
        }
    }
}
