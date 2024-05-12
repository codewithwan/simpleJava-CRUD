package com;

import com.DataBaseManager;
import java.sql.SQLException;
import java.util.Random;

public class DataBaseTester {

    private static final String[] NAMES = {
        "Michael", "Christopher", "Jessica", "Matthew", "Ashley", "Jennifer", "Joshua", "Amanda", "Daniel", "David",
        "James", "Robert", "John", "Joseph", "Andrew", "Ryan", "Brandon", "Jason", "Justin", "Sarah",
        "William", "Jonathan", "Stephanie", "Brian", "Nicole", "Nicholas", "Anthony", "Heather", "Eric", "Elizabeth",
        "Adam", "Megan", "Kevin", "Steven", "Thomas", "Timothy", "Christina", "Kyle", "Rachel", "Laura",
        "Lauren", "Amber", "Brittany", "Danielle", "Emily", "Melissa", "Rebecca", "Kimberly", "Mark", "Jeffrey",
        "Amy", "Crystal", "Michelle", "Jeremy", "Benjamin", "Samantha", "Aaron", "Kelly", "Jamie", "Patrick",
        "Sean", "Nathan", "Sara", "Brandi", "Zachary", "Katherine", "Andrea", "Gregory", "Scott", "Erica",
        "Mary", "Travis", "Lisa", "Kenneth", "Lindsay", "Kristen", "Jose", "Kristin", "Erin", "Jesse",
        "Paul", "Angela", "Shannon", "Tiffany", "Cody", "Stephen", "Heidi", "Chelsea", "Allison", "Crystal",
        "Cassandra", "Tara", "Kristina", "Anna", "Vanessa", "Jared", "Courtney", "Bryan", "Katie", "Lindsey",
        "Jordan", "Alicia", "Peter", "Catherine", "Caitlin", "Hannah", "Julie", "April", "Derek", "Christine"
    };

    private static final String[] CITIES = {
        "Jakarta", "Surabaya", "Bandung", "Medan", "Semarang", "Makassar", "Palembang", "Tangerang", "Depok", "Batam",
        "Bekasi", "Padang", "Bogor", "Malang", "Pekanbaru", "Bandar Lampung", "Denpasar", "Surakarta", "Banjarmasin", "Jambi",
        "Yogyakarta", "Pontianak", "Manado", "Balikpapan", "Ambon", "Samarinda", "Palu", "Cirebon", "Purwokerto", "Tasikmalaya",
        "Serang", "Banda Aceh", "Tegal", "Kupang", "Binjai", "Pematangsiantar", "Palangkaraya", "Probolinggo", "Pasuruan", "Salatiga",
        "Batam", "Lubuklinggau", "Cimahi", "Mojokerto", "Magelang", "Batu", "Tarakan", "Singkawang", "Prabumulih", "Bangka",
        "Padangsidimpuan", "Metro", "Tanjungpinang", "Bontang", "Kendari", "Parepare", "Lhokseumawe", "Ternate", "Bitung", "Palopo",
        "Bukittinggi", "Pagar Alam", "Tomohon", "Ambarawa", "Tebing Tinggi", "Tidore", "Palopo", "Pangkal Pinang", "Sukabumi", "Banda Aceh",
        "Dumai", "Madiun", "Bukittinggi", "Kendari", "Tual", "Sorong", "Merauke", "Cilacap", "Bengkulu", "Sawahlunto",
        "Pare-pare", "Pematangsiantar", "Sijunjung", "Tarakan", "Waingapu", "Blitar", "Kepahiang", "Padangpanjang", "Padang Sidempuan", "Pangkalpinang",
        "Parepare", "Pemalang", "Pematang Siantar", "Sungailiat", "Tabanan", "Tangerang Selatan", "Tanjungbalai", "Tanjungpinang", "Tapaktuan", "Tarakan",
        "Tasikmalaya", "Tebing Tinggi", "Tegal", "Tidore Kepulauan", "Tomohon", "Tual", "Banyuwangi", "Barru", "Takalar", "Lamongan",
        "Blitar", "Lhokseumawe", "Sukabumi", "Sungai Penuh", "Sungailiat", "Palopo", "Pangkajene", "Pasuruan", "Lamongan", "Langsa",
        "Biak", "Muna", "Buton", "Kefamenanu", "Rote Ndao", "Larantuka", "Ende", "Bima", "Sumbawa Besar", "Dompu"
    };

    public static void main(String[] args) {
        int numData = 200;

        try {
            for (int i = 0; i < numData; i++) {
                String nama = getRandomName();
                String telp = "08" + getRandomTenDigitNumber();
                String kota = getRandomCity();
                DataBaseManager.Add(new DataBaseManager(nama, kota, telp));
                System.out.println("Data berhasil ditambahkan: " + nama + ", " + telp + ", " + kota);
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
}
