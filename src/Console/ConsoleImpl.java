package Console;

/*
events als objekte die hier behandelt werden sollen
int weg
scanner aus der folien entnehmen
event handler listener n to n

 */
import Handler.Handler;

import java.io.*;
import java.util.Scanner;

public class ConsoleImpl implements Console{
    private int option;
    final private Scanner scanner = new Scanner(System.in);
    private Handler handler;


    @Override
    public String readString(String text) {
        write(text);
        String outputmid = "", outputmid2 = "", output = "";

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String input = null;
        try {
            input = bufferedReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean checker = false;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\\' && i < input.length() - 1 && (input.charAt(i + 1) == 't' || input.charAt(i + 1) == 'b'
                    || input.charAt(i + 1) == 'n' || input.charAt(i + 1) == 'r' || input.charAt(i + 1) == 'f'
                    || input.charAt(i + 1) == '\'' || input.charAt(i + 1) == '"' || input.charAt(i + 1) == '\\') && checker == false) {

                continue;
            } else if (i > 0 && input.charAt(i - 1) == '\\' && (input.charAt(i) == 't' || input.charAt(i) == 'b'
                    || input.charAt(i) == 'n' || input.charAt(i) == 'r' || input.charAt(i) == 'f'
                    || input.charAt(i) == '\'' || input.charAt(i) == '"' || input.charAt(i) == '\\') && checker == false) {
                continue;
            } else if (input.charAt(i) == ' ' && checker == false) {
                continue;
            } else {
                outputmid += input.charAt(i);
                checker = true;
            }

        }
        StringBuilder sb = new StringBuilder(outputmid);
        sb.reverse();

        checker = false;
        for (int i = 0; i < sb.length(); i++) {
            if ((sb.charAt(i) == 't' || sb.charAt(i) == 'b'
                    || sb.charAt(i) == 'n' || sb.charAt(i) == 'f'
                    || sb.charAt(i) == '\'' || sb.charAt(i) == '"' || sb.charAt(i) == '\\')
                    && sb.charAt(i + 1) == '\\' && i < sb.length() - 1 && checker == false) {
                continue;
            } else if (i > 0 && sb.charAt(i) == '\\' && (sb.charAt(i - 1) == 't' || sb.charAt(i - 1) == 'b'
                    || sb.charAt(i - 1) == 'n' || sb.charAt(i - 1) == 'r' || sb.charAt(i - 1) == 'f'
                    || sb.charAt(i - 1) == '\'' || sb.charAt(i - 1) == '"' || sb.charAt(i - 1) == '\\') && checker == false) {
                continue;
            } else if (sb.charAt(i) == ' ' && checker == false) {
                continue;
            } else {
                outputmid2 += sb.charAt(i);
                checker = true;
            }
        }
        StringBuilder sbEnd = new StringBuilder(outputmid2);
        sbEnd.reverse();
        output = sbEnd.toString();
        return output;
    }

    /*
    Ist eine Zahl angegeben ist dies die Kapazität.
     */
    @Override
    public int readInteger(String text) {
        write(text);

        while (true){
            String s = scanner.nextLine();
            try {
                this.option = Integer.parseInt(s);
                notifyHandler();//option nicht speichern- sofort übergeben
            }
            catch (NumberFormatException e){
                System.out.print(text);
                continue;
            }
            break;
        }
        return option;
    }


    @Override
    public void write(String s) {
        System.out.print(s);
    }

    @Override
    public void registerHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void notifyHandler() {
        handler.getOption(option);
    }


}
