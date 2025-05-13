// Classe Enseignant qui hérite de Utilisateur
public class Enseignant extends Utilisateur {
    private int anneeRecrutement;
    private String faculte;

    public Enseignant(String nom, String prenom, String matricule, int anneeRecrutement, String faculte, Gender gender) {
        super(nom, prenom, matricule, gender);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
    }

    // Getters spécifiques à Enseignant
    public int getAnneeRecrutement() { return anneeRecrutement; }
    public String getFaculte() { return faculte; }

    // Surcharge de la méthode abstraite
    @Override
    public String getType() {
        return "Enseignant";
    }
}