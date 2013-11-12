/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Connection.Krypter.Hasher;

/**
 *
 * @author Jan Koschke
 */
public class Message {

    private String benutzer, passwort, hash;
    private int punkte;

    public Message(String benutzer, String passwort, int punkte, String hash) {
        this.benutzer = benutzer;
        this.passwort = Hasher.ToMD5(passwort);
        this.punkte = punkte;
        this.hash = hash;
    }

    public String getBenutzer() {
        return benutzer;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getHash() {
        return hash;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getString() {
        return "benutzer:\"" + getBenutzer() + "\",passwort:\"" + getPasswort() + "\",punkte:\"" + getPunkte() + "\", \"" + getHash() + "\"";
    }

}
