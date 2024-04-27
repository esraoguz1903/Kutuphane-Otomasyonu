package librarymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class DatabaseHelper {

    Connection connection = null;
    PreparedStatement preparedstatement = null;
    ResultSet resultSet;
    Statement stmt;

    private String userName = "root";
    private String password = "Esra.123";
    private String url = "jdbc:mysql://localhost:3306/kütüphanetakipotomasyonu";

    public DatabaseHelper() {
        try {
            connection = getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(url, userName, password);
    }

    public void showErrorMessage(SQLException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Error code : " + e.getErrorCode());
    }

    public void insertUser(String isim, String soyisim, String kullanici_adi, String sifre, String cinsiyet) throws SQLException {

        try {
            //Bağlantı burda kuruluyor

            if (userExist(kullanici_adi) == false) {
                String sql = "INSERT INTO users (isim, soyisim, kullanici_adi, sifre, cinsiyet) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, isim);
                preparedStatement.setString(2, soyisim);
                preparedStatement.setString(3, kullanici_adi);
                preparedStatement.setString(4, sifre);
                preparedStatement.setString(5, cinsiyet);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Yeni kullanıcı başarıyla eklendi.");
                } else {
                    System.out.println("Kullanıcı eklenirken bir hata oluştu.");
                }
            } else {
                System.out.println("Kullanıcı mevcut"); //KULLANICI KAYITLI MESAJI
            }

        } catch (SQLException e) {
            showErrorMessage(e);
        } finally {
            if (preparedstatement != null) {
                preparedstatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public boolean userExist(String kullanici_adi) {
        String sql = "SELECT COUNT(*) FROM users WHERE kullanici_adi = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, kullanici_adi);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println((count > 0) + "user exist");
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean userIDExist(int id) {
        String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println((count > 0) + "user exist");
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void insertBook(String kitapAdi, String yazarAdi, int sayfaSayisi, String turu, int kitapAdeti) throws SQLException {

        try {
            //Bağlantı burda kuruluyor

            if (userExist(kitapAdi) == false) {
                String sql = "INSERT INTO book (kitapAdi, yazarAdi, sayfaSayisi, turu, kitapAdeti) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, kitapAdi);
                preparedStatement.setString(2, yazarAdi);
                preparedStatement.setInt(3, sayfaSayisi);
                preparedStatement.setString(4, turu);
                preparedStatement.setInt(5, kitapAdeti);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Yeni kitap başarıyla eklendi.");
                } else {
                    System.out.println("kitap eklenirken bir hata oluştu.");
                }
            } else {
                System.out.println("Kitap mevcut"); //KULLANICI KAYITLI MESAJI
            }

        } catch (SQLException e) {
            showErrorMessage(e);
        } finally {
            if (preparedstatement != null) {
                preparedstatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    public boolean bookExist(String kitapAdi) {
        String sql = "SELECT COUNT(*) FROM book WHERE kitapAdi = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, kitapAdi);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println(count > 0);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean bookIDExist(int id) {
        String sql = "SELECT COUNT(*) FROM book WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println(count > 0);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void updateBookCount(int id) {
        String sql = "UPDATE book SET kitapAdeti = kitapAdeti - 1 WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Kitap adeti güncellendi.");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void increaseBookCount(int id) {
        String sql = "UPDATE book SET kitapAdeti = kitapAdeti + 1 WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Kitap adeti güncellendi.");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(String isim, String soyisim, String kullanici_adi, String sifre, String cinsiyet) {
        if (userExist(kullanici_adi)) {
            String sql = "UPDATE users SET isim = (?), soyisim = (?), kullanici_adi = (?), sifre = (?), cinsiyet = (?) WHERE kullanici_adi = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Güncellenecek değerleri belirleyin ve sorguya parametre olarak ekleyin
                stmt.setString(1, isim);
                stmt.setString(2, soyisim);
                stmt.setString(3, kullanici_adi);
                stmt.setString(4, sifre);
                stmt.setString(5, cinsiyet);
                stmt.setString(6, kullanici_adi);
                stmt.executeUpdate();

                System.out.println("Kullanıcı güncellendi.");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }

    }

    public void deleteUser(String kullanici_adi) {
        if (userExist(kullanici_adi)) {
            String sql = "DELETE FROM users WHERE kullanici_adi = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Kullanıcı adını parametre olarak ekleyin
                stmt.setString(1, kullanici_adi);

                // SQL sorgusunu çalıştırın
                int rowsAffected = stmt.executeUpdate();

                // Silinen satır sayısını kontrol edin
                if (rowsAffected > 0) {
                    System.out.println("Kullanıcı başarıyla silindi.");
                } else {
                    System.out.println("Kullanıcı silinemedi.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }
    
    public void updateBook(String kitapAdi, String yazarAdi, String sayfaSayisi, String turu, int kitapAdeti) {
        if (bookExist(kitapAdi)) {
            String sql = "UPDATE book SET kitapAdi = (?), yazarAdi = (?), sayfaSayisi = (?), turu = (?), kitapAdeti = (?) WHERE kitapAdi = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Güncellenecek değerleri belirleyin ve sorguya parametre olarak ekleyin
                stmt.setString(1, kitapAdi);
                stmt.setString(2, yazarAdi);
                stmt.setString(3, sayfaSayisi);
                stmt.setString(4, turu);
                stmt.setInt(5, kitapAdeti);
                stmt.setString(6, kitapAdi);
                stmt.executeUpdate();

                System.out.println("Kitap güncellendi.");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }

    }
    
    public void deleteBook(String kitap_ismi) {
        if (bookExist(kitap_ismi)) {
            String sql = "DELETE FROM book WHERE kitapAdi = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Kullanıcı adını parametre olarak ekleyin
                stmt.setString(1, kitap_ismi);

                // SQL sorgusunu çalıştırın
                int rowsAffected = stmt.executeUpdate();

                // Silinen satır sayısını kontrol edin
                if (rowsAffected > 0) {
                    System.out.println("Kitap başarıyla silindi.");
                } else {
                    System.out.println("Kitap silinemedi.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

}
