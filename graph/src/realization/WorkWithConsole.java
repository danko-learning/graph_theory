package realization;

import java.util.Scanner;

public class WorkWithConsole {

    public void userHelp() {
        System.out.println("Перед началом использования введите тип графа:\n"
                           + "\"type ug\" (неориентированный граф)\n"
                           + "\"type ouwg\" (ориентированный невзвешенный граф)\n"
                           + "\"type owg\" (ориентированный взвешенный граф) \n");
        System.out.println();

        System.out.println("Для создания графа укажите тип его создания:\n"
                           + "\"create by empty\" (пустой граф)\n"
                           + "\"create by list\" (по списку смежности)\n"
                           + "\"create by file\" (по списку смежности из файла)\n"
                           + "\"create by graph\" (по уже заданному графу)\n\n"
                           + "а затем следуйте инструкциию.");
        System.out.println();

        System.out.println("Чтобы задать граф введите тип его определения:\n"
                           + "\"set empty\" (задаёт пустой граф)\n"
                           + "\"set list\" (задаёт по списку смежности)\n"
                           + "\"set file\" (задаёт по списку смежности из файла)\n"
                           + "\"set graph\" (задаёт по уже заданному графу)\n\n"
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
    }

    public void start()
    {
        System.out.println("Чтобы завершить работу введите \"0\"\n\n");
        Scanner sc = new Scanner(System.in);

        String in = sc.nextLine();
        while (!in.equals("0")){

            switch (in) {
                case ("type ug") :
                    break;

                case ("type ouwg") :
                    break;

                case ("type owg") :
                    break;

                case ("create by empty") :
                    break;

                case ("create by list") :
                    break;

                case ("create by file") :
                    break;

                case ("create by graph") :
                    break;

                case ("set empty") :
                    break;

                case ("set list") :
                    break;

                case ("set file") :
                    break;

                case ("set graph") :
                    break;

                case ("addVertex") :
                    break;

                case ("delVertex") :
                    break;

                case ("addEdge") :
                    break;

                case ("delEdge") :
                    break;
            }
        }
    }
}
