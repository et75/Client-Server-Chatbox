//======================================================  
// Name       :  Emad Tirmizi
// SID        :  31400222
// Course     :  IT114 
// Section    :  
// Instructor :  Maura Deek
// T.A        :   
//======================================================  
//======================================================  
// Assignment # :  5
// Date         :  11/30/2018
//====================================================== 
//====================================================== 
// Description: We must create a program that gives
// enables two users to chat. Using GUIs implement one 
// user as the server and the other as the client. The 
// sever has two text areas: one for entering text and
// the other (noneditable) for displaying text received
// from the client. The client has two text areas: one
// for receiving text from the server and the other for
// entering text. When the user presses the enter key,
// the current line is sent to the server or client.
 
//====================================================== 

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.io.InputStream;

public class Server extends Application{
	
   Stage window;
   Stage scene;
   
   public static void main(String[] args) {
		launch(args);
      //Client.main(new String[0]);	
	}
   
   
   
   @Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("Server");
		//I used grid the get the layout I wanted
		GridPane grid = new GridPane();
		//The padding is to essentially set the margins for which my nodes won't go past
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(15);
		
		TextArea ts = new TextArea();
		ts.setMaxWidth(500);
      ts.setEditable(false);
		GridPane.setConstraints(ts, 0, 0);
		
		TextField tw = new TextField();
		tw.setMaxWidth(500);
		GridPane.setConstraints(tw, 0, 1);
		
      grid.getChildren().addAll(ts, tw);
      
		Scene scene = new Scene(grid, 500, 200);
		window.setScene(scene);
		window.show();
      tw.setOnAction(e -> {
            String ItoClient = "";
            ItoClient = tw.getText();
            
            ts.appendText("Server: " + ItoClient + '\n');
            tw.clear();
         });
      
      new Thread(() -> {
			
         
         try{
				ServerSocket serverSocket = new ServerSocket(8000);
				Socket socket = serverSocket.accept();
            
				InputStream fromClient = socket.getInputStream();
				OutputStream toClient = socket.getOutputStream();
            
            while (true) {
            
            InputStreamReader inputReader = new InputStreamReader(fromClient);
            BufferedReader br = new BufferedReader(inputReader);
            String line = br.readLine();
            ts.appendText("Client: " + line + '\n');
            }
			}
			
			catch(IOException ex){
				ex.printStackTrace();
			}
      });
	}
}

//*************************************************************************

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		