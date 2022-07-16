package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mediaDB.AudioImpl;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;
import mediaDB.VideoImpl;
import verwaltung.VerwaltungMediaImpl;
import verwaltung.VerwaltungProductionImpl;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GUIController {
    private VerwaltungProductionImpl verwaltungProduction = new VerwaltungProductionImpl();
    private VerwaltungMediaImpl verwaltung = new VerwaltungMediaImpl(verwaltungProduction);
    private ArrayList<String> productions = new ArrayList<>();
    private ArrayList<String> media = new ArrayList<>();
    private ArrayList<String> mediaType = new ArrayList<>();


    public GUIController(){

    }
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
    private void addmediabuttonClick(ActionEvent event) {
            if(!mediaName.getText().equals("") && productionChoice.getValue()!=null && type.getValue()!=null && !media.contains(mediaName.getText())){
                if(type.getValue()=="audio"){
                    AudioImpl audio = new AudioImpl();
                    audio.setUploader((Uploader) verwaltungProduction.getProductions().get(productionChoice.getValue()));
                    audio.setSize(BigDecimal.valueOf(1));

                    verwaltung.insertFile(audio);
                }
                else {
                    VideoImpl video = new VideoImpl();
                    video.setUploader((Uploader) verwaltungProduction.getProductions().get(productionChoice.getValue()));
                    video.setSize(BigDecimal.valueOf(1));
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

