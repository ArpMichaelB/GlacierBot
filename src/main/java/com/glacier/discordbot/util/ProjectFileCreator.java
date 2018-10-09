package com.glacier.discordbot.util;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectFileCreator extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		HBox container = new HBox();
		VBox inputs = new VBox();
		HBox youtubeAPIKeyHolder = new HBox();
		HBox youtubeChannelIDHolder = new HBox();
		HBox discordTokenHolder = new HBox();
		Text APIKeyLabel = new Text("Youtube API key:");
		Text channelIDLabel = new Text("Youtube Channel ID:");
		Text discordTokenLabel = new Text("Discord token:");
		Text message = new Text("Close me after you've hit the button, and try again");
		TextField APIKeyInput = new TextField();
		TextField channelIDInput = new TextField();
		TextField discordTokenInput = new TextField();
		Button btBuild = new Button("Build me a properties file!");
		youtubeAPIKeyHolder.getChildren().addAll(APIKeyLabel,APIKeyInput);
		youtubeChannelIDHolder.getChildren().addAll(channelIDLabel,channelIDInput);
		discordTokenHolder.getChildren().addAll(discordTokenLabel,discordTokenInput);
		APIKeyInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String previous, String newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		channelIDInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String previous, String newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		discordTokenInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String previous, String newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		//every time a change is made to the textfields where the user can make input,
		//pass that change in text along to the actual handlers to work with
		btBuild.pressedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean previous, Boolean newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		//just in case, when the button is pressed, update the text fields, even though that shouldn't really have to happen
		btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText()));
		inputs.getChildren().addAll(youtubeAPIKeyHolder,youtubeChannelIDHolder,discordTokenHolder,message);
		container.getChildren().addAll(inputs,btBuild);
		Scene primaryScene = new Scene(container,UtilsAndConstants.MENU_SIZE, UtilsAndConstants.MENU_SIZE_TWO);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
}

class BuildFile implements EventHandler<ActionEvent>
{

	String APIKey;
	String channelID;
	String discordToken;

	public BuildFile(String APIKey, String channelID, String discordToken)
	{
		this.APIKey = APIKey;
		this.channelID = channelID;
		this.discordToken = discordToken;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		File discordProperties = new File("discordbot.properties");
		try {
			discordProperties.createNewFile();
			PrintWriter writeThings = new PrintWriter(discordProperties);
			writeThings.println("youtube.apikey="+APIKey);
			writeThings.println("youtube.channelid="+channelID);
			writeThings.println("discord.key="+discordToken);
			writeThings.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class ButtonHandler implements EventHandler<KeyEvent>
{
	
	String APIKey;
	String channelID;
	String discordToken;

	public ButtonHandler(String APIKey, String channelID, String discordToken)
	{
		this.APIKey = APIKey;
		this.channelID = channelID;
		this.discordToken = discordToken;
	}
	
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getCode() == KeyCode.ENTER)
		{
			new BuildFile(APIKey,channelID,discordToken).handle(new ActionEvent());
		}
	}
	
}