package kelas.controller;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import kelas.Navigator;
import kelas.data.DataProduk;

public class FormController {

    @FXML private TextField namaField;
    @FXML private ComboBox<String> kategoriBox;
    @FXML private TextField hargaField;
    @FXML private Label pesanLabel;
    
    // Komponen untuk Foto
    @FXML private Label namaFileLabel;
    @FXML private ImageView fotoPreview;
    private File fileFotoPilihan;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    private void initialize() {
        if (kategoriBox != null) {
            kategoriBox.getItems().addAll("Makanan", "Minuman");
            kategoriBox.setValue("Makanan");
        }
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
    }

    @FXML
    private void pilihFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar Produk");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        fileFotoPilihan = fileChooser.showOpenDialog(namaField.getScene().getWindow());
        
        if (fileFotoPilihan != null) {
            if (namaFileLabel != null) {
                namaFileLabel.setText(fileFotoPilihan.getName());
            }
            if (fotoPreview != null) {
                Image image = new Image(fileFotoPilihan.toURI().toString());
                fotoPreview.setImage(image);
            }
        }
    }

    @FXML
    private void simpan() {
        try {
            long harga = Long.parseLong(hargaField.getText().trim());
            String pathFoto = (fileFotoPilihan != null) ? fileFotoPilihan.getAbsolutePath() : "";
            
            data.tambah(namaField.getText(), kategoriBox.getValue(), harga, pathFoto);
            navigator.bukaDaftar();
        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus berupa angka.");
        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka halaman daftar.");
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka halaman daftar.");
        }
    }
}