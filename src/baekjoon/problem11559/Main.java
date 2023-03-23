package baekjoon.problem11559;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] map = new char[6][12];
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[j][i] = s.charAt(j);
            }
        }

        int result = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != '.') {
                        int count = bfs(i, j, map);
                        if (count >= 4) flag = true;
                    }
                }
            }

            for (int i = 0; i < map.length; i++) {
                int index = map[i].length - 1;
                for (int j = map[i].length - 1; j >= 0; j--) {
                    char c = map[i][j];
                    if(c != '.') {
                        map[i][index] = c;
                        if (j != index) {
                            map[i][j] = '.';
                        }
                        index--;
                    }
                }
            }

            if (!flag) break;
            result++;
        }

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int bfs(int x, int y, char[][] map) {
        char target = map[x][y];
        boolean[][] check = new boolean[map.length][map[0].length];

        check[x][y] = true;
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(x, y));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int count = 0;
        while (!q.isEmpty()) {
            int curX = q.peek().x;
            int curY = q.poll().y;
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + curX;
                int ny = dy[i] + curY;

                if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length) {
                    if (map[nx][ny] == target && !check[nx][ny]) {
                        check[nx][ny] = true;
                        q.offer(new Pair(nx, ny));
                    }
                }
            }
        }
        if (count >= 4) {
            for (int i = 0; i < check.length; i++) {
                for (int j = 0; j < check[i].length; j++) {
                    if (check[i][j]) map[i][j] = '.';
                }
            }
        }


        return count;
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
