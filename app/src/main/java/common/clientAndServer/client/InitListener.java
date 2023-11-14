package common.clientAndServer.client;

import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class InitListener implements MessageListener<InitialState> {
    private final GameClient gameClient;

    public InitListener(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleMessage(@NotNull Message<InitialState> message) {
        gameClient.handleInitialState(message.getPayload());
    }
}

