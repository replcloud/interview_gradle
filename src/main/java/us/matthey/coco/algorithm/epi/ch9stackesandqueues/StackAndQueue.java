package us.matthey.coco.algorithm.epi.ch9stackesandqueues;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class StackAndQueue {
    public static int eval(String RPNExpression) {
        Deque<Integer> intermediateResults = new LinkedList<>();
        String delimiter = ",";
        String[] symbols = RPNExpression.split(delimiter);
        for (String token : symbols) {
            if (token.length() == 1 && "+-*/".contains(token)) {
                int y = intermediateResults.removeFirst();
                int x = intermediateResults.removeFirst();
                switch (token.charAt(0)) {
                    case '+':
                        intermediateResults.addFirst(x + y);
                        break;
                    case '-':
                        intermediateResults.addFirst(x - y);
                        break;
                    case '*':
                        intermediateResults.addFirst(x * y);
                        break;
                    case '/':
                        intermediateResults.addFirst(x / y);
                        break;
                    default:
                        throw new IllegalArgumentException("Malformed RPN at :" + token);
                }
            } else {
                intermediateResults.addFirst(Integer.parseInt(token));
            }
        }
        return intermediateResults.removeFirst();
    }

    public static boolean isWellFormed(String s) {
        Deque<Character> leftChars = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                leftChars.addFirst(s.charAt(i));
            } else {
                if (leftChars.isEmpty()) return false;
                if ((s.charAt(i) == ')' && leftChars.peekFirst() != '(')
                        || (s.charAt(i) == '}' && leftChars.peekFirst() != '{')
                        || (s.charAt(i) == ']' && leftChars.peekFirst() != '[')) {
                    return false;
                }
                leftChars.removeFirst();
            }
        }
        return leftChars.isEmpty();
    }

    public static String shortestEquivalentPath(String path) {
        if (path.equals("")) {
            throw new IllegalArgumentException("Empty string is not a legal path.");
        }
        Deque<String> pathNames = new LinkedList<>();
        if (path.startsWith("/")) {
            pathNames.addFirst("/");
        }
        for (String token : path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
                    pathNames.addFirst(token);
                } else {
                    if (pathNames.peekFirst() == "/") {
                        throw new IllegalArgumentException("Path error, tyring to go up root  " + path);
                    }
                    pathNames.removeFirst();
                }
            } else if (!token.equals(".") && !pathNames.isEmpty()) {
                pathNames.addFirst(token);
            }
        }
        StringBuilder result = new StringBuilder();
        if (!pathNames.isEmpty()) {
            Iterator<String> it = pathNames.descendingIterator();
            String prev = it.next();
            result.append(prev);
            while (it.hasNext()) {
                if (!prev.equals("/")) {
                    result.append("/");
                }
                prev = it.next();
                result.append(prev);
            }
        }
        return result.toString();
    }

    public static void setJumpOrder(PositionListNode L) {
        setJumpOrderHelper(L, 0);
    }

    private static int setJumpOrderHelper(PositionListNode L, int order) {
        if (L != null && L.order != -1) {
            L.order = order++;
            order = setJumpOrderHelper(L.jump, order);
            order = setJumpOrderHelper(L.next, order);
        }
        return order;
    }
}
