import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("BASIC OPERATIONS");

        // 1. Create & Put
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Daniel", 85);
        scores.put("Amir", 92);
        scores.put("Dana", 77);
        System.out.println("1. Map: " + scores + " | Size: " + scores.size());

        // 2. Get & ContainsKey
        System.out.println("2. Get: Daniel " + scores.get("Daniel"));
        if (!scores.containsKey("Mira")) {
            System.out.println("   Mira not found (avoiding null confusion).");
        }

        // 3. Update Existing Value
        Integer oldDana = scores.put("Dana", 80);
        System.out.println("3. Dana's old score: " + oldDana + " | New map: " + scores);

        // 4. Remove by Key
        scores.remove("Amir");
        Object removedNone = scores.remove("NonExisting");
        System.out.println("4. After removing Amir: " + scores + " | NonExisting removal: " + (removedNone != null));

        // 5. isEmpty & clear
        System.out.println("5. Is empty? " + scores.isEmpty());
        scores.clear();
        System.out.println("   Is empty after clear? " + scores.isEmpty());

        // Re-filling for tasks 6-11
        scores.put("Daniel", 85);
        scores.put("Dana", 80);

        // 6. getOrDefault
        System.out.println("6. Mira's score (default): " + scores.getOrDefault("Mira", -1));

        // 7. putIfAbsent
        scores.putIfAbsent("Daniel", 90); // Won't change
        scores.putIfAbsent("Mira", 88); // Will insert
        System.out.println("7. After putIfAbsent: " + scores);

        // 8. replace
        scores.replace("Daniel", 85, 86); // Replaces because 85 matches
        scores.replace("Daniel", 91);     // Replaces unconditionally
        System.out.println("8. After replace: " + scores);

        // 9. Iterate over keys, values, entries
        System.out.print("9. Keys: " + scores.keySet());
        System.out.print(" | Values: " + scores.values());
        System.out.print(" | Entries: ");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

        // 10. Count scores >= 80
        int count80 = 0;
        for (int s : scores.values()) if (s >= 80) count80++;
        System.out.println("10. Count >= 80: " + count80);

        // 11. Find max score and who has it
        int maxVal = -1;
        String topStudent = "";
        for (var entry : scores.entrySet()) {
            if (entry.getValue() > maxVal) {
                maxVal = entry.getValue();
                topStudent = entry.getKey();
            }
        }
        System.out.println("11. Max score is " + maxVal + " by " + topStudent);

        System.out.println("\n--- SLIDE 2: PRACTICAL APPLICATIONS ---");

        // 12. Word Frequency Counter
        String text = "Java is fun and Java is powerful and fun";
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String w : text.toLowerCase().split(" ")) {
            wordCounts.put(w, wordCounts.getOrDefault(w, 0) + 1);
        }
        System.out.println("12. Word Freq: " + wordCounts);

        // 13. Character Frequency (letters only)
        String miss = "Mississippi";
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : miss.toCharArray()) charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        System.out.println("13. Char Freq: " + charFreq);

        // 14. Group Words by Length
        String[] words = {"hi", "book", "java", "sun", "loop", "map"};
        Map<Integer, List<String>> grouped = new HashMap<>();
        for (String w : words) {
            grouped.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        }
        System.out.println("14. Grouped by length: " + grouped);

        // 15. First Non-Repeating Character
        String swiss = "swiss";
        Map<Character, Integer> swissCounts = new LinkedHashMap<>(); // Linked keeps insertion order
        for (char c : swiss.toCharArray()) swissCounts.put(c, swissCounts.getOrDefault(c, 0) + 1);
        Character first = null;
        for (var entry : swissCounts.entrySet()) {
            if (entry.getValue() == 1) { first = entry.getKey(); break; }
        }
        System.out.println("15. First non-repeating in 'swiss': " + (first != null ? first : "None"));

        // 16. Two-Sum (Index Map)
        int[] nums = {2, 7, 11, 15}; int target = 9;
        Map<Integer, Integer> numMap = new HashMap<>();
        System.out.print("16. Two-Sum indices: ");
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (numMap.containsKey(diff)) System.out.println(numMap.get(diff) + ", " + i);
            numMap.put(nums[i], i);
        }

        // 17. Detect Duplicates
        String[] fruits = {"apple", "banana", "apple", "orange", "banana", "kiwi"};
        Map<String, Integer> fruitCounts = new HashMap<>();
        for (String f : fruits) fruitCounts.put(f, fruitCounts.getOrDefault(f, 0) + 1);
        System.out.println("17. Fruit Duplicates: " + fruitCounts);

        // 18. Equals & Order-Insensitivity
        HashMap<String, Integer> mapA = new HashMap<>(); mapA.put("A", 1); mapA.put("B", 2);
        HashMap<String, Integer> mapB = new HashMap<>(); mapB.put("B", 2); mapB.put("A", 1);
        System.out.println("18. Maps equal? " + mapA.equals(mapB) + " | HashCode A: " + mapA.hashCode());

        // 19. Remove Entries Conditionally
        Map<String, Integer> students = new HashMap<>();
        students.put("A", 50); students.put("B", 80); students.put("C", 40);
        Iterator<Map.Entry<String, Integer>> it = students.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() < 60) it.remove();
        }
        System.out.println("19. Students >= 60: " + students);

        // 20. Merge Scores
        Map<String, Integer> s1 = new HashMap<>(Map.of("Daniel", 40, "Amir", 35, "Dana", 50));
        Map<String, Integer> s2 = Map.of("Amir", 10, "Dana", 5, "Mira", 45);
        s2.forEach((name, val) -> s1.merge(name, val, Integer::sum));
        System.out.println("20. Merged: " + s1);

        // 21. Invert a Map
        Map<Integer, List<String>> inverted = new HashMap<>();
        s1.forEach((name, score) -> inverted.computeIfAbsent(score, k -> new ArrayList<>()).add(name));
        System.out.println("21. Inverted: " + inverted);

        // 22. Top-K Frequent Words (Top 2)
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCounts.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        System.out.println("22. Top 2 words: " + list.subList(0, Math.min(2, list.size())));
    }
}