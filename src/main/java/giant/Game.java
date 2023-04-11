package giant;

import processing.core.PApplet;
import processing.core.PConstants;

public class Game extends PApplet {
    Circle circle;

    int x = 0;
    int y = 0;
    int d = 900;
    int r = d / 2;

    public void settings() {
        size(1000, 1000);

        circle = new CircleMini();
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
            text(circle.table[slice][0], (8 * r / deno) * cos(radians(angle)), (8 * r / deno) * sin(radians(angle)));
            text(circle.table[slice][1], (5 * r / deno) * cos(radians(angle)), (5 * r / deno) * sin(radians(angle)));
            text(circle.table[slice][2], (2.20f * r / deno) * cos(radians(angle)),
                    (2.20f * r / deno) * sin(radians(angle)));

            slice++;
            angle = angle + 45;
        }
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }
    }
}
