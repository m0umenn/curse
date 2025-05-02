// Classe Profil
public class Profil {
    public enum Statut { PASSAGER, CHAUFFEUR }
    public enum TypeCourse { ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE }

    private Statut statut;
    private String itineraire; // Ex : "Université - Centre Ville"
    private String preferences; // Ex : "Musique, pas de bagages, filles uniquement"
    private String disponibilites; // Ex : "Lundi-Vendredi 8h-18h"
    private TypeCourse typeCourse;

    public Profil() {
        // Valeurs par défaut
        this.statut = Statut.PASSAGER;
        this.itineraire = "";
        this.preferences = "";
        this.disponibilites = "";
        this.typeCourse = TypeCourse.ALLER_RETOUR;
    }

    // Getters et setters
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public String getItineraire() { return itineraire; }
    public void setItineraire(String itineraire) { this.itineraire = itineraire; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }

    public String getDisponibilites() { return disponibilites; }
    public void setDisponibilites(String disponibilites) { this.disponibilites = disponibilites; }

    public TypeCourse getTypeCourse() { return typeCourse; }
    public void setTypeCourse(TypeCourse typeCourse) { this.typeCourse = typeCourse; }
}