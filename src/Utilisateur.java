
public abstract class Utilisateur {
    protected String nom;
    protected String prenom;
    protected String matricule;
    protected double reputation; // Moyenne des notes reçues
    protected Profil profil;

    public Utilisateur(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.reputation = 5.0; // Par défaut, réputation maximale
        this.profil = new Profil();
    }

    // Méthodes d'accès (getters/setters)
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMatricule() { return matricule; }
    public double getReputation() { return reputation; }
    public Profil getProfil() { return profil; }

    public void setReputation(double reputation) { this.reputation = reputation; }

    // Méthode abstraite pour obtenir le type d'utilisateur
    public abstract String getType();

    @Override
    public String toString() {
        return "Utilisateur{" +
                "matricule='" + matricule + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}