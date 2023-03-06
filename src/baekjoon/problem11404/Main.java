package baekjoon.problem11404;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] dist = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        for (int i = 0; i < m; i++) {
            int a, b, c;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (dist[a][b] > c) dist[a][b] = c;
        }

        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == (int) 1e9) {
                    System.out.print(0 + " ");
                }
                // 도달할 수 있는 경우 거리를 출력
                else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
