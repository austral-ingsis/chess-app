package common.clientAndServer.server;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerBuilder;

public class GameServer {

    private final Server server;
    private final GameEngine gameEngine;
    private final GameView gameView;

    public GameServer(GameEngine gameEngine, GameView gameView, ServerBuilder serverBuilder) {
        this.gameEngine = gameEngine;
        this.gameView = gameView;
        addStatesToGameView(new GameListener(this));
        this.server = buildServer(serverBuilder);
        server.start();
    }

    public void handleMove(Move move) {
        MoveResult result = gameEngine.applyMove(move);
        gameView.handleMoveResult(result);
        broadcastState(result);
    }

    public void broadcastState(MoveResult state) {
        if (state instanceof NewGameState) {
            server.broadcast(new Message<>("new-game-state", state));
        } else if (state instanceof GameOver) {
            server.broadcast(new Message<>("game-over", state));
        } else if (state instanceof InvalidMove) {
            server.broadcast(new Message<>("invalid-move", state));
        }
    }

    public void handleNewGame() {
        InitialState initialState = gameEngine.init();
        gameView.handleInitialState(initialState);
        server.broadcast(new Message<>("init", initialState));
    }

    public Server buildServer(ServerBuilder builder){
        return builder
                .withPort(8088)
                .withConnectionListener(new GameServerListener(this))
                .addMessageListener("move", new TypeReference<>(){}, new MoveListener(new GameListener(this)))
                .build();
    }

    private void addStatesToGameView(GameEventListener gameListener) {
        gameView.addListener(gameListener);
        gameView.handleInitialState(gameEngine.init());
    }
}
