package common.adapter;
import common.models.Board;
import common.models.Coordinate;
import common.models.Game;
import common.results.MoveResults;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DefaultGameEngine implements GameEngine {
    private final Game game;
    public DefaultGameEngine(Game game) {
        this.game = game;
    }
    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Position initialPosition = move.component1();
        Position finalPosition = move.component2();
        Coordinate initialCoordinate = Adapter.convertPositionToCoordinate(initialPosition);
        Coordinate finalCoordinate = Adapter.convertPositionToCoordinate(finalPosition);

        MoveResults<Board,Boolean> moveResults = game.movePiece(initialCoordinate,finalCoordinate,game.getCurrentPlayer());
        if (moveResults.errorResult()) {
            if (moveResults.message().equals("CheckMate")) {
                return new GameOver(Adapter.convertPlayerColor(game.getTurnHandler().turn()));
            } else {
                return new InvalidMove(moveResults.message());
            }
        }else {
            Board board = moveResults.successfulResult();
            List<ChessPiece> pieces = Adapter.getCurrentPieces(board);
            PlayerColor playerColor = Adapter.convertPlayerColor(game.getTurnHandler().turn());
            return new NewGameState(pieces,playerColor);
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(Adapter.getBoardSize(game.getBoard()), Adapter.getCurrentPieces(game.getBoard()),Adapter.convertPlayerColor(game.getTurnHandler().turn()));
    }

}
