package detectionPlagiat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectionPlagiatMots {

	public static void main(String[] args) {
		System.out.println(compterTotalSimilaireMots("this have lots kali linux kahlj", "this have lots hjhjyghh hhhh "));
		System.out.println(calculerTauxPlagiat("this have lots kali linux kahlj", "this have lots hjhjyghh hhhh kjhk"));

	}
	public static List<String> extrairetMots(String text) {
	    List<String> Mots = new ArrayList<>();
	    String[] tokens = text.split("\\s+");
	    for (String token : tokens) {
	        // supprimer tout non-alphabetic caractère 
	        token = token.replaceAll("[^a-zA-Z]", "");
	        
	        if (token.length() > 3) {
	            Mots.add(token);
	        }
	    }
	    return Mots;
	}
	public static List<String> chercheMotsSimilaire(String text1, String text2) {
	    List<String> Mots1 = extrairetMots(text1);
	    List<String> Mots2 = extrairetMots(text2);
	    List<String> MotsSimilaire = new ArrayList<>();
	    for (String Mot : Mots1) {
	        if (Mots2.contains(Mot)) {
	            MotsSimilaire.add(Mot);
	        }
	    }
	    return MotsSimilaire;
	}
	public static int countOccurrences(String text, String mot) {
	    int count = 0;
	    String[] Mots = text.split("\\s+");
	    for (String m : Mots) {
	        if (m.equalsIgnoreCase(mot)) {
	            count++;
	        }
	    }
	    return count;
	}


	public static int compterTotalSimilaireMots(String text1, String text2) {
	    List<String> MotsSimilaire = chercheMotsSimilaire(text1, text2);
	    int total = 0;
	    for (String Mot : MotsSimilaire) {
	        total += countOccurrences(text1, Mot);
	        total += countOccurrences(text2, Mot);
	    }
	    return total;
	}
	public static int countMots(String text) {
	    String[] Mots = text.split("\\s+");
	    return Mots.length;
	}

	public static double calculerTauxPlagiat(String text1, String text2) {
	    int totalMots1 =countMots( text1);
	    int totalMots2 =countMots( text2);
	    int totalSimilaireMots = compterTotalSimilaireMots(text1, text2);
	    double TauxPlagiat = (double) totalSimilaireMots / (totalMots1+ totalMots2) * 100;
	    return TauxPlagiat;
	}






}
