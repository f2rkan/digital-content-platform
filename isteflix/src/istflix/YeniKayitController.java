package istflix;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import baglanti.Baglanti;

public class YeniKayitController {

	
	public YeniKayitController() {
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
    private Button button_guncelle;

    @FXML
    private Button button_kaydet;

    @FXML
    private Button button_reset;

    @FXML
    private Button button_sil;

    @FXML
    private TextField field_id;

    @FXML
    private TextField field_isim;

    @FXML
    private TextField field_kullaniciadi;

    @FXML
    private TextField field_sifre;

    @FXML
    private TextField field_soyisim;

    @FXML
    private TableView<YeniKayit> tableView;

    @FXML
    private TableColumn<YeniKayit, Integer> view_id;

    @FXML
    private TableColumn<YeniKayit, String> view_isim;

    @FXML
    private TableColumn<YeniKayit, String> view_kullaniciadi;

    @FXML
    private TableColumn<YeniKayit, String> view_sifre;

    @FXML
    private TableColumn<YeniKayit, String> view_soyisim;

    @FXML
    void button_guncelleAction(ActionEvent event) {

    	if(event.getSource() == button_guncelle){
            kullaniciGuncelle();  
            
    }
    	
    }

    @FXML
    void button_kaydetAction(ActionEvent event) {

    	if(event.getSource() == button_kaydet){
            kullaniciEkle();  
            
    }
    	
    }

    @FXML
    void button_resetAction(ActionEvent event) {

    	reset();
    	
    }

    @FXML
    void button_silAction(ActionEvent event) {

    	if(event.getSource() == button_sil){
            kullaniciSil();  
            
    }
    	
    }

    @FXML
    void tableView_pressed(MouseEvent event) {

    	YeniKayit goruntule = tableView.getSelectionModel().getSelectedItem();
    	
    	field_id.setText("" + goruntule.getId());
    	field_isim.setText("" + goruntule.getIsim());
    	field_soyisim.setText("" + goruntule.getSoyisim());
    	field_kullaniciadi.setText("" + goruntule.getNick());
    	field_sifre.setText("" + goruntule.getSifre());
    	
    	
    }
    
    	private void reset() {   	
			field_id.setText("");
			field_isim.setText("");
			field_soyisim.setText("");
			field_kullaniciadi.setText("");
			field_sifre.setText("");
}
    
    public Connection getConnection() {
    	Connection connection;
    	try {
    		//jdbc:mysql://"veri tabanı konumu", "db user name", "db password"
    		connection = DriverManager.getConnection("jdbc:mysql://localhost/platform", "root", "mysql");
    		return connection;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }
    
  //veri tabanındaki kullanicilar tablosuna ulaşma fonksiyonu
    public ObservableList<YeniKayit>getKullaniciKaydi(){
    	ObservableList<YeniKayit>uyelikListesi = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String getSql = "SELECT * FROM uyelik";
    	Statement st;
    	ResultSet rs;
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(getSql);
			YeniKayit yeniKayit;
			while(rs.next()) {
				yeniKayit = new YeniKayit(rs.getInt("id"), rs.getString("isim"), rs.getString("soyisim"), rs.getString("nick"), rs.getString("sifre"));
				uyelikListesi.add(yeniKayit);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return uyelikListesi;
    }
    
  //veri tabanındaki kayıtları çeken fonksiyon
    public void kullanicilariGoster() {
    	ObservableList<YeniKayit>liste = getKullaniciKaydi();
    	
    	view_id.setCellValueFactory(new PropertyValueFactory<YeniKayit, Integer>("id"));
    	view_isim.setCellValueFactory(new PropertyValueFactory<YeniKayit, String>("isim"));
    	view_soyisim.setCellValueFactory(new PropertyValueFactory<YeniKayit, String>("soyisim"));
    	view_kullaniciadi.setCellValueFactory(new PropertyValueFactory<YeniKayit, String>("nick"));
    	view_sifre.setCellValueFactory(new PropertyValueFactory<YeniKayit, String>("sifre"));	
    	tableView.setItems(liste);
    }
    
  //getSql çalıştırma fonksiyonu
    private void calistir(String getSql) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(getSql);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
  //ekle butonu action sorgusu
	private void kullaniciEkle() {
	
	String getSql = "INSERT INTO uyelik VALUES(" 
	+ field_id.getText() + ",'" + field_isim.getText() + "','" + field_soyisim.getText() + "','" + field_kullaniciadi.getText() + "','" + field_sifre.getText().valueOf(sifreleme(field_sifre.getText()))+ "')";	
	//sorguyu çalıştır
	calistir(getSql);
	//çalışmış sorguyla güncellenen tabloyu kayitGoster() ile getir
	kullanicilariGoster();
}
	//güncelleme butonu ve md5 şifreleme
	private void kullaniciGuncelle(){
    	String getSql = "UPDATE  uyelik SET isim  = '" + field_isim.getText() + "', soyisim = '" + field_soyisim.getText() + "', nick = '" + field_kullaniciadi.getText() + "', sifre = '" + field_sifre.getText().valueOf(sifreleme(field_sifre.getText())) + "' WHERE id = " + field_id.getText() + "";    		    	
    	calistir(getSql);
    	kullanicilariGoster();
    }
	//sil butonu getSqlsu
	private void kullaniciSil(){
        String getSql = "DELETE FROM uyelik WHERE id =" + field_id.getText() + "";
        calistir(getSql);
        kullanicilariGoster();
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

    @FXML
    //açılış ekranı
    void initialize() {
    	kullanicilariGoster();
    }
}
