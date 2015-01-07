package afr.spelleritustest.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.appengine.api.utils.SystemProperty;


public class MySQLAccess
{
	private static final Logger LOGGER = Logger.getLogger(MySQLAccess.class.getName());
	private static Connection connect;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	public int flag = 0;

	public MySQLAccess()
	{
	}

	public static Connection getConn()
	{

		LOGGER.entering(" Entering getconn()" , " ");
		connect = null;
		String url = "";
		String db = "";
		String driver = "";
		String user = "";
		String pass = "";
		String sysprop = SystemProperty.environment.value().name();
		LOGGER.log(Level.INFO,"SystemProperty.Environment.value(): " + sysprop + " if this is not the production value you are getting the risk of getting the localhost db");
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
		{
			url = "jdbc:google:mysql://subtle-reserve-547:mysql-instance/";
			driver = "com.mysql.jdbc.GoogleDriver";
			db = "mysql";
			user = "root";
			LOGGER.log(Level.WARNING, "		Getting connection with cloud db");
		} else
		{
			url = "jdbc:mysql://localhost/";
			driver = "com.mysql.jdbc.Driver";
			db = "spelleritus";
			user = "root";
			pass = "Osiris74";
			LOGGER.log(Level.SEVERE, "		Getting connection with localhost db");

		}
		
		try
		{
			Class.forName(driver).newInstance();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
			LOGGER.log(Level.SEVERE , "		InstantiationException in getting new jdbc driver instance");
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
			LOGGER.log(Level.SEVERE , "		IllegalAccessException in getting new jdbc driver instance");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "		ClassNotFoundException in getting new jdbc driver instance");
		}
		try
		{
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)

				connect = DriverManager.getConnection(url + db + "?user=" + user);
			else
			{
				connect = DriverManager.getConnection(url + db, user, pass);
			}
		} catch (SQLException e)
		{
			LOGGER.log(Level.SEVERE, "		SQL exception, no connection possible");
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
		}
	return connect;
	}

	
	public void closeAll()
	{
		try
		{
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	

}