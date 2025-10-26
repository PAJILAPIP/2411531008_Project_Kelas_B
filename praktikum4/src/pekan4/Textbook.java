package pekan4;

public class Textbook extends Book {

	private String bidang; 

    public Textbook(String title, String author, String bidang) {
    	super(title, author);
        this.bidang = bidang;
    }

    public String getBidang() {
    	return bidang;
    }
}
