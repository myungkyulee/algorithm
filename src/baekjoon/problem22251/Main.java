package baekjoon.problem22251;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String x = st.nextToken();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k - x.length(); i++) {
            sb.append(0);
        }
        x = sb.append(x).toString();


        int[][] numberLED = new int[10][7];
        numberLED[0] = new int[]{1, 1, 1, 0, 1, 1, 1};
        numberLED[1] = new int[]{0, 0, 1, 0, 0, 1, 0};
        numberLED[2] = new int[]{1, 0, 1, 1, 1, 0, 1};
        numberLED[3] = new int[]{1, 0, 1, 1, 0, 1, 1};
        numberLED[4] = new int[]{0, 1, 1, 1, 0, 1, 0};
        numberLED[5] = new int[]{1, 1, 0, 1, 0, 1, 1};
        numberLED[6] = new int[]{1, 1, 0, 1, 1, 1, 1};
        numberLED[7] = new int[]{1, 0, 1, 0, 0, 1, 0};
        numberLED[8] = new int[]{1, 1, 1, 1, 1, 1, 1};
        numberLED[9] = new int[]{1, 1, 1, 1, 0, 1, 1};

        int[][] numberToNumberCount = new int[10][10];

        for (int i = 0; i < numberToNumberCount.length; i++) {
            for (int j = 0; j < numberToNumberCount.length; j++) {
                numberToNumberCount[i][j] = countChange(i, j, numberLED);
            }
        }

        dfs(0, 0, p, x, n, numberToNumberCount, "");

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int count, int index, int p, String currFloor,
                           int maxFloor, int[][] numberToNumberCount,
                           String s) {
        if (index == currFloor.length()) {
            if (count <= p && !currFloor.equals(s)) {
                int floor = Integer.parseInt(s);
                if (floor > maxFloor || floor == 0) return;
                result++;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            int curNum = currFloor.charAt(index) - '0';
            int countChange = numberToNumberCount[curNum][i];

            dfs(count + countChange, index + 1,
                    p, currFloor, maxFloor, numberToNumberCount, s + i);
        }
    }

    public static int countChange(int from, int to, int[][] numberLED) {
        int count = 0;
        for (int i = 0; i < numberLED[from].length; i++) {
            if (numberLED[from][i] != numberLED[to][i]) count++;
        }
        return count;
    }
}