package praktikum5;

public abstract class Kendaraan {
	private String merk;
	private String model;
	private double tahunProduksi;
	
	public Kendaraan(String merk, String model, double tahunProduksi) {
		this.merk = merk;
		this.model = model;
		this.tahunProduksi = tahunProduksi;
	}
	
	public abstract void nyalakanMesin();
	
	final public void tampilkanInfo() {
		
		System.out.println("Merk: " + merk);
		System.out.println("Model: " + model);
		System.out.println("Tahun produksi: " + tahunProduksi);
		
	}
}
