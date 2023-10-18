import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getXPosition() {
        return this.x;
    }

    int getYPosition() {
        return this.y;
    }
}

class ImageAnalyzer {
    static int N;

    static int findMaxSumSub(int[][] mat, int k) {

        if (k > N) {
            return 0;
        }

        //sumy wszystkich pasków o rozmiarze k x 1
        int[][] stripSum = new int[N][N];

        for (int j = 0; j < N; j++) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += mat[i][j];
            }
            stripSum[0][j] = sum;

            // sumy pozostałych kwadratów
            for (int i = 1; i < N - k + 1; i++) {
                sum += (mat[i + k - 1][j] - mat[i - 1][j]);
                stripSum[i][j] = sum;
            }
        }

        int maxSum = 0;

        for (int i = 0; i < N - k + 1; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += stripSum[i][j];
            }

            if (sum > maxSum) {
                maxSum = sum;
            }

            for (int j = 1; j < N - k + 1; j++) {
                sum += (stripSum[i][j + k - 1] - stripSum[i][j - 1]);

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PATH"));
            N = Integer.parseInt(reader.readLine());
            int[][] mat = new int[N][N];

            for (int i = 0; i < N; i++) {
                String row = reader.readLine();
                for (int j = 0; j < N; j++) {
                    mat[i][j] = Character.getNumericValue(row.charAt(j));
                }
            }
            int k = Integer.parseInt(reader.readLine());
            reader.close();

            long startTime = System.nanoTime();

            int maxSum = findMaxSumSub(mat, k);

            long endTime = System.nanoTime();

            long totalTimeInNanos = endTime - startTime;


            System.out.println("Max Sum: " + maxSum);
            System.out.println("Execution Time: " + totalTimeInNanos + "ns");

            FileWriter writer = new FileWriter("out.txt");
            writer.write("Max Sum: " + maxSum + "\n");
            writer.write("Execution Time: " + totalTimeInNanos + "ns");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
