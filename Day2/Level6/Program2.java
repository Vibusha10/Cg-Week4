import java.util.*;

public class Program2 {
    private Map<String, Integer> voteCountMap = new HashMap<>();
    private Map<String, Integer> voteOrderMap = new LinkedHashMap<>();
    private Map<String, Integer> sortedVoteMap = new TreeMap<>((candidate1, candidate2) -> {
        int voteCount1 = voteCountMap.get(candidate1);
        int voteCount2 = voteCountMap.get(candidate2);
        return Integer.compare(voteCount2, voteCount1);
    });

    public void castVote(String candidate) {
        voteCountMap.put(candidate, voteCountMap.getOrDefault(candidate, 0) + 1);
        voteOrderMap.put(candidate, voteCountMap.get(candidate));
        sortedVoteMap.put(candidate, voteCountMap.get(candidate));
    }

    public void displaySortedResults() {
        System.out.println("Sorted Results (By Votes):");
        for (Map.Entry<String, Integer> entry : sortedVoteMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

    public void displayOrderOfVotes() {
        System.out.println("\nResults in Order of Votes Cast:");
        for (Map.Entry<String, Integer> entry : voteOrderMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

    public static void main(String[] args) {
        Program2 votingSystem = new Program2();

        votingSystem.castVote("Alice");
        votingSystem.castVote("Bob");
        votingSystem.castVote("Alice");
        votingSystem.castVote("Charlie");
        votingSystem.castVote("Bob");
        votingSystem.castVote("Alice");

        votingSystem.displaySortedResults();
        votingSystem.displayOrderOfVotes();
    }
}