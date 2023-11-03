package edu.austral.dissis.checkers

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(CheckersGameApplication::class.java)
}

class CheckersGameApplication : Application() {
    private val gameEngine = CheckersEngine()
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Checkers"
    }


    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(gameEngine, imageResolver)
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}