package db;


//region [IMPORTS]
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//endregion



public class DB {
	
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		if(conn == null) {
			try{
				
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			
			}
			catch (SQLException e){
				throw new DbException(e.getMessage());
			}
			
		}
		return conn;
	}
	
	public static void closeConection() {
		
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		// Como o ficheiro está dentro da raiz do projeto, pode ser declarado assim
		try (FileInputStream fs = new FileInputStream("db.properties")){
			
			Properties props = new Properties();
			// função "loads" faz  a leitura do arquivo properties apontado para o inputStream fs e guarda dentro do objeto "props"
			props.load(fs);
			
			return props;
			
		}
		catch (IOException e) {
			
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
			st.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
			rs.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
