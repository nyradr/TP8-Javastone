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
	 * Invoque une creature
	 * 
	 * @param crea
	 * @param player
	 */
	private void applyInvoke(String crea, Joueur player) {
		try {
			Creature c = new Creature(crea);
			player.invoke(c);
		} catch (Exception e) {
			draw.getPrinter().print("Error : impossible de charger la creature " + crea);
		}
	}

	/**
	 * Applique un buff à la cible
	 * 
	 * @param dmg
	 * @param life
	 * @param player
	 * @param target
	 */
	private void applyBuff(int dmg, int life, Joueur player, int target) {

		if (target < player.getPlateau().size())
			player.getPlateau().get(target).buffCreature(life, dmg);

		// TODO erreur target
	}

	/**
	 * Inflique des dégats à la cible
	 * 
	 * @param dmg
	 * @param player
	 * @param target
	 */
	private void applyDmg(int dmg, Joueur player, int target) {
		if (target < player.getPlateau().size())
			player.getPlateau().get(target).takeDamage(dmg);

		// TODO erreurs target
	}

	/**
	 * Fait piocher n carte au joueur
	 * 
	 * @param n
	 * @param player
	 */
	private void applyDeck(int n, Joueur player) {
		for (int i = 0; i < n; i++)
			player.pioche();
	}

	/**
	 * Applique l'effet au joueur
	 * 
	 * @param e
	 *            effet
	 * @param player
	 *            joueur
	 * @param target
	 *            cible(dans le cas d'un BUFF ou d'un DMG)
	 */
	private void applyEngineOn(Engine e, Joueur player) {
		int target = 0;

		if (e.getType() == CardType.BUFF || e.getType() == CardType.DAMAGE) {
			target = draw.menu("Cible", (Drawable[]) player.getPlateau().toArray());
		}

		switch (e.getType()) {
		case INVOKE:
			applyInvoke(e.getArgs()[0], player);
			break;

		case BUFF:
			applyBuff(Integer.parseInt(e.getArgs()[0]), Integer.parseInt(e.getArgs()[1]), player, target);
			break;

		case DAMAGE:
			applyDmg(Integer.parseInt(e.getArgs()[0]), player, target);
			break;

		case DECK:
			applyDeck(Integer.parseInt(e.getArgs()[0]), player);
			break;

		default:
			break;
		}
	}

	/**
	 * Interprete l'effet d'une carte
	 * 
	 * @param c
	 *            carte à interpreter
	 * @param player
	 *            joueur courant
	 */
	private void interpret(Carte c, Joueur player) {
		System.out.println(c.getEffect());
		
		for (Engine e : Engine.extractEffects(c.getEffect())) {
			switch (e.getTarget()) {
			case ALL:
				applyEngineOn(e, player);
				applyEngineOn(e, getAdversaire(player));
				break;

			case PLAYER:
				applyEngineOn(e, player);
				break;

			case ADV:
				applyEngineOn(e, getAdversaire(player));
				break;
			}
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
			interpret(player.getMain().get(cartejouer), player);
			player.playCard(cj);
		} else
			draw.draw("Pas assez de mana");
	}

	/**
	 * Fait attaquer une creature au joueur
	 * 
	 * @param player
	 */
	private void attaque(Joueur player) {
		Creature attaquant;
		Creature cible;

		do {
			attaquant = player.getPlateau()
					.get(draw.menu("Attquant", (Drawable[]) player.getPlateau().toArray(new Creature[0])));
			cible = getAdversaire(player).getPlateau().get(
					draw.menu("defenseur", (Drawable[]) getAdversaire(player).getPlateau().toArray(new Creature[0])));
		} while (attaquant == player.getPlayerInstance()
				&& !(cible == getAdversaire(player).getPlayerInstance() && getAdversaire(player).asGuardien()));

		cible.takeDamage(attaquant.getDamage());
		attaquant.takeDamage(cible.getDamage());
	}

	/**
	 * Fait joueur le tour d'un joueur
	 * 
	 * @param player
	 */
	private void playerTurn(Joueur player) {
		player.newTurn();

		draw.getPrinter().println(player.getName() + " debut de votre tour...");

		// Adversaire
		draw.getPrinter().println("\nAdversaire:");
		draw.draw(getAdversaire(player), true);

		// Joueur:
		draw.getPrinter().println("\nVous:");
		draw.draw(player, false);

		switch (draw.menu("Que voullez vous faire",
				new String[] { "Jouer une carte", "Attaquer avec une creature", "Rien faire" })) {
		case 0:
			if (!player.getMain().isEmpty())
				playCard(player);
			break;

		case 1:
			if (player.getPlateau().size() > 1)
				attaque(player);
			break;

		default:
			draw.getPrinter().println("Vous n'avez rien fait");
			break;
		}
	}

	/**
	 * Initialise les joueurs
	 * @param player
	 */
	private void initPlayer(Joueur player){
		System.out.println("Initialisation de " + player.getName());
		/*
		if (draw.menu("Deck", new String[]{"Depuis un fichier", "Creation manuelle"}) == 0) {
			System.out.println("depuis un deck");
			
		}else{
			System.out.println("manuelle");
			draw.buildDeck(player.getDeck());
		}*/
		
		player.getDeck().addCard(new Carte(CardType.INVOKE, 1, "test", "test", "0 1 crea"));
		player.pioche();
		
		
	}

	/**
	 * Demare une partie à 2 joueurs
	 */
	public void startGame() {
		initPlayer(this.joueur1);
		initPlayer(this.joueur2);

		do {
			playerTurn(this.joueur1);
			if (!this.joueur2.isDead())
				playerTurn(this.joueur2);
		} while (!this.joueur1.isDead() && !this.joueur2.isDead());
	}

}
