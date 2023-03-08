package baekjoon.problem14889;

import java.io.*;
import java.util.*;

public class Main {
    public static boolean[] check = new boolean[20];
    public static int[][] map = new int[20][20];
    public static int result = 20 * 20 * 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a;
            }
        }

        DFS(0, 0, n / 2);

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }


    public static void DFS(int x, int count, int target) {
        if (count == target) {
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();

            for (int i = 0; i < target * 2; i++) {
                if (check[i]) {
                    start.add(i);
                } else {
                    link.add(i);
                }
            }

            int startAbility = 0;
            int linkAbility = 0;
            for (int i = 0; i < start.size(); i++) {
                int firstMember = start.get(i);
                for (int j = i + 1; j < start.size(); j++) {
                    int secondMember = start.get(j);
                    startAbility += map[firstMember][secondMember];
                    startAbility += map[secondMember][firstMember];
                }
            }

            for (int i = 0; i < link.size(); i++) {
                int firstMember = link.get(i);
                for (int j = i + 1; j < link.size(); j++) {
                    int secondMember = link.get(j);
                    linkAbility += map[firstMember][secondMember];
                    linkAbility += map[secondMember][firstMember];
                }
            }

            result = Math.min(Math.abs(startAbility - linkAbility), result);
        }

        for (int i = x; i < target * 2; i++) {
            if (!check[i]) {
                check[i] = true;
                DFS(i + 1, count + 1, target);
                check[i] = false;
            }
        }
    }
}
