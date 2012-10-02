package chess;

import java.util.ArrayList;

public class Queen extends Piece
{

	public static final int[][] moves =
	{
	{ 1, 1 },
	{ 0, 1 },
	{ 1, 0 } };

	public Queen(Position position, PieceColor color)
	{
		super(position, color);
		this.type = Piece.PieceType.Queen;
	}

	public ArrayList<Position> getAllowedMoves()
	{
		ArrayList<Position> positions = new ArrayList<Position>();

		positions.addAll(addPositions(1, 1, positions));
		positions.addAll(addPositions(1, -1, positions));
		positions.addAll(addPositions(-1, 1, positions));
		positions.addAll(addPositions(-1, -1, positions));

		return positions;
	}

	private ArrayList<Position> addPositions(int ri, int fi,
			ArrayList<Position> positions)
	{
		Piece[] gameState = Game.getInstance().getGameState();
		for (int i = 0; i < moves.length; i++)
		{
			for (int m = 1;; m++)
			{
				int r = (moves[i][0] * ri) * m + position.getRank();
				int f = (moves[i][1] * fi) * m + position.getFile();

				if (r >= 0 && r < ChessBoard.NUMBER_OF_RANKS && f >= 0
						&& f < ChessBoard.NUMBER_OF_FILES)
				{
					if (gameState[r * 8 + f] == null)
						positions.add(new Position(r * 8 + f));
					else if (gameState[r * 8 + f].getColor() != color)
					{
						positions.add(new Position(r * 8 + f));
						break;
					} else
						break;
				} else
					break;
			}
		}
		return positions;
	}

}
