package baekjoon.problem12100;

import java.io.*;
import java.util.*;

public class Main {
    private static final int TARGET_COUNT = 5;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, map);

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int count, int[][] map) {
        if (count == TARGET_COUNT) {
            result = Math.max(result, findMaxBlock(map));
            return;
        }

        dfs(count + 1, moveLeft(map));
        dfs(count + 1, moveRight(map));
        dfs(count + 1, moveUp(map));
        dfs(count + 1, moveDown(map));
    }

    public static int findMaxBlock(int[][] map) {
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    public static int[][] moveLeft(int[][] map) {
        int[][] newMap = new int[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0) tmp.add(map[i][j]);
            }
            int index = 0;
            int j = 0;
            for (; j < tmp.size() - 1; j++) {
                int num = tmp.get(j);
                int nextNum = tmp.get(j + 1);
                if (num == nextNum) {
                    newMap[i][index++] = num + nextNum;
                    j++;
                } else {
                    newMap[i][index++] = num;
                }
            }
            if (j == tmp.size() - 1) {
                newMap[i][index] = tmp.get(j);
            }
        }

        return newMap;
    }

    public static int[][] moveRight(int[][] map) {
        int[][] newMap = new int[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0) tmp.add(map[i][j]);
            }
            int index = map[0].length - 1;
            int j = tmp.size() - 1;
            for (; j > 0; j--) {
                int num = tmp.get(j);
                int nextNum = tmp.get(j - 1);
                if (num == nextNum) {
                    newMap[i][index--] = num + nextNum;
                    j--;
                } else {
                    newMap[i][index--] = num;
                }
            }
            if (j == 0) {
                newMap[i][index] = tmp.get(j);
            }
        }

        return newMap;
    }

    public static int[][] moveUp(int[][] map) {
        int[][] newMap = new int[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] != 0) tmp.add(map[j][i]);
            }
            int index = 0;
            int j = 0;
            for (; j < tmp.size() - 1; j++) {
                int num = tmp.get(j);
                int nextNum = tmp.get(j + 1);
                if (num == nextNum) {
                    newMap[index++][i] = num + nextNum;
                    j++;
                } else {
                    newMap[index++][i] = num;
                }
            }
            if (j == tmp.size() - 1) {
                newMap[index][i] = tmp.get(j);
            }
        }

        return newMap;
    }

    public static int[][] moveDown(int[][] map) {
        int[][] newMap = new int[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] != 0) tmp.add(map[j][i]);
            }
            int index = map.length - 1;
            int j = tmp.size() - 1;
            for (; j > 0; j--) {
                int num = tmp.get(j);
                int nextNum = tmp.get(j - 1);
                if (num == nextNum) {
                    newMap[index--][i] = num + nextNum;
                    j--;
                } else {
                    newMap[index--][i] = num;
                }
            }
            if (j == 0) {
                newMap[index][i] = tmp.get(j);
            }
        }

        return newMap;
    }
}
