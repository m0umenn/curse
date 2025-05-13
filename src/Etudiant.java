// Classe Etudiant qui hérite de Utilisateur
public class Etudiant extends Utilisateur {
    private int anneeAdmission;
    private String faculte;
    private String specialite;

    public Etudiant(String nom, String prenom, String matricule, int anneeAdmission, String faculte, String specialite, Gender gender) {
        super(nom, prenom, matricule, gender);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
    }

    // Getters spécifiques à Etudiant
    public int getAnneeAdmission() { return anneeAdmission; }
    public String getFaculte() { return faculte; }
    public String getSpecialite() { return specialite; }

    // Surcharge de la méthode abstraite
    @Override
    public String getType() {
        return "Etudiant";
    }
}