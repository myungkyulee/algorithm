package baekjoon.problem19637;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<String> titles = new ArrayList<>();
        List<Integer> powers = new ArrayList<>();

        int pre = -1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String title = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            if(pre == power) continue;
            pre = power;
            titles.add(title);
            powers.add(power);
        }

        for (int i = 0; i < m; i++) {
            int power = Integer.parseInt(br.readLine());
            int index = upperBound(power, powers);
            bw.write(titles.get(index)+'\n');
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static int upperBound(int target, List<Integer> powers) {
        int start = 0;
        int end = powers.size()-1;
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;

            if (powers.get(mid) == target) return mid;
            else if (powers.get(mid) < target) start = mid + 1;
            else end = mid;
        }

        return end;
    }
}
