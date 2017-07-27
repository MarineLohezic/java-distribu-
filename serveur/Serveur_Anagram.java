//----------
// Marine Lohezic
//20/03/2017
//----------


//Serveur Serveur_Anagram
   
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;


public class Serveur_Anagram implements Anagramme {


//instantiation d'une nouvelle instance d'Anagram
   private Anagram Ana= new Anagram();
   private int nombre=0;
   
   
   
   //constructeur du Serveur par défault
   public Serveur_Anagram()  {
   }
   
   
   //Descriptions des methodes utilisées par le Client 
   public int countAnagram(String data) throws RemoteException {
      try {
      
         nombre=Ana.countAnagram(data); // appel a la methode de la classe Anagram
      }
      catch (Exception e) {
         System.out.println(e.toString()); 
      }
     
      return nombre;
   }
   
   public String[] getAnagram()throws RemoteException {
   
      String[] liste={"il","n'y","a","pas","d'anagrammes","pour","ce","mot"};   // si pas d'anagramme
      try {
         if (nombre!=0){ // s'il y a au moins 1 anagramme
            liste =Ana.getAnagram();
         }
      }
      
      catch (Exception e) {
         System.out.println(e.toString()); 
      }
      
      return liste;
   }
            
            
   public static void main (String args[]) {
      
      try {
         // Créer un objet instance de la classe ServeurHello
         Serveur_Anagram obj = new Serveur_Anagram();
         
         // Exporter l'objet pour lui permettre de recevoir des appels externes sur le port 1234 par exemple
         Anagramme Anag = (Anagramme)UnicastRemoteObject.exportObject(obj, 1234);
         
         // Lier le stub de l'objet accessible à distance au registry
         // le port par défaut utilisé par le processus rmiregistery est le port 1099
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("monObjetDistant", Anag);
         
         System.err.println("Serveur_Anagram en attente ...");
      } 
      catch (Exception e) {
         System.err.println("Serveur_Anagram exception: " + e.toString());
         e.printStackTrace();
      }
      
      
   }
}

