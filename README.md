# Chess App

This repository is a base example of how to include `edu.austral.dissis.chess:chess-ui` as a dependency.
It runs with a mock version of a chess engine, provided by the ui dependency, that must be replaced with an actual implementation of the engine.

## Requirements 

This project depends on a package published in the GitHub Packages Registry. In order to download it a GitHub token must be used.
Instruction on how to create a GitHub personal token are [here](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token). Once created the following environment variables must be defined:
* GITHUB_USER
* GITHUB_TOKEN

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