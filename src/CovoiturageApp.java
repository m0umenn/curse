import java.util.*;

public class CovoiturageApp {
    private static Administrateur admin = new Administrateur();
    private static final String FICHIER_UTILISATEURS = "utilisateurs.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Charger les utilisateurs au démarrage
        admin.getUtilisateurs().addAll(FichierUtilisateurs.chargerUtilisateurs(FICHIER_UTILISATEURS));
        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Afficher les utilisateurs");
            System.out.println("3. Modifier le profil d'un utilisateur");
            System.out.println("4. Demander une course (passager)");
            System.out.println("5. Sauvegarder les utilisateurs");
            System.out.println("6. Administrateur");
            System.out.println("7. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    ajouterUtilisateur();
                    break;
                case 2:
                    afficherUtilisateurs();
                    break;
                case 3:
                    modifierProfilUtilisateur();
                    break;
                case 4:
                    demanderCourse();
                    break;
                case 5:
                    FichierUtilisateurs.sauvegarderUtilisateurs(admin.getUtilisateurs(), FICHIER_UTILISATEURS);
                    System.out.println("Utilisateurs sauvegardés.");
                    break;
                case 6 :
                    administrateurtools(0);
                    break;
                case 7:
                    quitter = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
        // Sauvegarde automatique à la sortie
        FichierUtilisateurs.sauvegarderUtilisateurs(admin.getUtilisateurs(), FICHIER_UTILISATEURS);
        System.out.println("Au revoir !");
    }

    private static void ajouterUtilisateur() {
        System.out.print("Type (1=Etudiant, 2=Enseignant, 3=ATS) : ");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();

        Utilisateur u = null;
        if (type == 1) {
            System.out.print("Année d'admission : ");
            int annee = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Faculté : ");
            String faculte = scanner.nextLine();
            System.out.print("Spécialité : ");
            String specialite = scanner.nextLine();
            u = new Etudiant(nom, prenom, matricule, annee, faculte, specialite);
        } else if (type == 2) {
            System.out.print("Année de recrutement : ");
            int annee = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Faculté : ");
            String faculte = scanner.nextLine();
            u = new Enseignant(nom, prenom, matricule, annee, faculte);
        } else if (type == 3) {
            System.out.print("Année de recrutement : ");
            int annee = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Service : ");
            String service = scanner.nextLine();
            u = new ATS(nom, prenom, matricule, annee, service);
        }
        if (u != null) {
            admin.ajouterUtilisateur(u);
            System.out.println("Utilisateur ajouté !");
        }
    }

    private static void afficherUtilisateurs() {
        List<Utilisateur> utilisateurs = admin.getUtilisateurs();
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur.");
        } else {
            for (Utilisateur u : utilisateurs) {
                System.out.println(u.getType() + " - " + u.getNom() + " " + u.getPrenom() + " (" + u.getMatricule() + ")");
            }
        }
    }

    private static void modifierProfilUtilisateur() {
        System.out.print("Entrez le matricule de l'utilisateur à modifier : ");
        String matricule = scanner.nextLine();
        Utilisateur utilisateur = null;
        for (Utilisateur u : admin.getUtilisateurs()) {
            if (u.getMatricule().equals(matricule)) {
                utilisateur = u;
                break;
            }
        }
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }
        Profil profil = utilisateur.getProfil();
        System.out.println("Profil actuel :");
        System.out.println("Statut : " + profil.getStatut());
        System.out.println("Itinéraire : " + profil.getItineraire());
        System.out.println("Préférences : " + profil.getPreferences());
        System.out.println("Disponibilités : " + profil.getDisponibilites());
        System.out.println("Type de course : " + profil.getTypeCourse());

        System.out.println("--- Modification du profil ---");
        System.out.print("Nouveau statut (1=PASSAGER, 2=CHAUFFEUR) : ");
        int statut = scanner.nextInt();
        scanner.nextLine();
        if (statut == 1) profil.setStatut(Profil.Statut.PASSAGER);
        else if (statut == 2) profil.setStatut(Profil.Statut.CHAUFFEUR);

        System.out.print("Nouvel itinéraire : ");
        String itineraire = scanner.nextLine();
        profil.setItineraire(itineraire);

        System.out.print("Nouvelles préférences : ");
        String preferences = scanner.nextLine();
        profil.setPreferences(preferences);

        System.out.print("Nouvelles disponibilités : ");
        String disponibilites = scanner.nextLine();
        profil.setDisponibilites(disponibilites);

        System.out.print("Type de course (1=ALLER_RETOUR, 2=ALLER_SIMPLE, 3=RETOUR_SIMPLE) : ");
        int typeCourse = scanner.nextInt();
        scanner.nextLine();
        if (typeCourse == 1) profil.setTypeCourse(Profil.TypeCourse.ALLER_RETOUR);
        else if (typeCourse == 2) profil.setTypeCourse(Profil.TypeCourse.ALLER_SIMPLE);
        else if (typeCourse == 3) profil.setTypeCourse(Profil.TypeCourse.RETOUR_SIMPLE);

        System.out.println("Profil modifié avec succès !");
    }

    private static void demanderCourse() {
        System.out.print("Entrez votre matricule (passager) : ");
        String matricule = scanner.nextLine();
        Utilisateur passager = null;
        for (Utilisateur u : admin.getUtilisateurs()) {
            if (u.getMatricule().equals(matricule)) {
                passager = u;
                break;
            }
        }
        if (passager == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        Profil profilPassager = passager.getProfil();
        System.out.println("Votre itinéraire actuel : " + profilPassager.getItineraire());
        System.out.print("Voulez-vous utiliser cet itinéraire ? (o/n) : ");
        String reponse = scanner.nextLine();
        String itineraireRecherche;
        if (reponse.equalsIgnoreCase("o")) {
            itineraireRecherche = profilPassager.getItineraire();
        } else {
            System.out.print("Entrez l'itinéraire souhaité : ");
            itineraireRecherche = scanner.nextLine();
        }

        // Recherche des chauffeurs disponibles
        List<Utilisateur> chauffeursDisponibles = new ArrayList<>();
        for (Utilisateur u : admin.getUtilisateurs()) {
            if (u.getProfil().getStatut() == Profil.Statut.CHAUFFEUR
                    && u.getProfil().getItineraire() != null
                    && !u.getMatricule().equals(passager.getMatricule())
                    && u.getProfil().getItineraire().equalsIgnoreCase(itineraireRecherche)) {
                chauffeursDisponibles.add(u);
            }
            // add preferences and  disponibilite match
        }

        if (chauffeursDisponibles.isEmpty()) {
            System.out.println("Aucun chauffeur disponible pour cet itinéraire.");
            return;
        }

        System.out.println("Chauffeurs disponibles pour l'itinéraire '" + itineraireRecherche + "' :");
        for (int i = 0; i < chauffeursDisponibles.size(); i++) {
            Utilisateur chauffeur = chauffeursDisponibles.get(i);
            System.out.println((i+1) + ". " + chauffeur.getNom() + " " + chauffeur.getPrenom() +
                    " (" + chauffeur.getMatricule() + "), Disponibilités : " + chauffeur.getProfil().getDisponibilites());
        }


        System.out.print("Choisissez un chauffeur (numéro) ou 0 pour annuler : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix > 0 && choix <= chauffeursDisponibles.size()) {
            Utilisateur chauffeurChoisi = chauffeursDisponibles.get(choix - 1);
            System.out.println("Vous avez rejoint la course du chauffeur " +
                    chauffeurChoisi.getNom() + " " + chauffeurChoisi.getPrenom() + " !");
            // Ici, tu pourrais ajouter la logique pour enregistrer la course/passager si tu veux aller plus loin
            Course c = new Course(chauffeurChoisi,passager,itineraireRecherche,chauffeurChoisi.getProfil().getDisponibilites(),chauffeurChoisi.getProfil().getTypeCourse());
            admin.ajouterCourse(c);
        } else {
            System.out.println("Action annulée.");
        }
    }

    public static void administrateurtools(Integer tries) {
        System.out.println("please enter admin password");
        String enteredMDP = scanner.nextLine();
        if (enteredMDP.equals(admin.getMotDePasse())) {
            System.out.println("1- Bannir utilisateur");
            System.out.println("2- Afficher utilisateurs");
            System.out.println("3- Afficher les courses en cours");
            System.out.println("4- Afficher utilisateurs bannus");
            System.out.println("Votre choix : ");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1":
                    System.out.println("User ID to be banned");
                    String banID = scanner.nextLine();
                    Utilisateur tobeBanned = null;
                    for (Utilisateur u : admin.getUtilisateurs()) {
                        if (u.matricule.equals(banID)) {
                            tobeBanned = u;
                            break;
                        }
                    }
                    if (tobeBanned == null) {
                        System.out.println("user doesnt exist");
                    }
                    admin.bannirUtilisateur(tobeBanned);
                    break;
                case "2":
                    if (admin.getUtilisateurs() != null && !admin.getUtilisateurs().isEmpty()) {
                        for (Utilisateur u : admin.getUtilisateurs()) {
                            System.out.println(u.toString());
                        }
                    } else {
                        System.out.println("No users found.");
                    }
                    break;
                case "3":
                    if (admin.getCourses() != null && !admin.getCourses().isEmpty()) {
                        for (Course c : admin.getCourses()) {
                            System.out.println(c.toString());
                        }
                    } else {
                        System.out.println("No rides found.");
                    }
                    break;
                case "4":
                    if (admin.getUtilisateursBannis() != null && !admin.getUtilisateursBannis().isEmpty()) {
                        for (Utilisateur u : admin.getUtilisateursBannis()) {
                            System.out.println(u.toString());
                        }
                    } else {
                        System.out.println("No users found.");
                    }
                    break;
                default:
                    System.out.println("invalid choice");
            }
        } else {
        if (tries < 3) {
            System.out.println("wrong password please try again"+" "+ String.valueOf(3-tries) +" left");
            administrateurtools(tries +1 );
        } else {
            System.exit(0);
        }
    }
}}