import java.io.File;
import java.util.*;

public class CovoiturageApp {
    private static Administrateur admin = new Administrateur();
    private static final String FICHIER_UTILISATEURS = "utilisateurs.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Charger les utilisateurs au démarrage
        File fichierUtilisateurs = new File(FICHIER_UTILISATEURS);
        System.out.println("Chargement des utilisateurs depuis: " + fichierUtilisateurs.getAbsolutePath());
        admin.getUtilisateurs().addAll(FichierUtilisateurs.chargerUtilisateurs(FICHIER_UTILISATEURS));
        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Ajouter un utilisateur");

            System.out.println("2. Modifier le profil d'un utilisateur");
            System.out.println("3. Demander une course (passager)");
            System.out.println("4. Sauvegarder les utilisateurs");
            System.out.println("5. Evaluer un chauffeur");
            System.out.println("6. Administrateur");
            System.out.println("7. Quitter et sauvegarder");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    ajouterUtilisateur();
                    break;

                case 2:
                    modifierProfilUtilisateur();
                    break;
                case 3:
                    demanderCourse();
                    break;
                case 4:
                    FichierUtilisateurs.sauvegarderUtilisateurs(admin.getUtilisateurs(), FICHIER_UTILISATEURS);
                    System.out.println("Utilisateurs sauvegardés.");
                    break;
                case 5 :
                    evaluerChauffeur();
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
        System.out.println("Disponibilités : " + profil.getDisponibilites());
        System.out.println("Type de course : " + profil.getTypeCourse());
        System.out.println("Préférence musique : " + profil.getMusicPreference());
        System.out.println("Préférence genre : " + profil.getGenderPreference());
        System.out.println("Taille bagages : " + profil.getLuggageSize());

        System.out.println("--- Modification du profil ---");
        System.out.print("Nouveau statut (1=PASSAGER, 2=CHAUFFEUR) : ");
        int statut = scanner.nextInt();
        scanner.nextLine();
        if (statut == 1) profil.setStatut(Profil.Statut.PASSAGER);
        else if (statut == 2) profil.setStatut(Profil.Statut.CHAUFFEUR);

        System.out.print("Nouvel itinéraire : ");
        String itineraire = scanner.nextLine();
        profil.setItineraire(itineraire);

        System.out.print("Nouvelles disponibilités : ");
        String disponibilites = scanner.nextLine();
        profil.setDisponibilites(disponibilites);

        System.out.print("Type de course (1=ALLER_RETOUR, 2=ALLER_SIMPLE, 3=RETOUR_SIMPLE) : ");
        int typeCourse = scanner.nextInt();
        scanner.nextLine();
        if (typeCourse == 1) profil.setTypeCourse(Profil.TypeCourse.ALLER_RETOUR);
        else if (typeCourse == 2) profil.setTypeCourse(Profil.TypeCourse.ALLER_SIMPLE);
        else if (typeCourse == 3) profil.setTypeCourse(Profil.TypeCourse.RETOUR_SIMPLE);

        // New preference options
        System.out.println("\n--- Nouvelles préférences ---");
        System.out.print("Préférence musique (1=OUI, 2=NON, 3=PEU IMPORTE) : ");
        int musicPref = scanner.nextInt();
        scanner.nextLine();
        if (musicPref == 1) profil.setMusicPreference(Profil.MusicPreference.YES);
        else if (musicPref == 2) profil.setMusicPreference(Profil.MusicPreference.NO);
        else if (musicPref == 3) profil.setMusicPreference(Profil.MusicPreference.DONT_CARE);

        System.out.print("Préférence genre (1=HOMME, 2=FEMME, 3=PEU IMPORTE) : ");
        int genderPref = scanner.nextInt();
        scanner.nextLine();
        if (genderPref == 1) profil.setGenderPreference(Profil.GenderPreference.MALE);
        else if (genderPref == 2) profil.setGenderPreference(Profil.GenderPreference.FEMALE);
        else if (genderPref == 3) profil.setGenderPreference(Profil.GenderPreference.ANY);

        System.out.print("Taille des bagages (1=AUCUN, 2=PETIT, 3=MOYEN, 4=GRAND) : ");
        int luggageSize = scanner.nextInt();
        scanner.nextLine();
        if (luggageSize == 1) profil.setLuggageSize(Profil.LuggageSize.NONE);
        else if (luggageSize == 2) profil.setLuggageSize(Profil.LuggageSize.SMALL);
        else if (luggageSize == 3) profil.setLuggageSize(Profil.LuggageSize.MEDIUM);
        else if (luggageSize == 4) profil.setLuggageSize(Profil.LuggageSize.LARGE);

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
                
                // Vérifier les préférences de musique
                boolean musicMatch = u.getProfil().getMusicPreference() == Profil.MusicPreference.DONT_CARE ||
                                   u.getProfil().getMusicPreference() == profilPassager.getMusicPreference();
                
                // Vérifier les préférences de genre
                boolean genderMatch = u.getProfil().getGenderPreference() == Profil.GenderPreference.ANY ||
                                    u.getProfil().getGenderPreference() == profilPassager.getGenderPreference();
                
                // Vérifier la compatibilité des bagages
                boolean luggageMatch = u.getProfil().getLuggageSize().ordinal() >= profilPassager.getLuggageSize().ordinal();
                
                if (musicMatch && genderMatch && luggageMatch) {
                    chauffeursDisponibles.add(u);
                }
            }
        }

        if (chauffeursDisponibles.isEmpty()) {
            System.out.println("Aucun chauffeur disponible pour cet itinéraire avec vos préférences.");
            return;
        }

        System.out.println("Chauffeurs disponibles pour l'itinéraire '" + itineraireRecherche + "' :");
        for (int i = 0; i < chauffeursDisponibles.size(); i++) {
            Utilisateur chauffeur = chauffeursDisponibles.get(i);
            Profil profilChauffeur = chauffeur.getProfil();
            System.out.println((i+1) + ". " + chauffeur.getNom() + " " + chauffeur.getPrenom() +
                    " (" + chauffeur.getMatricule() + ")");
            System.out.println("   Disponibilités : " + profilChauffeur.getDisponibilites());
            System.out.println("   Musique : " + profilChauffeur.getMusicPreference());
            System.out.println("   Genre : " + profilChauffeur.getGenderPreference());
            System.out.println("   Taille bagages acceptée : " + profilChauffeur.getLuggageSize());
        }

        System.out.print("Choisissez un chauffeur (numéro) ou 0 pour annuler : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix > 0 && choix <= chauffeursDisponibles.size()) {
            Utilisateur chauffeurChoisi = chauffeursDisponibles.get(choix - 1);
            System.out.println("Vous avez rejoint la course du chauffeur " +
                    chauffeurChoisi.getNom() + " " + chauffeurChoisi.getPrenom() + " !");
            Course c = new Course(chauffeurChoisi, passager, itineraireRecherche, 
                                chauffeurChoisi.getProfil().getDisponibilites(),
                                chauffeurChoisi.getProfil().getTypeCourse());
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
}

        public static void evaluerChauffeur() {
            System.out.println("Entrez votre matricule : ");
            String matricule = scanner.nextLine();
            
            if (admin.getCourses() == null || admin.getCourses().isEmpty()) {
                System.out.println("Aucune course disponible.");
                return;
            }
            
            boolean found = false;
            List<Course> coursesForUser = new ArrayList<>();
            
            // Recherche des courses où l'utilisateur est passager
            for (Course c : admin.getCourses()) {
                for (Utilisateur u : c.getPassagers()) {
                    if (u.getMatricule().equals(matricule)) {
                        coursesForUser.add(c);
                        found = true;
                        break;
                    }
                }
            }
            
            if (!found) {
                System.out.println("Aucune course trouvée avec votre matricule.");
                return;
            }
            
            // Afficher les courses trouvées
            System.out.println("Courses trouvées :");
            for (int i = 0; i < coursesForUser.size(); i++) {
                Course c = coursesForUser.get(i);
                System.out.println((i+1) + ". Chauffeur: " + c.getChauffeur().getNom() + 
                                   " " + c.getChauffeur().getPrenom() +
                                   ", Itinéraire: " + c.getItineraire());
            }
            
            System.out.print("Choisissez une course à évaluer (numéro) : ");
            int choixCourse = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            if (choixCourse < 1 || choixCourse > coursesForUser.size()) {
                System.out.println("Choix invalide.");
                return;
            }
            
            Course courseChoisie = coursesForUser.get(choixCourse - 1);
            Utilisateur chauffeur = courseChoisie.getChauffeur();
            
            System.out.println("Vous allez évaluer le chauffeur : " + chauffeur.getNom() + " " + chauffeur.getPrenom());
            
            // Demande de la note
            System.out.print("Entrez votre note (0-5) : ");
            int note = -1;
            do {
                try {
                    note = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Nettoyer l'entrée invalide
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } while (note < 0 || note > 5);
            
            // Demande du commentaire
            System.out.print("Entrez votre commentaire : ");
            String commentaire = scanner.nextLine();
            
            // Trouver l'utilisateur correspondant au matricule
            Utilisateur passager = null;
            for (Utilisateur u : courseChoisie.getPassagers()) {
                if (u.getMatricule().equals(matricule)) {
                    passager = u;
                    break;
                }
            }
            
            // Créer et ajouter l'évaluation
            Evaluation eval = new Evaluation(passager, chauffeur, note, commentaire);
            courseChoisie.ajouterEvaluation(eval);
            
            System.out.println("Évaluation enregistrée avec succès !");
            System.out.println(eval.toString());
        }
    }
