package Chess.model;

import java.awt.Point;

//Class's purpose is only contain the info of the move
public class Move {
	private Piece movedPiece;
	private Piece capturedPiece;
	private Point moveTo;
	public Move(Piece movedPiece, Point destionation, Piece capturedPiece) {
		this.movedPiece = movedPiece;
		this.moveTo = destionation;
		this.capturedPiece = capturedPiece;
	}
	
	public boolean isMovedPieceNull() {
		return this.movedPiece == null;
	}
	
	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public void setCapturedPiece(Piece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}
	
	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}
	
	public Point getMoveTo() {
		return moveTo;
	}
	
	public void setMoveTo(Point destionation) {
		this.moveTo = destionation;
	}

	@Override
	public String toString() {
		return "[movedPiece=" + movedPiece + ", capturedPiece=" + capturedPiece + ", moveTo=" + moveTo + "]";
	}
}
