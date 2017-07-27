//----------
// Interface Graphique
//----------


// Bibliothèques à importer
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



// Classe Interface sous classe de JFrame
public class Interface extends JFrame implements ActionListener{
   
   
   //-----
   // Ajout de composants
   private JLabel Consigne;
   private JTextField Textesaisi;
   private JTextArea Affichage;
   private JButton Bentree;
   private String Mot;
   private String Liste;
   private int nbrAna;
   private String[] anaTrouves;
   private boolean rechercher = false;
   
   
   //-----
   // Constructeur
   public Interface (){
   
      super();
            
      // Definition de la taille
      this.setSize(1200, 1200);
      // Titre
      this.setTitle("Recherche des anagrammes d'un mot");
      // Fin du programme lorsqu'on clique sur la croix rouge
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Définition des composants
      Consigne = new JLabel("Veuillez entrer le mot dont vous cherchez les anagrammes dans le champ ci-dessous", SwingConstants.CENTER);
      Textesaisi = new JTextField (20);
      Affichage = new JTextArea(20,40);
      Bentree = new JButton("Rechercher");
      
      // Affichage des composants et placement
      Container c = this.getContentPane();
      setLayout (new GridLayout (4,1,10,10));
      c.add(Consigne, "1");
      c.add(Textesaisi, "2");
      c.add(Affichage, "3");
      c.add(Bentree, "4");
      
      // Choix des couleurs
      Consigne.setBackground(new Color(116,208,241));
      Consigne.setOpaque(true);
      Consigne.setForeground(new Color(30,127,203));
      
      Textesaisi.setBackground(new Color(169,234,254));
      Textesaisi.setOpaque(true);
      
      Affichage.setBackground(new Color(169,234,254));
      Affichage.setOpaque(true);
      
      Bentree.setBackground(new Color(116,208,241));
      Bentree.setOpaque(true);
      Bentree.setForeground(new Color(30,127,203));
      
      // Choix des polices 
      Font police = new Font("Tahoma", Font.BOLD, 22);
      Consigne.setFont(police);
      Affichage.setFont(police);
      police = new Font("Tahoma", Font.BOLD, 36);
      Textesaisi.setFont(police);
      Bentree.setFont(police);
      
      
      // Rendre les composants prêts à des instructions
      Bentree.addActionListener(this);
      Textesaisi.addActionListener(this);
      
      // Rendre visible la fenêtre A LA FIN
      this.setVisible(true);     
   }
   
   
   //-----
   //methode test du mot saisi
   public int Test_erreur_saisi(String mot){
      int longeur_mot,i;
      char a;
      longeur_mot= mot.length();
      for (i=0;i<longeur_mot;i++){
         a = mot.charAt(i); // le mot lettre a lettre 
         if (((int)a <=64)||((91<=(int)a) &&((int)a <=96))||((123<=(int)a) &&((int)a <=127))||((155<=(int)a) &&((int)a <=159))){
            return 0;
         }
      }
      return 1;
   }
   
   
   public String RecupMot(){
      Mot = Textesaisi.getText(); // Récupérer le mot pour lequel on veut les anagrammes
      if ((Test_erreur_saisi(Mot)) == 1){
         return Mot;
      }
      else {
         return "";
      }
   }
   
   /////
   // Recuperation des anagrammes
   /*public void RecupAna() {
      Mot = Textesaisi.getText(); // Récupérer le mot pour lequel on veut les anagrammes
      Anagram Anagram1 = new Anagram(Mot); // Créer une entité d'Anagram pour ce mot
      nbrAna = Anagram1.getCount(); // Compter les anagrammes de ce mot
      anaTrouves = Anagram1.getAnagram(); // Mettre les anagrammes du mot dans un tableau
   }*/
     
     
   /////
   // Méthode pour l'affichage
   public void AffichageAna(int nbAna, String[] anaTrouves) {
      Affichage.setText(""); // Vider le champ d'affichage
      // Si le mot est correct 
      if(Test_erreur_saisi(Mot)!=0){
         // Lorsqu'il n'y pas pas d'anagrammes
         if (nbrAna == 0){ 
            Affichage.setBackground(new Color(169,234,254));
            Affichage.append("Il n'y a pas d'anagrammes du mot \"" + Mot + "\".\n");
         }
         // Lorsqu'il y a des anagrammes
         else{
            Affichage.setBackground(new Color(0,255,127));
            Affichage.append("Il y a " + nbrAna + " anagrammes du mot \"" + Mot + "\" :\n");
            // Affichages des anagrammes du mot
            for (int i=0 ; i < nbrAna ; i++) { 
               Affichage.append(anaTrouves[i] + "\n");
            } 
         }
      }
      // Si le mot n'est pas correct
      else{
         Affichage.setBackground(new Color(255, 111, 125));
         Affichage.setText("Le mot saisi est invalide\n");
      } 
          
   } 
   
   public boolean getRechercher(){
      return rechercher;
   }
   
   
   /////
   // Méthode des actions
   public void actionPerformed(ActionEvent e) {
      if ((e.getSource() == Bentree) || (e.getSource() == Textesaisi)) {
         rechercher = true;
      }
   }
   

// Fin   
}

