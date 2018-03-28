package codechef.cookoff.march2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CO92SUBW {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Integer> stations = new ArrayList<>();
    stations.add(1);
    for (int i=2;stations.get(stations.size()-1) < 1e9;i++) {
      stations.add(stations.get(stations.size()-1) + i);
    }
    int test = sc.nextInt();
    while (test-- > 0) {
      int X = sc.nextInt();
      int l = 0;
      int r = stations.size() - 1;
      while (l < r) {
        int mid = (l + r) >> 1;
        if (stations.get(mid) < X) {
          l = mid + 1;
        } else {
          r = mid;
        }
      }
      int a = 1 + l + stations.get(l) - X;
      int b = l + ((l != 0) ? X - stations.get(l-1) : 1000000000);
      System.out.println(Math.min(a,b));
    }
  }
}
