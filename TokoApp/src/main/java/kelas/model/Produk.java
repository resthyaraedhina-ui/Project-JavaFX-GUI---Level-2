package kelas.model;

public class Produk {

    private final String kode;
    private final String nama;
    private final String kategori;
    private final long harga;
    private final String foto; 

    public Produk(String kode, String nama, String kategori, long harga, String foto) {
        if (kode == null || kode.isBlank()) {
            throw new IllegalArgumentException("Kode produk tidak boleh kosong.");
        }
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama produk wajib diisi.");
        }
        if (!"Makanan".equals(kategori) && !"Minuman".equals(kategori)) {
            throw new IllegalArgumentException("Kategori harus Makanan atau Minuman.");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari nol.");
        }
        this.kode = kode;
        this.nama = nama.trim();
        this.kategori = kategori;
        this.harga = harga;
        this.foto = foto;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public long getHarga() {
        return harga;
    }

    public String getFoto() {
        return foto;
    }
}