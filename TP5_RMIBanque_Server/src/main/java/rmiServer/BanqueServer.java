package rmiServer;

import rmiService.BanqueImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BanqueServer {

    public static void main(String[] args) {
        try {
            // Activer le RMIRegistry (port 1099)
            LocateRegistry.createRegistry(1099);

            // Créer l'objet distant
            BanqueImpl banque = new BanqueImpl();

            // Publier dans l'annuaire
            Naming.rebind("rmi://localhost:1099/BanqueService", banque);

            System.out.println("Serveur Banque RMI démarré...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}