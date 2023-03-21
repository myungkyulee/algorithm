package baekjoon.problem18808;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] noteBook = new int[n][m];
        List<int[][]> stickerList = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int q = 0; q < c; q++) {
                    sticker[j][q] = Integer.parseInt(st.nextToken());
                }
            }
            stickerList.add(sticker);
        }

        for (int[][] sticker : stickerList) {
            for (int i = 0; i < 4; i++) {
                if (checkAndAttach(noteBook, sticker)) {
                    break;
                }
                sticker = rotateSticker(sticker);
            }
        }
        int count = 0;
        for (int i = 0; i < noteBook.length; i++) {
            for (int j = 0; j < noteBook[i].length; j++) {
                if (noteBook[i][j] == 1) count++;
            }
        }

        bw.write(count + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int[][] rotateSticker(int[][] sticker) {
        int rowSize = sticker.length;
        int colSize = sticker[0].length;
        int[][] result = new int[colSize][rowSize];

        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[i].length; j++) {
                result[j][rowSize - i - 1] = sticker[i][j];
            }
        }
        return result;
    }

    public static boolean checkAndAttach(int[][] noteBook, int[][] sticker) {
        for (int i = 0; i <= noteBook.length - sticker.length; i++) {
            for (int j = 0; j <= noteBook[0].length - sticker[0].length; j++) {
                if (check(i, j, noteBook, sticker)) {
                    attach(i, j, noteBook, sticker);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(int x, int y, int[][] noteBook, int[][] sticker) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] + noteBook[i + x][j + y] >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void attach(int x, int y, int[][] noteBook, int[][] sticker) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[i].length; j++) {
                noteBook[i + x][j + y] += sticker[i][j];
            }
        }
    }
}
