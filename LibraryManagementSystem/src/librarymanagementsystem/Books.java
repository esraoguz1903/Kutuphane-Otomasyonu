package librarymanagementsystem;


public class Books {
    private int id;
    private String kitapAdi;
    private String yazarAdi;
    private String turu;
    private int sayfaSayisi;
    private int kitapAdeti;

    public Books(int id, String kitapAdi, String yazarAdi, String turu, int sayfaSayisi, int kitapAdeti) {
        this.id = id;
        this.kitapAdi = kitapAdi;
        this.yazarAdi = yazarAdi;
        this.turu = turu;
        this.sayfaSayisi = sayfaSayisi;
        this.kitapAdeti = kitapAdeti;
    }

    public Books(int id, String KitapAdi) {
        this.id = id;
        this.kitapAdi =kitapAdi;
    }
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the kitapAdi
     */
    public String getKitapAdi() {
        return kitapAdi;
    }

    /**
     * @param kitapAdi the kitapAdi to set
     */
    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    /**
     * @return the yazarAdi
     */
    public String getYazarAdi() {
        return yazarAdi;
    }

    /**
     * @param yazarAdi the yazarAdi to set
     */
    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    /**
     * @return the turu
     */
    public String getTuru() {
        return turu;
    }

    /**
     * @param turu the turu to set
     */
    public void setTuru(String turu) {
        this.turu = turu;
    }

    /**
     * @return the sayfaSayisi
     */
    public int getSayfaSayisi() {
        return sayfaSayisi;
    }

    /**
     * @param sayfaSayisi the sayfaSayisi to set
     */
    public void setSayfaSayisi(int sayfaSayisi) {
        this.sayfaSayisi = sayfaSayisi;
    }

    /**
     * @return the kitapAdeti
     */
    public int getKitapAdeti() {
        return kitapAdeti;
    }

    /**
     * @param kitapAdeti the kitapAdeti to set
     */
    public void setKitapAdeti(int kitapAdeti) {
        this.kitapAdeti = kitapAdeti;
    }

}

    