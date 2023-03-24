package baekjoon.problem14891;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Gear> gearList = new ArrayList<>();
        int[] scoreList = new int[4];
        int tmp = 1;
        for (int i = 0; i < scoreList.length; i++, tmp *= 2) {
            scoreList[i] = tmp;
        }

        for (int i = 0; i < 4; i++) {
            String gearString = br.readLine();
            int[] gearArray = changeGearStringToIntArray(gearString);

            Gear gear = new Gear(gearArray);

            gearList.add(gear);
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int gearNumber = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            check(gearList, gearNumber, direction);
        }

        int result = 0;
        for (int i = 0; i < gearList.size(); i++) {
            Gear gear = gearList.get(i);
            int score = scoreList[i];

            result += gear.topValue() * score;
        }

        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void check(List<Gear> gearList, int gearNumber, int direction) {
        Gear gear = gearList.get(gearNumber);
        int leftValue = gear.leftValue();
        int rightValue = gear.rightValue();

        gear.rotate(direction);

        int leftDirection = direction * -1;
        // 왼쪽부터 차례로 판단
        for (int i = gearNumber - 1; i >= 0; i--) {
            Gear leftGear = gearList.get(i);
            if (movable(leftGear.rightValue(), leftValue)) {
                leftValue = leftGear.leftValue();
                leftGear.rotate(leftDirection);
                leftDirection *= -1;
            } else break;
        }

        int rightDirection = direction * -1;
        // 오른쪽부터 차례로 판단
        for (int i = gearNumber + 1; i < gearList.size(); i++) {
            Gear rightGear = gearList.get(i);
            if (movable(rightValue, rightGear.leftValue())) {
                rightValue = rightGear.rightValue();
                rightGear.rotate(rightDirection);
                rightDirection *= -1;
            } else break;
        }
    }

    public static boolean movable(int leftGearRightValue, int rightGearLeftValue) {
        return leftGearRightValue != rightGearLeftValue;
    }

    public static int[] changeGearStringToIntArray(String input) {
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = input.charAt(i) - '0';
        }
        return arr;
    }


    static class Gear {
        static final int RIGHT_START = 2;
        static final int LEFT_START = 6;

        private final int[] sawtooth;
        private int rightPointer;
        private int leftPointer;
        private int topPointer;

        Gear(int[] sawtooth) {
            this.sawtooth = sawtooth;
            rightPointer = RIGHT_START;
            leftPointer = LEFT_START;
            topPointer = 0;
        }

        public int topValue() {
            return sawtooth[topPointer];
        }

        public int leftValue() {
            return sawtooth[leftPointer];
        }

        public int rightValue() {
            return sawtooth[rightPointer];
        }

        public void rotate(int direction) {
            if (direction == 1) {
                rotateRight();
            } else {
                rotateLeft();
            }
        }

        public void rotateLeft() {
            rightPointer++;
            leftPointer++;
            topPointer++;
            if (rightPointer >= sawtooth.length)
                rightPointer -= sawtooth.length;
            if (leftPointer >= sawtooth.length) leftPointer -= sawtooth.length;
            if (topPointer >= sawtooth.length) topPointer -= sawtooth.length;
        }

        public void rotateRight() {
            rightPointer--;
            leftPointer--;
            topPointer--;
            if (rightPointer < 0) rightPointer += sawtooth.length;
            if (leftPointer < 0) leftPointer += sawtooth.length;
            if (topPointer < 0) topPointer += sawtooth.length;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = topPointer; i < topPointer + 8; i++) {
                int index = i;
                if (index > 7) index -= 8;
                sb.append(sawtooth[index]);
            }

            return sb.toString();
        }
    }
}
