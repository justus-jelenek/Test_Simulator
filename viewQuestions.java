package de.biplus.simulator.view;

import java.util.ArrayList;

import de.biplus.simulator.Main;
import de.biplus.simulator.controller.examController;
import de.biplus.simulator.data.dataObjectQuestions;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class viewQuestions {
	private Stage primaryStage;	
	private ArrayList<dataObjectQuestions> liste;
	private GridPane root = new GridPane();
	private Scene scene = new Scene(root,700,400);
	private Label lbexam = new Label(null);
	private Label lbtheme = new Label(null);
	private Label lbcount = new Label(null);
	private Label qid = new Label(null);
	private Label maxPoints = new Label(null);
	private Button vor = new Button("Weiter");
	private Button hilfe = new Button("Hilfe");
	private Button grafik = new Button("Grafik");
	private Button auswertung = new Button("Auswertung");
	private HBox buttonbox = new HBox();
	private VBox box = new VBox();
	private Text question = new Text(null);
	private boolean help = false;
	private examController ec;
	private ToggleGroup radioGroup;
	private TextField answer;
	private CheckBox[] cbs;
	private RadioButton rb[];
	
	public viewQuestions(Stage primaryStage, examController examController, ArrayList<dataObjectQuestions> liste) {
		ec=examController;
		this.liste=liste;
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("FBS 1.0");
		vor.setOnAction(ec);
		hilfe.setOnAction(ec);
		grafik.setOnAction(ec);
		auswertung.setOnAction(ec);
		makeWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private void makeBox()
	{
		buttonbox.getChildren().addAll(hilfe,grafik,auswertung,vor);
		root.add(buttonbox,0,6);
	}
	private void makeWindow()
	{
	    root.setPadding(new Insets(20));
	    root.setHgap(25);
	    root.setVgap(15);
		Label title = new Label("Prüfungssumulator");
		root.add(title, 0,0);
		
	}
	private void makeAnswer(int index)
	{
		root.getChildren().remove(box);
		box=new VBox();
		switch(liste.get(index).getType())
		{
		case 0:
			answer=new TextField();
			box.getChildren().add(answer);
			if(help)
			{
				Text explain = new Text(liste.get(index).getHelp(0));
				box.getChildren().add(explain);
			}
			break;
		case 1:
			cbs=new CheckBox[liste.get(index).getAnswerCount()];
			for(int i = 0; i < liste.get(index).getAnswerCount();i++)
			{
				cbs[i]=new CheckBox(liste.get(index).getAnswer(i));
				box.getChildren().add(cbs[i]);
				if(help)
				{
					Text explain = new Text(liste.get(index).getHelp(i));
					box.getChildren().add(explain);
				}				
			}
			break;
		default:
			rb = new RadioButton[liste.get(index).getAnswerCount()];
			radioGroup = new ToggleGroup();
			for(int i = 0; i < liste.get(index).getAnswerCount();i++)
			{
				rb[i]=new RadioButton(liste.get(index).getAnswer(i));
				rb[i].setToggleGroup(radioGroup);
				box.getChildren().add(rb[i]);
				if(help)
				{
					Text explain = new Text(liste.get(index).getHelp(i));
					box.getChildren().add(explain);
				}
			}			
			break;
		}
		root.add(box,0,5);
	}
	public TextField getTextInput()
	{
		return answer;
	}
	public RadioButton getRadioButton()
	{
		return (RadioButton) radioGroup.getSelectedToggle();
	}
	public CheckBox[] getCheckBox()
	{
		return cbs;
	}
	public void showQuestion(int index)
	{
		root.getChildren().remove(question);
		root.getChildren().remove(qid);	
		qid.setText("Frage Nr.: " + liste.get(index).getQid());
		question.setText(liste.get(index).getQuestion());
		root.add(qid,0,3);
		root.add(question, 0, 4);
		makeAnswer(index);
	}
	public Button getButton(String id)
	{
		switch(id)
		{
		case "vor":
			return vor;
		case "hilfe":
			return hilfe;
		case "grafik":
			return grafik;
		default:
			return auswertung;
		}
	}
	public void startExam(String examen, String thema) 
	{	
		lbexam.setText("Prüfung: " + examen);
		lbtheme.setText("Thema: " + thema);
		Main.AUSWERTUNG.setQuestions(liste.size());
		lbcount.setText("Fragen: " + Main.AUSWERTUNG.getQuestions());
		maxPoints.setText("Max. Punkte: " + Main.AUSWERTUNG.getMaxPoints());
		root.add(lbexam, 0, 1);
		root.add(lbtheme, 1, 1);
		root.add(lbcount, 2, 1);
		root.add(maxPoints,3,1);
		makeBox();
	}
	public void showHelp(int index) {
		if(help) {help=false;} else { help=true;};
		showQuestion(index);
	}

}
