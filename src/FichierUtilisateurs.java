import java.io.*;
import java.util.*;

public class FichierUtilisateurs {

    // Sauvegarder la liste des utilisateurs dans un fichier
    public static void sauvegarderUtilisateurs(List<Utilisateur> utilisateurs, String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (Utilisateur u : utilisateurs) {
                // Format : type;nom;prenom;matricule;reputation;[infos spécifiques]
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
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    // Charger la liste des utilisateurs depuis un fichier
    public static List<Utilisateur> chargerUtilisateurs(String nomFichier) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        File file = new File(nomFichier);
        
        // Check if file exists
        if (!file.exists() || file.length() == 0) {
            System.out.println("Le fichier " + nomFichier + " n'existe pas ou est vide.");
            return utilisateurs;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                try {
                    String[] parts = ligne.split(";");
                    if (parts.length < 5) {
                        System.out.println("Format de ligne invalide: " + ligne);
                        continue;
                    }
                    
                    String type = parts[0];
                    String nom = parts[1];
                    String prenom = parts[2];
                    String matricule = parts[3];
                    double reputation = Double.parseDouble(parts[4]);
                    
                    Utilisateur utilisateur = null;
                    
                    if (type.equals("Etudiant") && parts.length >= 8) {
                        int annee = Integer.parseInt(parts[5]);
                        String faculte = parts[6];
                        String specialite = parts[7];
                        utilisateur = new Etudiant(nom, prenom, matricule, annee, faculte, specialite);
                    } else if (type.equals("Enseignant") && parts.length >= 7) {
                        int annee = Integer.parseInt(parts[5]);
                        String faculte = parts[6];
                        utilisateur = new Enseignant(nom, prenom, matricule, annee, faculte);
                    } else if (type.equals("ATS") && parts.length >= 7) {
                        int annee = Integer.parseInt(parts[5]);
                        String service = parts[6];
                        utilisateur = new ATS(nom, prenom, matricule, annee, service);
                    }
                    
                    if (utilisateur != null) {
                        utilisateur.setReputation(reputation);
                        utilisateurs.add(utilisateur);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Erreur lors du traitement de la ligne: " + ligne + ", erreur: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }
}