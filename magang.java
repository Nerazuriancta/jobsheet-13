import java.util.Scanner;

public class magang {
    static void tampilkanMenu() {
        System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
        System.out.println("1. Tambah Data Magang");
        System.out.println("2. Tampilkan Semua Pendaftar Magang");
        System.out.println("3. Cari Pendaftar berdasarkan Program Studi");
        System.out.println("4. Hitung Jumlah Pendaftar untuk Setiap Status");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu (1-5): ");
    }

    static void headerTable() {
        System.out.printf("%-4s %-15s %-10s %-25s %-20s %-9s %-10s\n", "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status");
    }

    static void tampilkanSemua(String[][] data, int count) {
        if (count == 0) {
            System.out.println("Belum ada pendaftar");
            return;
        }
        headerTable();
        
        for (int i = 0; i < count; i++) {
            System.out.printf("%-4d %-15s %-10s %-25s %-20s %-9s %-10s\n", 
            (i+1), 
            data[i][0], //nama
            data[i][1], //nim
            data[i][2], //prodi
            data[i][3], //perusahaan
            data[i][4], //semester
            data[i][5] //status
            ); 
        }
    }

    static void cariProdi(String[][] data, int count, Scanner sc) {
        System.out.print("Masukkan Program Studi: ");
        String cari = sc.nextLine();

        boolean ada = false;

        headerTable();
       
        for(int i=0; i < count; i++) {
            if (data[i][2].equalsIgnoreCase(cari)) {
                ada = true;
                System.out.printf("%-4d %-15s %-10s %-25s %-20s %-9s %-10s\n", (i+1), data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5]);
            }
        }

        if (!ada) {
            System.out.println("Tidak ada pendaftar dari program studi tersebut.");
        }
    }

    static void hitungStatus(String[][] data, int count) {
        int diterima = 0, menunggu = 0, ditolak = 0;

        for(int i=0; i < count; i++) {
            String status = data[i][5];
            if (status.equalsIgnoreCase("Diterima")) {
                diterima++;
            } else if (status.equalsIgnoreCase("Menunggu")) {
                menunggu++;
            } else if (status.equalsIgnoreCase("Ditolak")) {
                ditolak++;
            }
        }
        System.out.println("\n=== Rekap Jumlah Pendaftar Berdasarkan Status ===");
        System.out.println("Diterima : "+diterima);
        System.out.println("Menunggu : "+menunggu);
        System.out.println("Ditolak : "+ditolak);
        System.out.println("Total pendaftar: "+count);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] data = new String[100][6];
        int count = 0;

        while (true) {
            tampilkanMenu();
            int menu = sc.nextInt();
            sc.nextLine();

            if (menu == 1 ) {
                System.out.print("Nama Mahasiswa: ");
                data[count][0] = sc.nextLine();

                System.out.print("NIM: ");
                data[count][1] = sc.nextLine();

                System.out.print("Program Studi: ");
                data[count][2] = sc.nextLine();

                System.out.print("Perusahaan Tujuan Magang: ");
                data[count][3] = sc.nextLine();

                while (true) {
                    System.out.print("Semester Pengambilan Magang (6 atau 7): ");
                    String semester = sc.nextLine();
                    if(semester.equals("6") || semester.equals("7")) {
                        data[count][4] = semester;  
                        break;
                    }
                    System.out.println("Semester hanya boleh 6 atau 7!");
                }

                while (true) {
                    System.out.print("Status Magang (Diterima/Menunggu/Ditolak): ");
                    String status = sc.nextLine();

                    if (status.equalsIgnoreCase("Diterima") || status.equalsIgnoreCase("Menunggu") || status.equalsIgnoreCase("Ditolak")) {
                        data[count][5] = status;
                        break;
                    }

                    System.out.println("Status tidak valid!");
                }
                count++;
                System.out.println("Data berhasil ditambahkan. Total pendaftar: "+count);
            }

            else if (menu == 2) {
                tampilkanSemua(data, count);
            }

            else if (menu == 3) {
                cariProdi(data, count, sc);
            }

            else if (menu == 4) {
                hitungStatus(data, count);
            }

            else if (menu == 5) {
                System.out.println("Program selesai. Terima kasih!");
                break;
            }

            else{
                System.out.println("Menu tidak tersedia.");
            }

            System.out.println();
        }

        sc.close();
    }
}