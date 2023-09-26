package test;

import realization.G;
import realization.OrientedUnWeightedGraph;
import realization.OrientedWeightedGraph;
import realization.UndirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String [] args) {

//        ///////////////////////////////////////////////////////test 1
//        List<String> a1 = new ArrayList<>();
//        a1.add("2"); a1.add("3");
//        List<String> a2 = new ArrayList<>();
//        a2.add("1"); a2.add("3"); a2.add("4");
//        List<String> a3 = new ArrayList<>();
//        a3.add("1"); a3.add("2"); a3.add("4");
//        List<String> a4 = new ArrayList<>();
//        a4.add("2"); a4.add("3");
//        HashMap<String, List<String>> ma = new HashMap<>();
//        ma.put("1", a1);
//        ma.put("2", a2);
//        ma.put("3", a3);
//        ma.put("4", a4);
//
//        G a = new G("undirected graph", ma);
//        a.getGraph().printGraph();
//
//        // добавляем вершину
//        System.out.println("\n добавили вершину 5");
//        List<String> a5 = new ArrayList<>();
//        a5.add("2"); a5.add("3");
//        a.getGraph().addVertex("5", a5);
//        a.getGraph().printGraph();
//
//        // добавляем вершину с некорректной связью
//        System.out.println("\n попытка добавить вершину с некорректной связью");
//        List<String> a6 = new ArrayList<>();
//        a6.add("8"); a6.add("1");
//        a.getGraph().addVertex("6", a6);
//        a.getGraph().printGraph();
//
//        // добавляем существующую вершину
//        System.out.println("\n попытка добавить сущетвующую вершину");
//        a.getGraph().addVertex("5", a5);
//        a.getGraph().printGraph();
//
//        // удаляем вершину
//        System.out.println("\n удаляем вершину");
//        a.getGraph().delVertex("1");
//        a.getGraph().printGraph();
//
//        // удаляем несуществующую вершину
//        System.out.println("\n попытка удалить несуществующую вершину");
//        a.getGraph().delVertex("1");
//        a.getGraph().printGraph();
//
//        // добавляем ребро
//        System.out.println("\n добавляем ребро");
//        a.getGraph().addEdge("5", "4");
//        a.getGraph().printGraph();
//
//        // добавляем ребро к несуществующей вершине
//        System.out.println("\n попытка добавить ребро к несуществующей вершине");
//        a.getGraph().addEdge("2", "1");
//        a.getGraph().printGraph();
//
//        // удаляем ребро
//        System.out.println("\n удаляем ребро");
//        a.getGraph().delEdge("2", "3");
//        a.getGraph().printGraph();
//
//        // удаляем несуществующее ребро
//        System.out.println("\n попытка удалить несуществующее ребро");
//        a.getGraph().delEdge("2", "6");
//        a.getGraph().printGraph();
//
//        // удаляем ребро у несуществующей вершины
//        System.out.println("\n попытка удаления ребра у несуществующей вершины");
//        a.getGraph().delEdge("10", "2");
//        a.getGraph().printGraph();

//        ///////////////////////////////////////////////////////test 2
//        List<String> b1 = new ArrayList<>();
//        b1.add("2-1"); b1.add("3-2");
//        List<String> b2 = new ArrayList<>();
//        b2.add("1-3"); b2.add("3-4"); b2.add("4-5");
//        List<String> b3 = new ArrayList<>();
//        b3.add("1-6"); b3.add("2-7"); b3.add("4-8");
//        List<String> b4 = new ArrayList<>();
//        b4.add("2-9"); b4.add("3-10");
//        List<String> b5 = new ArrayList<>();
//        b5.add("2"); b5.add("6-10");
//        HashMap<String, List<String>> nmb = new HashMap<>();
//        nmb.put("1", b1);
//        nmb.put("2", b2);
//        nmb.put("3", b3);
//        nmb.put("4", b4);
//        nmb.put("5", b5);
//
//        // попытка некорректного создания графа
//        System.out.println("\n попытка некорректного создания графа");
//        G b = new G("oriented weighted graph", nmb);
//        b.getGraph().printGraph();
//
//        // попытка работы с некорректно созданным графом
//        System.out.println("\n попытка работы с некорректно созданным графом");
//        b.getGraph().addVertex("1", b1);
//
//        // создание взвешенного ориентированного графа
//        System.out.println("\n создание взвешенного ориентированного графа");
//        HashMap<String, List<String>> mb = new HashMap<>();
//        mb.put("1", b1);
//        mb.put("2", b2);
//        mb.put("3", b3);
//        mb.put("4", b4);
//        b = new G("oriented weighted graph", mb);
//        b.getGraph().printGraph();
//
//        // добавляем вершину
//        System.out.println("\n добавили вершину 5");
//        b5 = new ArrayList<>();
//        b5.add("2-11"); b5.add("3-12");
//        b.getGraph().addVertex("5", b5);
//        b.getGraph().printGraph();
//
//        // добавляем вершину с некорректной связью
//        System.out.println("\n попытка добавить вершину с некорректной связью");
//        List<String> b6 = new ArrayList<>();
//        b6.add("8-1"); b6.add("1-1");
//        b.getGraph().addVertex("6", b6);
//        b.getGraph().printGraph();
//
//        // добавляем существующую вершину
//        System.out.println("\n попытка добавить сущетвующую вершину");
//        b.getGraph().addVertex("5", b5);
//        b.getGraph().printGraph();
//
//        // удаляем вершину
//        System.out.println("\n удаляем вершину");
//        b.getGraph().delVertex("1");
//        b.getGraph().printGraph();
//
//        // удаляем несуществующую вершину
//        System.out.println("\n попытка удалить несуществующую вершину");
//        b.getGraph().delVertex("1");
//        b.getGraph().printGraph();
//
//        // добавляем ребро
//        System.out.println("\n добавляем ребро");
//        b.getGraph().addEdge("5", "4-13");
//        b.getGraph().printGraph();
//
//        // добавляем ребро к несуществующей вершине
//        System.out.println("\n попытка добавить ребро к несуществующей вершине");
//        b.getGraph().addEdge("2", "1-1");
//        b.getGraph().printGraph();
//
//        // удаляем ребро
//        System.out.println("\n удаляем ребро");
//        b.getGraph().delEdge("2", "3-4");
//        b.getGraph().printGraph();
//
//        // удаляем несуществующее ребро
//        System.out.println("\n попытка удалить несуществующее ребро");
//        b.getGraph().delEdge("2", "6");
//        b.getGraph().printGraph();
//
//        // удаляем ребро у несуществующей вершины
//        System.out.println("\n попытка удаления ребра у несуществующей вершины");
//        b.getGraph().delEdge("10", "2");
//        b.getGraph().printGraph();

        UndirectedGraph c = new UndirectedGraph("/home/daniil/Общедоступные/university/terms/5 term/graph theory/graph/src/test/g1.txt");
        c.printGraph();
        System.out.println();

        OrientedUnWeightedGraph d = new OrientedUnWeightedGraph("/home/daniil/Общедоступные/university/terms/5 term/graph theory/graph/src/test/g2.txt");
        d.printGraph();
        System.out.println();

        UndirectedGraph e = new UndirectedGraph("/home/daniil/Общедоступные/university/terms/5 term/graph theory/graph/src/test/g3.txt");
        e.printGraph();
        System.out.println();

        OrientedWeightedGraph f = new OrientedWeightedGraph("/home/daniil/Общедоступные/university/terms/5 term/graph theory/graph/src/test/g4.txt");
        f.printGraph();
    }
}
