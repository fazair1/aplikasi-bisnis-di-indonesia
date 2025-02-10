package com.juaracoding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class SortbyName implements Comparator<ArrayList<String>>
{
    public int compare(ArrayList<String> a, ArrayList<String> b) {
        int nameCompare = a.get(0).compareTo(b.get(0));
        int p1 = Integer.parseInt(a.get(1));
        int p2 = Integer.parseInt(b.get(1));
        int priceCompare = p1-p2;
        return (nameCompare==0) ? priceCompare : nameCompare;
    }
}
class SortbyPrice implements Comparator<ArrayList<String>>
{
    public int compare(ArrayList<String> a, ArrayList<String> b) {
        int nameCompare = a.get(0).compareTo(b.get(0));
        int p1 = Integer.parseInt(a.get(1));
        int p2 = Integer.parseInt(b.get(1));
        int priceCompare = p1-p2;
        return (priceCompare==0) ? nameCompare : priceCompare;
    }
}
class SortbyCategory implements Comparator<ArrayList<String>>
{
    public int compare(ArrayList<String> a, ArrayList<String> b) {
        int nameCompare = a.get(0).compareTo(b.get(0));
        int categoryCompare = a.get(2).compareTo(b.get(2));
        return (categoryCompare==0) ? nameCompare : categoryCompare;
    }
}

public class Main {

    public static void main(String[] args) {

        ArrayList<ArrayList<String>> listProduk = new ArrayList<ArrayList<String>>();
        showMenu("0", listProduk);
    }
    static void showMenu (String num, ArrayList<ArrayList<String>> listProduk) {
        Scanner scan = new Scanner(System.in);
        int menu=0;

        while (menu!=5) {

            System.out.println("=====Menu Aplikasi Bisnis=====");
            System.out.println("1. Create Product");
            System.out.println("2. Read Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.println("==============================");

            do {
                System.out.print("Pilih Menu[1-5]: ");
                num = scan.nextLine();
                num = num.trim();

                if (onlyDigits(num) && !(num.isEmpty())) {
                    menu = Integer.parseInt(num);
                }

            } while (!(onlyDigits(num)) || num.isEmpty() || (menu<=0 || menu>5) );

            switch (menu) {
                case 1:
                    createProduct(listProduk);
                    break;

                case 2:
                    readProduct(listProduk);
                    break;

                case 3:
                    updateProduct(listProduk);
                    break;

                case 4:
                    deleteProduct(listProduk);
                    break;

                case 5:
                    return;
            }
        }
    }
    static void createProduct (ArrayList<ArrayList<String>> listProduk) {
        Scanner scan = new Scanner(System.in);

        String namaProduk;
        String hargaProduk;
        String kategoriProduk;
//        String stokProduk;

        System.out.println("========Create Produk=========");
//        System.out.println("==============================");

        do {
            System.out.print("Masukan Nama Produk [Not Nullable]: ");
            namaProduk = scan.nextLine();
            namaProduk = namaProduk.trim();

        } while (namaProduk.isEmpty());

        double d = 0;
        do {
            System.out.print("Masukan Harga Produk [Not Nullable|Is Digit|Harga > 0]: ");
            hargaProduk = scan.nextLine();
            hargaProduk = hargaProduk.trim();

            if (onlyDigits(hargaProduk) && !(hargaProduk.isEmpty())) {
                d = Double.parseDouble(hargaProduk);
            }
        } while (hargaProduk.isEmpty() || !(onlyDigits(hargaProduk)) || d < 0);

        do {
            System.out.print("Masukan kategori Produk [Not Nullable]: ");
            kategoriProduk = scan.nextLine();
            kategoriProduk = kategoriProduk.trim();

        } while (kategoriProduk.isEmpty());

        int len = listProduk.size();
        listProduk.add(new ArrayList<String>());
        listProduk.get(len).add(0, namaProduk);
        listProduk.get(len).add(1, hargaProduk);
        listProduk.get(len).add(2, kategoriProduk);

        System.out.println("Berhasil memasukan produk dengan data:");
        System.out.println("Nama: "+namaProduk);
        System.out.println("Harga: Rp."+hargaProduk);
        System.out.println("Kategori: "+kategoriProduk);

    }
    static boolean onlyDigits(String s) {

        // Traverse each character in the string
        for (int i = 0; i < s.length(); i++) {

            // Check if the character is not a digit
            if (!Character.isDigit(s.charAt(i))) {

                // If any character is not a digit, return false
                return false;
            }
        }
        return true;  // If all characters are digits, return true
    }
    static void readProduct (ArrayList<ArrayList<String>> listProduk) {
//        System.out.println("==============================");
        System.out.println("=========Read Product=========");
        Scanner scan = new Scanner(System.in);
        String temp;

        if (listProduk.isEmpty()) {
            System.out.println("Produk kosong");
            return;
        }
        showProductList(listProduk);
        do {
            System.out.print("Apakah anda ingin melakukan sorting? [y/n]: ");
            temp = scan.nextLine();
            temp = temp.trim();
        }while (!(temp.equals("y") || temp.equals("n")));

        if (temp.equals("n")) {
            return;
        }
        else {
            do {
                System.out.print("Apakah sorting berdasarkan nama, harga, atau kategori? [nama/harga/kategori]: ");
                temp = scan.nextLine();
                temp = temp.trim();

            }while (!(temp.equals("nama") || temp.equals("harga") || temp.equals("kategori")));
            if (temp.equals("nama")) {
                Collections.sort(listProduk, new SortbyName());
            } else if (temp.equals("harga")) {
                Collections.sort(listProduk, new SortbyPrice());
            } else if (temp.equals("kategori")) {
                Collections.sort(listProduk, new SortbyCategory());
            }
            System.out.println("Sorting berhasil!");
        }
    }
    static void updateProduct (ArrayList<ArrayList<String>> listProduk) {
//        System.out.println("==============================");
        System.out.println("========Update Product========");
        Scanner scan = new Scanner(System.in);
        String num;
        boolean produkFound;
        String temp;
        int nomor = 0;

        if (listProduk.isEmpty()) {
            System.out.println("Produk kosong");
            return;
        }
        showProductList(listProduk);
        do {
            System.out.print("Apakah searching produk berdasarkan nomor, nama, harga, atau kategori? [nomor/nama/harga/kategori]: ");
            temp = scan.nextLine();
            temp = temp.trim();
        }while (!(temp.equals("nomor") || temp.equals("nama") || temp.equals("harga") || temp.equals("kategori")));

        if (temp.equals("nomor")) {
            do {
                System.out.print("Masukan nomor produk untuk di update [1-"+listProduk.size()+"]: ");
                num = scan.nextLine();
                num = num.trim();

                if (onlyDigits(num) && !(num.isEmpty())) {
                    nomor = Integer.parseInt(num);
                }

            } while (!(onlyDigits(num)) || num.isEmpty() || (nomor<=0 || nomor>listProduk.size()) );
            selectEditProduct(nomor, listProduk);
        }
        else if (temp.equals("nama")) {
            ArrayList<Integer> jumlahProduk = new ArrayList<Integer>();

            do {
                System.out.print("Masukan nama produk untuk di update: ");
                num = scan.nextLine();
                num = num.trim();
                produkFound = false;

                for (int i = 0; i < listProduk.size(); i++) {
                    if (listProduk.get(i).get(0).equals(num)) {
                        produkFound = true;
                        jumlahProduk.add(i);
                        nomor = i+1;
                    }
                }
                if (jumlahProduk.size()>1) {
                    for (int i : jumlahProduk) {
                        System.out.println(i+1+".");
                        System.out.println("Nama: "+listProduk.get(i).get(0));
                        System.out.println("Harga: Rp."+listProduk.get(i).get(1));
                        System.out.println("Kategori: "+listProduk.get(i).get(2));
                    }
                    do {
                        System.out.print("Masukan nomor produk untuk di update: ");
                        num = scan.nextLine();
                        num = num.trim();

                        if (onlyDigits(num) && !(num.isEmpty())) {
                            nomor = Integer.parseInt(num);
                        }
                    } while (!(onlyDigits(num)) || num.isEmpty() || !(jumlahProduk.contains(nomor-1)));
                }
            } while (!(produkFound));
            selectEditProduct(nomor, listProduk);
        }
        else if (temp.equals("harga")) {
            ArrayList<Integer> jumlahProduk = new ArrayList<Integer>();

            do {
                System.out.print("Masukan harga produk untuk di update: ");
                num = scan.nextLine();
                num = num.trim();
                produkFound = false;

                for (int i = 0; i < listProduk.size(); i++) {
                    if (listProduk.get(i).get(1).equals(num)) {
                        produkFound = true;
                        jumlahProduk.add(i);
                        nomor = i+1;
                    }
                }
                if (jumlahProduk.size()>1) {
                    for (int i : jumlahProduk) {
                        System.out.println(i+1+".");
                        System.out.println("Nama: "+listProduk.get(i).get(0));
                        System.out.println("Harga: Rp."+listProduk.get(i).get(1));
                        System.out.println("Kategori: "+listProduk.get(i).get(2));
                    }
                    do {
                        System.out.print("Masukan nomor produk untuk di update: ");
                        num = scan.nextLine();
                        num = num.trim();

                        if (onlyDigits(num) && !(num.isEmpty())) {
                            nomor = Integer.parseInt(num);
                        }
                    } while (!(onlyDigits(num)) || num.isEmpty() || !(jumlahProduk.contains(nomor-1)));
                }
            } while (!(produkFound));
            selectEditProduct(nomor, listProduk);
        }
        else if (temp.equals("kategori")) {
            ArrayList<Integer> jumlahProduk = new ArrayList<Integer>();

            do {
                System.out.print("Masukan kategori produk untuk di update: ");
                num = scan.nextLine();
                num = num.trim();
                produkFound = false;

                for (int i = 0; i < listProduk.size(); i++) {
                    if (listProduk.get(i).get(2).equals(num)) {
                        produkFound = true;
                        jumlahProduk.add(i);
                        nomor = i+1;
                    }
                }
                if (jumlahProduk.size()>1) {
                    for (int i : jumlahProduk) {
                        System.out.println(i+1+".");
                        System.out.println("Nama: "+listProduk.get(i).get(0));
                        System.out.println("Harga: Rp."+listProduk.get(i).get(1));
                        System.out.println("Kategori: "+listProduk.get(i).get(2));
                    }
                    do {
                        System.out.print("Masukan nomor produk untuk di update: ");
                        num = scan.nextLine();
                        num = num.trim();

                        if (onlyDigits(num) && !(num.isEmpty())) {
                            nomor = Integer.parseInt(num);
                        }
                    } while (!(onlyDigits(num)) || num.isEmpty() || !(jumlahProduk.contains(nomor-1)));
                }
            } while (!(produkFound));
            selectEditProduct(nomor, listProduk);
        }
    }
    static void selectEditProduct (int nomor, ArrayList<ArrayList<String>> listProduk) {
        Scanner scan = new Scanner(System.in);
        String namaProduk;
        String hargaProduk;
        String kategoriProduk;

        System.out.println(nomor+".");
        System.out.println("Nama: "+listProduk.get(nomor-1).get(0));
        System.out.println("Harga: Rp."+listProduk.get(nomor-1).get(1));
        System.out.println("Kategori: "+listProduk.get(nomor-1).get(2));

        do {
            System.out.print("Masukan nama produk baru [Not Nullable]: ");
            namaProduk = scan.nextLine();
            namaProduk = namaProduk.trim();

        } while (namaProduk.isEmpty());

        double d = 0;
        do {
            System.out.print("Masukan harga produk baru [Not Nullable|Is Digit|Harga > 0]: ");
            hargaProduk = scan.nextLine();
            hargaProduk = hargaProduk.trim();

            if (onlyDigits(hargaProduk) && !(hargaProduk.isEmpty())) {
                d = Double.parseDouble(hargaProduk);
            }
        } while (hargaProduk.isEmpty() || !(onlyDigits(hargaProduk)) || d < 0);

        do {
            System.out.print("Masukan kategori produk baru [Not Nullable]: ");
            kategoriProduk = scan.nextLine();
            kategoriProduk = kategoriProduk.trim();

        } while (kategoriProduk.isEmpty());

        listProduk.get(nomor-1).set(0, namaProduk);
        listProduk.get(nomor-1).set(1, hargaProduk);
        listProduk.get(nomor-1).set(2, kategoriProduk);

        System.out.println("Berhasil mengupdate produk dengan data:");
        System.out.println("Nama: "+namaProduk);
        System.out.println("Harga: Rp."+hargaProduk);
        System.out.println("Kategori: "+kategoriProduk);
    }
    static void deleteProduct (ArrayList<ArrayList<String>> listProduk) {
//        System.out.println("==============================");
        System.out.println("========Delete Product========");
        Scanner scan = new Scanner(System.in);
        String num;
        String temp;
        int nomor = 0;

        if (listProduk.isEmpty()) {
            System.out.println("Produk kosong");
            return;
        }
        showProductList(listProduk);
        do {
            System.out.print("Masukan nomor produk untuk di delete [1-"+listProduk.size()+"]: ");
            num = scan.nextLine();
            num = num.trim();

            if (onlyDigits(num) && !(num.isEmpty())) {
                nomor = Integer.parseInt(num);
            }

        } while (!(onlyDigits(num)) || num.isEmpty() || (nomor<=0 || nomor>listProduk.size()) );

        System.out.println(nomor+".");
        System.out.println("Nama: "+listProduk.get(nomor-1).get(0));
        System.out.println("Harga: Rp."+listProduk.get(nomor-1).get(1));
        System.out.println("Kategori: "+listProduk.get(nomor-1).get(2));

        do {
            System.out.print("Apakah anda yakin ingin mendelete produk ini? [y/n]: ");
            temp = scan.nextLine();
            temp = temp.trim();

        }while (!(temp.equals("y") || temp.equals("n")));

        if (temp.equals("n")) {
            return;
        }
        else {
            listProduk.remove(nomor-1);
        }
    }
    static void showProductList (ArrayList<ArrayList<String>> listProduk) {
        for (int i = 0; i < listProduk.size(); i++) {
            System.out.println(i+1+".");
            System.out.println("Nama: "+listProduk.get(i).get(0));
            System.out.println("Harga: Rp."+listProduk.get(i).get(1));
            System.out.println("Kategori: "+listProduk.get(i).get(2));
        }
    }
}