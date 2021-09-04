package comp1110.ass2.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

// The star used in Wizard puzzle without little white star inside
public class Star2 extends Polygon {
	// center
	double x,y;
	final double unit; // height = 6 * unit  width = 4√3 * unit


	Star2(double x, double y, double winWidth, char color){
		super();
		unit = winWidth * (20 / 21) / 7 / (4 * Math.sqrt(3));
		this.x = x;
		this.y = y;
		super.getPoints().addAll(
				x+ 2*unit * Math.sqrt(3), y+ 0.0,
				x+ unit * Math.sqrt(3), y+ unit,
				x+ unit * Math.sqrt(3), y+ 3*unit,
				x+ 0.0, y+ 2*unit,
				x- unit * Math.sqrt(3), y+ 3*unit,
				x- unit * Math.sqrt(3), y+ unit,
				x- 2*unit * Math.sqrt(3), y+ 0.0,
				x- unit * Math.sqrt(3), y- unit,
				x- unit * Math.sqrt(3), y- 3*unit,
				x+ 0.0, y- 2*unit,
				x+ unit * Math.sqrt(3), y- 3*unit,
				x+ unit * Math.sqrt(3), y- unit
		);

		switch (color) {
			case 'r' -> super.setFill(Color.RED);
			case 'o' -> super.setFill(Color.ORANGE);
			case 'y' -> super.setFill(Color.GOLD);
			case 'g' -> super.setFill(Color.GREEN);
			case 'b' -> super.setFill(Color.BLUE);
			case 'i' -> super.setFill(Color.PURPLE);
			case 'p' -> super.setFill(Color.DEEPPINK);
		}
	}
}
