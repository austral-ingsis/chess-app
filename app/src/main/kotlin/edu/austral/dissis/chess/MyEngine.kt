package edu.austral.dissis.chess

import common.Game
import common.conector.Conector
import edu.austral.dissis.chess.gui.*

public class MyEngine: GameEngine {
    private var myGame : Game = createCheckersGame(createCheckersGameMode())
    private var currentplayer  = Conector.adaptColour(myGame.turn)

    override fun applyMove(move: Move): MoveResult {
        val oldPos = Conector.getPos(move.from)
        val newPos = Conector.getPos(move.to)
        myGame = (myGame.move(oldPos, newPos))
        if(myGame.isFinished) {return GameOver(currentplayer)}
        currentplayer = Conector.adaptColour(myGame.turn)
        return NewGameState(Conector.getPieces(myGame.board),currentplayer)
    }

    override fun init(): InitialState {
        return InitialState(Conector.adaptBoard(myGame.board), Conector.getPieces(myGame.board),PlayerColor.WHITE)
    }


}