package com;

import com.DataBaseManager;
import java.sql.SQLException;
import java.util.Random;

public class DataBaseTester {

    private static final String[] NAMES = {
        "Ahmad Maulana",
        "Siti Aisyah",
        "Budi Santoso",
        "Dewi Lestari",
        "Joko Susilo",
        "Ani Fitriani",
        "Fajar Pratama",
        "Nia Putri",
        "Adi Nugroho",
        "Rini Cahyani",
        "Andi Saputra",
        "Sari Indah",
        "Eko Prasetyo",
        "Ayu Wulandari",
        "Rudi Hermawan",
        "Maya Sari",
        "Dodi Firmansyah",
        "Nisa Rahmawati",
        "Agus Wijaya",
        "Dina Permata",
        "Rizki Ramadhan",
        "Ratna Dewi",
        "Bambang Susanto",
        "Lina Marwah",
        "Yoga Pratama",
        "Fitriani Setiawan",
        "Arya Wibowo",
        "Nina Kusuma",
        "Reza Firdaus",
        "Dini Septiani",
        "Rizal Rahman",
        "Yuni Kartika",
        "Wahyu Hidayat",
        "Desi Wahyuni",
        "Dito Pratama",
        "Neni Fitri",
        "Eko Saputro",
        "Retno Wulandari",
        "Fahmi Putra",
        "Siska Ayu",
        "Dani Firmansyah",
        "Tri Utami",
        "Andika Surya",
        "Mutiara Sari",
        "Hadi Santoso",
        "Wati Rahayu",
        "Denny Kusuma",
        "Santi Nurhaliza",
        "Galang Pratama",
        "Rina Anggraini"
    };

    private static final String[] CITIES = {
        "Jakarta", "Surabaya", "Bandung", "Medan", "Semarang", "Makassar", "Palembang", "Tangerang", "Depok", "Batam",
        "Bekasi", "Padang", "Bogor", "Malang", "Pekanbaru", "Bandar Lampung", "Denpasar", "Surakarta", "Banjarmasin", "Jambi",
        "Yogyakarta", "Pontianak", "Manado", "Balikpapan", "Ambon", "Samarinda", "Palu", "Cirebon", "Purwokerto", "Tasikmalaya",
        "Serang", "Banda Aceh", "Tegal", "Kupang", "Binjai", "Pematangsiantar", "Palangkaraya", "Probolinggo", "Pasuruan", "Salatiga",
        "Batam", "Lubuklinggau", "Cimahi", "Mojokerto", "Magelang", "Batu", "Tarakan", "Singkawang", "Prabumulih", "Bangka",
        "Padangsidimpuan"
    };

    public static void main(String[] args) {
        Random random = new Random();
        int targetData = 50;
        int minTimes = 1;
        int maxTimes = 20;

        try {
            for (int i = 0; i < targetData; i++) {
                String nama = getRandomName();
                String telp = "08" + getRandomTenDigitNumber();
                String kota = getRandomCity();
                String belanja = getRandomBelanja();
                String poin = "0";
                String member = "0";
                String total = "0";
                DataBaseManager orang = new DataBaseManager(nama, kota, poin, member, telp, belanja, total);
                int numTimes = random.nextInt(maxTimes - minTimes + 1) + minTimes;
                for (int j = 0; j < numTimes; j++) {
                    DataBaseManager.Add(orang);
                    System.out.println("Data berhasil ditambahkan: " + nama + ", " + telp + ", " + kota);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomName() {
        Random random = new Random();
        int index = random.nextInt(NAMES.length);
        return NAMES[index];
    }

    private static String getRandomTenDigitNumber() {
        Random random = new Random();
        long number = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(number);
    }

    private static String getRandomCity() {
        Random random = new Random();
        int index = random.nextInt(CITIES.length);
        return CITIES[index];
    }

    private static String getRandomBelanja() {
        Random random = new Random();
        double belanja = random.nextDouble() * 100000;
        return String.valueOf(belanja);
    }
}
