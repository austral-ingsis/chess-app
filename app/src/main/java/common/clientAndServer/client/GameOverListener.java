package common.clientAndServer.client;

import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class GameOverListener implements MessageListener<GameOver> {
    private final GameClient gameClient;

    public GameOverListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }


    @Override
    public void handleMessage(@NotNull Message<GameOver> message) {
        gameClient.handleGameOver(message.getPayload());
    }
}
