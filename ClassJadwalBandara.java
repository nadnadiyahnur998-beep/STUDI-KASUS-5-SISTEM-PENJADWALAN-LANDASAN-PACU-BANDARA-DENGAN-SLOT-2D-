class JadwalBandara {
    private Penerbangan[][] slot;

    public JadwalBandara() {
        slot = new Penerbangan[3][8];
    }

    public void tampilkanJadwal() {
        System.out.println("\n                +---------------------------------------------------------------------------------------------------------------+");
        System.out.println("                |                                 JADWAL LANDASAN PACU BANDARA (06.00 - 13.00)                                  |");
        System.out.println("----------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");
        
        System.out.printf("| %-13s |", "Landasan");
        for (int t = 0; t < 8; t++) {
            int jam = 6 + t;
            System.out.printf(" %-13s |", String.format("%02d.00", jam));
        }
        System.out.println("\n+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");

        for (int i = 0; i < 3; i++) {
            System.out.printf("| Landasan %-4d |", (i + 1));
            for (int j = 0; j < 8; j++) {
                if (slot[i][j] == null) {
                    System.out.printf(" %-13s |", "KOSONG");
                } else {
                    System.out.printf(" %-13s |", slot[i][j].getKode());
                }
            }
            System.out.println();
        }
        System.out.println("+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");
        System.out.printf("|         Status Slot: Total Slot = 24          |                Slot Kosong = %-2d               |         Tingkat Okupansi = %-5.2f%%             |\n", 
            cariSlotKosong(), hitungOkupansi());
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    public boolean jadwalkan(Penerbangan p, int landasan, int slotWaktu) {
        if (landasan < 0 || landasan >= 3 || slotWaktu < 0 || slotWaktu >= 8) {
            System.out.println("Error: Landasan harus antara 1-3 dan slot waktu antara 0-7 (pukul 06.00-13.00)!");
            return false;
        }
        if (slot[landasan][slotWaktu] != null) {
            System.out.println("Error: Slot pada Landasan " + (landasan + 1) + " jam " + (6 + slotWaktu) + ".00 sudah terisi oleh penerbangan " + slot[landasan][slotWaktu].getKode() + "!");
            return false;
        }
        slot[landasan][slotWaktu] = p;
        System.out.println("Sukses: Penerbangan " + p.getKode() + " berhasil dijadwalkan.");
        return true;
    }

    public void batalkan(int landasan, int slotWaktu) {
        if (landasan < 0 || landasan >= 3 || slotWaktu < 0 || slotWaktu >= 8) {
            System.out.println("Error: Indeks landasan atau slot tidak valid!");
            return;
        }
        if (slot[landasan][slotWaktu] == null) {
            System.out.println("Informasi: Slot tersebut sudah kosong.");
        } else {
            System.out.println("Sukses: Penerbangan " + slot[landasan][slotWaktu].getKode() + " telah dibatalkan dari jadwal.");
            slot[landasan][slotWaktu] = null;
        }
    }

    public int[] cariPenerbangan(String kode) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (slot[i][j] != null && slot[i][j].getKode().equalsIgnoreCase(kode)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int cariSlotKosong() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (slot[i][j] == null) {
                    count++;
                }
            }
        }
        return count;
    }

    public double hitungOkupansi() {
        int totalSlot = 3 * 8;
        int slotKosong = cariSlotKosong();
        int slotTerisi = totalSlot - slotKosong;
        return ((double) slotTerisi / totalSlot) * 100.0;
    }

    public Penerbangan[][] getSlot() {
        return slot;
    }
}