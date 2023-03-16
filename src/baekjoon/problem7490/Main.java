package baekjoon.problem7490;

import java.io.*;
import java.util.*;

public class Main {
    public static final int TARGET = 0;
    public static List<String> resultList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];

            for (int j = 1; j <= n; j++) {
                arr[j - 1] = j;
            }

            dfs(1, "" + arr[0], arr);

            resultList.add("");
        }

        resultList.forEach(System.out::println);

        br.close();
    }

    public static void dfs(int index, String result, int[] arr) {
        if (index == arr.length) {
            StringBuilder s = new StringBuilder();
            int sum = 0;
            char op = '!';

            for (int i = 0; i < result.length(); i++) {
                char c = result.charAt(i);

                if (c == '+' || c == '-') {
                    if (op == '+' || op == '!') {
                        sum += Integer.parseInt(s.toString());
                    } else {
                        sum -= Integer.parseInt(s.toString());
                    }
                    s = new StringBuilder();
                    op = c;
                } else if (c >= '0' && c <= '9') {
                    s.append(c);
                }
            }
            if (op == '+') {
                sum += Integer.parseInt(s.toString());
            } else if (op == '-') {
                sum -= Integer.parseInt(s.toString());
            } else {
                sum += Integer.parseInt(s.toString());
            }
            if (sum == 0) resultList.add(result);

            return;
        }
        dfs(index + 1, result + " " + arr[index], arr);
        dfs(index + 1, result + "+" + arr[index], arr);
        dfs(index + 1, result + '-' + arr[index], arr);
    }
}