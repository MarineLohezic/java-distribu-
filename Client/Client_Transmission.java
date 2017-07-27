// Client

import java.rmi.*;
import java.rmi.registry.*;

public class Client_Transmission {

   public static void main(String[] args) {
      try {
            // Obtenir une référence du registry distant
            // le port par défaut utilisé par le processus rmiregistery est le port 1099
         Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            
            // Obtenir une référence de l'objet distant
         Transmission fichier = (Transmission) registry.lookup("monObjetDistant");
            
            
            // Utiliser les méthodes de l'objet distant
         String response = fichier.getTexte("fichiers/5.txt");
         System.out.println("reponse du serveur : " + response);
      } 
      catch (Exception e) {
         System.err.println("Client_Transmission exception: " + e.toString());
         e.printStackTrace();
      }
      
   }
}