package sample.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.reactfx.Subscription;
import java.io.File;
import java.time.Duration;
import static sample.Constants.Configs.computeHighlighting;
import static sample.Constants.Configs.sampleCode;

public class Controller extends Application {
    private Stage stage;
    @FXML private Pane panesote;


    CodeArea codeArea = new CodeArea();

    @FXML protected void initialize(){
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.replaceText(0, 0, sampleCode);
        codeArea.setPrefSize(1200,330);
        Subscription cleanupWhenNoLongerNeedIt = codeArea
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText())));
        panesote.getChildren().add(codeArea);
    }//llave load


    public void evtSalir (ActionEvent event){ System.exit(0); }
    public void evtAbrir (ActionEvent event){
        FileChooser of= new FileChooser();
        of.setTitle("Abrir archivo ccf");
        FileChooser.ExtensionFilter filtro= new FileChooser.ExtensionFilter("Archivos .ccf","*.ccf");
        of.getExtensionFilters().add(filtro);
        File file = of.showOpenDialog(stage);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
    }
}
