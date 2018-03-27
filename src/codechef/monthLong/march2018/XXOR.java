package codechef.monthLong.march2018;

import java.util.Scanner;

public class XXOR {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int Q = sc.nextInt();
    int[] arr = new int[N];
    int[][] bits = new int[N][31];
    for (int i=0;i<N; i++) {
      arr[i] = sc.nextInt();
      for (int j=0;j<31;j++) {
        if ((arr[i] & (1 << j)) != 0) {
          bits[i][j] = (i == 0) ? 1 : bits[i-1][j] + 1;
        } else {
          bits[i][j] = (i == 0) ? 0 : bits[i-1][j];
        }
      }
    }

    while (Q-- > 0) {
      int l = sc.nextInt();
      int r = sc.nextInt();
      r--;
      l--;
      int ans = 0;
      for (int i=0;i<31;i++) {
        int ones = bits[r][i] - ((l == 0) ? 0 : bits[l-1][i]);
        int zeros = r - l + 1 - ones;
        if (ones < zeros) {
          ans |= (1 << i);
        }
      }
      System.out.println(ans);
    }
  }
}
