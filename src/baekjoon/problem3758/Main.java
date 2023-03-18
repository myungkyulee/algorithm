package baekjoon.problem3758;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());


        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Map<Integer, SubmitSolution> teamsData = new HashMap<>();

            for (int j = 1; j <= m; j++) {
                st = new StringTokenizer(br.readLine());
                int teamId = Integer.parseInt(st.nextToken());
                int problemId = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());

                if (teamsData.containsKey(teamId)) {
                    SubmitSolution submitSolution = teamsData.get(teamId);
                    submitSolution.submit(problemId, score, j);
                } else {
                    SubmitSolution submitSolution = new SubmitSolution(teamId);
                    submitSolution.submit(problemId, score, j);
                    teamsData.put(teamId, submitSolution);
                }
            }

            List<SubmitSolution> teams = new ArrayList<>(teamsData.values());

            Collections.sort(teams);
            bw.write("T = " + i +"\n");
            for (int j = 0; j < teams.size(); j++) {
                int teamId = teams.get(i).getTeamId();
                if(n == teamId) bw.write(j + 1 + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static class SubmitSolution implements Comparable<SubmitSolution> {
        private int teamId;
        private int totalScore;
        private int totalCount;
        private Map<Integer, Integer> scores = new HashMap<>();
        private int updatedAt;

        public SubmitSolution(int teamId) {
            this.teamId = teamId;
            totalScore = 0;
        }

        public void submit(int problemId, int score, int time) {
            if (!scores.containsKey(problemId)) {
                totalScore += score;
                scores.put(problemId, score);
                updatedAt = time;
            } else if (scores.get(problemId) < score) {
                totalScore += score - scores.get(problemId);
                scores.put(problemId, score);
                updatedAt = time;
            }

            totalCount++;
        }

        public int getTeamId() {
            return teamId;
        }

        @Override
        public int compareTo(SubmitSolution o) {
            if (this.totalScore == o.totalScore) {
                if (this.totalCount == o.totalCount) {
                    return this.updatedAt - o.updatedAt;
                }
                return this.totalCount - o.totalCount;
            }
            return o.totalScore - this.totalScore;
        }
    }
}
