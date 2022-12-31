package Chess.model;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Cloneable, Serializable{
	protected boolean white;
	protected Point piecePosition;
	public abstract List<Move> calculatePossibleMoves(Board board, boolean checkKing);
	protected boolean firstMove = true;
	
	public Point getPiecePosition() {
		return piecePosition;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	public void setPiecePosition(Point piecePosition) {
		this.piecePosition = piecePosition;
	}

	public boolean isWhite() {
		return this.white;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}
	
	protected void removeMovesPutsKingInCheck(Board board, List<Move> movesList) {
		for(int i = 0; i < movesList.size(); ++i) {
			if(board.movePutsKingInCheck(movesList.get(i), this.white)) {
				movesList.remove(movesList.get(i));
				--i;
			}
		}
	}
	
	public void moveTo(Point destination) {
		this.firstMove = false;
		this.piecePosition = destination;
	}
	
	public abstract String toString();
	@Override
	protected abstract Piece clone();

	public abstract String getImageName();
}
