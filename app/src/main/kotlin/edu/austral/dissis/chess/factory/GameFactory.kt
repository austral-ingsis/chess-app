package edu.austral.dissis.chess.factory

import common.Game
import common.GameMode
import common.Player
import common.enums.Color

class GameFactory {
    fun createNormalGame(gm: GameMode): Game {
        val player1 = Player("player1", Color.WHITE)
        val player2 = Player("player2", Color.BLACK)
        val players = ArrayList<Player>()
        players.add(player1)
        players.add(player2)
        val game = Game(gm, players)
        return game
    }
}