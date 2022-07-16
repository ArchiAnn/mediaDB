package verwaltung;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Collection;

public class VerwaltungZustand<T> {

    public static <T> void serializeJOS(String filename, Collection<T> items){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> Collection<T> deserializeJOS(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return (Collection<T>)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void writeJBPXML(String fileName, Collection<T> items) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));) {
            encoder.writeObject(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> Collection<T> readJBPXML(String filename) {
        try (XMLDecoder decoder= new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));) {
            return (Collection<T>) decoder.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
