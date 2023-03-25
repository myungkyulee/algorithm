package baekjoon.problem14499;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice();

        st = new StringTokenizer(br.readLine());

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        while (st.hasMoreTokens()) {
            int direction = Integer.parseInt(st.nextToken()) - 1;

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length) {
                dice.move(direction);

                if (map[nx][ny] == 0) {
                    map[nx][ny] = dice.getBottom();
                } else {
                    dice.setBottom(map[nx][ny]);
                    map[nx][ny] = 0;
                }
                x = nx;
                y = ny;
                bw.write(dice.getTop() + "\n");
            }
        }


        br.close();
        bw.flush();
        bw.close();
    }

    static class Dice {
        private int top;
        private int bottom;
        private int front;
        private int rear;
        private int left;
        private int right;

        public int getTop() {
            return top;
        }

        public void setBottom(int value) {
            bottom = value;
        }

        public int getBottom() {
            return bottom;
        }

        public void move(int direction) {
            if (direction == 0) moveRight();
            else if (direction == 1) moveLeft();
            else if (direction == 2) moveUp();
            else if (direction == 3) moveDown();
        }

        public void moveDown() {
            int tmp = top;
            top = front;
            front = bottom;
            bottom = rear;
            rear = tmp;
        }

        public void moveUp() {
            int tmp = top;
            top = rear;
            rear = bottom;
            bottom = front;
            front = tmp;
        }

        public void moveLeft() {
            int tmp = top;
            top = right;
            right = bottom;
            bottom = left;
            left = tmp;
        }

        public void moveRight() {
            int tmp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = tmp;
        }
    }
}
