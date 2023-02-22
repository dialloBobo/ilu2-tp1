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

		int trouverEtatLibre() {
			for (int i = 0; i < nbEtal; i++) {
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
			}

			return -1;

		}

		Etal[] trouverEtals(String produit) {
			Etal[] etal = new Etal[etals.length];
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etal[i] = etals[i];
				}
			}

			return etal;

		}

		Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;

		}

		public String afficherMarche() {
			int nombre = 0;

			for (int i = 0; i < etals.length; i++) {
				if ((etals[i].isEtalOccupe())) {
					etals[i].afficherEtal();
					nombre += 1;
				}
			}
			int nbEtalVide = nbEtal - nombre;
			return "Il reste " + nbEtalVide + " étals non utilisés dans le marché \n";
		}
		

	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtalL = marche.trouverEtatLibre();

			chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");

			chaine.append(
					"le vendeur " + vendeur.getNom() + " vend des " + produit + " " + " à l'étal n° " + indiceEtalL);



		return chaine.toString();

	}


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