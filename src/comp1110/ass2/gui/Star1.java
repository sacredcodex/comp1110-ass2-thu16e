package comp1110.ass2.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

//The normal star with little white star inside
public class Star1 extends Polygon {
	//center
	double x,y;
	final double unit; // height = 6 * unit  width = 4√3 * unit


	Star1(double x, double y, double winWidth, char color){
		super();
		unit = winWidth * (20 / 21) / 7 / (4 * Math.sqrt(3));
		this.x = x;
		this.y = y;
		super.getPoints().addAll(
				x+ 1.5*unit * Math.sqrt(3), y+ 0.0,
				x+ 0.75*unit * Math.sqrt(3), y+ 0.75*unit,
				x+ 0.75*unit * Math.sqrt(3), y+ 2.25*unit,
				x+ 0.0, y+ 1.5*unit,
				x- 0.75*unit * Math.sqrt(3), y+ 2.25*unit,
				x- 0.75*unit * Math.sqrt(3), y+ 0.75*unit,
				x- 1.5*unit * Math.sqrt(3), y+ 0.0,
				x- 0.75*unit * Math.sqrt(3), y- 0.75*unit,
				x- 0.75*unit * Math.sqrt(3), y- 2.25*unit,
				x+ 0.0, y- 1.5*unit,
				x+ 0.75*unit * Math.sqrt(3), y- 2.25*unit,
				x+ 0.75*unit * Math.sqrt(3), y- 0.75*unit
			);

		super.setFill(Color.WHITE);

		// StrokeWidth is the distance from outer side of Polygon edges to inner sides
		super.setStrokeWidth(0.5*unit * Math.sqrt(3));
		switch (color) {
			case 'r' -> super.setStroke(Color.RED);
			case 'o' -> super.setStroke(Color.ORANGE);
			case 'y' -> super.setStroke(Color.GOLD);
			case 'g' -> super.setStroke(Color.GREEN);
			case 'b' -> super.setStroke(Color.BLUE);
			case 'i' -> super.setStroke(Color.PURPLE);
			case 'p' -> super.setStroke(Color.DEEPPINK);
		}
	}
}
