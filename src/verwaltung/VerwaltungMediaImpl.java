package verwaltung;

import mediaDB.MediaContent;
import mediaDB.Uploadable;
import mediaDB.Uploader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;

public class VerwaltungMediaImpl<T> extends VerwaltungZustand<T> implements VerwaltungMedia <T>{
    private VerwaltungProduction verwaltungProduction;
    private BigDecimal exceedPoint = BigDecimal.valueOf(10000);
    private ArrayList<T> savedFiles = new ArrayList<>();
    private ArrayList<String> mediaName = new ArrayList<>();
    private int asciiCounter = 33;
    private int charCounter = 0;
    private String lastAdress = "";

    public VerwaltungMediaImpl(VerwaltungProduction verwaltungProduction){
        this.verwaltungProduction = verwaltungProduction;
    }

    @Override
    public synchronized void insertFile(T item) {
        if (productionExists(((Uploadable)item).getUploader()) && capacityNotExceeded(((MediaContent)item).getSize())){
            savedFiles.add(item);
            Date currentDate = new Date();
            ((Setter)item).setUploadDate(currentDate);
            //TODO: adresse
            /*
            beim Hochladen wird eine Abrufadresse vergeben (address); zu
            keinem Zeitpunkt können mehrere Mediadateien innerhalb der
            Verwaltung die gleiche Abrufadresse haben
             */
        }
    }
    //TODO: Gesamtkapazität
    private synchronized boolean capacityNotExceeded(BigDecimal size) {
        return (size.compareTo(exceedPoint) < 0) ;
    }

    private synchronized boolean productionExists(Uploader uploader) {
        return verwaltungProduction.getProductions().containsValue(uploader);
    }

    @Override
    public List<T> anzeigen() {
        return savedFiles;
    }

    @Override
    public synchronized void delete(int index) {
        savedFiles.remove(index);
    }

    @Override
    public synchronized void delete(T item) {
        savedFiles.remove(item);
    }

    @Override
    public synchronized void update(T item) {
        ((Updatable)item).update();
    }

    public ArrayList<T> getEingefuegteDataien() {
        return savedFiles;
    }
    public ArrayList<String> getMediaName(){
        return mediaName;
    }
    public String show(){
        String showStr = "";

        for(T obj : savedFiles){
            //showStr.concat(obj.toString() + "\n");
            showStr+=obj.toString();
            showStr+="\n";
        }

        return showStr;
    }

    public void setMedia(ArrayList<T> savedFiles) {
        //mediaName.clear(); //  alles in show nur: lazy
    }

    private String createAdress(){
        String result = "";
        if(asciiCounter==127){
            asciiCounter=33;
            charCounter++;
        }
       //for(int i = 0; i<=charCounter; i++){
       //    for(int i = asciiCounter; i<)
       //}

        return result;
    }



    /*
    bruf vorhandener Mediadateien; wird ein Typ angegeben werden nur
    Mediadateien von diesem Typ aufgelistet
     */


}
