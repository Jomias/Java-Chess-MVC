package Chess.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	//Condition for the pawn can be captured by en passant move.
	public boolean enPassantOK = false;
	
	public Pawn(Point piecePosition, boolean white) {
		this.piecePosition = piecePosition;
		this.white = white;
		this.firstMove = true;
	}
	
	//Constructor clone method
	private Pawn(Point piecePosition, boolean white, boolean firstMove) {
		this.firstMove = firstMove;
		this.piecePosition = piecePosition;
		this.white = white;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public List<Move> calculatePossibleMoves(Board board, boolean checkKing) {
		List<Move> moves = new ArrayList<Move>();

        // if no board given, return empty list
        if (board == null)
            return moves;

        // checks moves where the pawn advances a rank
        advance(moves, board);
        // checks moves where the pawn captures another piece
        capture(moves, board);
        // checks en passant moves
        enPassant(moves, board);

        // check that move doesn't put own king in check
        if (checkKing) {
        	this.removeMovesPutsKingInCheck(board, moves);
        }
        return moves;
	}
	
	private void capture(List<Move> moveList, Board board) {
		int x = piecePosition.x;
        int y = piecePosition.y;
        
        Piece pc;
        Point pt;
        int move;
        
        if (this.white)
            move = -1;
        else
            move = 1;
            
        pt = new Point(x - 1, y + move);
        if (board.ValidSpot(pt)) {
            pc = board.getPieceAt(pt);            
            if (pc != null)
                if(this.white != pc.white)
                	moveList.add(new Move(this, pt, pc));    
        }
        pt = new Point(x + 1, y + move);
        if (board.ValidSpot(pt)) {
            pc = board.getPieceAt(pt);           
            if (pc != null)
                if(this.white != pc.white) {
                	moveList.add(new Move(this, pt, pc));       
                }
        }
	}
	
	private void advance(List<Move> moveList, Board board) {
		int x = piecePosition.x;
        int y = piecePosition.y;
        
        Piece pc;
        Point pt;
        int move;
        
        if (this.white)
            move = -1;
        else
            move = 1;
                
        pt = new Point(x, y + move);
        if (board.ValidSpot(pt)) {
            pc = board.getPieceAt(pt);            
            if(pc == null) {
            	moveList.add(new Move(this, pt, pc));     
                pt = new Point(x, y + move * 2);
                pc = board.getPieceAt(pt);
                if (board.ValidSpot(pt) && pc == null && this.firstMove) {
                    moveList.add(new Move(this, pt, pc));
                }
            } 
        }
	}
	
	private void enPassant(List<Move> moves, Board board) {
		int x = piecePosition.x;
        int y = piecePosition.y; 
        
        if (this.white && this.piecePosition.y == 3) {
            if(canCaptureEnPassant(board, new Point(x - 1, y)))
                moves.add(new Move(this, new Point(x - 1, y - 1),
                        board.getPieceAt(new Point(x - 1, y))));
            if(canCaptureEnPassant(board, new Point(x + 1, y)))
                moves.add(new Move(this, new Point(x + 1, y - 1),
                        board.getPieceAt(new Point(x + 1, y)))); 
        }
        if (!this.white && this.piecePosition.y == 4) {
            if(canCaptureEnPassant(board, new Point(x - 1, y)))
                moves.add(new Move(this, new Point(x - 1, y + 1),
                        board.getPieceAt(new Point(x - 1, y))));
            if(canCaptureEnPassant(board, new Point(x + 1, y)))
                moves.add(new Move(this, new Point(x + 1, y + 1),
                        board.getPieceAt(new Point(x + 1, y))));            
        }
    }
	
	private boolean canCaptureEnPassant(Board board, Point point) {
        Piece piece = board.getPieceAt(point);
        if(piece != null)
            if (piece instanceof Pawn && piece.isWhite() !=  this.white)
                if (((Pawn)piece).enPassantOK)
                    return true;
        return false;
    }

	@Override
	public Pawn clone() {
		return new Pawn(this.piecePosition, this.white, this.firstMove);
	}

	@Override
	public String getImageName() {
		return "Pawn";
	}
}
