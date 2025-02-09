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
            System.out.println("==============================");

            do {
                System.out.print("Pilih Menu[1-4]: ");
                num = scan.nextLine();
                num = num.trim();

                if (onlyDigits(num) && !(num.isEmpty())) {
                    menu = Integer.parseInt(num);
                }

            } while (!(onlyDigits(num)) || num.isEmpty() || (menu<=0 || menu>4) );

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

        int len = listProduk.size();
        listProduk.add(new ArrayList<String>());
        listProduk.get(len).add(0, namaProduk);
        listProduk.get(len).add(1, hargaProduk);

        System.out.println("Berhasil memasukan produk dengan data:");
        System.out.println("Nama: "+namaProduk);
        System.out.println("Harga: Rp."+hargaProduk);
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
        for (int i = 0; i < listProduk.size(); i++) {
            System.out.println(i+1+".");
            System.out.println("Nama: "+listProduk.get(i).get(0));
            System.out.println("Harga: Rp."+listProduk.get(i).get(1));
        }
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
                System.out.print("Apakah sorting berdasarkan nama atau harga? [nama/harga]: ");
                temp = scan.nextLine();
                temp = temp.trim();

            }while (!(temp.equals("nama") || temp.equals("harga")));
            if (temp.equals("nama")) {
                Collections.sort(listProduk, new SortbyName());
            } else if (temp.equals("harga")) {
                Collections.sort(listProduk, new SortbyPrice());
            }
            System.out.println("Sorting berhasil!");
        }
    }
    static void updateProduct (ArrayList<ArrayList<String>> listProduk) {
//        System.out.println("==============================");
        System.out.println("========Update Product========");
        Scanner scan = new Scanner(System.in);
        String num;
        String namaProduk;
        String hargaProduk;
        int nomor = 0;

        if (listProduk.isEmpty()) {
            System.out.println("Produk kosong");
            return;
        }
        System.out.println("Jumlah produk: "+listProduk.size());
        do {
            System.out.print("Masukan nomor produk untuk di update [1-"+listProduk.size()+"]: ");
            num = scan.nextLine();
            num = num.trim();

            if (onlyDigits(num) && !(num.isEmpty())) {
                nomor = Integer.parseInt(num);
            }

        } while (!(onlyDigits(num)) || num.isEmpty() || (nomor<=0 || nomor>listProduk.size()) );

        System.out.println(nomor+".");
        System.out.println("Nama: "+listProduk.get(nomor-1).get(0));
        System.out.println("Harga: Rp."+listProduk.get(nomor-1).get(1));

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

        listProduk.get(nomor-1).set(0, namaProduk);
        listProduk.get(nomor-1).set(1, hargaProduk);

        System.out.println("Berhasil mengupdate produk dengan data:");
        System.out.println("Nama: "+namaProduk);
        System.out.println("Harga: Rp."+hargaProduk);
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
        System.out.println("Jumlah produk: "+listProduk.size());
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
}