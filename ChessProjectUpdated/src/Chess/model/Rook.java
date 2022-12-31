package Chess.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

	public Rook(Point piecePosition, boolean white) {
		this.piecePosition = piecePosition;
		this.white = white;
	}
	
	//Constructor for clone method
	private Rook(Point piecePosition, boolean white, boolean firstMove) {
		this.firstMove = firstMove;
		this.piecePosition = piecePosition;
		this.white = white;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public List<Move> calculatePossibleMoves(Board board, boolean checkKing) {
		List<Move> moves = new ArrayList<Move>();

        // if no board given, return empty list
        if (board == null)
            return moves;
        
        addMovesInLine(board, moves, 1, 0);
        addMovesInLine(board, moves, 0, 1);
        addMovesInLine(board, moves, -1, 0);
        addMovesInLine(board, moves, 0, -1);
        
        // check that move doesn't put own king in check
		if(checkKing) {
			this.removeMovesPutsKingInCheck(board , moves);
		}
		
		return moves;
	}

	private void addMovesInLine(Board board, List<Move> moves, int xi, int yi) {
		int x = piecePosition.x;
        int y = piecePosition.y;
        
        Point pt = new Point(x + xi, y + yi);
        Piece pc;
        
        while(board.ValidSpot(pt)) {
            pc = board.getPieceAt(pt);
            if(pc == null) {
                moves.add(new Move(this, pt, pc));
            } else if(pc.white != this.white) {
                moves.add(new Move(this, pt, pc));
                break;
            } else {
                break;
            }
            pt = new Point(pt.x + xi, pt.y + yi);
        }
	}

	@Override
	public Rook clone() {
		return new Rook(this.piecePosition, this.white, this.firstMove);
	}

	@Override
	public String getImageName() {
		return "Rook";
	}
}
