
package ChessEngine.piece;

import java.util.*;

import ChessEngine.*;
import ChessEngine.board.*;
import ChessEngine.board.move.*;

public class King extends Piece {
	public boolean hasMoved;

	public King(int row, int col, ChessColor color) {
		super(row, col, color);
		this.hasMoved = false;

		if (color == ChessColor.white) {
			imagePath = "src/Main/Resources/piece-image2/wk.png";
		} else {
			imagePath = "src/Main/Resources/piece-image2/bk.png";
		}
	}

	public King(King piece) {
		super(piece);
		this.hasMoved = piece.hasMoved;
		this.imagePath = piece.imagePath;
	}

	@Override
	public King clone() {
		return new King(this);
	}

	@Override
	public ArrayList<Move> calculateLegalMoves(Gameplay game) {
		Board board = game.board;

		ArrayList<Move> legalMoves = new ArrayList<>();
		final Tile tileFrom = board.tiles[this.row][this.col];
		Tile tiles[][] = board.tiles;

		// Normal move
		final int[][] possibleDirections = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 },
				{ -1, -1 } };

		for (int[] direction : possibleDirections) {
			int rowTo = this.row + direction[0], colTo = this.col + direction[1];
			if (rowTo < 0 || rowTo >= 8 || colTo < 0 || colTo >= 8) {
				continue;
			}
			Tile tileTo = tiles[rowTo][colTo];
			if (tileTo.isOccupied()) {
				if (tileTo.getPiece().color != this.color) {
					RegularMove newMove = new RegularMove(tileFrom, tileTo);
					if (newMove.isInCheckedAfterMove(board) == false) {
						legalMoves.add(newMove);
					}
				}
				continue;
			}
			Move newMove = new RegularMove(tileFrom, tileTo);
			if (newMove.isInCheckedAfterMove(board) == false) {
				legalMoves.add(newMove);
			}
		}

		// DONE: Castling move
		if (this.hasMoved == false) {
			// King has not moved, King is on col 4
			// Castling king-side
			if (tiles[this.row][7].isOccupied()) {
				Piece pieceAtTile = tiles[this.row][7].getPiece();
				if (pieceAtTile instanceof Rook && pieceAtTile.color == this.color) {
					if (((Rook) pieceAtTile).hasMoved == false) {
						// Check if there are no pieces between the king and the rook
						if (tiles[this.row][5].isOccupied() == false && tiles[this.row][6].isOccupied() == false) {
							// Check if king is in checked when move along the way
							CastlingMove newMove = new CastlingMove(tileFrom, tiles[this.row][5]);
							if (newMove.isInCheckedAfterMove(board) == false) {
								newMove = new CastlingMove(tileFrom, tiles[this.row][6]);
								if (newMove.isInCheckedAfterMove(board) == false) {
									legalMoves.add(newMove);
								}
							}

						}
					}
				}
			}

			// Castling queen-side
			if (tiles[this.row][0].isOccupied()) {
				Piece pieceAtTile = tiles[this.row][0].getPiece();
				if (pieceAtTile instanceof Rook && pieceAtTile.color == this.color) {
					if (((Rook) pieceAtTile).hasMoved == false) {
						// Check if there are no pieces between the king and the rook
						if (tiles[this.row][3].isOccupied() == false && tiles[this.row][2].isOccupied() == false) {
							// Check if king is in checked when move along the way
							CastlingMove newMove = new CastlingMove(tileFrom, tiles[this.row][3]);
							if (newMove.isInCheckedAfterMove(board) == false) {
								newMove = new CastlingMove(tileFrom, tiles[this.row][2]);
								if (newMove.isInCheckedAfterMove(board) == false) {
									legalMoves.add(newMove);
								}
							}

						}
					}
				}
			}

		}
		return legalMoves;
	}

	private int abs(int val) {
		return (val > 0) ? val : (0 - val);
	}

	public boolean isChecked(Board board) {
		ArrayList<Piece> opponentPieces;
		if (this.color == ChessColor.white) {
			opponentPieces = board.blackPieces;
		} else {
			opponentPieces = board.whitePieces;
		}

		for (Piece piece : opponentPieces) {
			int deltaRow = piece.row - this.row, deltaCol = piece.col - this.col;

			// Is checked by pawn
			if (piece instanceof Pawn) {
				if (deltaCol == 1 || deltaCol == -1) {
					if ((this.color == ChessColor.white && deltaRow == 1)
							|| (this.color == ChessColor.black && deltaRow == -1)) {
						return true;
					}
				}
			}
			// Is checked by bishop
			if (piece instanceof Bishop) {
				if (deltaRow == deltaCol || deltaRow == -deltaCol) {
					// Check if there is a piece between them
					int absDelta = (deltaRow == 0) ? abs(deltaCol) : abs(deltaRow);
					int[] direction = { deltaRow / absDelta, deltaCol / absDelta };

					int rowBetween = this.row, colBetween = this.col;
					while (true) {
						rowBetween += direction[0];
						colBetween += direction[1];
						if (rowBetween == piece.row) {
							return true;
						}

						if (board.tiles[rowBetween][colBetween].isOccupied()) {
							break;
						}
					}
				}
			}

			// Is checked by knight
			if (piece instanceof Knight) {
				if ((deltaRow == 2 && deltaCol == 1) || (deltaRow == 2 && deltaCol == -1)
						|| (deltaRow == 1 && deltaCol == 2) || (deltaRow == 1 && deltaCol == -2)
						|| (deltaRow == -2 && deltaCol == 1) || (deltaRow == -2 && deltaCol == -1)
						|| (deltaRow == -1 && deltaCol == 2) || (deltaRow == -1 && deltaCol == -2)) {
					return true;
				}
			}

			// Is checked by rook
			if (piece instanceof Rook) {
				if (deltaRow == 0 || deltaCol == 0) {
					int absDelta = (deltaRow == 0) ? abs(deltaCol) : abs(deltaRow);

					int[] direction = { deltaRow / absDelta, deltaCol / absDelta };
					int rowBetween = this.row, colBetween = this.col;
					while (true) {
						rowBetween += direction[0];
						colBetween += direction[1];

						if (rowBetween == piece.row) {
							return true;
						}

						if (board.tiles[rowBetween][colBetween].isOccupied()) {
							break;
						}
					}
				}
			}

			// Is checked by queen
			if (piece instanceof Queen) {
				// By straight line
				if (deltaRow == 0 || deltaCol == 0) {
					int absDelta = (deltaRow == 0) ? abs(deltaCol) : abs(deltaRow);

					int[] direction = { deltaRow / absDelta, deltaCol / absDelta };
					int rowBetween = this.row, colBetween = this.col;
					while (true) {
						rowBetween += direction[0];
						colBetween += direction[1];

						if (rowBetween == piece.row) {
							return true;
						}

						if (board.tiles[rowBetween][colBetween].isOccupied()) {
							break;
						}
					}
				}

				// By diagonal
				if (deltaRow == deltaCol || deltaCol == -deltaCol) {
					int absDelta = (deltaRow == 0) ? abs(deltaCol) : abs(deltaRow);
					int[] direction = { deltaRow / absDelta, deltaCol / absDelta };
					int rowBetween = this.row, colBetween = this.col;
					while (true) {
						rowBetween += direction[0];
						colBetween += direction[1];

						if (rowBetween == piece.row) {
							return true;
						}

						if (board.tiles[rowBetween][colBetween].isOccupied()) {
							break;
						}
					}
				}
			}
		}
		return false;
	}

	public ChessEnding isEnded(Gameplay game) {
		Board board = game.board;
		ArrayList<Piece> friendlyPieces = new ArrayList<>();
		if (this.color == ChessColor.white) {
			friendlyPieces = board.whitePieces;
		} else {
			friendlyPieces = board.blackPieces;
		}
		for (Piece piece : friendlyPieces) {
			ArrayList<Move> legalMoves = piece.calculateLegalMoves(game);
			if (legalMoves.size() != 0) {
				return ChessEnding.ongoing;
			}
		}

		if (this.isChecked(board)) {
			return (this.color == ChessColor.white) ? ChessEnding.blackWin : ChessEnding.whiteWin;
		} else {
			return ChessEnding.stalemate;
		}
	}

}