protected HashMap<String, List<String>> readGraphFromFile(String path) {
    HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
    File file = new File(path);
    FileReader fr;
    try {
        fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            int inedxColon = line.indexOf(":");
            if (inedxColon != -1) {
                String vertex = line.substring(0, inedxColon);
                if (line.length() != inedxColon + 1) {
                    String edges = line.substring(inedxColon + 1, line.length());
                    graph.put(vertex, new ArrayList<>(Arrays.asList(edges.split(" "))));
                } else {
                    graph.put(vertex, new ArrayList<>());}

                line = reader.readLine();
            } else {
                System.out.println("Некорректно задана запись в файле: " + line);
                if (this.graph == null) {
                    this.isNormal = false;}
                return null;}
        }
        return graph;
    } catch (FileNotFoundException fn) {
        System.out.println("Не получилось прочитать файл!");
        if (this.graph == null) {
            this.isNormal = false;}
        return null;
    } catch (IOException io) {
        System.out.println("Не удалось прочитать строку");
        if (this.graph == null) {
            this.isNormal = false;}

        return null;}
}