/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

/**
 *
 * @author nghia
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    
    String url = "jdbc:derby:Database;create=true"; 
    String dbusername = "nghiapham";  
    String dbpassword = "21137774";   
    Connection conn = null;
    
    
    public void setup()
    {
       try{            
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
           
            String tableName = "PlayerInfo";

            if (!checkTableExisting(tableName)) {
                
                statement.executeUpdate("CREATE TABLE " + tableName + " (username VARCHAR(12), password VARCHAR(12), balance INT)");
            }   
            
            //statement.executeUpdate("INSERT INTO PlayerInfo "+ "VALUES('testPlayer1', 'demo', 0)");
            
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
        
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
    
    public void quitGame(int score, String username) {
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE PlayerInfo SET score=" + score + " WHERE username='" + username + "'");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public PlayerData checkInfo(String username, String password)
    {
        PlayerData pd = new PlayerData();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username, password, balance FROM PlayerInfo " + "WHERE username = '" + username + "'");
            if(rs.next())
            {
                String p = rs.getString("password");
                System.out.println("found user with password is: " + p);
                pd.existFlag = true;
                
                if(p.compareTo(password) == 0)
                {
                    pd.balance = rs.getInt("balance");
                    pd.loginFlag = true;
                    System.out.println("Log in success!");
                }
                else
                {
                    pd.loginFlag = false;
                }
            }
            
            else
            {
                pd.loginFlag = false;
                pd.existFlag = false;
                System.out.println("No such user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pd;
    }
    
    public PlayerData addNewPlayer(String username, String password)
    {
        PlayerData pd = new PlayerData();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT username, password, balance FROM PlayerInfo " + "WHERE username = '" + username + "'");
            if(rs.next())
            {
                System.out.println("This username has been taken!");
                pd.loginFlag = false;
                pd.existFlag = true;
            }
            
            else
            {
                System.out.println("Adding new player success!");
                statement.executeUpdate("INSERT INTO PlayerInfo " + "VALUES('" + username + "', '" + password + "', 100)");
                pd.balance = 100; //new player will have 100 credit initially.
                pd.loginFlag = true;               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pd;
    }
}
