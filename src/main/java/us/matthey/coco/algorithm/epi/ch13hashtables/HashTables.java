package us.matthey.coco.algorithm.epi.ch13hashtables;

import java.util.*;

public class HashTables {
    public static List<List<String>> findAnagrams(List<String> dictionary) {
        Map<String, List<String>> sortedStringAnagrams = new HashMap<>();
        for (String s : dictionary) {
            char[] sortedCharArray = s.toCharArray();
            Arrays.sort(sortedCharArray);
            String sortedStr = new String(sortedCharArray);
            if (!sortedStringAnagrams.containsKey(sortedStr)) {
                sortedStringAnagrams.put(sortedStr, new ArrayList<String>());
            }
            sortedStringAnagrams.get(sortedStr).add(s);
        }

        List<List<String>> anagramGroups = new ArrayList<>();
        for (Map.Entry<String, List<String>> p : sortedStringAnagrams.entrySet()) {
            if (p.getValue().size() >= 2) {
                anagramGroups.add(p.getValue());
            }
        }

        return anagramGroups;
    }

    public static List<ContactList> mergeContactLists(List<ContactList> contacts) {
        return new ArrayList<>(new HashSet<>(contacts));
    }

    public static class ContactList {
        public List<String> names;

        public ContactList(List<String> names) {
            this.names = names;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return this == o ? true : new HashSet<>(names).equals(new HashSet<>(((ContactList) o).names));
        }

        @Override
        public int hashCode() {
            return new HashSet<>(names).hashCode();
        }

        public static boolean canFormPalidrome(String s) {
            Map<Character, Integer> charFrequencies = new HashMap<>();
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                if (!charFrequencies.containsKey(c)) {
                    charFrequencies.put(c, 1);
                } else {
                    charFrequencies.put(c, charFrequencies.get(c) + 1);
                }
            }
            int oddCount = 0;
            for (Map.Entry<Character, Integer> p : charFrequencies.entrySet()) {
                if ((p.getValue() % 2) != 0 && ++oddCount > 1) {
                    return false;
                }
            }
            return true;
        }
    }
}
