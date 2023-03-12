package baekjoon.problem1062;

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] check = new boolean[26];
    static List<String> arr = new ArrayList<>();
    static Map<Character, Boolean> m = new HashMap<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 기본적인 개수 5개
        check[0] = true;
        check['n' - 'a'] = true;
        check['t' - 'a'] = true;
        check['i' - 'a'] = true;
        check['c' - 'a'] = true;

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            arr.add(word);
        }
        if (k < 5) {
            bw.write(0 + "");
            br.close();
            bw.flush();
            bw.close();
            return;
        }
        dfs(k - 5, 0, 0);

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int k, int count, int x) {
        if (count == k) {
            int cnt = 0;
            for (String s : arr) {
                boolean che = true;
                for (int j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (!check[c - 'a']) {
                        che = false;
                        break;
                    }
                }
                if (che) cnt++;
            }
            result = Math.max(result, cnt);
            return;
        }

        for (int i = x; i < 26; i++) {
            if (!check[i]) {
                check[i] = true;
                dfs(k, count + 1, i);
                check[i] = false;
            }
        }
    }
}