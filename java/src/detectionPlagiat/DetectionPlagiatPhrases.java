package detectionPlagiat;

import java.util.ArrayList;
import java.util.Arrays;

public class DetectionPlagiatPhrases {
    
    public static void main(String[] args) {
        String text1 = "This is a sample sentence. And it contains some words that are similar to text2.";
        String text2 = "This is another sample sentence. And it contains some words that are similar to text2.";
        
        ArrayList<String> PhraseSimilaire = ChercherPhraseSimailaire(text1, text2);
        System.out.println("Phrases similaires: " + PhraseSimilaire);
        
        int totalOccurrences = countTotalOccurrences(text2, PhraseSimilaire);
        System.out.println("Total des occurrences: " + totalOccurrences);
        
        double TauxPlagiat = CalculerTauxPlagiat(text1, text2, PhraseSimilaire);
        System.out.println("Taux de Plagiat: " + TauxPlagiat + "%");
        System.out.println(ChercherPhraseSimailaire("This is a sample sentence. It has more than one sentence. And it contains some words that are similar to text2.","This is another sample sentence. It also has more than one sentence. And it contains some words that are similar to text2."));
    }
    
    public static ArrayList<String> ChercherPhraseSimailaire(String text1, String text2) {
        ArrayList<String> PhraseSimilaire = new ArrayList<String>();
        String[] Phrase1 = text1.split("[.?!]\\s*");
        String[] Phrase2 = text2.split("[.?!]\\s*");
        for (String s1 : Phrase1) {
            for (String s2 : Phrase2) {
             
                if(s1.equals(s2))
                    PhraseSimilaire.add(s1);
                
            }
        }
        return PhraseSimilaire;
    }
    
    public static int countTotalOccurrences(String text, ArrayList<String> Phrases) {
        int totalOccurrences = 0;
        for (String s : Phrases) {
            totalOccurrences += countOccurrences(text, s);
        }
        return totalOccurrences;
    }
    
    public static double CalculerTauxPlagiat(String text1, String text2, ArrayList<String> PhrasesSimilaires) {
        int totalPhrase1 = text1.split("[.?!]\\s*").length;
        int totalPhrase2 = text2.split("[.?!]\\s*").length;
        int minTotalPhrases = Math.min(totalPhrase1, totalPhrase2);
        int totalOccurrences = countTotalOccurrences(text2, PhrasesSimilaires);
        double TauxPlagiat = (double) totalOccurrences / minTotalPhrases * 100;
        return TauxPlagiat;
    }
    
    public static int countOccurrences(String text, String Phrase) {
        int count = 0;
        String[] Phrases = text.split("[.?!]\\s*");
        for (String s : Phrases) {
            if (s.equalsIgnoreCase(Phrase)) {
                count++;
            }
        }
        return count;
    }
    
   
}
