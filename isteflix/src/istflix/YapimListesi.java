package istflix;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class YapimListesi {
	
	private Integer id;
	private String isim;
	private String ulke;
	private String tur;
	private String diziFilm;
	private String yonetmen;
	private String starlar;
	private String muzik;
	private String imdb;	
	private Date cikisTarih;
	
	//tablo icin olusturulan yapıların KURUCULARI
	public YapimListesi(Integer id, String isim, String ulke, String tur, String diziFilm, String yonetmen,
			String starlar, String muzik, String imdb, Date cikisTarih) {
		super();
		this.id = id;
		this.isim = isim;
		this.ulke = ulke;
		this.tur = tur;
		this.diziFilm = diziFilm;
		this.yonetmen = yonetmen;
		this.starlar = starlar;
		this.muzik = muzik;
		this.imdb = imdb;
		this.cikisTarih = cikisTarih;
	}

	//tablo icin olusturulan yapıların GETTER ve SETTERLARI
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String getUlke() {
		return ulke;
	}

	public void setUlke(String ulke) {
		this.ulke = ulke;
	}

	public String getTur() {
		return tur;
	}

	public void setTur(String tur) {
		this.tur = tur;
	}

	public String getDiziFilm() {
		return diziFilm;
	}

	public void setDiziFilm(String diziFilm) {
		this.diziFilm = diziFilm;
	}

	public String getYonetmen() {
		return yonetmen;
	}

	public void setYonetmen(String yonetmen) {
		this.yonetmen = yonetmen;
	}

	public String getStarlar() {
		return starlar;
	}

	public void setStarlar(String starlar) {
		this.starlar = starlar;
	}

	public String getMuzik() {
		return muzik;
	}

	public void setMuzik(String muzik) {
		this.muzik = muzik;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public Date getCikisTarih() {
		return cikisTarih;
	}

	public void setCikisTarih(Date cikisTarih) {
		this.cikisTarih = cikisTarih;
	}
	
	
	
}
