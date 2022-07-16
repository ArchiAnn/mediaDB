package Threads;

import mediaDB.AudioImpl;
import mediaDB.UploaderImpl;
import mediaDB.VideoImpl;
import verwaltung.VerwaltungMediaImpl;
import verwaltung.VerwaltungProduction;

import java.math.BigDecimal;

public class AddFile extends Thread{

    private VerwaltungMediaImpl verwaltung;
    private VerwaltungProduction verwaltungProduction;

    public AddFile(VerwaltungMediaImpl verwaltung, VerwaltungProduction verwaltungProduction) {
        this.verwaltung = verwaltung;
        this.verwaltungProduction = verwaltungProduction;
    }

    @Override
    public void run() {
        while(true){
            UploaderImpl uploader1 = new UploaderImpl();
            verwaltungProduction.insertProduction(uploader1);
            AudioImpl audio = new AudioImpl();
            audio.setUploader(uploader1);
            audio.setSize(BigDecimal.valueOf(1));
            VideoImpl video = new VideoImpl();
            video.setUploader(uploader1);
            video.setSize(BigDecimal.valueOf(1));

            int var = 10;
            int randomNum = (int)(Math.random()* ++var);

            if(randomNum%2!=0){
                verwaltung.insertFile(audio);
                System.out.println("audio added. " + verwaltung.anzeigen().size() + " files saved");//+observer
            }
            else {
                verwaltung.insertFile(video);
                System.out.println("video added. " + verwaltung.anzeigen().size() + " files saved");
            }


        }
    }
}
