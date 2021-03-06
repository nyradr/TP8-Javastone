import java.util.ArrayList;
import java.util.List;

public class GamePanel {

	private Joueur joueur1;
	private Joueur joueur2;

	DrawingPanel draw;

	public GamePanel() {
		this.joueur1 = new Joueur("player 1");
		this.joueur2 = new Joueur("player 2");

		this.draw = new DrawingPanel();
	}

	/**
	 * Obtient l'instace Joueur du joueur adverse
	 * 
	 * @param player
	 *            joueur courant
	 * @return son adversaire
	 */
	private Joueur getAdversaire(Joueur player) {
		if (player == this.joueur1)
			return joueur2;
		return joueur1;
	}
	
	
	/**
	 * Chosis une creature parmis celles du joueur
	 * @param player joueur
	 * @param sp true pour éliminer le joueur du choix (impossible de cibler le joueur)
	 * @return
	 */
	private Creature choseCrea(Joueur player, boolean sp){
		List<Creature> creas = player.getPlateau();
		if(sp)
			creas = player.getCrea();
		
		int item = draw.menu("Choisisez une cible", creas.toArray(new Creature[0]));
		
		if(sp)
			return player.getCrea().get(item);
		else
			return player.getPlateau().get(item);
	}
	
	/**
	 * Obtient la liste des cibles potentiels
	 * @param player
	 * @param t
	 * @return
	 */
	private List<IEngineTarget> gettargets(Joueur player, Target t){
		List<IEngineTarget> targets = new ArrayList<IEngineTarget>();
		Creature maxdmg = getAdversaire(player).getPlayerInstance();
		
		switch (t) {
		case ALLPLAYER:
			targets.add(player);
			targets.add(getAdversaire(player));
			break;

		case PLAYER:
			targets.add(player);
			break;
			
		case ADV:
			targets.add(getAdversaire(player));
			break;
			
		case ALLCREA:
			targets.addAll(player.getCrea());
			targets.addAll(getAdversaire(player).getCrea());
			break;
			
		case PLAYERCREA:
			targets.addAll(player.getCrea());
			break;
			
		case ADVCREA:
			targets.addAll(player.getCrea());
			break;
			
		case ALL:
			targets.addAll(player.getPlateau());
			targets.addAll(getAdversaire(player).getPlateau());
			break;
			
		case CHOSEADV:
			targets.add(choseCrea(getAdversaire(player), false));
			break;
			
		case CHOSEPLAYER:
			targets.add(choseCrea(player, false));
			break;
			
		case CHOSEALL:
			switch (draw.menu("Equipe cible", new String[]{"Vous", "Adversaire"})) {
			case 0:
				targets.add(choseCrea(player, false));
				break;
			case 1:
				targets.add(choseCrea(getAdversaire(player), false));
				break;
			default:
				break;
			}
			break;
			
		case CREAADVMAXDMG:
			for(Creature c : getAdversaire(player).getCrea())
				if(c.getDamage() > maxdmg.getDamage())
					maxdmg = c;
			targets.add(maxdmg);
			break;
			
		default:
			break;
		}
		
		return targets;
	}

	/**
	 * Interprete l'effet d'une carte
	 * 
	 * @param c
	 *            carte à interpreter
	 * @param player
	 *            joueur courant
	 * @throws Exception 
	 */
	private void interpret(Jouable c, Joueur player, Declancheur event) throws Exception {
		int nbr = 0;
		Creature crea;
		
		for (Engine e : c.getEffets(event)) {
			
			List<IEngineTarget> cibles = gettargets(player, e.getTarget());
			if(e.getTarget() == Target.SELF)
				cibles.add((IEngineTarget) c);
			
			for(IEngineTarget t : cibles){
				switch (e.getType()) {
				case INVOKE:
					crea = t.eng_invoke(e.getArgs()[0], c.fileName);
					if(crea != null)
						interpret(crea, player, Declancheur.PLAY);
					
					break;
					
				case BUFF:
					try{
						nbr = Integer.parseInt(e.getArgs()[2]);
						if(nbr == 0)
							nbr = player.getCrea().size() -1;
						else if(nbr == 1)
							nbr = getAdversaire(player).getCrea().size() -1;
					}catch (Exception e2) {
						nbr = 1;
					}
					
					for(int i = 0; i < nbr; i++){
						t.eng_buff(Integer.parseInt(e.getArgs()[0])
								,Integer.parseInt(e.getArgs()[1]));
						if(e.getTarget() != Target.SELF)
							interpret((Jouable) t, player, Declancheur.MODIF);
					}
					break;
					
				case DECK:
					nbr = Integer.parseInt(e.getArgs()[0]);
					if(nbr == 0)
						nbr = getAdversaire(player).getCrea().size();
					
					t.eng_deck(nbr);
					break;
					
				case RENVOIS:
					getAdversaire(player).getDeck().addCard(new Carte(t.eng_renv()));
					getAdversaire(player).getDeck().generateAleat();
					break;
				}
			}
			player.clearDead();
			getAdversaire(player).clearDead();
		}
	}

	/**
	 * Fait jouer une carte au joueur
	 * 
	 * @param player
	 */
	private void playCard(Joueur player) {
		int cartejouer = draw.menu("Quelle carte jouez vous", (Drawable[]) player.getMain().toArray(new Carte[0]));

		Carte cj = player.getMain().get(cartejouer);

		if (cj.getMana() <= player.getMana()) {
			try{
				interpret(player.getMain().get(cartejouer), player, Declancheur.PLAY);
				player.playCard(cj);
			}catch(Exception e){
				draw.error("Erreur : Impossible d'appliquer l'effet de la carte, elle n'est donc pas jouer");
			}
		} else
			draw.error("Pas assez de mana");
	}

	/**
	 * Fait attaquer une creature au joueur
	 * 
	 * @param player
	 */
	private void attaque(Joueur player) {
		if(player.getCrea().isEmpty())
			return;
		
		Creature attaquant;
		Creature cible;

		attaquant = choseCrea(player, true);
		cible = choseCrea(getAdversaire(player), false);
		
		if(!attaquant.getFatigue() && !(getAdversaire(player).asGuardien() && !cible.isGuardian())){

			cible.takeDamage(attaquant.getDamage());
			attaquant.takeDamage(cible.getDamage());
			
			try{
				if(attaquant.getDamage() > 0)
					interpret(cible, getAdversaire(player), Declancheur.MODIF);
				if(cible.getDamage() > 0)
					interpret(attaquant, player, Declancheur.MODIF);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(attaquant.isDead())
				player.clearDead();
			else
				attaquant.setFatigue(true);
			
			if(cible.isDead())
				getAdversaire(player).clearDead();
		}else{
			if(attaquant.getFatigue())
				draw.error("Impossible d'attaquer avec une creature fatiguée");
			
			if(!(getAdversaire(player).asGuardien() && !cible.isGuardian()))
				draw.error("Impossible d'attaquer cette cible en présence d'un gardien");
		}
	}

	/**
	 * Fait joueur le tour d'un joueur
	 * 
	 * @param player
	 */
	private void playerTurn(Joueur player) {
		player.newTurn();

		draw.getPrinter().println(player.getName() + " debut de votre tour...");

		boolean end = false;
			while(!end){
			// Adversaire
			draw.getPrinter().println("\nAdversaire:");
			draw.draw(getAdversaire(player), true);

			// Joueur:
			draw.getPrinter().println("\nVous:");
			draw.draw(player, false);	
				
			switch (draw.menu("Que voullez vous faire",
					new String[] { "Jouer une carte", "Attaquer avec une creature", "Fin du tour" })) {
			case 0:
				if (!player.getMain().isEmpty())
					playCard(player);
				break;
	
			case 1:
				if (player.getPlateau().size() > 1)
					attaque(player);
				break;
	
			default:
				end = true;
				break;
			}
		}
	}

	/**
	 * Initialise les joueurs
	 * @param player
	 */
	private void initPlayer(Joueur player){
		draw.draw("Initialisation de " + player.getName());
		boolean ctn = true;
		
		do{
			if (draw.menu("Deck", new String[]{"Depuis un fichier", "Creation manuelle"}) == 0) {
				draw.draw("depuis un deck");
				ctn = draw.loadDeck(player.getDeck());
			}else{
				draw.draw("manuelle");
				draw.buildDeck(player.getDeck());
			}
		}while(!ctn);

		player.getDeck().generateAleat();
		for(int i = 0; i < 2; i++)
			player.pioche();
		
		player.getPlayerInstance().setFatigue(false);
	}

	/**
	 * Demare une partie à 2 joueurs
	 */
	public void startGame() {
		initPlayer(this.joueur1);
		initPlayer(this.joueur2);

		do {
			playerTurn(this.joueur1);
			if (!this.joueur2.isDead() && !this.joueur1.isDead())
				playerTurn(this.joueur2);
		} while (!this.joueur1.isDead() && !this.joueur2.isDead());
	}

}
