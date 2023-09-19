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
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
            oUWG = new OrientedUnWeightedGraph();
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
            oWG = new OrientedWeightedGraph();
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }
    public G(String type, HashMap<String, List<String>> graph) {
        if (type.equals("undirected graph")) {
            this.type = type;
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }
    public G(String type) {
        if (type.equals("undirected graph")) {
            this.type = type;
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }
    public G(String type) {
        if (type.equals("undirected graph")) {
            this.type = type;
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }
    public G(String type) {
        if (type.equals("undirected graph")) {
            this.type = type;
        } else if (type.equals("oriented unweighted graph")) {
            this.type = type;
        } else if (type.equals("oriented weighted graph")) {
            this.type = type;
        } else {
            System.out.println("Такой тип графа не поддерживается!");
        }
    }



    public UndirectedGraph uG() {
        if (this.type.equals("undirected graph")) {
            return this.uG;
        } else {
            System.out.println("У вашего графа другой тип!");
            return null;
        }
    }
    public OrientedUnWeightedGraph oUWG() {
        if (this.type.equals("oriented unweighted graph")) {
            return this.oUWG;
        } else {
            System.out.println("У вашего графа другой тип!");
            return null;
        }
    }
    public OrientedWeightedGraph oWG() {
        if (this.type.equals("oriented weighted graph")) {
            return this.oWG;
        } else {
            System.out.println("У вашего графа другой тип!");
            return null;
        }
    }
}
