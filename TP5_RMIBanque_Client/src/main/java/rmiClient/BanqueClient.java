package rmiClient;

import metier.Compte;
import rmiService.IBanque;

import java.rmi.Naming;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            IBanque banque = (IBanque) Naming.lookup("rmi://localhost:1099/BanqueService");

            // 1. Créer un compte
            System.out.println(banque.creerCompte(new Compte(1, 1000)));

            // 2. Essayer de recréer le même code (doit renvoyer une erreur)
            System.out.println(banque.creerCompte(new Compte(1, 500)));

            // 3. Consulter un compte existant
            System.out.println(banque.getInfoCompte(1));

            // 4. Consulter un compte inexistant
            System.out.println(banque.getInfoCompte(99));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}