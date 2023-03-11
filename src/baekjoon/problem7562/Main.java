package baekjoon.problem7562;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int t;
    static int[][] map = new int[300][300];
    static int[][] visit = new int[300][300];
    static int currX, currY;
    static int targetX, targetY;

    static int dx[] = {2, 2, 1, 1, -2, -2, -1, -1};
    static int dy[] = {1, -1, 2, -2, 1, -1, 2, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());

        while (t > 0) {
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < 300; i++) {
                Arrays.fill(visit[i], 0);
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            currX = Integer.parseInt(st.nextToken());
            currY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            targetX = Integer.parseInt(st.nextToken());
            targetY = Integer.parseInt(st.nextToken());

            Queue<Pair> q = new LinkedList<>();

            q.offer(new Pair(currX, currY));
            while (!q.isEmpty()) {
                int x = q.peek().x;
                int y = q.poll().y;

                if (x == targetX && y == targetY) break;

                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (visit[nx][ny] == 0) {
                            visit[nx][ny] = visit[x][y] + 1;
                            q.offer(new Pair(nx, ny));
                        }
                    }
                }
            }
            bw.write(visit[targetX][targetY] + "\n");

            t--;
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
