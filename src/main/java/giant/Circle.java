package giant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import processing.core.PApplet;
import processing.core.PConstants;

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

    private int d = 900;
    private int r = d / 2;

    public void draw(Game game) {
        game.background(220);
        game.fill(220);

        drawCircles(game);

        drawLines(game);

        drawIndexes(game);
    }

    private void drawCircles(Game game) {
        game.strokeWeight(8);
        game.stroke(0, 49, 75);
        for (int m = 0; m < rings; m++) {
            float radius = r - m * r / rings;
            game.circle(0, 0, radius * 2);
        }
    }

    private void drawLines(Game game) {
        game.strokeWeight(6);
        float angle = 0;
        float delta = 360f / slices;
        for (int n = 0; n < slices / 2; n++) {
            game.line(r * cos(angle), r * sin(angle), -r * cos(angle), -r * sin(angle));
            angle += delta;
        }
    }

    private void drawIndexes(Game game) {
        game.textSize(30);
        game.fill(0, 49, 75);
        game.textAlign(PConstants.CENTER, PConstants.CENTER);

        int slice = 0;
        int deno = 3 * rings;
        float angle = 180f / slices;
        float delta = 360f / slices;

        for (int i = 0; i < slices; i++) {
            int off = deno - 1;
            for (int j = 0; j < rings; j++) {
                game.text(table[slice][j], (off * r / deno) * cos(angle), (off * r / deno) * sin(angle));
                off -= 3;
            }
            slice++;
            angle += delta;
        }
    }

    public static final float sin(float angle) {
        return PApplet.sin(PApplet.radians(angle));
    }

    public static final float cos(float angle) {
        return PApplet.cos(PApplet.radians(angle));
    }
}
