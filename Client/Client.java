//----------
// Marine Lohezic & Helene Levry 
//20/03/2017
//----------



//----------
// Client
//----------

//---------
// Imports 
import java.rmi.*;
import java.rmi.registry.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

//---------
// Classe Client (sans interface graphique)
public class Client{

   public static void main(String[] args) {   
     
      try {
      // Obtenir une référence du registry distant
      // le port par défaut utilisé par le processus rmiregistery est le port 1099
         Registry registry = LocateRegistry.getRegistry("172.17.3.191");
      
      // Obtenir une référence de l'objet distant
         Anagramme objetd = (Anagramme) registry.lookup("monObjetDistant");
         
         Scanner sc = new Scanner(System.in);
         System.out.println("VEUILLEZ SAISIR UN MOT :");
         String mot = sc.nextLine(); // saisi du mot par l'utilisateur
         System.out.println(mot); // verification du mot entrée
         
         int nbAna = objetd.countAnagram(mot); // appel la methode coté serveur
         System.out.println("Nombre d'anagrammes du mot " + mot+ " : " + nbAna);
         
         String[] Anagrammes = objetd.getAnagram(); // appel a la methode
      
         for (int i = 0; i<Anagrammes.length; i++){
            System.out.println(Anagrammes[i]); // affichage des anagrammes trouvés par le serveur
         } 
         
      } 
      catch (Exception e) {
         System.err.println("Client exception: " + e.toString());
         e.printStackTrace();
      }  
   }
   
}