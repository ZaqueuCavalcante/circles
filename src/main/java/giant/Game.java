package giant;

import processing.core.PApplet;
import processing.core.PConstants;

public class Game extends PApplet {
    int x = 0;
    int y = 0;
    int d = 900;
    int r = d / 2;

    int[][] table = new int[8][3];

    public void settings() {
        size(1000, 1000);

        table[0] = new int[] { 0, 8, 16 };
        table[1] = new int[] { 1, 9, 17 };
        table[2] = new int[] { 2, 10, 18 };
        table[3] = new int[] { 3, 11, 19 };
        table[4] = new int[] { 4, 12, 20 };
        table[5] = new int[] { 5, 13, 21 };
        table[6] = new int[] { 6, 14, 22 };
        table[7] = new int[] { 7, 15, 23 };
    }

    public void draw() {
        translate(500, 500);

        background(220);
        fill(220);

        strokeWeight(8);
        stroke(0, 49, 75);

        circle(x, y, d); // M=0
        circle(x, y, d * 4 / 6); // M=1
        circle(x, y, d * 2 / 6); // M=2

        fill(0, 49, 75);
        circle(x, y, 10); // Center

        // Slices
        strokeWeight(6);

        float angle = 0;
        line(r * cos(radians(angle)), r * sin(radians(angle)), -r * cos(radians(angle)), -r * sin(radians(angle)));
        angle = 45;
        line(r * cos(radians(angle)), r * sin(radians(angle)), -r * cos(radians(angle)), -r * sin(radians(angle)));
        angle = 90;
        line(r * cos(radians(angle)), r * sin(radians(angle)), -r * cos(radians(angle)), -r * sin(radians(angle)));
        angle = 135;
        line(r * cos(radians(angle)), r * sin(radians(angle)), -r * cos(radians(angle)), -r * sin(radians(angle)));

        // Points
        angle = 45 / 2;
        int deno = 9;
        fill(0, 49, 75);

        int slice = 0;
        textSize(30);
        textAlign(PConstants.CENTER, PConstants.CENTER);
        for (int i = 0; i < 8; i++) {
            text(table[slice][0], (8 * r / deno) * cos(radians(angle)), (8 * r / deno) * sin(radians(angle)));
            text(table[slice][1], (5 * r / deno) * cos(radians(angle)), (5 * r / deno) * sin(radians(angle)));
            text(table[slice][2], (2.20f * r / deno) * cos(radians(angle)), (2.20f * r / deno) * sin(radians(angle)));

            slice++;
            angle = angle + 45;
        }
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }
    }
}
