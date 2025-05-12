import java.util.ArrayList;
import java.util.List;

// Classe Course
public class Course {
    private Utilisateur chauffeur;
    private List<Utilisateur> passagers;
    private String itineraire;
    private String horaires;
    private Profil.TypeCourse typeCourse;
    private List<Evaluation> evaluations; // Liste des évaluations reçues

    public Course(Utilisateur chauffeur,Utilisateur pass ,String itineraire, String horaires, Profil.TypeCourse typeCourse) {
        this.chauffeur = chauffeur;
        this.itineraire = itineraire;
        this.horaires = horaires;
        this.typeCourse = typeCourse;
        this.passagers = new ArrayList<>();
        this.evaluations = new ArrayList<>();
        this.passagers.add(pass);
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

    @Override
    public String toString() {
        return "Course{" +
                "typeCourse=" + typeCourse +
                ", horaires='" + horaires + '\'' +
                ", itineraire='" + itineraire + '\'' +
                ", passagers=" + passagers +
                ", chauffeur=" + chauffeur +
                '}';
    }
}