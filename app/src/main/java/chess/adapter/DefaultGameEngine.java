package chess.adapter;

import chess.CreateClassicGame;
import chess.Models.Board;
import chess.Models.Coordinate;
import chess.Models.Game;
import chess.Results.MoveResults;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DefaultGameEngine implements GameEngine {
    private final Game game;
    public DefaultGameEngine() {
        this.game = CreateClassicGame.ClassicGame();
    }
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Position initialPosition = move.component1();
        Position finalPosition = move.component2();
        Coordinate initialCoordinate = Adapter.convertPositionToCoordinate(initialPosition);
        Coordinate finalCoordinate = Adapter.convertPositionToCoordinate(finalPosition);
        String name = game.getBoard().getSquare(initialCoordinate).getPiece().getName();
        MoveResults<Board,Boolean> moveResults = game.movePiece(name,finalCoordinate,game.getCurrentPlayer());
        if (moveResults.errorResult()) {
            return new InvalidMove(moveResults.message());
        } else {
            Board board = moveResults.successfulResult();
            List<ChessPiece> pieces = Adapter.getCurrentPieces(board);
            PlayerColor playerColor = Adapter.convertPlayerColor(game.getTurnHandler().getTurn());
            return new NewGameState(pieces,playerColor);
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(Adapter.getBoardSize(game.getBoard()), Adapter.getCurrentPieces(game.getBoard()),Adapter.convertPlayerColor(game.getTurnHandler().getTurn()));
    }

}
