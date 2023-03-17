package baekjoon.problem2110;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Integer> home = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            home.add(x);
        }

        Collections.sort(home);

        int start = 1;
        int end = home.get(n - 1) - home.get(0) + 1;
        int result = 0;
        while (start < end) {
            int target = (start + end) / 2;

            if (checkInstall(target, home) >= c) {
                start = target + 1;
            } else {
                end = target;
            }
        }
        result = end - 1;

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int checkInstall(int dist, List<Integer> home) {
        int count = 1;
        int preLoc = home.get(0);
        for (int i = 1; i < home.size(); i++) {
            int loc = home.get(i);
            if (loc - preLoc >= dist) {
                count++;
                preLoc = loc;
            }
        }
        return count;
    }
}
