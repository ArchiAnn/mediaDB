import Console.*;
import Listener.VerteilungsListener;
import mediaDB.AudioImpl;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;
import mediaDB.VideoImpl;
import verwaltung.VerwaltungMediaImpl;
import verwaltung.VerwaltungProductionImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {


    public static void main(String[] args) throws Exception {
        try {
            ServerSocket serverSocket=new ServerSocket(8800);
            try {Socket socket=serverSocket.accept();
                 DataInputStream in=new DataInputStream(socket.getInputStream());
                 DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                System.out.println("client: "+socket.getInetAddress()+":"+socket.getPort());
                VerwaltungProductionImpl verwaltungProduction = new VerwaltungProductionImpl();
                UploaderImpl uploader1 = new UploaderImpl();
                verwaltungProduction.insertProduction(uploader1);
                VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
                VerteilungsListener listener = new VerteilungsListener(verwaltungProduction, verwaltung);
                AudioImpl audio = new AudioImpl();
                audio.setUploader(uploader1);
                audio.setSize(BigDecimal.valueOf(1));
                audio.setName(audio.toString());
                verwaltung.insertFile(audio);
                while (true){
                    String input =  in.readUTF();
                    //out.writeInt(-in.readInt());
                    if(input.equals("r")){

                        String output = verwaltung.show();
                        System.err.println("output is" + output);
                        out.writeUTF(output);
                    }
                    else if(input.equals("q")) {
                        serverSocket.close();
                    }
                    else{
                        System.err.println("input ist " + input);
                        listener.handleOption(input);
                    }
                }
            } catch (EOFException e) {
                System.out.println("client disconnect");
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
