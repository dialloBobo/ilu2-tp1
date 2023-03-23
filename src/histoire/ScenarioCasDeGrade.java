package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDeGrade {

	public static void main(String[] args) {

		
		
		Etal etal = new Etal();
		Gaulois golo = new Gaulois("golo",10);
		// etal.libererEtal();
		try {
			// System.out.println(etal.acheterProduit(2, golo));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
		}
		
	}

