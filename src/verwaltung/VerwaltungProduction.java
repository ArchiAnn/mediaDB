package verwaltung;

import mediaDB.Uploader;

import java.util.HashMap;

public interface VerwaltungProduction <T>{
    void insertProduction(Uploader name);
    HashMap<String, Uploader> getProductions();
    void delete(String name);

}
