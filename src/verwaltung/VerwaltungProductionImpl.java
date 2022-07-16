package verwaltung;

import mediaDB.Uploader;

import java.util.ArrayList;
import java.util.HashMap;

public class VerwaltungProductionImpl <T> extends VerwaltungZustand<T> implements VerwaltungProduction <T>{

    private HashMap<String, Uploader> registeredProduction = new HashMap<>();
    private ArrayList<String> productionsName = new ArrayList<>();
    private ArrayList<Uploader> productions = new ArrayList<>();

    @Override
    public void insertProduction(Uploader name) {
        if(!registeredProduction.containsKey(name.getName())){// TODO: why not value
            registeredProduction.put(name.getName(), name);
            productionsName.add(name.getName());
            productions.add(name);
        }
    }

    @Override
    public HashMap<String, Uploader>  getProductions() {
        return registeredProduction;
    }

    public ArrayList<Uploader> getUploader(){
        return productions;
    }

    @Override
    public void delete(String name) {
        registeredProduction.remove(name);
        productionsName.remove(name);
    }
    public ArrayList<String> getProductionsName(){
        return productionsName;
    }

    public void setProductions(ArrayList<Uploader> productions){
        for(Uploader upl : productions){
            insertProduction(upl);
        }
    }
}
