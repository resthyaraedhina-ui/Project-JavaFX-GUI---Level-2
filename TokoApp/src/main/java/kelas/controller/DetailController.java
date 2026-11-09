package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kelas.Navigator;
import kelas.model.Produk;

public class DetailController {

    @FXML
    private Label kodeLabel;
    @FXML
    private Label namaLabel;
    @FXML
    private Label kategoriLabel;
    @FXML
    private Label hargaLabel;
    @FXML
    private ImageView fotoBesar;
    @FXML
    private Label pesanLabel;

    private Navigator navigator;

    public void setData(Navigator navigator, Produk produk) {
        this.navigator = navigator;
        
        if (produk != null) {
            if (kodeLabel != null) kodeLabel.setText(produk.getKode());
            if (namaLabel != null) namaLabel.setText(produk.getNama());
            if (kategoriLabel != null) kategoriLabel.setText(produk.getKategori());
            
            if (hargaLabel != null) {
                NumberFormat rupiah = NumberFormat.getCurrencyInstance(
                        Locale.forLanguageTag("id-ID"));
                hargaLabel.setText(rupiah.format(produk.getHarga()));
            }

            if (fotoBesar != null && produk.getFoto() != null && !produk.getFoto().isEmpty()) {
                try {
                    String namaFoto = produk.getFoto().trim();
                    Image img = null;
                    
                    if (namaFoto.contains(":") || namaFoto.startsWith("C:") || namaFoto.startsWith("file:")) {
                        String pathFile = namaFoto.startsWith("file:") ? namaFoto : "file:" + namaFoto;
                        img = new Image(pathFile);
                    } else {
                        var stream = getClass().getResourceAsStream("/kelas/images/" + namaFoto);
                        if (stream != null) {
                            img = new Image(stream);
                        }
                    }
                    
                    if (img != null && !img.isError()) {
                        fotoBesar.setImage(img);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            if (pesanLabel != null) {
                pesanLabel.setText("Daftar gagal dibuka.");
            }
            e.printStackTrace();
        }
    }
}