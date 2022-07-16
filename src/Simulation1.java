import Threads.*;
import verwaltung.*;

public class Simulation1 {
    public static void main(String []args){
        VerwaltungProduction verwaltungProduction = new VerwaltungProductionImpl();


        VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);

        Thread threadAdd = new AddFile(verwaltung, verwaltungProduction);
        threadAdd.start();
        Thread threadDelete = new DeleteFile(verwaltung);
        threadDelete.start();
    }

}
