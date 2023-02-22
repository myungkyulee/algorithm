package baekjoon.problem2138;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String currentStr = br.readLine();
        String targetStr = br.readLine();

        int answer = Integer.MAX_VALUE;
        int count = 0;

        int[] current = new int[currentStr.length()];
        int[] target = new int[targetStr.length()];
        // 두 번 판단해야 하기 때문에 current를 바꾸지 않고 temp를 바꾸면서 진행한다.
        int[] temp = new int[current.length];

        for (int i = 0; i < currentStr.length(); i++) {
            current[i] = currentStr.charAt(i) - '0';
            target[i] = targetStr.charAt(i) - '0';
        }

        copy(current, temp);

        int result = checkAndCount(count, target, temp);
        if (result != -1) {
            answer = Math.min(answer, result);
        }

        copy(current, temp);

        count = 1;
        // 첫번째를 누를 경우
        temp[0] = change(temp[0]);
        temp[1] = change(temp[1]);
        result = checkAndCount(count, target, temp);
        if (result != -1) {
            answer = Math.min(answer, result);
        }

        if (answer == Integer.MAX_VALUE) {
            bw.write(-1 + "");
        } else {
            bw.write(answer + "");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static int checkAndCount(int count, int[] target, int[] temp) {
        // i번 스위치를 누를지 말지를 결정한다.
        // pre는 i-1번 전구로 i-1번 전구가 목표하는 상태의 전구와 같은 상태이어야 한다.
        // 아니라면 i번 스위치를 눌러서 상태를 같게 만들어준다.
        for (int i = 1; i < temp.length - 1; i++) {
            int pre = temp[i - 1];
            int targetPre = target[i - 1];

            if (pre != targetPre) {
                count++;
                temp[i - 1] = change(temp[i - 1]);
                temp[i] = change(temp[i]);
                temp[i + 1] = change(temp[i + 1]);
            }
        }
        // N번 스위치를 누를지 말지 판단
        if (temp[temp.length - 2] != target[temp.length - 2]) {
            count++;
            temp[temp.length - 2] = change(temp[temp.length - 2]);
            temp[temp.length - 1] = change(temp[temp.length - 1]);
        }
        // N번 스위치를 누른 후에도 N번 전구의 상태가 target과 다를 경우
        // 만들 수 없는 경우이기 때문에 return -1
        if (temp[temp.length - 1] != target[temp.length - 1]) {
            return -1;
        }
        return count;
    }

    // current를 temp에 복사하는 메소드
    private static void copy(int[] current, int[] temp) {
        for (int i = 0; i < current.length; i++) {
            temp[i] = current[i];
        }
    }

    // 전구의 상태를 바꾸는 메소드
    private static int change(int i) {
        if (i == 0) return 1;
        return 0;
    }
}