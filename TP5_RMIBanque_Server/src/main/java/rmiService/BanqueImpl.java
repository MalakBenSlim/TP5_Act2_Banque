package rmiService;

import metier.Compte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {

    private final Map<Integer, Compte> comptes = new ConcurrentHashMap<>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public String creerCompte(Compte c) throws RemoteException {
        // putIfAbsent : n'ajoute que si le code n'existe pas déjà
        Compte existant = comptes.putIfAbsent(c.getCode(), c);
        if (existant != null) {
            return "Erreur : le compte " + c.getCode() + " existe déjà.";
        }
        System.out.println("Compte créé : " + c);
        return "Compte " + c.getCode() + " créé avec succès (solde initial : " + c.getSolde() + ").";
    }

    @Override
    public String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        if (c == null) {
            return "Compte " + code + " introuvable.";
        }
        return c.toString();
    }
}