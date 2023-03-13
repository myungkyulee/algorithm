package baekjoon.problem2668;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        boolean[] check = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            check[i] = true;
            dfs(i, i, arr, check);
            check[i] = false;
        }

        Collections.sort(resultList);

        bw.write(resultList.size() + "\n");
        for (int i = 0; i < resultList.size(); i++) {
            bw.write(resultList.get(i) + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void dfs(int index, int target, int[] arr, boolean[] check) {
        if (arr[index] == target) {
            resultList.add(target);
            return;
        }

        if (!check[arr[index]]) {
            check[arr[index]] = true;
            dfs(arr[index], target, arr, check);
            check[arr[index]] = false;
        }
    }
}
