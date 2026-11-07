class Bandara {
    private JadwalBandara jadwal;
    private List<Penerbangan> daftarPenerbangan;

    public Bandara() {
        this.jadwal = new JadwalBandara();
        this.daftarPenerbangan = new ArrayList<>();
    }

    public boolean tambahPenerbanganBaru(Penerbangan p, int landasan, int slotWaktu) {
        boolean berhasil = jadwal.jadwalkan(p, landasan, slotWaktu);
        if (berhasil) {
            daftarPenerbangan.add(p);
        }
        return berhasil;
    }

    public void batalkanPenerbangan(int landasan, int slotWaktu) {
        Penerbangan p = jadwal.getSlot()[landasan][slotWaktu];
        if (p != null) {
            daftarPenerbangan.remove(p);
            jadwal.batalkan(landasan, slotWaktu);
        } else {
            System.out.println("Tidak ada penerbangan yang aktif di slot tersebut.");
        }
    }

    public void hitungPendapatan() {
            if (daftarPenerbangan.isEmpty()) {
                System.out.println("\nBelum ada penerbangan terdaftar untuk menghitung pendapatan.");
                return;
            }

            Map<String, Integer> hitungMaskapai = new HashMap<>();
            for (Penerbangan p : daftarPenerbangan) {
                hitungMaskapai.put(p.getMaskapai(), hitungMaskapai.getOrDefault(p.getMaskapai(), 0) + 1);
            }

            double totalPendapatanBandara = 0;
            
            System.out.println("\n+-----+------------+--------------------+---------------+--------------------+--------------------+--------------------+");
            System.out.println("|                                         LAPORAN PENDAPATAN & BIAYA BANDARA                                           |");
            System.out.println("+-----+------------+--------------------+---------------+--------------------+--------------------+--------------------+");
            System.out.printf("| %-3s | %-10s | %-18s | %-13s | %-18s | %-18s | %-18s |\n", 
                "No", "Kode", "Maskapai", "Penumpang", "Biaya Landasan", "Biaya Penumpang", "Total Penerbangan");
            System.out.println("+-----+------------+--------------------+---------------+--------------------+--------------------+--------------------+");

            int nomor = 1;
            for (Penerbangan p : daftarPenerbangan) {
                String maskapai = p.getMaskapai();
                int jumlahPenerbanganMaskapai = hitungMaskapai.get(maskapai);
                
                double biayaLandasan = 2500000;
                
                if (jumlahPenerbanganMaskapai >= 3) {
                    biayaLandasan *= 0.90; 
                }
                
                double biayaPenumpang = p.getJumlahPenumpang() * 15000.0;
                double totalPenerbangan = biayaLandasan + biayaPenumpang;
                totalPendapatanBandara += totalPenerbangan;

                System.out.printf("| %-3d | %-10s | %-18s | %-13d | Rp%-16.0f | Rp%-16.0f | Rp%-16.0f |\n", 
                    nomor++, p.getKode(), maskapai, p.getJumlahPenumpang(), biayaLandasan, biayaPenumpang, totalPenerbangan);
            }
            
            System.out.println("+-----+------------+--------------------+---------------+--------------------+--------------------+--------------------+");
            System.out.printf("| TOTAL PENDAPATAN KESELURUHAN BANDARA  : Rp%-75.0f|\n", totalPendapatanBandara);
            System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("| MASKAPAI TERSIBUK                     : %-76s |\n", maskapaiTersibuk());
            System.out.println("+----------------------------------------------------------------------------------------------------------------------+");
    }

    public String maskapaiTersibuk() {
        if (daftarPenerbangan.isEmpty()) {
            return "Tidak ada data";
        }

        Map<String, Integer> hitungMaskapai = new HashMap<>();
        for (Penerbangan p : daftarPenerbangan) {
            hitungMaskapai.put(p.getMaskapai(), hitungMaskapai.getOrDefault(p.getMaskapai(), 0) + 1);
        }

        String tersibuk = "";
        int maxPenerbangan = -1;
        for (Map.Entry<String, Integer> entry : hitungMaskapai.entrySet()) {
            if (entry.getValue() > maxPenerbangan) {
                maxPenerbangan = entry.getValue();
                tersibuk = entry.getKey();
            }
        }
        return tersibuk + " (" + maxPenerbangan + " penerbangan)";
    }

    public JadwalBandara getJadwal() {
        return jadwal;
    }
}