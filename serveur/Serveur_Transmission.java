//Serveur Transmission

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.io.*; // Pour le bufferdreader 


public class Serveur_Anagram implements Anagramme implements Anagram  {
   
   public Serveur_Anagram()  {
      
   }
   
   public int countAnagram(String data) throws RemoteException {
      String LineIn="echec";
   
      //Lire et recuperer la premier ligne du fichier
      try {
         BufferedReader BR = new BufferedReader(new FileReader(numero_fichier));
         LineIn = BR.readLine();
         BR.close();
      }
        
      catch (Exception e) {
         System.out.println(e.toString()); 
      }
      
         //Ecrire sur le fichier rmi.log
      try {
         DataOutputStream DOS = new DataOutputStream(new FileOutputStream("rmi.log",true));
         DOS.writeBytes(" [client : ");
         DOS.writeBytes(RemoteServer.getClientHost());
         DOS.writeBytes("] [fichier : ");
         DOS.writeBytes(numero_fichier);
         DOS.writeByte(']');
         DOS.writeByte('\n');
         DOS.flush();
         DOS.close();
      }
      catch (Exception e) {
         System.out.println(e.toString());
      }
      return LineIn;
   }
   
   
   public static void main (String args[]) {
      
      try {
         // Créer un objet instance de la classe ServeurHello
         Serveur_Transmission obj = new Serveur_Transmission();
         
         // Exporter l'objet pour lui permettre de recevoir des appels externes sur le port 1234 par exemple
         Transmission fichier = (Transmission)UnicastRemoteObject.exportObject(obj, 1234);
         
         // Lier le stub de l'objet accessible à distance au registry
         // le port par défaut utilisé par le processus rmiregistery est le port 1099
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("monObjetDistant", fichier);
         
         System.err.println("Serveur_Transmission en attente ...");
      } 
      catch (Exception e) {
         System.err.println("Serveur_Transmission exception: " + e.toString());
         e.printStackTrace();
      }
      
      
   }
}

