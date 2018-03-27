package codechef.monthLong.march2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class GCDCNT {
  static int[] primes = new int[9592];
  static BitSet[] record = new BitSet[9592];

  public static void sieve() {
    boolean[] flag = new boolean[100001];
    int cnt = 0;
    for (int i=2;i<100001;i++) {
      if (!flag[i]) {
        primes[cnt++] = i;
        for (int j=i;j<100001;j+=i) {
          flag[j] = true;
        }
      }
    }
  }

  public static List factorize(int val) {
    List<Integer> bits = new ArrayList<>();
    int cnt = 0;
    while (val > 1) {
      if (val % primes[cnt] == 0) {
        bits.add(cnt);
        while (val % primes[cnt] == 0) {
          val /= primes[cnt];
        }
      }
      cnt++;
    }
    return bits;
  }

  public static void setAt(List<Integer> bits, int index, boolean val) {
    for (int bit : bits) {
      record[bit].set(index, val);
    }
  }

  public static void main(String[] args) {
    sieve();
    for (int i=0;i<record.length;i++) {
      record[i] = new BitSet();
    }
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] arr = new int[N];
    for (int i=0;i<N;i++) {
      arr[i] = sc.nextInt();
      setAt(factorize(arr[i]), i, true);
    }
    int Q = sc.nextInt();
    while (Q-- > 0) {
      int op = sc.nextInt();
      if (op == 1) {
        int X = sc.nextInt();
        X--;
        int Y = sc.nextInt();
        setAt(factorize(arr[X]), X, false);
        arr[X] = Y;
        setAt(factorize(Y), X, true);
      } else {
        int l = sc.nextInt();
        int r = sc.nextInt();
        l--;
        r--;
        List<Integer> bits = factorize(sc.nextInt());
        BitSet ans = new BitSet();
        for (int bit : bits) {
          ans.or(record[bit]);
        }
        ans = ans.get(l, r + 1);
        System.out.println(r - l + 1 - ans.cardinality());
      }
    }
  }
}
