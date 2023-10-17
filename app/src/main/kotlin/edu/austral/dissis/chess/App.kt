/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package edu.austral.dissis.chess

import edu.austral.dissis.chess.gui.*
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(MyChessGameApplication::class.java)
}

class MyChessGameApplication : AbstractChessGameApplication() {
    override val gameEngine: GameEngine = MyEngine()
    override val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(gameEngine, imageResolver)
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}