public class MainBandara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bandara bandara = new Bandara();
        int pilihan = 0;

        do {
            System.out.println("\n=================================================");
            System.out.println("    SISTEM PENJADWALAN LANDASAN PACU BANDARA     ");
            System.out.println("=================================================");
            System.out.println(" 1. Jadwalkan Penerbangan");
            System.out.println(" 2. Batalkan Jadwal Penerbangan");
            System.out.println(" 3. Ubah Status Penerbangan");
            System.out.println(" 4. Cari Penerbangan");
            System.out.println(" 5. Tampilkan Jadwal (Tabel Grid 2D)");
            System.out.println(" 6. Laporan Pendapatan & Statistik (Tabel)");
            System.out.println(" 7. Keluar");
            System.out.println("-------------------------------------------------");
            System.out.print("Pilih menu (1-7): ");

            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Input tidak valid! Harap masukkan angka 1 sampai 7.");
                scanner.next();
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- Tambah Jadwal Penerbangan ---");
                    System.out.print("Masukkan Kode Penerbangan (cth: GA-101): ");
                    String kode = scanner.nextLine();
                    System.out.print("Masukkan Nama Maskapai: ");
                    String maskapai = scanner.nextLine();
                    System.out.print("Masukkan Kota Tujuan: ");
                    String tujuan = scanner.nextLine();
                    
                    int penumpang = -1;
                    while (true) {
                        System.out.print("Masukkan Jumlah Penumpang (>= 0): ");
                        if (scanner.hasNextInt()) {
                            penumpang = scanner.nextInt();
                            if (penumpang >= 0) break;
                            System.out.println("Jumlah penumpang tidak boleh negatif!");
                        } else {
                            System.out.println("Input harus berupa angka.");
                            scanner.next();
                        }
                    }
                    
                    int landasan = -1;
                    while (true) {
                        System.out.print("Pilih Landasan (1 - 3): ");
                        if (scanner.hasNextInt()) {
                            landasan = scanner.nextInt() - 1;
                            if (landasan >= 0 && landasan < 3) break;
                            System.out.println("Landasan harus antara 1 sampai 3!");
                        } else {
                            System.out.println("Input harus berupa angka.");
                            scanner.next();
                        }
                    }

                    int slotWaktu = -1;
                    while (true) {
                        System.out.print("Pilih Slot Waktu (06.00 = 0, 07.00 = 1, ..., 13.00 = 7) [0-7]: ");
                        if (scanner.hasNextInt()) {
                            slotWaktu = scanner.nextInt();
                            if (slotWaktu >= 0 && slotWaktu < 8) break;
                            System.out.println("Slot waktu harus antara 0 sampai 7!");
                        } else {
                            System.out.println("Input harus berupa angka.");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();

                    Penerbangan pBaru = new Penerbangan(kode, maskapai, tujuan, penumpang);
                    bandara.tambahPenerbanganBaru(pBaru, landasan, slotWaktu);
                    break;

                case 2:
                    System.out.println("\n--- Batalkan Jadwal Penerbangan ---");
                    bandara.getJadwal().tampilkanJadwal();
                    int bLandasan = -1, bSlot = -1;
                    while (true) {
                        System.out.print("Pilih Landasan (1-3): ");
                        if (scanner.hasNextInt()) {
                            bLandasan = scanner.nextInt() - 1;
                            if (bLandasan >= 0 && bLandasan < 3) break;
                            System.out.println("Landasan harus 1-3!");
                        } else {
                            System.out.println("Input tidak valid.");
                            scanner.next();
                        }
                    }
                    while (true) {
                        System.out.print("Pilih Slot Waktu (0-7): ");
                        if (scanner.hasNextInt()) {
                            bSlot = scanner.nextInt();
                            if (bSlot >= 0 && bSlot < 8) break;
                            System.out.println("Slot waktu harus 0-7!");
                        } else {
                            System.out.println("Input tidak valid.");
                            scanner.next();
                        }
                    }
                    scanner.nextLine();
                    bandara.batalkanPenerbangan(bLandasan, bSlot);
                    break;

                case 3:
                    System.out.println("\n--- Ubah Status Penerbangan ---");
                    System.out.print("Masukkan Kode Penerbangan: ");
                    String cariKode = scanner.nextLine();
                    int[] pos = bandara.getJadwal().cariPenerbangan(cariKode);
                    if (pos != null) {
                        Penerbangan pTarget = bandara.getJadwal().getSlot()[pos[0]][pos[1]];
                        System.out.println("Ditemukan: " + pTarget);
                        System.out.print("Masukkan Status Baru (TERJADWAL / BOARDING / LEPAS LANDAS / TUNDA): ");
                        String statusBaru = scanner.nextLine();
                        String upperStatus = statusBaru.toUpperCase();
                        if (upperStatus.equals("TERJADWAL") || 
                            upperStatus.equals("BOARDING") || 
                            upperStatus.equals("LEPAS LANDAS") || 
                            upperStatus.equals("TUNDA")) {
                            
                            pTarget.ubahStatus(statusBaru);
                            System.out.println("Status berhasil diperbarui!");
                        } else {
                            System.out.println("Status tidak valid! Pilihan yang tersedia: TERJADWAL, BOARDING, LEPAS LANDAS, TUNDA.");
                        }
                    } else {
                        System.out.println("Penerbangan dengan kode tersebut tidak ditemukan.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Cari Penerbangan ---");
                    System.out.print("Masukkan Kode Penerbangan: ");
                    String kCari = scanner.nextLine();
                    int[] kPos = bandara.getJadwal().cariPenerbangan(kCari);
                    if (kPos != null) {
                        Penerbangan pKetemu = bandara.getJadwal().getSlot()[kPos[0]][kPos[1]];
                        System.out.println("Penerbangan Ditemukan!");
                        System.out.println("Detail: " + pKetemu);
                        System.out.println("Lokasi Slot: Landasan " + (kPos[0] + 1) + ", Pukul " + (6 + kPos[1]) + ".00");
                    } else {
                        System.out.println("Penerbangan tidak ditemukan.");
                    }
                    break;

                case 5:
                    bandara.getJadwal().tampilkanJadwal();
                    break;

                case 6:
                    bandara.hitungPendapatan();
                    break;

                case 7:
                    System.out.println("Terima kasih telah menggunakan sistem penjadwalan bandara.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih angka 1-7.");
            }
        } while (pilihan != 7);

        scanner.close();
    }
}