package us.matthey.coco.algorithm.amerisave;

import java.util.HashMap;
import java.util.Map;

//Write a function in Java that takes a string as its input and prints the letters in that string in reverse order, with the letters in the odd-numbered positions in upper-case and the letters in even-numbered positions in lower case.
public class Amerisave {
    public static void printReverseStringUpperLower(String input) {
        for (int i=input.length()-1; i>=0; i--) {
            // Non alphabetic will be ignored
            if (i % 2 ==0) { // even-numbered
                System.out.print(String.valueOf(input.charAt(i)).toUpperCase());
            } else { // odd-numbered
                System.out.print(String.valueOf(input.charAt(i)).toLowerCase());
            }
        }
    }

    // Write Java code that defines an array, loads it with some data, and then loops through the members of that array. Do the same for a HashMap.

    public static void printArray(String data) {
        char[] arr = new char[data.length()];
        // Load array
        for (int i=0; i<data.length(); i++) {
            arr[i] = data.charAt(i);
        }
        // Loop array
        for (int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }
        Map<Character, Integer> map = new HashMap<>();
        // Load map
        for (int i=0; i<data.length(); i++) {
            map.put(data.charAt(i), i);
        }
        // Loop map
        for (Character c : map.keySet()) {
            System.out.println(c);
            System.out.println(map.get(c));
        }
    }

    //*3. What is synchronization and when would you use it? When would you not use it?

    public static void trueorfalse() {
        String s1 = "AmeriSave";
        String s2 = "AmeriSave";
        String s3 = "AMERISAVE";

        s1.toUpperCase();

        if (s2 == s1) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    // problem
    public void updatePrivilege(String label, String description, String privId) {
//        try {
//            JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
//            jdbcTemplate.update(
//                    "UPDATE AXS_Privilege SET " +
//                            "label = '" + label + "', " +
//                            "desc = '" + description + "', " +
//                            "WHERE privId = '" + privId + "'"
//            );
//        } catch(DataAccessException ex) {
//        }
    }

    public static void main(String[] args) {
//        printReverseStringUpperLower("abc123!@#");
        trueorfalse();
    }
}
