import java.rmi.*;

// interface Transmission cote Client
   
public interface Transmission extends java.rmi.Remote {

   public String getTexte(String numero_fichier) throws java.rmi.RemoteException;

}
