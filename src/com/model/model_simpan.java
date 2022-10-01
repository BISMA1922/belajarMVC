/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_siswa;
import com.koneksi.koneksi;
import com.view.view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class model_simpan implements controller_siswa {
String jk;
    @Override
    public void Simpan(view siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()){
            jk = "Laki-laki";
        } else{
            jk = "Perempuan";
        }
        try{
    Connection con = koneksi.getcon();
    String sql = "Insert Into siswa_sabtu Values(?, ?, ?, ?)";
    PreparedStatement prepare = con.prepareStatement(sql);
    prepare.setString(1, siswa.txtNIS.getText());
    prepare.setString(2, siswa.txtNama.getText());
    prepare.setString(3, jk);
    prepare.setString(4, (String) siswa.cbJurusan.getSelectedItem());
    prepare.executeUpdate();
    JOptionPane.showMessageDialog(null, "Data Berhasil diSimpan");
    prepare.close();
    Baru(siswa);
}catch (Exception e) {
    System.out.println(e);
}finally {
    Tampil(siswa);
    
}

    }
   
    @Override
    public void Ubah(view siswa) throws SQLException {
       if (siswa.rbLaki.isSelected()){
           jk = "Laki-Laki";
       } else {
           jk = "Perempuan";
       }
       try {
           Connection con = koneksi.getcon();
           String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?, "
                   + "jurusan=? WHERE NIS=?";
           PreparedStatement prepare = con.prepareStatement(sql);
           prepare.setString(4, siswa.txtNIS.getText());
           prepare.setString(1, siswa.txtNama.getText());
           prepare.setString(2, jk);
           prepare.setString(3, (String) siswa.cbJurusan.getSelectedItem());
           prepare.executeUpdate();
           JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
           prepare.close();
       } catch (Exception e) {
           System.out.println(e); 
       } finally {
               Tampil(siswa);
//               siswa.setLebarKolom();
               Baru(siswa);
               }
       }
           
    

    @Override
    public void Hapus(view siswa) throws SQLException {
       try {
           Connection con = koneksi.getcon();
           String sql = "DELETE FROM siswa WHERE NIS =?";
           PreparedStatement prepare = con.prepareStatement(sql);
           prepare.setString(1, siswa.txtNIS.getText());
           prepare.executeUpdate();
           JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
           prepare.close();
       } catch (Exception e){
           System.out.println(e);
       }finally {
           Tampil(siswa);
//           siswa.setLebarKolom();
           Baru(siswa);
       }
    }

    @Override
    public void Tampil(view siswa) throws SQLException {
        siswa.tblmodel.getDataVector().removeAllElements();
        siswa.tblmodel.fireTableDataChanged();
        try {
            Connection con = koneksi.getcon();
            Statement stt = con.createStatement();
            //Querry menampilkan semua data pada tabel siswa
            //dengan urutan NIS dari kecil ke besar
            String sql = "SELECT * FROM siswa ORDER BY NIS ASC";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                siswa.tblmodel.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    @Override
    public void Baru(view siswa) throws SQLException {
       siswa.txtNIS.setText("");
       siswa.txtNama.setText("");
       siswa.rbLaki.setSelected(true);
       siswa.cbJurusan.setSelectedIndex(0);
    }

    @Override
    public void KlikTabel(view siswa) throws SQLException {
        try {
            int pilih = siswa.tabel.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            siswa.txtNIS.setText(siswa.tblmodel.getValueAt(pilih, 0).toString());
            siswa.txtNama.setText(siswa.tblmodel.getValueAt(pilih, 1).toString());
            siswa.cbJurusan.setSelectedItem(siswa.tblmodel.getValueAt(pilih, 3).toString());
            jk = String.valueOf(siswa.tblmodel.getValueAt(pilih, 2));
        } catch (Exception e) {    
        }
        //memberi nilai jk pada radio button
        if (siswa.rbLaki.getText().equals(jk)) {
            siswa.rbLaki.setSelected(true);
        } else {
            siswa.rbPerempuan.setSelected(true);
        }
    }
    
}
