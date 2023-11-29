package edu.austral.dissis.checkers

import edu.austral.dissis.checkers.factory.createClassicCheckersGame
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.common.Adapter
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    Application.launch(CheckersApp::class.java)
}

class CheckersApp : Application() {
    private val gameAdapter = Adapter(createClassicCheckersGame())
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }


    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(gameAdapter, imageResolver)
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}