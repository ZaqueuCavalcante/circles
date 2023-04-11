package giant;

import processing.core.PApplet;

public class Game extends PApplet {
    Circle circle;

    public void settings() {
        size(1000, 1000);

        circle = new CircleMini();
    }

    public void draw() {
        translate(500, 500);

        circle.draw(this);
    }

    public void keyPressed() {
        if (keyCode == 10) {

        }
    }
}
