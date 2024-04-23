import java.io.*;
import java.util.*;

public class Q1{

    private static List<String> wordList = new ArrayList<>();

    public static void main(String[] args) {
        
        readFile("input.txt");

        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a word: ");
            String query = scanner.nextLine().trim();
            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                break;
            }
            List<String> suggestions = search(query, 5); 
            System.out.println("Suggestions:");
            for (String suggestion : suggestions) {
                System.out.println(suggestion);
            }
        }
        scanner.close();
    }

    
    private static void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> search(String query, int k) {
        Map<String, Integer> similarityMap = new HashMap<>();
        for (String word : wordList) {
            int distance = levenshteinDistance(query, word);
            similarityMap.put(word, distance);
        }
        List<String> sortedWords = new ArrayList<>(similarityMap.keySet());
        sortedWords.sort(Comparator.comparingInt(similarityMap::get));
        return sortedWords.subList(0, Math.min(k, sortedWords.size()));
    }

    
    private static int levenshteinDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int cost = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));
            }
        }
        return dp[word1.length()][word2.length()];
    }
}