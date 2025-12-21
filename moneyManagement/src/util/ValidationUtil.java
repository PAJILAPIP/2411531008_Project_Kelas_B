package util;

import error.ValidationException;
import model.Transaksi;
import model.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidationUtil {

    
    public static void validate(Transaksi tr)
            throws ValidationException, NullPointerException {

        if (tr == null) {
            throw new NullPointerException("Transaksi belum dibuat");
        }

   
        if (tr.getTanggal() == null || tr.getTanggal().isBlank()) {
            throw new ValidationException("Tanggal tidak boleh kosong");
        }

        try {
            LocalDate.parse(tr.getTanggal()); 
        } catch (DateTimeParseException e) {
            throw new ValidationException("Format tanggal harus YYYY-MM-DD");
        }

        
        if (tr.getNominal() <= 0) {
            throw new ValidationException("Nominal harus lebih dari 0");
        }

        
        if (tr.getKategori() == null || tr.getKategori().isBlank()) {
            throw new ValidationException("Kategori harus dipilih");
        }

        
        if (tr.getDeskripsi() == null || tr.getDeskripsi().isBlank()) {
            throw new ValidationException("Deskripsi tidak boleh kosong");
        }

        
        if (tr.getUserId() <= 0) {
            throw new ValidationException("User tidak valid");
        }
    }
}
