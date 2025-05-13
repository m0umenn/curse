public class Profil {
    public enum Statut { PASSAGER, CHAUFFEUR }
    public enum TypeCourse { ALLER_RETOUR, ALLER_SIMPLE, RETOUR_SIMPLE }
    public enum MusicPreference { YES, NO, DONT_CARE }
    public enum GenderPreference { MALE, FEMALE, ANY }
    public enum LuggageSize { NONE, SMALL, MEDIUM, LARGE }

    private Statut statut;
    private String itineraire;
    private String disponibilites;
    private TypeCourse typeCourse;
    private MusicPreference musicPreference;
    private GenderPreference genderPreference;
    private LuggageSize luggageSize;

    // ali

    public Profil() {
        this.statut = Statut.PASSAGER;
        this.itineraire = "";
        this.disponibilites = "";
        this.typeCourse = TypeCourse.ALLER_RETOUR;
        this.musicPreference = MusicPreference.DONT_CARE;
        this.genderPreference = GenderPreference.ANY;
        this.luggageSize = LuggageSize.NONE;
    }

    // Getters et setters
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public String getItineraire() { return itineraire; }
    public void setItineraire(String itineraire) { this.itineraire = itineraire; }

    public String getDisponibilites() { return disponibilites; }
    public void setDisponibilites(String disponibilites) { this.disponibilites = disponibilites; }

    public TypeCourse getTypeCourse() { return typeCourse; }
    public void setTypeCourse(TypeCourse typeCourse) { this.typeCourse = typeCourse; }

    public MusicPreference getMusicPreference() { return musicPreference; }
    public void setMusicPreference(MusicPreference musicPreference) { this.musicPreference = musicPreference; }

    public GenderPreference getGenderPreference() { return genderPreference; }
    public void setGenderPreference(GenderPreference genderPreference) { this.genderPreference = genderPreference; }

    public LuggageSize getLuggageSize() { return luggageSize; }
    public void setLuggageSize(LuggageSize luggageSize) { this.luggageSize = luggageSize; }
}