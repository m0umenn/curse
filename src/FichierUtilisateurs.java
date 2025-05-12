import java.io.*;
import java.util.*;

public class FichierUtilisateurs {

    // Sauvegarder la liste des utilisateurs dans un fichier
    public static void sauvegarderUtilisateurs(List<Utilisateur> utilisateurs, String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            for (Utilisateur u : utilisateurs) {
                // Format : type;nom;prenom;matricule;reputation;[infos spécifiques];profil fields
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
                Profil p = u.getProfil();
                ligne += ";" + p.getStatut().name()
                        + ";" + p.getItineraire()
                        + ";" + p.getDisponibilites()
                        + ";" + p.getTypeCourse().name()
                        + ";" + p.getMusicPreference().name()
                        + ";" + p.getGenderPreference().name()
                        + ";" + p.getLuggageSize().name();
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
                Utilisateur user = null;
                if (type.equals("Etudiant")) {
                    user = new Etudiant(parts[1], parts[2], parts[3], Integer.parseInt(parts[5]), parts[6], parts[7]);
                } else if (type.equals("Enseignant")) {
                    user = new Enseignant(parts[1], parts[2], parts[3], Integer.parseInt(parts[5]), parts[6]);
                } else if (type.equals("ATS")) {
                    user = new ATS(parts[1], parts[2], parts[3], Integer.parseInt(parts[5]), parts[6]);
                }
                if (user != null && parts.length >= 15) {
                    Profil p = user.getProfil();
                    p.setStatut(Profil.Statut.valueOf(parts[8]));
                    p.setItineraire(parts[9]);
                    p.setDisponibilites(parts[10]);
                    p.setTypeCourse(Profil.TypeCourse.valueOf(parts[11]));
                    p.setMusicPreference(Profil.MusicPreference.valueOf(parts[12]));
                    p.setGenderPreference(Profil.GenderPreference.valueOf(parts[13]));
                    p.setLuggageSize(Profil.LuggageSize.valueOf(parts[14]));
                }
                if (user != null) {
                    utilisateurs.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }
}