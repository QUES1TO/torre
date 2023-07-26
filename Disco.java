import java.awt.*;
import java.awt.geom.RoundRectangle2D;
public class Disco extends RoundRectangle2D.Double {

    private Color color;

    public Disco(double x, double y, double width, double height, double arcWidth, Color color) {
        super(x, y, width, height, arcWidth, arcWidth);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}