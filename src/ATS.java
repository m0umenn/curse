// Classe ATS qui hérite de Utilisateur
public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String service;

    // meriem
    
    public ATS(String nom, String prenom, String matricule, int anneeRecrutement, String service, Gender gender) {
        super(nom, prenom, matricule, gender);
        this.anneeRecrutement = anneeRecrutement;
        this.service = service;
    }

    // Getters spécifiques à ATS
    public int getAnneeRecrutement() { return anneeRecrutement; }
    public String getService() { return service; }

    // Surcharge de la méthode abstraite
    @Override
    public String getType() {
        return "ATS";
    }
}