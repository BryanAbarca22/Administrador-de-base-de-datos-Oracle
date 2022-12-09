package Modelo;

import Conexion.GestorBD;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class Tablespace extends Observable {

    private float tamanno;
    private String Nombre;
    private GestorBD gestor = null;

    public Tablespace() {
        this.gestor = new GestorBD();
    }

    public float getTamanno() {
        return tamanno;
    }

    public void setTamanno(float tamanno) {
        this.tamanno = tamanno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public GestorBD getGestor() {
        return gestor;
    }

    public void setGestor(GestorBD gestor) {
        this.gestor = gestor;
    }

    public void cerrarConexion() {
        this.gestor.cerrar();
        setChanged();
        notifyObservers("Se cerro la conexion");
    }

    public void agregarObservador(Observer observador) {
        addObserver(observador);
        setChanged();
        notifyObservers("Observador");
    }

    public void crearTS(String Nombre, String num) {
       
    
        try {
            String TS = "CREATE TABLESPACE " + Nombre + " DATAFILE 'C:\\DATAFILES\\" + Nombre + ".dbf' SIZE " + num + "M ONLINE;";
            Statement st = gestor.getConexion().createStatement();
            ResultSet rs = st.executeQuery(TS);
            
            setChanged();
            notifyObservers("CARGANDO DUEÑOS");
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        } finally {
            gestor.cerrar();
        }
    }

}
