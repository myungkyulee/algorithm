package baekjoon.problem14503;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        Robot robot = new Robot(x, y, d);

        while (true) {
            if(robot.checkClean(map)) robot.clean(map);
            if (robot.check(map)) {
                while(true){
                    robot.rotate();
                    if (robot.checkFront(map)) {
                        robot.moveFront();
                        break;
                    }
                }
            } else {
                if (robot.checkBack(map)) {
                    robot.moveBack();
                } else {
                    break;
                }
            }
        }

        bw.write(robot.count + "");


        br.close();
        bw.flush();
        bw.close();
    }


    static class Robot {
        int x, y;
        int d;
        int count;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.count = 0;
        }

        public boolean checkClean(int[][] map){
            if(map[x][y] == 0) return true;
            return false;
        }

        public boolean check(int[][] map){
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length) {
                    if (map[nx][ny] == 0) return true;
                }
            }
            return false;
        }

        public void clean(int[][] map){
            if (map[x][y] == 0) {
                map[x][y] = 2;
                count++;
            } else {
                throw new IllegalArgumentException("청소하면 안되는 부분을 청소합니다.");
            }
        }

        public void moveFront() {
            this.x += dx[d];
            this.y += dy[d];
        }

        public boolean checkBack(int[][] map) {
            int direct = d + 2;
            if (direct >= 4) direct -= 4;
            int nx = x + dx[direct];
            int ny = y + dy[direct];
            if (map[nx][ny] == 1) return false;
            return true;
        }

        public void moveBack() {
            int direct = d + 2;
            if (direct >= 4) direct -= 4;
            this.x += dx[direct];
            this.y += dy[direct];
        }

        public boolean checkFront(int[][] map){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(map[nx][ny] == 0) return true;
            return false;
        }

        public void rotate() {
            d--;
            if (d < 0) d += 4;
        }
    }
}
