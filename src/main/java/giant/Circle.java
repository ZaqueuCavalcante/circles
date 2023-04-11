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

    int d = 900;
    int r = d / 2;

    public void draw(Game game) {
        game.background(220);
        game.fill(220);

        game.strokeWeight(8);
        game.stroke(0, 49, 75);

        game.circle(0, 0, d); // M=0
        game.circle(0, 0, d * 4 / 6); // M=1
        game.circle(0, 0, d * 2 / 6); // M=2

        game.fill(0, 49, 75);
        game.circle(0, 0, 10); // Center

        // Slices
        game.strokeWeight(6);

        float angle = 0;
        game.line(r * cos(angle), r * sin(angle), -r * cos(angle), -r * sin(angle));
        angle = 45;
        game.line(r * cos(angle), r * sin(angle), -r * cos(angle), -r * sin(angle));
        angle = 90;
        game.line(r * cos(angle), r * sin(angle), -r * cos(angle), -r * sin(angle));
        angle = 135;
        game.line(r * cos(angle), r * sin(angle), -r * cos(angle), -r * sin(angle));

        // Points
        angle = 45 / 2;
        int deno = 9;
        game.fill(0, 49, 75);

        int slice = 0;
        game.textSize(30);
        game.textAlign(PConstants.CENTER, PConstants.CENTER);
        for (int i = 0; i < 8; i++) {
            game.text(game.circle.table[slice][0], (8 * r / deno) * cos(angle), (8 * r / deno) * sin(angle));
            game.text(game.circle.table[slice][1], (5 * r / deno) * cos(angle), (5 * r / deno) * sin(angle));
            game.text(game.circle.table[slice][2], (2.20f * r / deno) * cos(angle), (2.20f * r / deno) * sin(angle));

            slice++;
            angle = angle + 45;
        }
    }

    public static final float sin(float angle) {
        return PApplet.sin(PApplet.radians(angle));
    }

    public static final float cos(float angle) {
        return PApplet.cos(PApplet.radians(angle));
    }
}
