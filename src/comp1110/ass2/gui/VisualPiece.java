package comp1110.ass2.gui;

import comp1110.ass2.Piece;
import javafx.collections.ObservableList;
import javafx.scene.Group;

import java.util.HashSet;
import java.util.Set;

public class VisualPiece extends Group {

	private Set<Star> stars = new HashSet<>();
	private char color;
	double x,y;
	double starWidth;


	VisualPiece(double x, double y, Piece piece, double starWidth){
		super();
		this.starWidth = starWidth;
		this.x = x;
		this.y = y;
		this.color = piece.getColor();
		Set<Star> starSet = new HashSet<>();
		for (int i : piece.getShape()) {
			starSet.add(new Star(getNextX(i), getNextY(i), starWidth));
		}
		if (color != 'p')
			starSet.add(new Star(x, y, starWidth));
		if (color == 'g')
			starSet.add(new Star(2 * getNextX(piece.getShape()[0]) - x, 2 * getNextY(piece.getShape()[0]) -y, starWidth));
			//x+2i=2(x+i)-x
		for (Star i : starSet) {
			i.setPieceStar(color);
		}
		this.getChildren().addAll(starSet);
	}
	private double getNextX(int direction){
		return x + Math.cos(Math.PI / 3 * direction) * starWidth;
	}
	private double getNextY(int direction){
		return y + Math.sin(Math.PI / 3 * direction) * starWidth;
	}


}
