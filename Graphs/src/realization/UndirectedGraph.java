package realization;

import java.util.ArrayList;
import java.util.List;

public abstract class UndirectedGraph extends Graph {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2
    @Override
    public int vertexDegree(String vertex) {
        if (this.isNormal) {
            if (this.graph.containsKey(vertex)) {
                return this.graph.get(vertex).size();
            } else {
                System.out.println("В графе нет вершины: " + vertex + "!");
                return -1;
            }
        } else {
            System.out.println("Граф был некорректно создан, пресоздайте его!");
            return -1;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// task2

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task11
    @Override
    public int findMaximumFlow(String start, String end) {
        System.out.println("Данный тип графа не поддерживает алгоритм Форда-Фалкерсона!");
        return -1;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////// task11
}
