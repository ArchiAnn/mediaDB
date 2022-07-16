package verwaltung;

import java.util.List;

public interface VerwaltungMedia <T>{
    void insertFile(T item);
    List<T> anzeigen();
    void delete(int index);
    void delete(T item);
    void update(T item);
}
