package baekjoon.problem1107;

import java.util.*;

public class Main {
    static int n;
    static int m;
    static Map<Integer, Boolean> wrongButton = new HashMap<>();
    static int result = Integer.MAX_VALUE;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();


        for (int i = 0; i < m; i++) {
            wrongButton.put(sc.nextInt(), true);
        }

        result = Math.min(result, Math.abs(n - 100));
        for (int x = 0; x < 500000 + (500000-100); x++) {
            String s = Integer.toString(x);
            boolean check = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (wrongButton.get(c - '0') != null) {
                    check = true;
                    break;
                }
            }
            // 바로 갈 수 있다면
            if (!check) {
                result = Math.min(result, Integer.toString(x).length() + Math.abs(n - x));
            }
        }
        System.out.println(result);
    }
}