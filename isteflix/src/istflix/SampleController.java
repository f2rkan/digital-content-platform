package istflix;

import java.net.URL;
import java.util.Date;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SampleController {

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
    private TextField field_diziFilm;

    @FXML
    private TextField field_imdb;

    @FXML
    private TextField field_isim;

    @FXML
    private TextField field_muzik;

    @FXML
    private TextField field_starlar;

    @FXML
    private DatePicker field_tarih;

    @FXML
    private TextField field_tur;

    @FXML
    private TextField field_ulke;

    @FXML
    private TextField field_yonetmen;

    @FXML
    private TextField field_id;
    
    @FXML
    private TableView<YapimListesi> tableView;

    @FXML
    private TableColumn<YapimListesi, String> view_diziFilm;

    @FXML
    private TableColumn<YapimListesi, Integer> view_id;

    @FXML
    private TableColumn<YapimListesi, String> view_imdb;

    @FXML
    private TableColumn<YapimListesi, String> view_isim;

    @FXML
    private TableColumn<YapimListesi, String> view_muzik;

    @FXML
    private TableColumn<YapimListesi, String> view_starlar;

    @FXML
    private TableColumn<YapimListesi, Date> view_tarih;

    @FXML
    private TableColumn<YapimListesi, String> view_tur;

    @FXML
    private TableColumn<YapimListesi, String> view_ulke;

    @FXML
    private TableColumn<YapimListesi, String> view_yonetmen;

    @FXML
    void button_guncelleAction(ActionEvent event) {
    	if(event.getSource() == button_guncelle){
            kayitGuncelle(); 
    	}	
    }
    @FXML
    void button_kaydetAction(ActionEvent event) {
    		if(event.getSource() == button_kaydet){
            kayitEkle(); 
    	}
    }
    @FXML
    void button_resetAction(ActionEvent event) {    	
    	reset();
    }
    @FXML
    void button_silAction(ActionEvent event) {
    	if(event.getSource() == button_sil){
            yapimSil(); 	
    	}  	
    }
    @FXML
    void tableView_pressed(MouseEvent event) {
    	YapimListesi goruntule = tableView.getSelectionModel().getSelectedItem();	
    	field_id.setText("" + goruntule.getId());
    	field_isim.setText("" + goruntule.getIsim());
    	field_ulke.setText("" + goruntule.getUlke());
    	field_tur.setText("" + goruntule.getTur());
    	field_diziFilm.setText("" + goruntule.getDiziFilm());
    	field_yonetmen.setText("" + goruntule.getYonetmen());
    	field_starlar.setText("" + goruntule.getStarlar());
    	field_muzik.setText("" + goruntule.getMuzik()); 
    	field_imdb.setText("" + goruntule.getImdb()); 
    	field_tarih.setValue(((java.sql.Date) goruntule.getCikisTarih()).toLocalDate());
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
    	private void reset() {   	
    		field_id.setText("");
    		field_isim.setText("");
    		field_ulke.setText("");
    		field_tur.setText("");
    		field_diziFilm.setText("");
    		field_yonetmen.setText("");
    		field_starlar.setText("");
    		field_muzik.setText("");   
    		field_imdb.setText("");
    		field_tarih.getEditor().clear();
    } 
    	 public ObservableList<YapimListesi>getYapimEkle(){
    	    	ObservableList<YapimListesi>yapimEkle = FXCollections.observableArrayList();
    	    	Connection connection = getConnection();
    	    	String getSql = "SELECT * FROM yapim_ekrani";
    	    	Statement sta;
    	    	ResultSet rsu;
    	    	try {
    				sta = connection.createStatement();
    				rsu = sta.executeQuery(getSql);
    				YapimListesi yapimEklemek;
    				while(rsu.next()) {
    					yapimEklemek = new YapimListesi(rsu.getInt("id"), rsu.getString("isim"), rsu.getString("ulke"), rsu.getString("tur"), rsu.getString("diziFilm"),  rsu.getString("yonetmen"), rsu.getString("starlar"), rsu.getString("muzik"), rsu.getString("imdb") , rsu.getDate("cikisTarih"));
    					yapimEkle.add(yapimEklemek);				
    				}
    			} catch (Exception e) {
    				
    				e.printStackTrace();
    			}
    	    	return yapimEkle;
    	    }   
    	 public void yapimlariGoster() {
    	    	ObservableList<YapimListesi>gor = getYapimEkle();
    	    	
    	    	view_id.setCellValueFactory(new PropertyValueFactory<YapimListesi, Integer>("id"));
    	    	view_isim.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("isim"));
    	    	view_ulke.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("ulke"));
    	    	view_tur.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("tur"));
    	    	view_diziFilm.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("diziFilm"));
    	    	view_yonetmen.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("yonetmen"));
    	    	view_starlar.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("starlar"));
    	    	view_muzik.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("muzik"));
    	    	view_imdb.setCellValueFactory(new PropertyValueFactory<YapimListesi, String>("imdb"));
    	    	view_tarih.setCellValueFactory(new PropertyValueFactory<YapimListesi, Date>("cikisTarih"));    	    	
    	    	tableView.setItems(gor);
    	    }  	 
    	 private void kayitEkle() {
    	    	
    	    	String getSql = "INSERT INTO yapim_ekrani VALUES(" 
    	    	+ field_id.getText() + ",'" + field_isim.getText() + "','" + field_ulke.getText() + "','" + field_tur.getText() + "','" + field_diziFilm.getText() + "','" + field_yonetmen.getText() + "','" + field_starlar.getText()+ "','" + field_muzik.getText() + "','" + field_imdb.getText() + "','" + field_tarih.getValue() + "')";
    	    	calistir(getSql);
    	    	yapimlariGoster();
    	    }
    	 
    	 private void kayitGuncelle(){
    	    	String getSql = "UPDATE  yapim_ekrani SET isim  = '" + field_isim.getText() + "', ulke = '" + field_ulke.getText() + "', tur = '" + field_tur.getText() + "', diziFilm = '" + field_diziFilm.getText() + "', yonetmen = '" + field_yonetmen.getText() + "', starlar = '" + field_starlar.getText() + "', muzik = '" + field_muzik.getText() + "', imdb = '" + field_imdb.getText() + "', cikisTarih = '" + field_tarih.getValue() + "' WHERE id = " + field_id.getText() + "";    	       		    	
    	    	calistir(getSql);
    	    	yapimlariGoster();
    	    }   	 
    	 private void yapimSil(){
    	        String getSql = "DELETE FROM yapim_ekrani WHERE id =" + field_id.getText() + "";
    	        calistir(getSql);
    	        yapimlariGoster();
    	    }    	 
    	 private void calistir(String getSql) {
    	        Connection connection = getConnection();
    	        Statement st;
    	        try{
    	            st = connection.createStatement();
    	            st.executeUpdate(getSql);
    	        }catch(Exception ex){
    	            ex.printStackTrace();
    	        }
    	    }	 
    @FXML
    //baslangıc ekranı
    void initialize() {
    	yapimlariGoster();
    }

}
