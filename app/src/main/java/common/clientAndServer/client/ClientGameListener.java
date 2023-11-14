package common.clientAndServer.client;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import org.jetbrains.annotations.NotNull;

public class ClientGameListener implements GameEventListener {
    private final GameClient client;

    public ClientGameListener(GameClient client) {
        this.client = client;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        client.handleMove(move);
    }
}
