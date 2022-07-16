package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mediaDB.*;
import verwaltung.VerwaltungMediaImpl;
import verwaltung.VerwaltungProductionImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class GUIControllerWithPersistence {
    private VerwaltungProductionImpl verwaltungProduction = new VerwaltungProductionImpl();
    private VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
    private ArrayList<String> productions = new ArrayList<>();
    private ArrayList<String> media = new ArrayList<>();
    private ArrayList<String> mediaType = new ArrayList<>();


    public GUIControllerWithPersistence(){
    }


    @FXML
    private Button restoreJBPXML;

    @FXML
    private Button restoreJOS;

    @FXML
    private Button saveJBPXML;

    @FXML
    private Button saveJOS;
    @FXML
    private MenuItem mediaContext;

    @FXML
    private Button addMediaButton;

    @FXML
    private Button addProductionButton;

    @FXML
    private ListView<String> mediaList;

    @FXML
    private TextField mediaName;

    @FXML
    private ChoiceBox<String> productionChoice;

    @FXML
    private ListView<String> productionList;

    @FXML
    private TextField productionName;

    @FXML
    private MenuItem productionsContext;

    @FXML
    private ChoiceBox<String> type;

    @FXML private void initialize() {
        mediaType.add("video");
        mediaType.add("audio");

        type.getItems().addAll(mediaType);
    }


    @FXML
    void restoreJBPXMLClick(ActionEvent event) {
        Collection<Object> colMediaFromFile = verwaltung.readJBPXML("MediaFromGUIJBP.xml");
        ArrayList<Object> mediaFromFile = new ArrayList<>(colMediaFromFile);
        verwaltung.setMedia(mediaFromFile);
        media.clear();
        media = verwaltung.getMediaName();

        Collection<Uploader> colProdFromFile = verwaltungProduction.readJBPXML("ProductionsFromGUIJBP.xml");
        ArrayList<Uploader> prodFromFile = new ArrayList<>(colProdFromFile);
        verwaltungProduction.setProductions(prodFromFile);
        this.updateView();
    }

    @FXML
    void restoreJOSClick(ActionEvent event) {
        Collection<Object> colMediaFromFile = verwaltung.deserializeJOS("MediaFromGUIJOS");
        ArrayList<Object> mediaFromFile = new ArrayList<>(colMediaFromFile);
        verwaltung.setMedia(mediaFromFile);


        media = verwaltung.getMediaName();

        Collection<Uploader> colProdFromFile = verwaltungProduction.deserializeJOS("ProductionsFromGUIJOS");
        ArrayList<Uploader> prodFromFile = new ArrayList<>(colProdFromFile);
        verwaltungProduction.setProductions(prodFromFile);
        this.updateView();

    }

    @FXML
    void saveJBPXMLClick(ActionEvent event) {
        verwaltung.writeJBPXML("MediaFromGUIJBP.xml", verwaltung.getEingefuegteDataien());
        verwaltungProduction.writeJBPXML("ProductionsFromGUIJBP.xml",  verwaltungProduction.getUploader());
    }

    @FXML
    void saveJOSClick(ActionEvent event) {
        verwaltung.serializeJOS("MediaFromGUIJOS", verwaltung.getEingefuegteDataien());//extra button
        verwaltungProduction.serializeJOS("ProductionsFromGUIJOS", verwaltungProduction.getUploader());
    }

    @FXML
    private void addmediabuttonClick(ActionEvent event) {
            if(!mediaName.getText().equals("") && productionChoice.getValue()!=null && type.getValue()!=null && !media.contains(mediaName.getText())){
                if(type.getValue()=="audio"){
                    AudioImpl audio = new AudioImpl();
                    audio.setUploader((Uploader) verwaltungProduction.getProductions().get(productionChoice.getValue()));
                    audio.setSize(BigDecimal.valueOf(1));
                    audio.setName(mediaName.getText());

                    verwaltung.insertFile(audio);
                }
                else {
                    VideoImpl video = new VideoImpl();
                    video.setUploader((Uploader) verwaltungProduction.getProductions().get(productionChoice.getValue()));
                    video.setSize(BigDecimal.valueOf(1));
                    video.setName(mediaName.getText());
                    verwaltung.insertFile(video);
                }
                media.add(mediaName.getText());
                this.updateView();
            }
    }

    @FXML
    private void addproductionbuttonClick(ActionEvent event) {
        if(!verwaltungProduction.getProductionsName().contains(this.productionName.getText()) && !this.productionName.getText().equals("")){
            UploaderImpl uploader1 = new UploaderImpl();
            uploader1.setName(this.productionName.getText());
            verwaltungProduction.insertProduction(uploader1);
            this.updateView();
        }
    }

    @FXML
    void mediaContextClick(ActionEvent event) {
        verwaltung.delete(mediaList.getSelectionModel().getSelectedItem());
        media.remove(mediaList.getSelectionModel().getSelectedItem());
        this.updateView();
    }

    @FXML
    void productionsContextClick(ActionEvent event) {
        //Uploader uploader = (Uploader)verwaltungProduction.getProductions().get(productionList.getSelectionModel().getSelectedItem());
        verwaltungProduction.delete(productionList.getSelectionModel().getSelectedItem());
        this.updateView();
    }

    private void updateView(){
        this.productionList.getItems().clear();
        productionList.getItems().addAll(verwaltungProduction.getProductionsName());
        productionChoice.getItems().clear();
        productionChoice.getItems().addAll(verwaltungProduction.getProductionsName());
        mediaList.getItems().clear();
        mediaList.getItems().addAll(media);
    }
}

