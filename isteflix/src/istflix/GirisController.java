package istflix;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import baglanti.Baglanti;

public class GirisController {

	public GirisController() {
		connection = Baglanti.c0nnection();
	}
	Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_giris;

    @FXML
    private Button button_kayit;

    @FXML
    private TextField field_kullanici;

    @FXML
    private TextField field_sifre;

    @FXML
    private Label label_hata;

    //giriş butonu action event'i
    @FXML
    void button_girisAction(ActionEvent event) {
    	if(event.getSource() == button_giris) {
    		if(login().equals("dogru")) {
    		
    			try {
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Sample.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}    				
    		}
    	}
    }
    //kayıt butonu action event
    @FXML
    void button_kayitAction(ActionEvent event) {
    	if(event.getSource() == button_kayit) { 		
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("YeniKayit.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
    	}   	
    }
  //md5 şifreleme
  	public static String sifreleme(String sifrele) {
  		try {
  			
  			MessageDigest md5 = MessageDigest.getInstance("MD5");
  			byte[] encrypted = md5.digest(sifrele.getBytes());
  			BigInteger no = new BigInteger(1, encrypted);
  			String hashPass = no.toString(16);
  			while(hashPass.length() < 32) {
  				hashPass = "0" + hashPass;
  			}
  			return hashPass;
  		} catch (NoSuchAlgorithmException e) {
  			throw new RuntimeException(e);
  		}
  	}  	
  //login fonksiyonu
  	private String login() {
      	String nick = field_kullanici.getText().toString();
      	String sifre = field_sifre.getText().toString();
      	
      	//login sorgusu
      	String sorgu = "SELECT * FROM uyelik WHERE nick = ? and sifre = ?";
      	try {
  			preparedStatement = connection.prepareStatement(sorgu);			
  			preparedStatement.setString(1, nick);
  			preparedStatement.setString(2, sifre.valueOf(sifreleme(sifre)));
  			
  			resultSet = preparedStatement.executeQuery();
  			if(!resultSet.next()) {
  				label_hata.setTextFill(Color.TOMATO);
  				label_hata.setText("Kullanıcı Adı veya Şifre Hatalı..");
  				return "yanlis";
  			}
  			else {
  				label_hata.setTextFill(Color.GREEN);
  				label_hata.setText("Giriş Yaptın");
  				return "dogru";
  			}			
  		} catch (SQLException e) {
  			
  			System.err.println(e.getMessage());
  			return "exception";
  		}
      }	 
    @FXML
    void initialize() {
        assert button_giris != null : "fx:id=\"button_giris\" was not injected: check your FXML file 'Giris.fxml'.";
        assert button_kayit != null : "fx:id=\"button_kayit\" was not injected: check your FXML file 'Giris.fxml'.";
        assert field_kullanici != null : "fx:id=\"field_kullanici\" was not injected: check your FXML file 'Giris.fxml'.";
        assert field_sifre != null : "fx:id=\"field_sifre\" was not injected: check your FXML file 'Giris.fxml'.";
        assert label_hata != null : "fx:id=\"label_hata\" was not injected: check your FXML file 'Giris.fxml'.";
    }
}
