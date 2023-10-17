package edu.austral.dissis.chess

import chess.program.src.Game
import chess.program.src.Position
import chess.program.src.conector.Conector
import edu.austral.dissis.chess.gui.*

public class MyEngine: GameEngine {
    private var myGame : Game = createGame(createGameMode())
    private var currentplayer = PlayerColor.WHITE

    override fun applyMove(move: Move): MoveResult {
        val oldPos = Conector.getPos(move.from)
        val newPos = Conector.getPos(move.to)
        myGame = (myGame.move(oldPos, newPos))
        currentplayer = if (currentplayer == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE
        return NewGameState(Conector.getPieces(myGame.board),currentplayer)
    }

    override fun init(): InitialState {
        return InitialState(Conector.adaptBoard(myGame.board),Conector.getPieces(myGame.board),PlayerColor.WHITE)
    }


}