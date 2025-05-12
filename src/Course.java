import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

// Classe Course
public class Course {
    private Utilisateur chauffeur;
    private List<Utilisateur> passagers;
    private String itineraire;
    private String horaires;
    private Profil.TypeCourse typeCourse;
    private List<Evaluation> evaluations; // Liste des évaluations reçues
    private LocalDateTime dateTime;

    public Course(Utilisateur chauffeur, Utilisateur pass, String itineraire, String horaires, Profil.TypeCourse typeCourse, LocalDateTime dateTime) {
        this.chauffeur = chauffeur;
        this.itineraire = itineraire;
        this.horaires = horaires;
        this.typeCourse = typeCourse;
        this.passagers = new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.passagers.add(pass);
        this.dateTime = dateTime;
    }

    // Ajouter un passager à la course
    public void ajouterPassager(Utilisateur passager) {
        passagers.add(passager);
    }

    // Ajouter une évaluation à la course
    public void ajouterEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

    // Getters
    public Utilisateur getChauffeur() { return chauffeur; }
    public List<Utilisateur> getPassagers() { return passagers; }
    public String getItineraire() { return itineraire; }
    public String getHoraires() { return horaires; }
    public Profil.TypeCourse getTypeCourse() { return typeCourse; }
    public List<Evaluation> getEvaluations() { return evaluations; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    @Override
    public String toString() {
        return "Course{" +
                "typeCourse=" + typeCourse +
                ", horaires='" + horaires + '\'' +
                ", itineraire='" + itineraire + '\'' +
                ", passagers=" + passagers +
                ", chauffeur=" + chauffeur +
                ", dateTime=" + dateTime +
                '}';
    }
}