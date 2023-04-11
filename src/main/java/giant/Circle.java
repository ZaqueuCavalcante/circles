package giant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;

public abstract class Circle {
    int rings;
    int slices;

    int[][] table;

    public Circle() {
        loadInitialState();
    }

    private void loadInitialState() {
        String className = this.getClass().getSimpleName();

        File file = new File("src/circles/" + className + ".txt");
        InputStream input;
        try {
            input = new FileInputStream(file);

            String[] lines = PApplet.loadStrings(input);

            slices = lines.length;
            rings = (int) lines[0].chars().filter(c -> c == ' ').count() + 1;

            table = new int[slices][rings];
            for (int slice = 0; slice < slices; slice++) {
                String[] splitedSlice = lines[slice].split(" ");
                for (int ring = 0; ring < rings; ring++) {
                    int value = Integer.parseInt(splitedSlice[ring]);
                    table[slice][ring] = value;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
