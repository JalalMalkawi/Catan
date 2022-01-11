import java.util.Random;
import java.util.Scanner;

public class Jeu {

    private Joueur joueur;
    private Plateau plato;
    
    
    public Jeu(Joueur j, Plateau p){
        joueur=j;
        plato=p;
    } 

    public void tour(){    
        Joueur[] participants=plato.getJoueurs();
        while(joueur.veutJouer()){
            int action= joueur.demanderAction();
            switch(action){
                case 1:
                    int de=joueur.lancerDe();
                    System.out.println("Vous avez obtenu aux dés le nombre :"+de);
                    if(de!=7){
                        System.out.println("Distribution des ressources...");
                        plato.distribueRessources(de);
                    }else{
                        System.out.println("Vous allez deplacer le voleur");
                        int[] coordonnesVoleur  = joueur.demandeCoordonneesVoleur();
                        int absVoleur = coordonnesVoleur[0];
                        int ordVoleur = coordonnesVoleur[1];
                        Tuile voleur = plato.getTuiles()[absVoleur][ordVoleur];
                        while( voleur.getVoleurPresent() || plato.tuileHorsLimite(absVoleur, ordVoleur) ){
                            System.out.println("Veuillez saisir des coordonnées valides : des coordonnees d'une tuile dans les limites du plateau, et sans voleur. \n ");
                            coordonnesVoleur = joueur.demandeCoordonneesVoleur();
                            absVoleur = coordonnesVoleur[0];
                            ordVoleur = coordonnesVoleur[1];
                            voleur = plato.getTuiles()[absVoleur][ordVoleur];
                        }
                        voleur.setVoleurPresent(true);
                        System.out.println("Tous les joueurs qui ont plus de 7 cartes vont perdre la moitié de leur carte");
                        joueur.perdrecarte(participants);//tous les joueurs qui ont plus de 7 cartes vont perdre la moitié de leur cartes
                        System.out.println("Vous allez voler une carte à l'un des joueurs que vous venez d'embêter avec le voleur"); 
                        joueur.volercarte(plato, coordonnesVoleur);//voler une carte d'un joueur proche de la tuile où l'on vient de placer le volleur
                    }   
                break;
                case 2:
                    if(joueur.peutConstruireRoute()){   // verifie si le joueur a les ressource nécéssaire pour construire une route
                       int [] coordonneesRoute =joueur.demanderCordonneesRoute(); // on demande les coordonné de la route
                       if(plato.peutConstruireRoute(coordonneesRoute[0], coordonneesRoute[1], coordonneesRoute[2], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                               plato.ajouteRoute(coordonneesRoute [0], coordonneesRoute[1], coordonneesRoute[3],joueur); //construction de la route
                               joueur.suprimerCarte(new Carte("bois"),1);   //supression des ressource utilisé 
                               joueur.suprimerCarte(new Carte("argile"),1);   //pour la construction d'une      
                       }
                       else{
                        System.out.println("Vous ne pouvez pas construire une route aux coordonnées indiquées");
                       }
                    }
                break;
                case 3:
                    if(joueur.peutConstruireVille()){
                        System.out.println("Vous avez les ressources nécessaires pour construire une ville");
                        int []coordonneesVille =joueur.demanderCoordonneesBatiment();
                        if(plato.peutConstruireVille(coordonneesVille[0], coordonneesVille[1], joueur)){
                            plato.ajouteVille(coordonneesVille[0], coordonneesVille[1], joueur);
                            joueur.suprimerCarte(new Carte("ble"),2);// suppression des ressource utilisées par le joueur pour la construction
                            joueur.suprimerCarte(new Carte("minerai"),3);
                            joueur.ajoutePoints(2); //incrémenter le nombre de points de victoire
                        }else{
                            System.out.println("Vous ne pouvez pas construire une ville aux coordonnées indiquées");
                        }
                    }
                break;
                case 4:
                    if(joueur.peutConstruireColonie()){ 
                        int []coordonneesColonie = joueur.demanderCoordonneesBatiment();
                        if(plato.peutConstruireColonie(coordonneesColonie[0], coordonneesColonie[1], joueur)){ //On vérifie qu'on peut placer une colonie au coordonnées indiquées ...
                            plato.ajouteColonie(coordonneesColonie[0], coordonneesColonie[1], joueur);//...place la colonie,
                            joueur.suprimerCarte(new Carte("bois"), 1); //Et on supprime les ressources utilisées pour la construction,...
                            joueur.suprimerCarte(new Carte("argile"), 1);
                            joueur.suprimerCarte(new Carte("ble"), 1);
                            joueur.suprimerCarte(new Carte("laine"), 1);
                            joueur.setNbpoints(joueur.getNbpoints()+1);//...pour à la fin incrémenter le nombre de points de victoire du joueur.
                        }else{
                            System.out.println("Vous ne pouvez pas construire une colonie au coordonné indiqué");
                        }
                    }
                break;
                case 5: //jouer la carte chevalier;
                    if(joueur.getNbreChevalierJouer()<joueur.nombrechevalier()){
                        System.out.println("Vous avez joué une carte chevalier vous allez déplacez le voleur");
                        //deplacer le voleur 
                        int []gre=joueur.demandeCoordonneesVoleur();
                        while(plato.getTuiles()[gre[0]][gre[1]].getVoleurPresent()){
                            System.out.println("impossible :le voleur est déja present sur cette tuille \n ");
                            gre=joueur.demandeCoordonneesVoleur();
                        }
                        plato.getTuiles()[gre[0]][gre[1]].setVoleurPresent(true);
                        //voler la carte d'un joueur
                        System.out.println("Vous allez volez une carte à l'un des joueur que vous venez d'embeter par le voleur"); 
                        joueur.volercarte(plato, gre);   
                        joueur.setNbreChevalierJouer(); 
                    }else{
                        System.out.println("Désolé vous avez jouez tous vos carte chevalier");
                    }
                break;
                case 6:// acheter une carte developement
                        if(joueur.peutAcheterCarteDeveloppement()){
                            System.out.println("Vous avez les ressource nécessaire pour acheter une carte devellopement");
                            String [] dev={"chevalier","Construire 2 route","invention","Monopole","bibiotheque","place du marché","parlement","université"};
                            Random r=new Random();
                            int a=r.nextInt(dev.length);
                            if(a==0){
                                joueur.ajouteCarteDeve(new CarteDeDeveloppement("chevalier",2));
                            }
                            else if(a==1){
                                int b=2;
                                while(b>0){
                                    System.out.println("Bravo vous venez de gagné une carte progée qui vous donne la possibilité de construire deux route gratuitement\n");
                                    int [] coordonnesVoleur =joueur.demanderCordonneesRoute(); 
                                    if(plato.peutConstruireRoute(coordonnesVoleur [0], coordonnesVoleur [1], coordonnesVoleur [2], joueur)){ 
                                        plato.ajouteRoute(coordonnesVoleur [0], coordonnesVoleur [1], coordonnesVoleur [3],joueur); 
                                        b--;
                                    }
                                }
                            }else if(a==2){
                                System.out.println("Bravo! vous avez gagné une carte invention;vous avez la possibilité de choisir deux ressource de votre choix qui seront ajouté aux votre\n");
                                int c=2;
                                while(c>0){
                                   joueur.invention();
                                   c--;
                                }
                            }else if(a==3){
                                System.out.println("Bravo vous venez de gagner le droit de monopoliser une ressource \n");
                                String carte=joueur.demanderStr("Donner le nom de la ressource dont vous voulez avoir le monopole");
                                joueur.monopole(new Carte(carte.toLowerCase()), participants);
                            }else{
                                System.out.println("bravo vous venez de gagner une carte dévelopemment qui vous rapporte un point de victoire");
                                joueur.ajouteCarteDeve(new CarteDeDeveloppement(dev[a], 1));
                                joueur.setNbpoints(joueur.getNbpoints()+1);
                            }
                        }else{
                            System.out.println("Désolé vous n'avez pas assez de ressources pour acheter une carte développement");
                        }
                break;

                case 7: 
                    joueur.afficheRessoure();
                break;
                // case 8: //faire un echange 
                // break;
            }
            joueur.armeeLaplusPuissante(participants);
            plato.afficheTabR();
        }     
       
    }
   
}