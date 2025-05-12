import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

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

    // Visualiser l'historique des courses passées
    public List<Course> getHistoriqueCourses() {
        LocalDateTime now = LocalDateTime.now();
        return courses.stream()
                .filter(c -> c.getDateTime() != null && c.getDateTime().isBefore(now))
                .collect(Collectors.toList());
    }

    // Planning journalier
    public List<Course> getCoursesByDay(LocalDate date) {
        return courses.stream()
                .filter(c -> c.getDateTime() != null && c.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    // Planning hebdomadaire
    public List<Course> getCoursesByWeek(LocalDate weekStart) {
        LocalDate weekEnd = weekStart.plusDays(6);
        return courses.stream()
                .filter(c -> c.getDateTime() != null &&
                        !c.getDateTime().toLocalDate().isBefore(weekStart) &&
                        !c.getDateTime().toLocalDate().isAfter(weekEnd))
                .collect(Collectors.toList());
    }

    // Visualiser les courses en cours à un instant donné
    public List<Course> getOngoingCourses() {
        LocalDateTime now = LocalDateTime.now();
        return courses.stream()
                .filter(c -> c.getDateTime() != null && c.getDateTime().isAfter(now))
                .collect(Collectors.toList());
    }

    // Nombre d'étudiants
    public int getNombreEtudiants() {
        return (int) utilisateurs.stream().filter(u -> u instanceof Etudiant).count();
    }
    // Nombre d'enseignants
    public int getNombreEnseignants() {
        return (int) utilisateurs.stream().filter(u -> u instanceof Enseignant).count();
    }
    // Nombre d'ATS
    public int getNombreATS() {
        return (int) utilisateurs.stream().filter(u -> u instanceof ATS).count();
    }

    // Catégories qui proposent plus de courses
    public Map<String, Integer> getCourseCountsByUserType() {
        Map<String, Integer> counts = new HashMap<>();
        for (Course c : courses) {
            String type = c.getChauffeur().getType();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }
        return counts;
    }

    // Facultés
    public Map<String, Integer> getFacultyCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (Utilisateur u : utilisateurs) {
            if (u instanceof Etudiant) {
                String fac = ((Etudiant) u).getFaculte();
                counts.put(fac, counts.getOrDefault(fac, 0) + 1);
            } else if (u instanceof Enseignant) {
                String fac = ((Enseignant) u).getFaculte();
                counts.put(fac, counts.getOrDefault(fac, 0) + 1);
            }
        }
        return counts;
    }

    public int getNombreUtilisateursActifs() {
        return utilisateurs.size();
    }

    public List<Utilisateur> getTopDrivers(int n) {
        return utilisateurs.stream()
                .filter(u -> u.getProfil().getStatut() == Profil.Statut.CHAUFFEUR)
                .sorted(Comparator.comparingDouble(Utilisateur::getReputation).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Utilisateur> getWorstDrivers(int n) {
        return utilisateurs.stream()
                .filter(u -> u.getProfil().getStatut() == Profil.Statut.CHAUFFEUR)
                .sorted(Comparator.comparingDouble(Utilisateur::getReputation))
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Utilisateur> getUsersBelowReputation(double threshold) {
        return utilisateurs.stream()
                .filter(u -> u.getReputation() < threshold)
                .collect(Collectors.toList());
    }

    public void banUsersBelowReputation(double threshold) {
        List<Utilisateur> toBan = getUsersBelowReputation(threshold);
        for (Utilisateur u : toBan) {
            bannirUtilisateur(u);
        }
    }

    public List<Utilisateur> getUtilisateursBannis() {
        return utilisateursBannis;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    // Remove Map-returning statistics methods
    // Add top 10 methods
    public List<Utilisateur> getTop10Drivers() {
        return utilisateurs.stream()
                .filter(u -> u.getProfil().getStatut() == Profil.Statut.CHAUFFEUR)
                .sorted(Comparator.comparingDouble(Utilisateur::getReputation).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Utilisateur> getTop10WorstDrivers() {
        return utilisateurs.stream()
                .filter(u -> u.getProfil().getStatut() == Profil.Statut.CHAUFFEUR)
                .sorted(Comparator.comparingDouble(Utilisateur::getReputation))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Utilisateur> getTop10Passengers() {
        return utilisateurs.stream()
                .filter(u -> u.getProfil().getStatut() == Profil.Statut.PASSAGER)
                .sorted(Comparator.comparingDouble(Utilisateur::getReputation).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Utilisateur> getTop10WorstPassengers() {
        return utilisateurs.stream()
                .filter(u -> u.getProfil().getStatut() == Profil.Statut.PASSAGER)
                .sorted(Comparator.comparingDouble(Utilisateur::getReputation))
                .limit(10)
                .collect(Collectors.toList());
    }
}