import java.rmi.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class Verification  {

public Verification()throws RemoteException {};
public Verification(String bdd,String user,String passwd )throws RemoteException {
	
	
};


synchronized static public boolean Connexion_utilisateur(String login, String pass)
{
	
	boolean valide;
    	
    	
        valide=false;
    	
		try {
			   
				Statement state = Connect.getInstance().createStatement();
				String requete = "SELECT log,passe FROM utilisateurs";
				ResultSet resultat = state.executeQuery(requete);
			
		
			while(resultat.next() && !valide){
				if(login.equals(resultat.getString("log")) && pass.equals(resultat.getString("passe"))) {
					
					valide =true;
				}
							
			}

                        resultat.close();
                        state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
return  valide;
}
}