package realization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class WorkWithConsole {

    public void userHelp() {
        System.out.println("Для создания графа введите его тип:\n"
                           + "\"type ug\" (неориентированный граф)\n"
                           + "\"type ouwg\" (ориентированный невзвешенный граф)\n"
                           + "\"type owg\" (ориентированный взвешенный граф) \n");
        System.out.println();

        System.out.println("Чтобы задать граф введите тип его определения:\n"
                           + "\"set empty\" (задаёт пустой граф)\n"
                           + "\"set list\" (задаёт по списку смежности)\n"
                           + "\"set file\" (задаёт по списку смежности из файла)\n\n"
                           + "а затем следуйте инструкциию.");
        System.out.println();

        System.out.println("Чтобы добавить вершину введите:" +
                           "\"addVertex\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы добавить вершину введите:" +
                "\"delVertex\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы добавить вершину введите:" +
                "\"addEdge\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы добавить вершину введите:" +
                "\"delEdge\" и затем следуйте инструкции");
        System.out.println();

        System.out.println("Чтобы вевыести граф введите:" +
                "\"print\"");
        System.out.println();

        System.out.println("Чтобы сохранить граф в файл введите:" +
                "\"write\"");

        System.out.println("Чтобы вывести все висячие вершины (степень вершины 1) введите:" +
                "\"hanging vertexes\"");

        System.out.println("Чтобы вывести вершину в которую есть дуга из вершины u и нет дуги из вершины v\n" +
                "введите: \"from u and not from v\"");

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

        System.out.println("Чтобы завершить работу введите \"0\"\n\n");
        Scanner sc = new Scanner(System.in);

        String in = sc.nextLine();
        while (!in.equals("0")){

            switch (in) {
                case ("help") :
                    userHelp();
                    break;

                case ("type ug") :
                    g = new G("undirected graph");
                    break;

                case ("type ouwg") :
                    g = new G("oriented unweighted graph");
                    break;

                case ("type owg") :
                    g = new G("oriented weighted graph");
                    break;

                case ("set empty") :
                    try {
                        g = new G (g.getType());
                    } catch (Exception ex) {
                        System.out.println("Сначала создайте граф!");
                    }
                    break;

                case ("set list") :
                    HashMap<String, List<String>> graph = new HashMap<String, List<String>>();

                    System.out.println("Введите списко смежности\n(чтобы завершить ввод введите \"end list\"):");
                    String temp = sc.nextLine();

                    boolean err = false;

                    while (!temp.equals("end list") || !temp.equals("")) {
                        int inedxColon = temp.indexOf(":");
                        if (inedxColon != -1) {
                            String vertex = temp.substring(0, inedxColon);
                            String edges = temp.substring(inedxColon + 1, temp.length());
                            if (edges.length() > 0) {
                                graph.put(vertex, new ArrayList<>( Arrays.asList(edges.split(" "))));
                            } else {
                                graph.put(vertex, new ArrayList<>());
                            }
                            temp = sc.nextLine();
                        } else {
                            if (temp.equals("end list")) {
                                break;
                            }
                            System.out.println("Некорректно задан список смежности (не удалось найти имя вершины в строке \t" + temp);
                            err = true;
                            break;
                        }
                    }

                    if (!err) {
                        g.setGraph(graph);
                    }
                    break;

                case ("set file") :
                    System.out.println("Введите путь к файлу:");
                    String wayToFile = sc.nextLine();

                    g.setGraph(wayToFile);
                    break;

                case ("addVertex") :
                    List<String> connect;

                    System.out.println("Введите имя вершины:");
                    String name = sc.nextLine();
                    System.out.println("Введите список смежных вершин\n(если граф взвешенный, то через - введите вес связи):");
                    String connections = sc.nextLine();

                    if (connections.length() > 0) {
                        connect =  new ArrayList<>( Arrays.asList(connections.split(" ")));
                    } else {
                        connect = new ArrayList<>();
                    }
                    g.getGraph().addVertex(name, connect);
                    break;

                case ("delVertex") :
                    System.out.println("Введите имя вершины:");
                    name = sc.nextLine();

                    g.getGraph().delVertex(name);
                    break;

                case ("addEdge") :
                    System.out.println("Введите имя вершины:");
                    name = sc.nextLine();
                    System.out.println("Введите имя смежной вершины\n(если граф взвешенный, то через - введите вес связи):");
                    String edge = sc.nextLine();

                    g.getGraph().addEdge(name, edge);
                    break;

                case ("delEdge") :
                    System.out.println("Введите имя вершины:");
                    name = sc.nextLine();
                    System.out.println("Введите имя смежной вершины\n(если граф взвешенный, то через - введите вес связи):");
                    edge = sc.nextLine();

                    g.getGraph().delEdge(name, edge);
                    break;

                case ("print") :
                    System.out.println("\n graph:");
                    g.getGraph().printGraph();
                    break;

                case ("write") :
                    try {
                        PrintWriter out = new PrintWriter("/home/daniil/Общедоступные/university/terms/5 term/graph theory/graph/src/test/graph");

                        if (g.getList() != null) {
                            for (String key : g.getList().keySet()) {
                                String p = key + ":";
                                for (String con : g.getList().get(key)) {
                                    p += con + " ";
                                }
                                out.println(p);
                            }
                        } else {
                            System.out.println("Граф не был создан!");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Что-то пошло не так!");
                    }
                    break;

                case ("print hanging vertexes"):
                    g.getGraph().printHangingVertices();
                    break;

                case ("from u and not from v"):
                    String u;
                    String v;

                    System.out.println("Введите u:");
                    u = sc.nextLine();

                    System.out.println("Введите v:");
                    v = sc.nextLine();

                    g.getGraph().printFromUAndNotFromV(u, v);
                    break;

                default:
                    System.out.println("Нет такой команды");
            }

            in = sc.nextLine();
        }
    }
}
