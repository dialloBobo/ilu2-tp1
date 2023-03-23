package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		this.marche = new Marche(nbEtal);
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	private static class Marche {
		private Etal[] etals;
		int nbEtal;
		

		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}

		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtatLibre() {
			int indiceEtalDispo = -1;
			int i = 0;
			while (indiceEtalDispo == -1 && i < etals.length) {
				if (!etals[i].isEtalOccupe())
					indiceEtalDispo = i;
				i++;
			}
			return indiceEtalDispo;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtal = 0;
			for (Etal etal : etals) {
				if (etal.isEtalOccupe() && etal.contientProduit(produit)) {
					nbEtal++;
				}
			}
			Etal[] etalsProduitsRecherche = null;
			if (nbEtal > 0) {
				etalsProduitsRecherche = new Etal[nbEtal];
				int nbEtalTrouve = 0;
				for (int i = 0; i < etals.length && nbEtalTrouve < nbEtal; i++) {
					if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
						etalsProduitsRecherche[nbEtalTrouve] = etals[i];
						nbEtalTrouve++;
					}
				}
			}
			return etalsProduitsRecherche;
		}

		Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;

		}
		

		private String afficherMarche() {
			int nbEtalvide = 0;
			StringBuilder chaine = new StringBuilder();
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalvide++;
				}
			}
			chaine.append("Il reste " + nbEtalvide + " etals non utilises dans le marche.\n");
			return chaine.toString();
		}

	}

	
	

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtalL = marche.trouverEtatLibre();

		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		
		if (indiceEtalL != -1) {
			marche.utiliserEtal(indiceEtalL, vendeur, produit, nbProduit);
			chaine.append("le vendeur " + vendeur.getNom() + " vend des " + produit + " " + " à l'étal n° "
					+ (indiceEtalL + 1));

		}
		else{
			chaine.append("impossible de s'installer\n");
		}
		return chaine.toString();

	}




	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des " + produit + " sont :");
		Etal[] vendeursProduit = marche.trouverEtals(produit);
		for (int i = 0; i < vendeursProduit.length; i++) {
			chaine.append("- " + vendeursProduit[i].getVendeur() + "\n");
		}
		return chaine.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		StringBuilder chaine = new StringBuilder();
		chaine.append(etal.libererEtal());
		return chaine.toString();

	}

	public String afficherMarche() {

		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village << le village des irréductibles >> possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
//



	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
}





