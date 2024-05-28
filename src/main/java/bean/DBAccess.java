package bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

	private static final String DB_DRIVER	= "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL 		= "jdbc:mysql://localhost/sogo?useUnicode=true&characterEncoding=utf8";
	private static final String DB_USER 	= "root";
	private static final String DB_PWD 		= "";

	private	Connection connection = null;

	public Connection getConnection(){
		return this.connection;
	}

	//DB接続（オーバーロード）
	public void connect(){
		connect(DB_DRIVER,DB_URL,DB_USER,DB_PWD);
	}

	//DB接続（オーバーロード）
	public void connect(String driver,String url,String user,String password){
		try{
			//JDBCドライバの登録
			Class.forName(driver);
			//データベースへの接続
			this.connection = DriverManager.getConnection(url,user,password);

		}catch(ClassNotFoundException e){
			e.printStackTrace();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	//DB切断
	public void disconnect(){
		try{
			if(this.connection != null) {
				//リソースの開放
			 	this.connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.connection = null;
		}
	}
}
