package Listener;

import Console.*;
import mediaDB.AudioImpl;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;
import mediaDB.VideoImpl;
import verwaltung.VerwaltungMediaImpl;
import verwaltung.VerwaltungProductionImpl;

import java.math.BigDecimal;

public class VerteilungsListener implements Listener{
    private VerwaltungProductionImpl verwaltungProduction = new VerwaltungProductionImpl();
    private VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
    private Console console = new ConsoleImpl();

    private long size;
    private String production;

    public VerteilungsListener(VerwaltungProductionImpl verwaltungProduction, VerwaltungMediaImpl verwaltung){
        this.verwaltung = verwaltung;
        this.verwaltungProduction = verwaltungProduction;
    }

    @Override
    public void actualise() {

    }

    @Override
    public void handleOption(String input) {



            if(input.startsWith("Audio ")){
                AudioImpl audio = new AudioImpl();
                parseInput(input.substring(6));
                audio.setUploader((Uploader) verwaltungProduction.getProductions().get(production));
                audio.setSize(BigDecimal.valueOf(size));
                audio.setName(audio.toString());
                verwaltung.insertFile(audio);
            }
            else if (input.startsWith("Video ")){
                VideoImpl video = new VideoImpl();
                parseInput(input.substring(6));
                video.setUploader((Uploader) verwaltungProduction.getProductions().get(production));
                video.setSize(BigDecimal.valueOf(size));
                video.setName(video.toString());
                verwaltung.insertFile(video);
            }
            else {
                if(!verwaltungProduction.getProductionsName().contains(input)){
                    UploaderImpl uploader1 = new UploaderImpl();
                    uploader1.setName(input);
                    verwaltungProduction.insertProduction(uploader1);
                }
            }
    }

    private void parseInput(String in){
        int index = 0;
        for(int i = 0; i<in.length(); i++){
            if(in.charAt(i)==' '){
                index=i;
                break;
            }
        }
        production = in.substring(0,index-1);
        size = Integer.parseInt(in.substring(index+1));
    }

}
