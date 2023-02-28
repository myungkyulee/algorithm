package baekjoon.problem1339;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Integer[] values = new Integer[26];

        Arrays.fill(values, 0);

        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            int tmp = 1;
            for (int j = num.length() - 1; j >= 0; j--, tmp *= 10) {
                int index = num.charAt(j) - 'A';
                values[index] += tmp;
            }
        }

        Arrays.sort(values, Collections.reverseOrder());

        int sum = 0;
        int value = 9;
        for (int i = 0; i < 26; i++) {
            if (values[i] > 0) {
                sum += values[i] * value--;
            } else {
                break;
            }
        }
        bw.write(sum + "");

        br.close();
        bw.flush();
        bw.close();
    }
}
