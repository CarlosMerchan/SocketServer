package conexionMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import entidad.Cliente;

public class ClienteDao {
	private static final String SQL_SELECT = "SELECT * FROM  clientes.cliente";
    private static final String SQL_INSERT = "INSERT INTO clientes.cliente (nombres, apellidos, saldo) VALUES (?,?,?);";
    private static final String SQL_UPDATE = "UPDATE clientes.cliente SET nombres = ?,apellidos = ?, saldo= ? WHERE id_cedula= ?";
    private static final String SQL_DELETE = "DELETE FROM clientes.cliente WHERE id_cedula = ?";
    private static final String SQL_FOUND ="SELECT * FROM clientes.cliente where id_cedula = ?";
    private ConexionMysql conMysl = new ConexionMysql();
    
    public List<Cliente> seleccionar() {
    	Connection conexion = null;    	
        PreparedStatement stt = null;
        ResultSet rs = null;
        Cliente cliente;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conexion = conMysl.getConexion();
            stt = conexion.prepareStatement(SQL_SELECT);
            rs = stt.executeQuery();
            while (rs.next()) {                
                cliente = new Cliente(rs.getInt("id_cedula"),rs.getString("nombres"),rs.getString("apellidos"),rs.getDouble("saldo"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
               conMysl.close(rs);
               conMysl.close(stt);
               conMysl.close(conexion);
            } catch (SQLException ex) {
            	ex.printStackTrace(System.out);
            }
        }

        return clientes;
    }
    
    public int insertar(Cliente cliente) {
        Connection conexion = null;
        PreparedStatement stt = null;
        int registro = 0;

        try {
        	conexion = conMysl.getConexion();
            stt = conexion.prepareStatement(SQL_INSERT);
            stt.setString(1, cliente.getNombres());
            stt.setString(2, cliente.getApellidos());
            stt.setDouble(3, cliente.getSaldo());            
            registro = stt.executeUpdate();//este metodo se usa cuando se actulizan datos o se modifica algo en la base de datos, puede ejecutar sentencias update,delete o insert.
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            
            try {
                conMysl.close(stt);
                conMysl.close(conexion);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registro;
    }
    
    public int actulizar(Cliente cliente){
        int registro=0;
        Connection conexion = null;
        PreparedStatement stt = null;
        
        try {
            conexion = conMysl.getConexion();
            stt = conexion.prepareStatement(SQL_UPDATE);            
            stt.setString(1, cliente.getNombres());
            stt.setString(2, cliente.getApellidos());
            stt.setDouble(3, cliente.getSaldo());
            stt.setInt(4, cliente.getIdCedula());           
            registro = stt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return registro;
    }
    
    public int eliminar(Cliente cliente){
        int registro = 0;
        Connection con = null;
        PreparedStatement stt = null;
        
        try {
            con = conMysl.getConexion();
            stt = con.prepareStatement(SQL_DELETE);
            stt.setInt(1, cliente.getIdCedula());
            registro = stt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return registro;
    }
    
    public Cliente getCliente(int cedula) {
    	Cliente cliente = null;
    	Connection conexion = null;    	
        PreparedStatement stt = null;
        ResultSet rs = null;
        
        try {
            conexion = conMysl.getConexion();
            stt = conexion.prepareStatement(SQL_FOUND);
            stt.setInt(1, cedula);
            rs = stt.executeQuery();
            while (rs.next()) {                
                cliente = new Cliente(rs.getInt("id_cedula"),rs.getString("nombres"),rs.getString("apellidos"),rs.getDouble("saldo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
               conMysl.close(rs);
               conMysl.close(stt);
               conMysl.close(conexion);
            } catch (SQLException ex) {
            	ex.printStackTrace(System.out);
            }
        }
    	
    	return cliente;
    }

}
