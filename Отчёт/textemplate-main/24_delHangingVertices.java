public void delHangingVertexes() {
    if (this.isNormal) {
        if (!this.hangingVertices().isEmpty()) {
            for (String vertex: this.hangingVertices()) {
                delVertex(vertex);}
        }
    } else {
        System.out.println("Граф был некорректно создан, пресоздайте его!");}
}