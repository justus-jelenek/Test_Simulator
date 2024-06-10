package de.biplus.simulator.view;

import java.util.ArrayList;

import de.biplus.simulator.controller.mainController;
import de.biplus.simulator.data.dataObjectExam;
import de.biplus.simulator.data.dataObjectThema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/* Codename: Fucking bastard simulator
 * Productname: FBS
 * Package: de.biplus.simulator.view
 * File: viewStartScreen.java
 * Version: 0.9
 */
public final class viewStartScreen {
	private Stage primaryStage;		
	private GridPane root = new GridPane();
	private Scene scene = new Scene(root,700,400);
	private ComboBox<String> cbExam;
	private ComboBox<String> cbTheme;
	private Button start = new Button("Start");
	private Label lbTheme = new Label("Thema");
	private TextField anzahl = new TextField();
	private mainController mc;
	Label countExam = new Label("");
	Label countThema = new Label("");
	public viewStartScreen(Stage primaryStage,mainController mc)
	{
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("FBS 1.0");
		this.mc=mc;
		scene.getStylesheets().add("/de/biplus/simulator/application.css");
		root.getStyleClass().add("pane");
		root.add(countExam, 0, 2);
		root.add(countThema, 0, 4);
		makeWindow();
		makeField();
		makeButton();
	}
	private void makeWindow()
	{
	    root.setPadding(new Insets(20));
	    root.setHgap(25);
	    root.setVgap(15);
		Label title = new Label("Prüfungssimulator");
		title.getStyleClass().add("headline");
		root.add(title, 0,0);
	}
	private void makeField()
	{
		Label lbQuest = new Label("Anzahl:");
		lbQuest.getStyleClass().add("startlabel");
		anzahl.setText("max");
		root.add(lbQuest, 0, 5);
		root.add(anzahl,1,5);
	}
	private void makeButton()
	{
		start.setOnAction(mc);
		root.add(start,1,7);
		
	}
	public void showCountExam(int anzahl)
	{	
		root.getChildren().remove(countExam);
		countExam.getStyleClass().add("startlabel");
		countExam.setText("Fragen: " + anzahl);
		root.add(countExam, 0, 2);
	}
	public void showCountThema(String max)
	{	
		root.getChildren().remove(countThema);
		countThema.getStyleClass().add("startlabel");
		countThema.setText("Fragen: " + max);
		root.add(countThema, 0, 4);
	}
	public void setDropDown(ArrayList<dataObjectExam> exam) {
		// TODO Auto-generated method stub
		Label lbExam = new Label("Prüfung");
		lbExam.getStyleClass().add("startlabel");
		ObservableList<String> options=FXCollections.observableArrayList();
		for (int i = 0; i < exam.size();i++)
		{
			options.add(exam.get(i).getExam());
		}		
		cbExam = new ComboBox<String>(options);
		cbExam.setOnAction(mc);
		root.add(lbExam, 0,1);
		cbExam.getStyleClass().add("startdropdown");
		root.add(cbExam,1,1);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public ComboBox getComboBox(String id)
	{
		
		switch(id)
		{
		case "theme":
			return cbTheme;
		default:
			return cbExam;
		}	
	}
	public void setDropDownTheme(ArrayList<dataObjectThema> themen) {
		root.getChildren().remove(cbTheme);
		root.getChildren().remove(lbTheme);
		ObservableList<String> options=FXCollections.observableArrayList();
		options.add("Alle");
		for (int i = 0; i < themen.size();i++)
		{
			options.add(themen.get(i).getTitle());
		}		
		cbTheme = new ComboBox<String>(options);
		cbTheme.setOnAction(mc);
		cbTheme.getSelectionModel().selectFirst();
		lbTheme.getStyleClass().add("startlabel");
		root.add(lbTheme, 0, 3);
		cbTheme.getStyleClass().add("startdropdown");
		root.add(cbTheme,1,3);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	public Button getButton() {
		// TODO Auto-generated method stub
		return this.start;
	}
	public String getQuestions()
	{
		return anzahl.getText();
	}

}
