// Classe Evaluation
public class Evaluation {
    private Utilisateur evaluateur;
    private int note; // de 1 à 5
    private String commentaire;

    public Evaluation(Utilisateur evaluateur, int note, String commentaire) {
        this.evaluateur = evaluateur;
        this.note = note;
        this.commentaire = commentaire;
    }

    // Getters
    public Utilisateur getEvaluateur() { return evaluateur; }
    public int getNote() { return note; }
    public String getCommentaire() { return commentaire; }
}