
package beans;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.Scanner;
import java.sql.ResultSetMetaData;
/**
 *
 * @author aya
 */
public class ExoJDBC {
    
        Statement stmt;
	ConnexionSQL con;

	    public ExoJDBC(ConnexionSQL con) {
	        this.con = con;
	        stmt = con.getStmt();
	    }
            
   public void MAXnbscripts() {
	try {
	ResultSet result = stmt.executeQuery("SELECT MAX(NbScripts) AS MaxScripts, Jour, Developpeurs " +"FROM devdata\n" +"GROUP BY Jour");
        System.out.println(" le nombre max des Scripts ");
	             
        while (result.next()) {
	System.out.println("-------------------------------------------------------\n");
        int maxScripts;
        String Jour;
        String Developpeurs;
        //System.out.println("-Le nom du Developpeurs :"+ result.getString("Developpeurs"));
	System.out.println("-Le jour du NBScripts :"+ result.getString("Jour"));
	System.out.println("-Le nombre du NBScripts :"+ result.getInt("MaxScripts"));
	}
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	    }   
   
   
   
  
   
    public void SUBnbscripts() {
	try {
	ResultSet result = stmt.executeQuery("SELECT SUM(NbScripts) AS C, Developpeurs, Jour " +"FROM devdata\n" +"GROUP BY NbScripts" );
        System.out.println(" La Liste décroissante ");
	 System.out.println("--------------------------- La Liste décroissante----------------------------\n");
                   
        while (result.next()) {
	 int c;
        String Jour;
        String Developpeurs;
       System.out.println("-Le nom du Developpeurs :"+ result.getString("Developpeurs"));
	System.out.println("-Le jour du NBScripts :"+ result.getString("Jour"));
	System.out.println("-Le nombre du NBScripts :"+ result.getInt("C"));
	}
	} catch (SQLException e) {
	    e.printStackTrace();
	}
   }
   

            
            
        public void TotalScriptsParSemaine() {
        try {
            ResultSet result = stmt.executeQuery("SELECT SUM(NbScripts) AS TotalScripts, WEEK(Jour) AS WeekNumber FROM devdata GROUP BY WEEK(Jour);");
            System.out.println("***** Total Scripts par Semaine ******");
            while (result.next()) {
                System.out.println("-------------------------------------------------------\n");
                int totalScripts = result.getInt("TotalScripts");
             
               // System.out.println("Semaine: " + weekNumber);
                System.out.println("Nombre Total de Scripts: " + totalScripts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TotalScriptsParDev(String devName) {
        try {
            ResultSet result = stmt.executeQuery("SELECT SUM(NbScripts) AS TotalScripts FROM devdata WHERE Developpeurs = '" + devName + "';");
            System.<bos>out.println("***** Total Scripts par Developpeur ******");
            if (result.next()) {
                System.out.println("-------------------------------------------------------\n");
                int totalScripts = result.getInt("TotalScripts");
                System.out.println("Développeur: " + devName);
                System.out.println("Nombre Total de Scripts: " + totalScripts);
            } else {
                System.out.println("Aucun script trouvé pour le développeur: " + devName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }}
          public void requeteLibre() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez votre requête SQL : ");
            String sql = scanner.nextLine();

            if (sql.trim().toUpperCase().startsWith("SELECT")) {
                ResultSet result = stmt.executeQuery(sql);
                ResultSetMetaData metaData = result.getMetaData();
                // Afficher le nombre de colonnes
                int columnCount = metaData.getColumnCount();
                System.out.println("Nombre de colonnes : " + columnCount);

                // Afficher les noms et types de données des colonnes
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnType = metaData.getColumnTypeName(i);
                    System.out.println("Colonne " + i + ": " + columnName + " (Type: " + columnType + ")");
                }

                // Afficher le contenu de la table
                while (result.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(result.getString(i) + " ");
                    }
                    System.out.println();
                }
            } else {
                int rowsAffected = stmt.executeUpdate(sql);
                System.out.println("Nombre de lignes modifiées : " + rowsAffected);
            }
         
    }catch (SQLException e) {
            e.printStackTrace();
        
    }
   
            
  }}