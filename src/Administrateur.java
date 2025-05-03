import java.util.ArrayList;
import java.util.List;

// Classe Administrateur
public class Administrateur {
    private List<Utilisateur> utilisateurs;
    private List<Course> courses;
    private List<Utilisateur> utilisateursBannis;
    private String motDePasse = "admin";

    public Administrateur() {
        this.utilisateurs = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.utilisateursBannis = new ArrayList<>();
    }

    // Ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    // Supprimer (bannir) un utilisateur
    public void bannirUtilisateur(Utilisateur utilisateur) {
        utilisateurs.remove(utilisateur);
        utilisateursBannis.add(utilisateur);
    }

    // Ajouter une course
    public void ajouterCourse(Course course) {
        courses.add(course);
    }

    // Visualiser tous les utilisateurs
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    // Visualiser toutes les courses
    public List<Course> getCourses() {
        return courses;
    }

    // Visualiser l’historique des courses passées
    public List<Course> getHistoriqueCourses() {
        // Pour simplifier, on retourne toutes les courses
        // ajoute les horaires comme un log
        return courses;
    }

    // Statistiques simples
    public int getNombreEtudiants() {
        int count = 0;
        for (Utilisateur u : utilisateurs) {
            if (u instanceof Etudiant) count++;
        }
        return count;
    }

    public int getNombreEnseignants() {
        int count = 0;
        for (Utilisateur u : utilisateurs) {
            if (u instanceof Enseignant) count++;
        }
        return count;
    }

    public int getNombreATS() {
        int count = 0;
        for (Utilisateur u : utilisateurs) {
            if (u instanceof ATS) count++;
        }
        return count;
    }

    public int getNombreUtilisateursActifs() {
        return utilisateurs.size();
    }

    public List<Utilisateur> getUtilisateursBannis() {
        return utilisateursBannis;
    }

    public String getMotDePasse() {
        return motDePasse;
    }


}