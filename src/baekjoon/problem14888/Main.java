package baekjoon.problem14888;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> numbers;
    static List<Integer> opCount;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        numbers = new ArrayList<>();
        opCount = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers.add(num);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int op = Integer.parseInt(st.nextToken());
            opCount.add(op);
        }

        dfs(1, numbers.get(0));

        bw.write(max + "\n" + min);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int count, int value) {
        if (count == numbers.size()) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        if (opCount.get(0) > 0) {
            opCount.set(0, opCount.get(0) - 1);
            dfs(count + 1, value + numbers.get(count));
            opCount.set(0, opCount.get(0) + 1);
        }
        if (opCount.get(1) > 0) {
            opCount.set(1, opCount.get(1) - 1);
            dfs(count + 1, value - numbers.get(count));
            opCount.set(1, opCount.get(1) + 1);
        }
        if (opCount.get(2) > 0) {
            opCount.set(2, opCount.get(2) - 1);
            dfs(count + 1, value * numbers.get(count));
            opCount.set(2, opCount.get(2) + 1);
        }
        if (opCount.get(3) > 0) {
            opCount.set(3, opCount.get(3) - 1);
            dfs(count + 1, value / numbers.get(count));
            opCount.set(3, opCount.get(3) + 1);
        }
    }
}
