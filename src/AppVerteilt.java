import Console.*;
import Handler.*;

public class AppVerteilt {

    // public String getGreeting() {
    //     return  "Enter [Capacity] \n\n";
    // }
    public String getGreeting2() {
        return  "Hello, here are some modes \n" +
                " c to add \n" +
                " r to show \n" +
                " q to quit \n\n";
    }

    public static void main(String[] args) {
        //System.out.println(new AppVerteilt().getGreeting());
        Console console = new ConsoleImpl();
        Handler handler = new HandlerImpl();
        console.registerHandler(handler);


        while(true) {
            System.out.println(new AppVerteilt().getGreeting2());
            String option = console.readString(" Please enter a letter for an option:");
            if(option.equals("c") || option.equals("r")){
                continue;
            }
            else if(option.equals("q")){
                return;
            }
            else{
                option = console.readString(" Please enter a letter for an option:");
            }
        }
    }
}

