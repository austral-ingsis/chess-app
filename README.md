# Chess App

This repository is a base example of how to include `edu.austral.dissis.chess:chess-ui` as a dependency.
It runs with a mock version of a chess engine, provided by the ui dependency, that must be replaced with an actual implementation of the engine.

## Implementing the engine

In order to implement the engine of the `chess-ui` dependency to interfaces must be implemented:
* `edu.austral.dissis.chess.gui.GameEngine`
* `edu.austral.dissis.chess.gui.ImageResolver`
* `edu.austral.dissis.chess.gui.ImageResolver`

By default `edu.austral.dissis.chess.gui.DefaultImageResolver` should be enough implementation of `ImageResolver`, but the `gui` only includes the following images:
* archbishop_black
* archbishop_white
* bishop_black
* bishop_white
* chancellor_black
* chancellor_white
* king_black
* king_white
* knight_black
* knight_white
* pawn_black
* pawn_white
* queen_black
* queen_white
* rook_black
* rook_white

## Running

To run the application use: 

`./gradlew run`