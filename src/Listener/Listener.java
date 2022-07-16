package Listener;

/*

listner kennt Geschäftslogik
event verstehen
methodeaufruf sn GL übergeben


ui ->(event) gl (da gibts o1 o2)
observer kennen kein cli
event eigenes listener
 */

public interface Listener {
    void actualise();
    void handleOption(String option);
}
