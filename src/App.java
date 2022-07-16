import Console.*;
import Handler.*;

/*
cli

befehle lesen
event erzeugen
event an handler

handler
an die listner verteilen

listner(kennt Geschäftslogik)
event verstehen
methodeaufruf sn GL übergeben


ui ->(event) gl (da gibts o1 o2)
observer kennen kein cli
event eigenes listener
 */
public class App {

    public String getGreeting() {
        return  "Hello, you can add a production and add/delete an audio or a video.\n\n" +
                " write 1 to add a production \n" +
                " write 2 to add an audio \n" +
                " write 3 to add a video \n" +
                " write 4 to delete audio/video \n" +
                " write 0 to exit.\n\n";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        Console console = new ConsoleImpl();
        Handler handler = new HandlerImpl();
        console.registerHandler(handler);


        while(true) {
            int option = console.readInteger(" Please enter a number for an option:");
            if(option < 5 && option>=0){
                if (option==0){
                    return;
                }
            }
            else{
                option = console.readInteger(" Please enter a number for an option:");
            }
        }
    }
}
