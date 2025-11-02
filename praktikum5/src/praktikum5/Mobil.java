package praktikum5;

public final class Mobil extends Kendaraan implements BahanBakar {
	
	private String jenisTransmisi;
	
	public Mobil(String merk, String model, double tahunProduksi, String jenisTransmisi) {
		super(merk, model, tahunProduksi);
		this.jenisTransmisi = jenisTransmisi;
	}
	
	public String getTransmisi() {
		return jenisTransmisi;
	}

	@Override
	public String jenisBahanBakar() {
		return "Bensin";
	}

	@Override
	public void nyalakanMesin() {
		System.out.println("Nyalakan Mesin: Tekan tombol Start");
	}
	
	public void fiturMobil() {
		System.out.println("Fitur mobil: Memilki AC dan audio premium");
	}	
}
