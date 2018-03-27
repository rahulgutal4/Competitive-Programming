package codechef.monthLong.march2018;

import java.util.Scanner;

public class MINVOTE {

  public static void fast(int N, long[] arr, long[] sum) {
    int[] updates = new int[N];
    for (int i=0;i<N;i++) {
      int l = i;
      int r = N-1;
      int where = i+1;
      while (l <= r) {
        int mid = (l+r) >> 1;
        long rsum = 0;
        if (mid > 0) {
          rsum = sum[mid-1] - sum[i];
        }
        if (rsum <= arr[i]) {
          where = mid;
          l = mid+1;
        }else {
          r = mid-1;
        }
      }

      if (where < N-1) {
        updates[where+1] -= 1;
      }
      if (i < N-1){
        updates[i+1] += 1;
      }

      l = 0;
      r = i-1;
      where = i;
      while (l <= r) {
        int mid = (l+r) >> 1;
        long rsum = 0;
        if (i > 0){
          rsum = sum[i-1] - sum[mid];
        }
        if (rsum <= arr[i]) {
          where = mid;
          r = mid-1;
        }else {
          l = mid+1;
        }
      }
      if (i >= 0) {
        updates[i] -= 1;
      }
      if (where >= 0){
        updates[where] += 1;
      }
    }

    int total = 0;
    for (int i=0;i<N;i++) {
      total += updates[i];
      System.out.print(total + " ");
    }
    System.out.println();
  }

  public static void slow(int N, long[] arr, long[] sum) {
    int[] votes = new int[N];
    for (int i=0;i<N;i++) {
      for (int j=i+1;j<N;j++) {
        long rsum = sum[j-1] - sum[i];
        if (rsum <= arr[i]) {
          votes[j]++;
        }
        if (rsum <= arr[j]) {
          votes[i]++;
        }
      }
    }

    for (int i=0;i<N;i++) {
      System.out.print(votes[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int test = sc.nextInt();
    while (test-- > 0) {
      int N = sc.nextInt();
      long[] arr = new long[N];
      long[] sum = new long[N];
      for (int i=0;i<N;i++) {
        arr[i] = sc.nextInt();
        sum[i] = (i == 0) ? arr[i]
                          : (sum[i-1] + arr[i]);
      }
      fast(N, arr, sum);
      //slow(N, arr, sum);
    }
  }
}
