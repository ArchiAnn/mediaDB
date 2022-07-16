package Threads;

import verwaltung.VerwaltungMediaImpl;

public class DeleteFile extends Thread {
    private VerwaltungMediaImpl verwaltung;

    public DeleteFile(VerwaltungMediaImpl verwaltung) {
        this.verwaltung = verwaltung;
    }

    @Override
    public void run() {
        while(true){
            int var = verwaltung.anzeigen().size();
            if(var!=0){
                var -= 1;
                int idxToDelete = (int)(Math.random() * ++var);
                System.out.println("File with " + idxToDelete + " index is to delete");
                verwaltung.delete(idxToDelete);
                System.out.println("one file was deleted." );//observer sagt bescheid
            }
        }
    }
}
