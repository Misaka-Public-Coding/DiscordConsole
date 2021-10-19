package uwu.misaka.discordLogger;

import arc.util.Log;
import mindustry.mod.Plugin;

public class EntryPoint extends Plugin {
    public static String password = "ohayo-Onii-chan191021!";
    public Log.LogHandler oldLogger;

    @Override
    public void init() {
        oldLogger = Log.logger;
        Log.logger = (logLevel, s) -> {
            parseLog(logLevel, s);
            oldLogger.log(logLevel, s);
        };
    }

    public void parseLog(Log.LogLevel l, String s) {
        if (l == Log.LogLevel.debug || l == Log.LogLevel.none) {
            return;
        }
        if (l == Log.LogLevel.err) {
            //TODO обработка stacktrace;
        } else if (l == Log.LogLevel.info) {
            //TODO проверка на многолинейность.
        }
    }
}
