package codechef.cookoff.march2018;

import java.util.Scanner;

public class CO92MATR {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    while (T-- > 0) {
      int N = sc.nextInt();
      int M = sc.nextInt();
      int[][] mat = new int[N][M];
      boolean invalid = false;
      for (int i=0;i<N;i++) {
        for (int j=0;j<M;j++) {
          mat[i][j] = sc.nextInt();
          int a = Math.max((i != 0) ? mat[i-1][j] : 1, (j != 0) ? mat[i][j-1] : 1);
          if (mat[i][j] == -1) {
            mat[i][j] = a;
          } else if (mat[i][j] < a) {
            invalid = true;
          }
        }
      }
      if (invalid) {
        System.out.println("-1");
      } else {
        for (int i=0;i<N;i++) {
          for (int j=0;j<M;j++) {
            System.out.print(mat[i][j] + " ");
          }
          System.out.println();
        }
      }
    }
  }
}
