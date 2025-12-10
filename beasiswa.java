import java.util.Scanner;

public class beasiswa {
    static void tampilkanMenu() {
        System.out.println("=== Sistem Pendaftaran Beasiswa ===");
        System.out.println("1. Tambah Data Pendaftar Beasiswa");
        System.out.println("2. Tampilkan Semua Pendaftar");
        System.out.println("3. Cari Pendaftar berdasarkan Jenis Beasiswa");
        System.out.println("4. Hitung Rata-rata IPK per Jenis Beasiswa");
        System.out.println("5. Keluar");
        System.out.print("Pilih menu (1-5): ");
    }

    static void tampilkanSemua(String[] nama, String[] nim, double[] ipk, String[] jenis, double[] penghasilan, int count) { //menerima array yang menyimpan data mhs
        if (count == 0) { //count itu jumlah pendaftar
            System.out.println("Belum ada pendaftar.");
        } else {
            for(int i = 0; i < count; i++) { //loop
                System.out.println("Nama: "+nama[i]);
                System.out.println("NIM: "+nim[i]);
                System.out.println("IPK: "+ipk[i]);
                System.out.println("Jenis: "+jenis[i]);
                System.out.println("Penghasilan: "+penghasilan[i]);
                System.out.println();
            }
        }
    }

    static void cariJenis(String[] nama, String[] nim, double[] ipk, String[] jenis, double[] penghasilan, int count, Scanner sc) { //menerima array yang menyimpan data mhs
        System.out.print("Masukkan jenis beasiswa yang dicari: ");
        String cari = sc.nextLine();

        boolean ada = false;

        for(int i = 0; i < count; i++) {
            if (jenis[i].equalsIgnoreCase(cari)) {
                ada = true;
                System.out.println("Nama: "+nama[i]);
                System.out.println("NIM: "+nim[i]);
                System.out.println("IPK: "+ipk[i]);
                System.out.println("Jenis: "+jenis[i]);
                System.out.println("Penghasilan: "+penghasilan[i]);
                System.out.println();
            }
        }
        if (!ada) {
            System.out.println("Tidak ada pendaftar dengan jenis tersebut.");
        }
    }

    static void hitungRata(double[] ipk, String[] jenis, int count) {
        String[] listJenis ={"Reguler", "Unggulan", "Riset"};

        for(String j : listJenis) {
            double total = 0;
            int jumlah = 0;

            for(int i=0; i < count; i++) {
                if (jenis[i].equalsIgnoreCase(j)) {
                    total += ipk[i];
                    jumlah++;
                }
            }
            if (jumlah == 0) {
                System.out.println(j+" : tidak ada pendaftar.");
            } else {
                System.out.println(j+" : rata-rata IPK = "+ (total/jumlah));
            }
        } 
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);

        String[] nama = new String[100];
        String[] nim = new String[100];
        double[] ipk = new double[100];
        String[] jenis = new String[100];
        double[] penghasilan = new double[100];
        int count = 0;

        while (true) {
            tampilkanMenu();
            int menu = sc.nextInt();
            sc.nextLine();

            if (menu == 1) {
                System.out.print("Nama Mahasiswa: ");
                nama [count] = sc.nextLine();
                System.out.print("NIM: ");
                nim [count] = sc.nextLine();
                System.out.print("IPK Terakhir: ");
                ipk [count] = sc.nextDouble();
                sc.nextLine();
                System.out.print("Jenis Beasiswa (Reguler/Unggulan/Riset): ");
                jenis [count] = sc.nextLine();
                System.out.print("Penghasilan orang tua (maximal 2000000):");
                penghasilan [count] = sc.nextDouble();
                sc.nextLine();

                if (penghasilan [count] > 2000000) {
                    System.out.println("Pendaftaran dibatalkan karena penghasilan melebihi batas maksimal.");
                } else {
                    count++;
                    System.out.println("Pendaftar berhasil disimpan. Total pendaftar: "+count);
                }
                System.out.println();
            } else if (menu == 2) {
                tampilkanSemua(nama, nim, ipk, jenis, penghasilan, count);
            } else if (menu == 3) {
                cariJenis(nama, nim, ipk, jenis, penghasilan, count, sc);
            } else if (menu == 4) {
                hitungRata(ipk, jenis, count);
            } else if (menu == 5) {
                break;
            }
            System.out.println();
        }
        sc.close();
    }
}
