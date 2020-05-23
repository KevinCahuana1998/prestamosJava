/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class CtlPrestamo {
    public ArrayList<Prestamo> ListEmpleados() {
        ArrayList<Prestamo> empleado = new ArrayList();
            
        try {
            int m=0;
            Connection cnx = Model.Conexion.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGO,PRESTAMO,HORA,FECHA,MORA " + "          FROM CEIISS ORDER BY 1 ");
            while (rs.next()) {
                m=m+1;
                Prestamo empl=new Prestamo("","","","","");
                empl.setCodigo(rs.getString("CODIGO"));
                empl.setPrestamo(rs.getString("PRESTAMO"));
                empl.setFecha(rs.getString("HORA"));
                empl.setFechaTotal(rs.getString("FECHA"));
                empl.setMora(rs.getString("MORA"));
                empleado.add(empl);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.err.println("Error en Listado cliente db");
        }
        return empleado;
    }
    public ArrayList<Prestamo> ListarNombres() {
        ArrayList<Prestamo> empleado = new ArrayList();
            
        try {
            int m=0;
            Connection cnx = Model.Conexion.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT CODIGO,NOMBRES " + "          FROM ALUMFIIS ORDER BY 1 ");
            while (rs.next()) {
                m=m+1;
                Prestamo empl=new Prestamo("","","","","");
                empl.setCodigo(rs.getString("CODIGO"));
                empl.setPrestamo(rs.getString("PRESTAMO"));
                empl.setFecha(rs.getString("HORA"));
                empl.setFechaTotal(rs.getString("FECHA"));
                empl.setMora(rs.getString("MORA"));
                empleado.add(empl);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.err.println("Error en Listado cliente db");
        }
        return empleado;
    }
    
    public ArrayList<Prestamo> ListEmpleados2(int ctr, String prm) {
        ArrayList<Prestamo> empleado = new ArrayList();
        String SQL="";
        if (ctr==0){
            SQL ="SELECT * FROM CEIISS WHERE CODIGO like'" + prm+"%'";
                    
        }
        if (ctr==1){
            SQL =("SELECT * FROM CEIISS WHERE PRESTAMO like'" + prm+"%'");
        }

        try {
            
            Connection cnx = Model.Conexion.getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Prestamo empl=new Prestamo("","","","","");
                empl.setCodigo(rs.getString("CODIGO"));
                empl.setPrestamo(rs.getString("PRESTAMO"));
                empl.setFecha(rs.getString("HORA"));
                empl.setFechaTotal(rs.getString("FECHA"));
                empl.setMora(rs.getString("MORA"));
                empleado.add(empl);
            }
            rs.close();
            st.close();
            
        } catch (SQLException ex) {
            System.err.println("Error en Listado cliente db");
        }
        return empleado;
    }
    
    public void insertarEmpleados(Prestamo empleado) {
        try {
            Connection cnx = Model.Conexion.getConnection();
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO CEIISS(CODIGO,PRESTAMO,HORA,FECHA,MORA) " + "        VALUES(?,?,?,?,?)     ");
            pst.setString(1, empleado.getCodigo());
            pst.setString(2, empleado.getPrestamo());
            pst.setString(3, empleado.getFecha());
            pst.setString(4, empleado.getFechaTotal());
            pst.setString(5, empleado.getMora());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Insert GAA");
        }
    }
    
}
