package test;

import realization.UndirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String [] args) {
        List<String> a1 = new ArrayList<String>();
        a1.add("b");
        a1.add("c");
        List<String> b1 = new ArrayList<String>();
        b1.add("a");
        b1.add("c");
        List<String> c1 = new ArrayList<String>();
        c1.add("a");
        c1.add("b");
        HashMap<String, List<String>> a = new HashMap<>();
        a.put("a", a1);
        a.put("b", b1);
        a.put("c", c1);

        UndirectedGraph ug = new UndirectedGraph(a);

        List<String> d1 = new ArrayList<String>();
        d1.add("a");
        d1.add("b");
        ug.addVertex("d", d1);


        System.out.println(ug.getGraph());
    }
}
