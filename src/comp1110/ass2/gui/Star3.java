package comp1110.ass2.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

// The empty star, color darkblue (RGB 44, 44, 69)
public class Star3 extends Polygon {
	double x,y;
	final double unit;

	Star3(double x, double y, double winWidth){
		super();
		unit = winWidth * (20 / 21) / 7 / (4 * Math.sqrt(3));
		this.x = x;
		this.y = y;
		super.getPoints().addAll(
				x+ 1.9*unit * Math.sqrt(3), y+ 0.0,
				x+ 0.95*unit * Math.sqrt(3), y+ 0.95*unit,
				x+ 0.95*unit * Math.sqrt(3), y+ 2.85*unit,
				x+ 0.0, y+ 1.9*unit,
				x- 0.95*unit * Math.sqrt(3), y+ 2.85*unit,
				x- 0.95*unit * Math.sqrt(3), y+ 0.95*unit,
				x- 1.9*unit * Math.sqrt(3), y+ 0.0,
				x- 0.95*unit * Math.sqrt(3), y- 0.95*unit,
				x- 0.95*unit * Math.sqrt(3), y- 2.85*unit,
				x+ 0.0, y- 1.9*unit,
				x+ 0.95*unit * Math.sqrt(3), y- 2.85*unit,
				x+ 0.95*unit * Math.sqrt(3), y- 0.95*unit
		);
		super.setFill(new Color(0.172, 0.172, 0.27, 1.0));
		super.setStroke(Color.BLACK);
		super.setStrokeWidth(0.1*unit);
	}
}
