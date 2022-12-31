package Chess.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public King(Point piecePosition, boolean white) {
		this.piecePosition = piecePosition;
		this.white = white;
		this.firstMove = true;
	}

	// Constructor for clone method
	private King(Point piecePosition, boolean white, boolean firstMove) {
		this.firstMove = firstMove;
		this.piecePosition = piecePosition;
		this.white = white;
	}

	@Override
	public List<Move> calculatePossibleMoves(Board board, boolean checkKing) {
		int x = piecePosition.x;
		int y = piecePosition.y;

		List<Move> moves = new ArrayList<Move>();

		// if no board given, return empty list
		if (board == null)
			return moves;

		// add moves around the king if they are valid
		addIfValid(board, moves, new Point(x - 1, y - 1));
		addIfValid(board, moves, new Point(x, y - 1));
		addIfValid(board, moves, new Point(x + 1, y - 1));
		addIfValid(board, moves, new Point(x + 1, y));
		addIfValid(board, moves, new Point(x + 1, y + 1));
		addIfValid(board, moves, new Point(x, y + 1));
		addIfValid(board, moves, new Point(x - 1, y + 1));
		addIfValid(board, moves, new Point(x - 1, y));

		// castling
		if (this.firstMove) {
			if (checkKing && this != board.getInCheck()) {
				List<Piece> pieces = board.getAllPieces();
				List<Piece> okRooks = new ArrayList<Piece>();

				// finds rooks available for castling
				for (int i = 0; i < pieces.size(); i++) {
					if (pieces.get(i).white == this.white && pieces.get(i) instanceof Rook && 
						pieces.get(i).firstMove) {
						okRooks.add(pieces.get(i));
					}
				}

				// for each eligible rook
				for (Piece p : okRooks) {
					boolean canCastle = true;
					// if on right side of board
					if (p.getPiecePosition().x == 7) {
						// if there are pieces between the king and the rook
						for (int ix = this.piecePosition.x + 1; ix < 7; ix++) {
							if (board.getPieceAt(new Point(ix, y)) != null) {
								// castling is not possible
								canCastle = false;
								break;
							}
						}
						if (canCastle)
							moves.add(new CastleMove(this, new Point(x + 2, y), p, new Point(x + 1, y)));
						// if on left side of board
					} else if (p.getPiecePosition().x == 0) {
						// if there are pieces between the king and the rook
						for (int ix = this.piecePosition.x - 1; ix > 0; ix--) {
							if (board.getPieceAt(new Point(ix, y)) != null) {
								// castling is not possible
								canCastle = false;
								break;
							}
						}
						if (canCastle) {
							moves.add(new CastleMove(this, new Point(x - 2, y), p, new Point(x - 1, y)));
						}
					}
				}
			}
		}

		// Remove moves that making own king in check
		if (checkKing) {
			this.removeMovesPutsKingInCheck(board, moves);
		}
		return moves;
	}

	private void addIfValid(Board board, List<Move> moves, Point point) {
		// if the location is valid
		if (board.ValidSpot(point)) {
			// and the location does not contain same color piece
			Piece pc = board.getPieceAt(point);
			if (pc == null || pc.white != this.white) {
				// all the move to the list
				moves.add(new Move(this, point, pc));
			}
		}
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public King clone() {
		return new King(this.piecePosition, this.white, this.firstMove);
	}

	@Override
	public String getImageName() {
		return "King";
	}

}
