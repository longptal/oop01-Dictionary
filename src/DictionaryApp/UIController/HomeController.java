package DictionaryApp.UIController;

import DictionaryApp.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable {

    public static final int M = 100;
    private int curI =0;
    private ArrayList<Word> wordList;
    private DictionaryManagement dictionaryManagement;
    private GTTS gtts;
    private Mode curMode = Mode.Search;
    private StringBuilder curEnWord;
    public static  boolean isSearch = true;


    @FXML
    private AnchorPane view;

    @FXML
    private JFXDrawer navDrawer;

    @FXML
    private BorderPane background;

    @FXML
    private JFXButton ButtonAddWord;

    @FXML
    private JFXListView<?> showAllWord;

    @FXML
    private AnchorPane topBar;

    @FXML
    private ChoiceBox<?> choiceLanguage;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private TextField inputBox;

    @FXML
    private Label wordLabel;

    @FXML
    private void onClickAddWord(ActionEvent event) throws IOException {
        Parent addWord = FXMLLoader.load(getClass().getResource("../../UIUX/CRUD1Word.fxml"));
        Scene addWord_scene = new Scene(addWord);
        Stage addWord_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addWord_stage.setScene(addWord_scene);
        addWord_stage.show();
    }

    /*
    Call Navigation Drawer and control it
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("../../UIUX/NavDrawer.fxml"));
            navDrawer.setSidePane(box);

            /*Change the Menu button: Menu <-> Arrow*/
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (navDrawer.isOpened()) {
                    navDrawer.close();
                }
                else {
                    navDrawer.open();
                }
            });
        }

        catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
