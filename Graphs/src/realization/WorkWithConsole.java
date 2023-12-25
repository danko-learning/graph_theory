package realization;

import java.util.*;

public class WorkWithConsole {
    public void userHelp() {
        System.out.println("Для создания графа введите его тип:\n"
                + "\"uU\" (неориентированный невзвешенный граф)\n"
                + "\"uW\" (неориентированный взвешенный граф)\n"
                + "\"oU\" (ориентированный невзвешенный граф)\n"
                + "\"oW\" (ориентированный взвешенный граф) \n");
        System.out.println();

        System.out.println("Чтобы задать граф введите тип его определения:\n"
                + "\"set empty\" (задаёт пустой граф)\n"
                + "\"set list\" (задаёт по списку смежности)\n"
                + "\"set file\" (задаёт по списку смежности из файла)\n"
                + "а затем следуйте инструкциям.");
        System.out.println();

        System.out.println("Чтобы добавить вершину введите:" +
                "\"add vertex\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы добавить вершину введите:" +
                "\"del vertex\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы добавить ребро введите:" +
                "\"add edge\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы удалить ребро введите:" +
                "\"del edge\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы вывести висячие вершины шрафа введите:" +
                "\"print hanging vertices\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы вывести вершину, в которую есть путь из вершины u и нет из вершины v введите:" +
                "\"from u and not v\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы удалить висячие вершины в графе введите:" +
                "\"del hanging vertices\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы проверить граф на связность введите:" +
                "\"check graph to connected\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы вевыести граф введите:" +
                "\"print\"");
        System.out.println();

        System.out.println("Пример списка смежности неориентированного графа:\n" +
                "a:b\n" +
                "b:a c d\n" +
                "c:b\n" +
                "d:b\n" +
                "e:");
        System.out.println();

        System.out.println("Пример списка смежности ориентированного взвешенного графа:\n" +
                "a:b-1 e-2\n" +
                "b:c-3 d-4\n" +
                "c:d-6\n" +
                "d:e--5\n" +
                "e:");
        System.out.println();
    }

    public void work()
    {
        G g = new G();

        System.out.println("Чтобы завершить работу введите \"0\"");
        Scanner sc = new Scanner(System.in);

        System.out.print(">> ");
        String in = sc.nextLine();
        while (!in.equals("0")){

            switch (in) {
                case ("help") :
                    userHelp();
                    System.out.println();
                    break;

                case ("uU") :
                    g = new G("uU");
                    System.out.println();
                    break;

                case ("uW") :
                    g = new G("uW");
                    System.out.println();
                    break;

                case ("oU") :
                    g = new G("oU");
                    System.out.println();
                    break;

                case ("oW") :
                    g = new G("oW");
                    System.out.println();
                    break;

                case ("set empty") :
                    try {
                        g = new G(g.getType());
                        System.out.println();
                    } catch (Exception ex) {
                        System.out.println("Сначала задайте вид графа!\n");
                    }
                    break;

                case ("set list") :
                    HashMap<String, List<String>> graph = new HashMap<String, List<String>>();

                    System.out.println("Введите списко смежности (чтобы завершить ввод введите \"end list\"):");
                    System.out.print("> ");
                    String line = sc.nextLine();

                    boolean err = false;
                    while (!line.equals("end list")) {
                        int inedxColon = line.indexOf(":");

                        if (inedxColon != -1) {
                            String vertex = line.substring(0, inedxColon);

                            if (line.length() != inedxColon + 1) {
                                String edges = line.substring(inedxColon + 1, line.length());
                                graph.put(vertex, new ArrayList<>(Arrays.asList(edges.split(" "))));
                            } else {
                                graph.put(vertex, new ArrayList<>());
                            }

                            System.out.print("> ");
                            line = sc.nextLine();
                        } else {
                            System.out.println("Некорректно задана запись в файле: " + line);
                            err = true;
                            break;
                        }
                    }
                    if (!err) {
                        g.setGraph(graph);
                    }
                    System.out.println();
                    break;

                case ("set file") :
                    System.out.print("Введите путь к файлу >> ");
                    String path = sc.nextLine();

                    g.setGraph(path);
                    System.out.println();
                    break;

                case ("add vertex") :
                    List<String> connect;

                    System.out.print("Введите имя вершины >> ");
                    String vertex = sc.nextLine();
                    System.out.println("Введите список смежных вершин (если граф взвешенный, то через - введите вес связи)");
                    System.out.print("> ");
                    String connections = sc.nextLine();

                    if (connections.length() > 0) {
                        connect =  new ArrayList<>( Arrays.asList(connections.split(" ")));
                    } else {
                        connect = new ArrayList<>();
                    }
                    g.getGraph().addVertex(vertex, connect);
                    System.out.println();
                    break;

                case ("del vertex") :
                    System.out.print("Введите имя вершины >> ");
                    vertex = sc.nextLine();

                    g.getGraph().delVertex(vertex);
                    System.out.println();
                    break;

                case ("add edge") :
                    System.out.print("Введите имя вершины >> ");
                    vertex = sc.nextLine();
                    System.out.println("Введите имя смежной вершин (если граф взвешенный, то через - введите вес связи)");
                    System.out.print("> ");
                    String edge = sc.nextLine();

                    g.getGraph().addEdge(vertex, edge);
                    System.out.println();
                    break;

                case ("del edge") :
                    System.out.print("Введите имя вершины >> ");
                    vertex = sc.nextLine();
                    System.out.println("Введите имя смежной вершины (если граф взвешенный, то через - введите вес связи)");
                    System.out.print("> ");
                    edge = sc.nextLine();

                    g.getGraph().delEdge(vertex, edge);
                    System.out.println();
                    break;

                case ("print") :
                    System.out.println("graph:");
                    g.getGraph().printGraph();
                    System.out.println();
                    break;

                case ("print hanging vertices") : //////////////////////////////////////////////////////////////// task2
                    g.getGraph().printHangingVertices();
                    System.out.println();
                    break;

                case ("from u and not v") : ////////////////////////////////////////////////////////////////////// task3
                    System.out.print("Введите вершину u >> ");
                    String u = sc.nextLine();
                    System.out.print("Введите вершину v >> ");
                    String v = sc.nextLine();
                    g.getGraph().printFromUAndNotFromV(u, v);
                    System.out.println();
                    break;

                case ("del hanging vertices"): /////////////////////////////////////////////////////////////////// task4
                    g.getGraph().delHangingVertexes();
                    System.out.println();
                    break;

                case ("check graph to connected"): /////////////////////////////////////////////////////////////// task5
                    g.getGraph().printIsConnectedGraph();
                    System.out.println();
                    break;

                case ("shortest path from u to v"): ////////////////////////////////////////////////////////////// task6
                    System.out.print("Введите вершину u >> ");
                    u = sc.nextLine();
                    System.out.print("Введите вершину v >> ");
                    v = sc.nextLine();
                    g.getGraph().printShortestPathFromUtoV(u, v);
                    System.out.println();
                    break;

                case ("krascal's algorithm"): //////////////////////////////////////////////////////////////////// task7
                    g.getGraph().printKrascalsAlgorithm();
                    System.out.println();
                    break;

                case ("dijkstra's algorithm"): /////////////////////////////////////////////////////////////////// task8
                    System.out.print("Введите вершину истока >> ");
                    String start = sc.nextLine();
                    g.getGraph().printDijkstrasAlgorithm(start);
                    System.out.println();
                    break;

                case ("ford-belman's algorithm"): //////////////////////////////////////////////////////////////// task9
                    System.out.print("Введите вершину истока >> ");
                    start = sc.nextLine();
                    System.out.print("Введите вершину u >> ");
                    u = sc.nextLine();
                    System.out.print("Введите вершину v >> ");
                    v = sc.nextLine();
                    g.getGraph().printFordBelmansAlgorithm(start, u, v);
                    System.out.println();
                    break;

                case ("floyd's algorithm") : //////////////////////////////////////////////////////////////////// task10
                    System.out.print("Введите вершину истока >> ");
                    start = sc.nextLine();
                    System.out.print("Введите вершину u >> ");
                    u = sc.nextLine();
                    System.out.print("Введите вершину v >> ");
                    v = sc.nextLine();
                    g.getGraph().printFloydsAlgorithm(start, u, v);
                    System.out.println();
                    break;

                case ("ford-falkerson's algorithm") : /////////////////////////////////////////////////////////// task11
                    System.out.print("Введите вершину истока >> ");
                    start = sc.nextLine();
                    System.out.print("Введите вершину стока >> ");
                    u = sc.nextLine();
                    g.getGraph().printMaximumFlow(start, u);
                    System.out.println();
                    break;

                default:
                    System.out.println("Нет такой команды");
            }

            System.out.print(">> ");
            in = sc.nextLine();
        }
    }
}
