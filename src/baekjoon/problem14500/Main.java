package baekjoon.problem14500;

import java.util.*;

public class Main {
    static int n,m;
    static int result = Integer.MIN_VALUE;
    static int[][] map = new int[500][500];
    static boolean[][] visited = new boolean[500][500];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int a = sc.nextInt();
                map[i][j]=a;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j]=true;
                DFS(i, j, 1, map[i][j]);
                visited[i][j]=false;
            }
        }
        System.out.println(result);
    }

    public static void DFS(int x, int y, int count, int sum) {
        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if(!visited[nx][ny]){
                if (count == 2) {
                    visited[nx][ny]=true;
                    DFS(x, y, count + 1, sum + map[nx][ny]);
                    visited[nx][ny]=false;
                }
                visited[nx][ny]=true;
                DFS(nx, ny, count + 1, sum + map[nx][ny]);
                visited[nx][ny]=false;
            }
        }
    }
}