package istflix;

public class YeniKayit {

	private Integer id;
	private String isim;
	private String soyisim;
	private String nick;
	private String sifre;
	
	
	public YeniKayit(Integer id, String isim, String soyisim, String nick, String sifre) {
		super();
		this.id = id;
		this.isim = isim;
		this.soyisim = soyisim;
		this.nick = nick;
		this.sifre = sifre;
	}
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
	public String getSoyisim() {
		return soyisim;
	}
	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	
	
}
