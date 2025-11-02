package praktikum5;


public class Main {

	public static void main(String[] args) {
		Mobil avanza = new  Mobil ("Toyota", "Avanza", 2021, "Manual");
		avanza.tampilkanInfo();
		System.out.println("Jenis Transmisi: " + avanza.getTransmisi());
		avanza.nyalakanMesin();
		System.out.println("Jenis bahan bakar: " + avanza.jenisBahanBakar());
		avanza.infoKonsumsi();
		avanza.fiturMobil();
		
		System.out.println();
		
		Bus busPariwisata = new Bus("Mercedez-Benz", "Bus Pariwisata", 2018, "A");
		busPariwisata.tampilkanInfo();
		System.out.println("Kelas Bus: " + busPariwisata.getKelasBus());
		busPariwisata.nyalakanMesin();
		System.out.println("Jenis bahan bakar: " + busPariwisata.jenisBahanBakar());
		busPariwisata.infoKonsumsi();
		busPariwisata.fiturBus();
		Bus.JadwalPerjalanan jadwal = busPariwisata.new JadwalPerjalanan("Jakrta - Bandung", "08:00");
		jadwal.infoJadwal();
		
		System.out.println();
		
		Pesawat garuda = new Pesawat("Garuda", "Boeing 737", 100);
		garuda.tampilkanInfo();
		garuda.nyalakanMesin();
		System.out.println("Jenis Bahan Bakar: " + garuda.jenisBahanBakar());
		System.out.println("Nama Maskapai: " + garuda.namaMaskapai());
		System.out.println("Jenis Penerbangan: " + garuda.jenisPenerbangan());
		

	}

}
