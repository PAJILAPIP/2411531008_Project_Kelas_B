package praktikum5;

public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai {

	public Pesawat(String merk, String model, double tahunProduksi) {
		super(merk, model, tahunProduksi);
	}

	@Override
	public void nyalakanMesin() {
		System.out.println("Nyalakan Mesin: Bersiap lepas landas");
		
	}

	@Override
	public String jenisBahanBakar() {
		return "Avtur";
	}

	@Override
	public String namaMaskapai() {
		return "Garuda Indonesia";
	}

	@Override
	public String jenisPenerbangan() {
		return "Penerbangan Umum";
	}

}
