package de.biplus.simulator.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class helperConnectDB {
	private static final String DBUSER="***********";
	private static final String DBPASSWD="***********";
	private static final String DBHOST="***********";
	private static final String DBDATABASE="***********";
	private static Connection dbconnection;
	private Statement statement;
	protected ResultSet results;
	helperConnectDB()
	{
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			dbconnection=DriverManager.getConnection("jdbc:mariadb://"+DBHOST+":3306/" + DBDATABASE + "?serverTimezone=UTC",DBUSER,DBPASSWD);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*helperConnectDB()
	{
		
			try {
				dbconnection=DriverManager.getConnection("jdbc:mariadb://"+DBHOST+":3306/" + DBDATABASE + "?serverTimezone=UTC",DBUSER,DBPASSWD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}*/
	protected void setStatement(String statement)
	{
		try {
			this.statement=dbconnection.createStatement();
			this.results=this.statement.executeQuery(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected abstract void generateStatement();
	protected abstract ArrayList<?> getResults();

}
