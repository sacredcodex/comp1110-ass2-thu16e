package comp1110.ass2.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author Xiao Cui
 */
public class Star extends Polygon {

	final double x,y;
	final double UNIT;

	Star(double x, double y, double width){
		this.x = x;
		this.y = y;
		UNIT = width / (4 * Math.sqrt(3));
	}

	public void setPieceStar(char color){
		super.getPoints().setAll(
				x+ 1.5*UNIT * Math.sqrt(3), y+ 0.0,
				x+ 0.75*UNIT * Math.sqrt(3), y+ 0.75*UNIT,
				x+ 0.75*UNIT * Math.sqrt(3), y+ 2.25*UNIT,
				x+ 0.0, y+ 1.5*UNIT,
				x- 0.75*UNIT * Math.sqrt(3), y+ 2.25*UNIT,
				x- 0.75*UNIT * Math.sqrt(3), y+ 0.75*UNIT,
				x- 1.5*UNIT * Math.sqrt(3), y+ 0.0,
				x- 0.75*UNIT * Math.sqrt(3), y- 0.75*UNIT,
				x- 0.75*UNIT * Math.sqrt(3), y- 2.25*UNIT,
				x+ 0.0, y- 1.5*UNIT,
				x+ 0.75*UNIT * Math.sqrt(3), y- 2.25*UNIT,
				x+ 0.75*UNIT * Math.sqrt(3), y- 0.75*UNIT
		);

		super.setFill(Color.WHITE);
		// StrokeWidth is the distance from outer side of Polygon edges to inner sides
		super.setStrokeWidth(0.5*UNIT * Math.sqrt(3));
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

	public void setWizardStar (char color){
		super.getPoints().setAll(
				x+ 2*UNIT * Math.sqrt(3), y+ 0.0,
				x+ UNIT * Math.sqrt(3), y+ UNIT,
				x+ UNIT * Math.sqrt(3), y+ 3*UNIT,
				x+ 0.0, y+ 2*UNIT,
				x- UNIT * Math.sqrt(3), y+ 3*UNIT,
				x- UNIT * Math.sqrt(3), y+ UNIT,
				x- 2*UNIT * Math.sqrt(3), y+ 0.0,
				x- UNIT * Math.sqrt(3), y- UNIT,
				x- UNIT * Math.sqrt(3), y- 3*UNIT,
				x+ 0.0, y- 2*UNIT,
				x+ UNIT * Math.sqrt(3), y- 3*UNIT,
				x+ UNIT * Math.sqrt(3), y- UNIT
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
		super.setStrokeWidth(0);
	}

	public void setEmptyStar(){
		super.getPoints().setAll(
				x+ 1.9*UNIT * Math.sqrt(3), y+ 0.0,
				x+ 0.95*UNIT * Math.sqrt(3), y+ 0.95*UNIT,
				x+ 0.95*UNIT * Math.sqrt(3), y+ 2.85*UNIT,
				x+ 0.0, y+ 1.9*UNIT,
				x- 0.95*UNIT * Math.sqrt(3), y+ 2.85*UNIT,
				x- 0.95*UNIT * Math.sqrt(3), y+ 0.95*UNIT,
				x- 1.9*UNIT * Math.sqrt(3), y+ 0.0,
				x- 0.95*UNIT * Math.sqrt(3), y- 0.95*UNIT,
				x- 0.95*UNIT * Math.sqrt(3), y- 2.85*UNIT,
				x+ 0.0, y- 1.9*UNIT,
				x+ 0.95*UNIT * Math.sqrt(3), y- 2.85*UNIT,
				x+ 0.95*UNIT * Math.sqrt(3), y- 0.95*UNIT
		);
		super.setFill(new Color(0.172, 0.172, 0.27, 1.0));
		super.setStroke(Color.BLACK);
		super.setStrokeWidth(0.2*UNIT);
	}
}
