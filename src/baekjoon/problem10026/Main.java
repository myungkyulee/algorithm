package baekjoon.problem10026;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map = new char[100][100];
    static boolean[][] visit = new boolean[100][100];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int countFirst = 0;
        int countSecond = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    bfs(i, j, map[i][j]);
                    countFirst++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(visit[i], false);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (!visit[i][j]) {
                    if (map[i][j] == 'R' || map[i][j] == 'G') {
                        bfs1(i,j,'R','G');
                        countSecond++;
                    } else if (map[i][j] == 'B') {
                        bfs(i, j, 'B');
                        countSecond++;
                    }
                }
            }
        }

        bw.write(countFirst + " " + countSecond);

        br.close();
        bw.flush();
        bw.close();
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void bfs(int x, int y, char c) {
        visit[x][y] = true;

        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(x, y));

        while (!q.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + q.peek().x;
                int ny = dy[i] + q.peek().y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] == c && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    }
                }
            }
            q.poll();
        }
    }


    public static void bfs1(int x, int y, char c1, char c2) {
        visit[x][y] = true;

        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(x, y));

        while (!q.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + q.peek().x;
                int ny = dy[i] + q.peek().y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if ((map[nx][ny] == c1 || map[nx][ny] == c2) && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    }
                }
            }
            q.poll();
        }
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
