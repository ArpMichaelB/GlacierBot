package com.glacier.discordbot.util;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectFileCreator extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		VBox vbox = new VBox();
		Text text = new Text("Hi hello I am a neat new file creator for you");
		vbox.getChildren().add(text);
		Scene primaryScene = new Scene(vbox,UtilsAndConstants.MENU_SIZE, UtilsAndConstants.MENU_SIZE_TWO);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
}
