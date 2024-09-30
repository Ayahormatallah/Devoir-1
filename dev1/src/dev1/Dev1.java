package dev1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import beans.*;

/**
 *
 * @author aya
 */
public class Dev1 {

     
    public static void main(String[] args) {
         try (ConnexionSQL mysqlconnexion = new ConnexionSQL("jdbc:mysql://localhost/dev1", "root", "")) {
	        
        ExoJDBC exo = new ExoJDBC(mysqlconnexion);
        //ExoJDBC1 exojdbc = new ExoJDBC1("alami", "Lundi", 5);
        
        exo.MAXnbscripts();
        exo.SUBnbscripts();
         exo.TotalScriptsParSemaine();
        exo.TotalScriptsParDev("ALAMI");
        exo.requeteLibre();
        
        } catch (Exception e) {
	            e.printStackTrace();
	        }
         
    }
}