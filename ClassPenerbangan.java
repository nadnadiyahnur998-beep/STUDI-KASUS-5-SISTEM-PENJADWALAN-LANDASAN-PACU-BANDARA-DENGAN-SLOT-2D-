import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Penerbangan {
    private String kode;
    private String maskapai;
    private String tujuan;
    private int jumlahPenumpang;
    private String status;

    public Penerbangan(String kode, String maskapai, String tujuan, int jumlahPenumpang) {
        this.kode = kode;
        this.maskapai = maskapai;
        this.tujuan = tujuan;
        this.jumlahPenumpang = jumlahPenumpang;
        this.status = "TERJADWAL";
    }
public void ubahStatus(String statusBaru) {
    String statusUpper = statusBaru.toUpperCase();
    if (statusUpper.equals("TERJADWAL") || 
        statusUpper.equals("BOARDING") || 
        statusUpper.equals("LEPAS LANDAS") ||
        statusUpper.equals("TUNDA")) {
        this.status = statusUpper;
    } else {
        System.out.println("Status tidak valid! Pilihan yang tersedia: TERJADWAL, BOARDING, LEPAS LANDAS, TUNDA.");
    }
}

    public String getKode() { return kode; }
    public String getMaskapai() { return maskapai; }
    public String getTujuan() { return tujuan; }
    public int getJumlahPenumpang() { return jumlahPenumpang; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "[" + kode + " | Maskapai: " + maskapai + " | Tujuan: " + tujuan + " | Penumpang: " + jumlahPenumpang + " | Status: " + status + "]";
    }
}