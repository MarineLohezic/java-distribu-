//----------
// Marine Lohezic
//20/03/2017
//----------

import java.rmi.*;

// interface Transmission cote Serveur
   
public interface Anagramme extends java.rmi.Remote {

   public int countAnagram(String data) throws java.rmi.RemoteException;
   public String[] getAnagram() throws java.rmi.RemoteException; 

}
