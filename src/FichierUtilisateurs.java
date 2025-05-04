import java.io.*;
import java.util.*;

public class FichierUtilisateurs {

    // Sauvegarder la liste des utilisateurs dans un fichier
    public static void sauvegarderUtilisateurs(List<Utilisateur> utilisateurs, String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (Utilisateur u : utilisateurs) {
                // Format : type;nom;prenom;matricule;reputation;[infos spécifiques]
                String ligne = u.getType() + ";" + u.getNom() + ";" + u.getPrenom() + ";" + u.getMatricule() + ";" + u.getReputation();
                if (u instanceof Etudiant) {
                    Etudiant e = (Etudiant) u;
                    ligne += ";" + e.getAnneeAdmission() + ";" + e.getFaculte() + ";" + e.getSpecialite();
                } else if (u instanceof Enseignant) {
                    Enseignant ens = (Enseignant) u;
                    ligne += ";" + ens.getAnneeRecrutement() + ";" + ens.getFaculte();
                } else if (u instanceof ATS) {
                    ATS ats = (ATS) u;
                    ligne += ";" + ats.getAnneeRecrutement() + ";" + ats.getService();
                }
                writer.write(ligne);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    // Charger la liste des utilisateurs depuis un fichier
    public static List<Utilisateur> chargerUtilisateurs(String nomFichier) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                String type = parts[0];
                if (type.equals("Etudiant")) {
                    utilisateurs.add(new Etudiant(parts[1], parts[2], parts[3], Integer.parseInt(parts[5]), parts[6], parts[7]));
                } else if (type.equals("Enseignant")) {
                    utilisateurs.add(new Enseignant(parts[1], parts[2], parts[3], Integer.parseInt(parts[6]), parts[7]));
                } else if (type.equals("ATS")) {
                    utilisateurs.add(new ATS(parts[1], parts[2], parts[3], Integer.parseInt(parts[6]), parts[7]));
                }
                // On peut aussi charger la réputation si besoin
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }
}