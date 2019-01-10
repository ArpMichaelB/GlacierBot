package com.glacier.discordbot.util;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	//TODO: update this WHOLE thing to play nice with making a json file/adding a new bot to the file
	@Override
	public void start(Stage primaryStage)
	{
		HBox container = new HBox();
		VBox inputs = new VBox();
		HBox youtubeAPIKeyHolder = new HBox();
		HBox youtubeChannelIDHolder = new HBox();
		HBox discordTokenHolder = new HBox();
		Text botName = new Text("Enter the name for the bot:");
		Text APIKeyLabel = new Text("Youtube API key:");
		Text channelIDLabel = new Text("Youtube Channel ID:");
		Text discordTokenLabel = new Text("Discord token:");
		Text message = new Text("Close me after you've hit the button, and try again");
		TextField APIKeyInput = new TextField();
		TextField channelIDInput = new TextField();
		TextField discordTokenInput = new TextField();
		TextField nameInput = new TextField();
		Button btBuild = new Button("Build me a properties file!");
		youtubeAPIKeyHolder.getChildren().addAll(APIKeyLabel,APIKeyInput);
		youtubeChannelIDHolder.getChildren().addAll(channelIDLabel,channelIDInput);
		discordTokenHolder.getChildren().addAll(discordTokenLabel,discordTokenInput);
		APIKeyInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String previous, String newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		channelIDInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String previous, String newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		discordTokenInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String previous, String newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		//every time a change is made to the textfields where the user can make input,
		//pass that change in text along to the actual handlers to work with
		btBuild.pressedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean previous, Boolean newThing) {
            	btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	APIKeyInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	channelIDInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	discordTokenInput.setOnKeyPressed(new ButtonHandler(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
            	System.gc();
            	//call garbage collection to make sure we don't make too many instances of the BuildFile and ButtonHandler class
            }
        });
		//just in case, when the button is pressed, update the text fields, even though that shouldn't really have to happen
		btBuild.setOnAction(new BuildFile(APIKeyInput.getText(),channelIDInput.getText(),discordTokenInput.getText(),nameInput.getText()));
		inputs.getChildren().addAll(new HBox(botName,nameInput),new HBox(APIKeyLabel,APIKeyInput),new HBox(channelIDLabel,channelIDInput),new HBox(discordTokenLabel,discordTokenInput),message);
		container.getChildren().addAll(inputs,btBuild);
		Scene primaryScene = new Scene(container,UtilsAndConstants.MENU_SIZE, UtilsAndConstants.MENU_SIZE_TWO);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
}

class BuildFile implements EventHandler<ActionEvent>
{
	String name;
	String APIKey;
	String channelID;
	String discordToken;

	public BuildFile(String APIKey, String channelID, String discordToken, String name)
	{
		this.name = name;
		this.APIKey = APIKey;
		this.channelID = channelID;
		this.discordToken = discordToken;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		File storage = new File(File.listRoots()[0].toString()+"/Glacier Nester/properties/");
		File discordProperties = new File(storage.getAbsolutePath()+"discordbot_properties.json");
		try 
		{
			JSONArray allprops = new JSONArray();
			JSONObject prop = new JSONObject();
			prop.put("name", name);
			prop.put("APIKey", APIKey);
			prop.put("channelID",channelID);
			prop.put("discordToken", discordToken);
			allprops.add(prop);
			if(!storage.exists())
				storage.mkdirs();
			boolean nyoom = false;
			Scanner scan = new Scanner(new FileReader(discordProperties));
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				if(line.contains("]"))
				{
					nyoom = true;
				}
			}
			if(nyoom)
			//i.e. if the file exists, since if the file doesn't exist it can't contain a closing bracket for the json array
			{
				String allPropsHold = "";
				Scanner s = new Scanner(new FileReader(discordProperties));
				while(s.hasNextLine())
				{
					allPropsHold += s.nextLine();
				}
				allPropsHold = allPropsHold.substring(0, allPropsHold.lastIndexOf("]"));
				allPropsHold += ","+prop.toJSONString()+"]";
				allPropsHold = allPropsHold.replace("\uFEFF", "");
				FileWriter writer = new FileWriter(discordProperties);
				writer.write(allPropsHold);
				writer.flush();
				writer.close();
				s.close();
				scan.close();
			}
			else
			{
				FileWriter writer = new FileWriter(discordProperties);
				writer.write(allprops.toJSONString().replace("\uFEFF", ""));
				writer.flush();
				writer.close();
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}

class ButtonHandler implements EventHandler<KeyEvent>
{
	String name;
	String APIKey;
	String channelID;
	String discordToken;

	public ButtonHandler(String APIKey, String channelID, String discordToken,String name)
	{
		this.name = name;
		this.APIKey = APIKey;
		this.channelID = channelID;
		this.discordToken = discordToken;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
		{
			new BuildFile(APIKey,channelID,discordToken,name).handle(new ActionEvent());
		}
	}
	
}