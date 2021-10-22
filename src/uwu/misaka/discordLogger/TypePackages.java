package uwu.misaka.discordLogger;

import arc.util.Log;
import mindustry.game.Team;
import mindustry.gen.Groups;
import mindustry.gen.Player;

public class TypePackages {
    public static long lastId;

    public interface PlayerHeader {
        public Player getPlayer();
    }

    public interface TeamHeader {
        public Team getTeam();
    }

    public static class BaseCollector {
        public String fullMessage;
        public long id;

        public BaseCollector(String fullMessage) {
            this.fullMessage = fullMessage;
            id = lastId + 1;
            lastId = id;
        }
    }

    public static class JoinMessageCollector extends BaseCollector implements PlayerHeader {
        public String fullMessage;
        private Player player;

        public JoinMessageCollector(String message) {
            super(message);
            fullMessage = message;
            if (!message.contains("has connected. [")) {
                Log.err("Not connection message");
            }
            String[] a = message.split("has connected.");
            String playerId = a[a.length - 1].replace("[", "").replaceAll(" ", "").replaceAll("]", "");
            player = Groups.player.find(player -> player.uuid().equals(playerId));
            if (player == null) {
                player = Player.create();
                player.name(a[0]);
            }
        }

        @Override
        public Player getPlayer() {
            return player;
        }
    }
}
