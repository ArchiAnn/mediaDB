package verwaltung;

import mediaDB.Audio;
import mediaDB.AudioImpl;
import mediaDB.Content;
import mediaDB.UploaderImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VerwaltungMediaTest {


    @Test
    void insertBadNoProduction() {
        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();
        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
        UploaderImpl uploader1 = new UploaderImpl();
        AudioImpl audio = new AudioImpl();
        audio.setUploader(uploader1);
        audio.setSize(BigDecimal.valueOf(1));

        verwaltung.insertFile(audio);
        assertFalse(verwaltung.getEingefuegteDataien().size()==1);
    }

    @Test
    void insert() {
        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();
        UploaderImpl uploader1 = new UploaderImpl();
        uploader1.setName("uploader1");
        verwaltungProduction.insertProduction(uploader1);

        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
        AudioImpl audio = new AudioImpl();
        audio.setUploader(uploader1);
        audio.setSize(BigDecimal.valueOf(1));

        verwaltung.insertFile(audio);
        assertTrue(verwaltung.getEingefuegteDataien().size()==1);
    }

    @Test
    void anzeigen() {

        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();
        UploaderImpl uploader1 = new UploaderImpl();
        verwaltungProduction.insertProduction(uploader1);

        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
        AudioImpl audio = new AudioImpl();
        audio.setUploader(uploader1);
        audio.setSize(BigDecimal.valueOf(1));

        ArrayList<Audio> result = new ArrayList<>();
        result.add(audio);


        verwaltung.insertFile(audio);
        assertEquals(result, verwaltung.anzeigen());
    }

    @Test
    void deleteByItem() {
        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();
        UploaderImpl uploader1 = new UploaderImpl();
        verwaltungProduction.insertProduction(uploader1);

        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
        AudioImpl audio = new AudioImpl();
        audio.setUploader(uploader1);
        audio.setSize(BigDecimal.valueOf(1));

        verwaltung.insertFile(audio);
        verwaltung.delete(audio);
        assertTrue(verwaltung.getEingefuegteDataien().size()==0);
    }


    @Test
    void updateAccess() {
        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();
        UploaderImpl uploader1 = new UploaderImpl();
        verwaltungProduction.insertProduction(uploader1);

        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
        AudioImpl audio = new AudioImpl();
        audio.setUploader(uploader1);
        audio.setSize(BigDecimal.valueOf(1));

        verwaltung.insertFile(audio);
        verwaltung.update(audio);
        verwaltung.update(audio);
        verwaltung.update(audio);

        assertEquals(3, ((Content)verwaltung.anzeigen().get(0)).getAccessCount());
    }

    @Test
    void show(){
        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();
        UploaderImpl uploader1 = new UploaderImpl();
        verwaltungProduction.insertProduction(uploader1);

        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
        AudioImpl audio = new AudioImpl();
        audio.setUploader(uploader1);
        audio.setSize(BigDecimal.valueOf(1));
        audio.toString();
        verwaltung.insertFile(audio);


        assertEquals(audio.toString()+"\n", verwaltung.show());
    }



}