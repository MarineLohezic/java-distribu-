import java.util.*;
import java.io.*;
import java.text.*; 
import java.text.Normalizer.*;


public class Anagram {

   public static int size = 0;
   public static String[] result; 
  
   private  String word; 
   private  ArrayList<String> solution;
   private  ArrayList<String> dictionary;// mise en static
   
   //Constructeur par default
   public void Anagram(){
   }
 /**
 * Returns the count of anagrams
 * 
 * @param data the input string (only one word!)
 * @return the count of anagrams    
 */    
   public int countAnagram(String data) {
      this.solution = new ArrayList<String>();
      this.dictionary = new ArrayList<String>();
      this.word = new String(this.removeAccents(data.toLowerCase()));
      this.readDictionary();
      this.solution = permutation(this.word);
      this.searchAnagram();
      return(this.size);
   }
   
 /**
 * Returns the occurrences of anagrams
 * 
 * @param none
 * @return an array of strings that contains the anagrams
 */
   public String[] getAnagram() {
      return(this.result);
   }

 // All the above methods are privates

   private String removeAccents(String text){ 
      return text == null ? null 
         : Normalizer.normalize(text,Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); 
   } 
  
 /**
 * List permutation of a string
 * 
 * @param s the input string
 * @return the list of permutation
 */
   private ArrayList<String> permutation(String s) {
    // The result
      ArrayList<String> res = new ArrayList<String>();
    // If input string's length is 1, return {s}
      if (s.length() == 1) {
      
         res.add(s);
      } 
      else if (s.length() > 1) {
         int lastIndex = s.length() - 1;
        // Find out the last character
         String last = s.substring(lastIndex);
        // Rest of the string
         String rest = s.substring(0, lastIndex);
        // Perform permutation on the rest string and
        // merge with the last character
         res = merge(permutation(rest), last);               }
      return res;
   }

/**
 * @param list a result of permutation, e.g. {"ab", "ba"}
 * @param c the last character
 * @return a merged new list, e.g. {"cab", "acb" ... }
 */
   private ArrayList<String> merge(ArrayList<String> list, String c) {
      ArrayList<String> res = new ArrayList<String>();
    // Loop through all the string in the list
      for (String s : list) {
      
        // For each string, insert the last character to all possible postions
        // and add them to the new list
         for (int i = 0; i <= s.length(); ++i) {
            String ps = new StringBuffer(s).insert(i, c).toString();
            res.add(ps);
         }
      }
      return res;
   }

   private void readDictionary() {
      String fichier ="ods5.txt";
      int i=0;
      try{
         InputStream ips=new FileInputStream(fichier); 
         InputStreamReader ipsr=new InputStreamReader(ips);
         BufferedReader br=new BufferedReader(ipsr);
         String ligne;
         while ((ligne=br.readLine())!=null){
            this.dictionary.add(ligne);
            i++;
         }
         br.close(); 
      }		
      catch (Exception e){
         System.out.println(e.toString());
      }
   }
        
   private void searchAnagram() {
      ArrayList<String> buff = new ArrayList<String>();
      String[] temp = this.dictionary.toArray(new String[this.dictionary.size()-1]);
      Arrays.sort(temp);
      for (String s : this.solution) {
         if ( (!this.word.equals(s)) && (!buff.contains(s)) ) {
            if(Arrays.binarySearch(temp, s) >=0 ){
               buff.add(s);
               //System.out.println(s);
            }
         }
      }
      this.size = buff.size();
      if (this.size>0) {
         this.result = buff.toArray(new String[this.size-1]);
         Arrays.sort(this.result);
      }       
   }

}