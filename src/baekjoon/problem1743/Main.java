package baekjoon.problem1743;

import java.io.*;
import java.util.*;


public class Main {
    static int n, m, k;
    static int[][] map = new int[101][101];

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        int result = 0;


        for (int i = 1; i <= Main.n; i++) {
            for (int j = 1; j <= m; j++) {
                // 음식물 쓰레기가 있으면 bfs
                if (map[i][j] == 1) {
                    int size = bfs(i, j);
                    result = Math.max(result, size);
                }
            }
        }

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    static int bfs(int a, int b) {
        map[a][b] = 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(a, b));

        int count = 0;
        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.poll().y;

            count++;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx > 0 && nx <= n && ny > 0 && ny <= m) {
                    if (map[nx][ny] == 1) {
                        map[nx][ny] = 0;
                        q.offer(new Pair(nx, ny));
                    }
                }
            }
        }

        return count;
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
