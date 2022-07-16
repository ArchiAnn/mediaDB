package Console;

import Handler.Handler;
import Listener.Listener;

/*
cli

befehle lesen
event erzeugen
event an handler

handler
an die listner verteilen
 */

public interface Console {
    String readString(String text);
    int readInteger(String text);
    void write(String s);
    void registerHandler(Handler handler);
    void notifyHandler();
}
