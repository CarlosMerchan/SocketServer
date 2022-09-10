package conexionMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMysql {
	 	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/clientes?ussSll=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "1234";//asignar su contraseña
	
	public ConexionMysql() {
		
	}
	
		public  Connection getConexion() throws SQLException {
	        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	    }

	    public  void close(Connection cox) throws SQLException {
	        cox.close();
	    }

	    public  void close(Statement stt) throws SQLException {
	        stt.close();
	    }

	    public  void close(PreparedStatement stt) throws SQLException {
	        stt.close();
	    }

	    public  void close(ResultSet rs) throws SQLException {
	        rs.close();
	    }
	
	
	
}