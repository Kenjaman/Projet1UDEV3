package G7Netflix.modele;


public class Utils {
	
		public static boolean isBlank(String valeur) {
			return valeur == null || "".equals(valeur);
		}

		
		public static boolean isNotBlank(String valeur) {
			return ! isBlank(valeur);
		}
		
		
}
