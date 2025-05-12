// Classe Evaluation
public class Evaluation {
    private Utilisateur evaluateur;
    private Utilisateur evaluee;
    private int note; // de 1 à 5
    private String commentaire;

    public Evaluation(Utilisateur evaluateur,Utilisateur evaluee,int note, String commentaire) {
        this.evaluateur = evaluateur;
        this.evaluee = evaluee;
        this.note = note;
        this.commentaire = commentaire;
    }

    // Getters
    public Utilisateur getEvaluateur() { return evaluateur; }
    public int getNote() { return note; }
    public String getCommentaire() { return commentaire; }
    public Utilisateur getEvaluee() { return evaluee; }

    @Override
    public String toString() {
        return "evaluee : " + evaluee + " note : " + note + " commentaire : "
                + commentaire + "";
    }


}