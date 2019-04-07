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

public class Client extends Application{
   Stage window;
   Stage scene;
   
   public static void main(String[] args) {
		launch(args);	
	}
   
   @Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("Client");
		
		//I used grid the get the layout I wanted
		GridPane grid = new GridPane();
		//The padding is to essentially set the margins for which my nodes won't go past
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(15);
		
		TextArea tc = new TextArea();
		tc.setMaxWidth(500);
      tc.setEditable(false);
		GridPane.setConstraints(tc, 0, 0);
		
		TextField tw = new TextField();
		tw.setMaxWidth(500);
		GridPane.setConstraints(tw, 0, 1);
		
      grid.getChildren().addAll(tc, tw);
      
		Scene scene = new Scene(grid, 500, 200);
		window.setScene(scene);
		window.show();

      InputStream fromServer = null;
      OutputStream toServer = null; 
      
      tw.setOnAction(e -> {
            String ItoServer = "";
            ItoServer = tw.getText();
            
            tc.appendText("Client: " + ItoServer + '\n');
            tw.clear();
         });
     
     try {
         Socket socket = new Socket("localhost", 8000);
         
         toServer = socket.getOutputStream();
         fromServer = socket.getInputStream();
         
         InputStreamReader inputReader = new InputStreamReader(fromServer);
         BufferedReader br = new BufferedReader(inputReader);
         String line = br.readLine();
         tc.appendText("Server: " + line + '\n');
      }
      catch(IOException ex){
         tc.appendText(ex.toString() + '\n');
      }
   }
}
