package plagiarismDetection;

import java.util.ArrayList;
import java.util.Arrays;

public class compare {
    public double countSimilarSentences(String text1, ArrayList<String> files) {
        String[] sentences = text1.split("[.!?]");
        ArrayList<String> sentencesArr = new ArrayList<>(Arrays.asList(sentences));

        ArrayList<String> originalSentences = removeSimilarSentences(sentencesArr, files);

        double similarityPercentage = (double) (sentencesArr.size() - originalSentences.size()) / sentencesArr.size();
        return similarityPercentage * 100;
    }

    public double countSimilarSentences(String text1, String files) {
        String[] sentences = text1.split("[.!?]");
        ArrayList<String> sentencesArr = new ArrayList<>(Arrays.asList(sentences));

        ArrayList<String> originalSentences = removeSimilarSentences(sentencesArr, files);

        double similarityPercentage = (double) (sentencesArr.size() - originalSentences.size()) / sentencesArr.size();
        return similarityPercentage * 100;
    }

    public ArrayList<String> removeSimilarSentences(ArrayList<String> sentencesArr, ArrayList<String> files) {
        ArrayList<String> remainingSentences = new ArrayList<>(sentencesArr);

        for (String file : files) {
            String[] sentences = file.split("[.!?]");

            for (String sentence : sentences) {
                String trimmedSentence = sentence.trim();

                if (remainingSentences.stream().anyMatch(s -> isSimilar(s, trimmedSentence))) {
                    remainingSentences.removeIf(s -> isSimilar(s, trimmedSentence));
                }
            }
        }

        return remainingSentences;
    }

    public ArrayList<String> removeSimilarSentences(ArrayList<String> sentencesArr, String file) {
        ArrayList<String> remainingSentences = new ArrayList<>(sentencesArr);

        String[] sentences = file.split("[.!?]");

        for (String sentence : sentences) {
            String trimmedSentence = sentence.trim();

            if (remainingSentences.stream().anyMatch(s -> isSimilar(s, trimmedSentence))) {
                remainingSentences.removeIf(s -> isSimilar(s, trimmedSentence));
            }

        }

        return remainingSentences;
    }

    private boolean isSimilar(String sentence1, String sentence2) {
        return sentence1.trim().equalsIgnoreCase(sentence2.trim());
    }

}
