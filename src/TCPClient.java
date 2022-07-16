import Console.*;
import Handler.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8800);
             DataInputStream in=new DataInputStream(socket.getInputStream());
             DataOutputStream out=new DataOutputStream(socket.getOutputStream());){
            Console console = new ConsoleImpl();
            Handler handler = new HandlerImpl();
            console.registerHandler(handler);

            while(true) {
                //int cnt=0;
                //while (cnt<10){
                //    System.out.println("send: " + cnt);
                //    out.writeInt(cnt++);
                //    System.out.println("received: " + in.readInt());
                //}
                System.out.println(getGreeting());
                String option = console.readString(" Please enter a letter for an option:");

                if(option.equals("c")){
                    String input = console.readString(" Please enter [Production name] or [Audio/Video] [Production name] [Size]\n");
                    out.writeUTF(input);
                }
                else if(option.equals("r")){
                    out.writeUTF(option);
                    System.out.println(in.readUTF());
                }

                else if(option.equals("q")){
                    return;
                }
                else{
                    option = console.readString(" Please enter a letter for an option:");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getGreeting() {
        return  "Hello, here are some modes \n" +
                " c to add \n" +
                " r to show \n" +
                " q to quit \n\n";
    }
}
