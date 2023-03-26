package baekjoon.problem13335;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        List<Integer> truckList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int truckWeight = Integer.parseInt(st.nextToken());
            truckList.add(truckWeight);
        }

        int index = 0;
        int[] bridge = new int[w];

        int result = 0;
        while (true) {
            for (int i = bridge.length - 1; i > 0; i--) {
                bridge[i] = bridge[i - 1];
            }
            bridge[0] = 0;
            int sum = sumTruckWeightOnBridge(bridge);

            result++;
            if (index != truckList.size() && sum + truckList.get(index) <= l) {
                bridge[0] = truckList.get(index);
                index++;
            } else {
                if (isEmpty(bridge)) {
                    break;
                }
            }
        }
        bw.write(result + "");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int sumTruckWeightOnBridge(int[] bridge) {
        int sum = 0;
        for (int i = 0; i < bridge.length; i++) {
            sum += bridge[i];
        }
        return sum;
    }

    public static boolean isEmpty(int[] bridge) {
        for (int i = 0; i < bridge.length; i++) {
            if (bridge[i] != 0) return false;
        }
        return true;
    }
}
