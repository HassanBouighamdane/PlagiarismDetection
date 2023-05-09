package detectionPlagiat;

import java.util.ArrayList;
import java.util.Arrays;

public class DetectionPlagiatParagraphs {
    
    public static void main(String[] args) {
        String text1 = "This is a sample sentence.\n And it contains some words that are similar to text2.";
        String text2 = "This is another sample sentence.\n And it contains some words that are similar to text2.";
        
        ArrayList<String> PhraseSimilaire = chercheSimilairePhrase(text1, text2);
        System.out.println("Phrase similaire: " + PhraseSimilaire);
        
        int totalOccurrences = countTotalOccurrences(text2, PhraseSimilaire);
        System.out.println("Total occurrences: " + totalOccurrences);
        
        double TauxPlagiat = CalculerTauxPlagiat(text1, text2, PhraseSimilaire);
        System.out.println("Taux de plagiat: " + TauxPlagiat + "%");
        System.out.println(chercheSimilairePhrase("This is a sample sentence. It has more than one sentence. And it contains some words that are similar to text2.","This is another sample sentence. It also has more than one sentence. And it contains some words that are similar to text2."));
    }
    
    public static ArrayList<String> chercheSimilairePhrase(String text1, String text2) {
        ArrayList<String> PhraseSimilaire = new ArrayList<String>();
        String[] Phrase1 = text1.split("\n");
        String[] Phrase2 = text2.split("\n");
        for (String s1 : Phrase1) {
            for (String s2 : Phrase2) {
             // double similarity = calculateSimilarity(s1, s2);
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
    static double CalculerTauxPlagiat(String text1, String text2, ArrayList<String> similarSentences) {
        int totalPhrases1 = text1.split("\n").length;
        int totalPhrases2 = text2.split("\n").length;
        int minTotalPhrases = Math.min(totalPhrases1, totalPhrases2);
        int totalOccurrences = countTotalOccurrences(text2, similarSentences);
        double tauxPlagiat = (double) totalOccurrences / minTotalPhrases * 100;
        return tauxPlagiat;
    }
    
    public static int countOccurrences(String text, String Phrase) {
        int count = 0;
        String[] Phrases = text.split("\n");
        for (String s : Phrases) {
            if (s.equalsIgnoreCase(Phrase)) {
                count++;
            }
        }
        return count;
    }
    
   
}
