package codechef.monthLong.march2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PSHTRG {
  static List<Integer>[] tree;
  static int N;

  public static List<Integer> merge(List<Integer> a, List<Integer> b) {
    if (a == null) a = new ArrayList<>();
    if (b == null) b = new ArrayList<>();
    List<Integer> res = new ArrayList<>();
    int i = 0;
    int j = 0;
    while((i < a.size() || j < b.size()) && res.size() < 50) {
      int p = i != a.size() ? a.get(i) : 0;
      int r = j != b.size() ? b.get(j) : 0;
      if (p > r) {
        res.add(p);
        i++;
      } else {
        res.add(r);
        j++;
      }
    }
    return res;
  }

  public static void build(int node, int start, int end, int pos, int x) {
    if (start == end) {
      tree[node] = new ArrayList<>();
      tree[node].add(x);
    } else {
      int mid = (start + end) >> 1;
      if (pos <= mid) {
        build(2*node, start, mid, pos, x);
      } else {
        build(2*node+1, mid+1, end, pos, x);
      }
      tree[node] = merge(tree[2*node], tree[2*node+1]);
    }
  }

  public static List<Integer> get(int node, int l, int r, int L, int R) {
    if (r < L || R < l) return new ArrayList<>();
    if (L <= l && r <= R) return tree[node];
    int mid = (l + r) >> 1;
    return merge(get(2*node, l, mid, L, R), get(2*node+1, mid+1, r, L, R));
  }

  public static long get(int L, int R) {
    List<Integer> sides = get(1, 0, N-1, L, R);
    long ans = 0;
    for (int i=0;i+2<sides.size();i++) {
      long a = (long)sides.get(i);
      long b = (long)sides.get(i+1);
      long c = (long)sides.get(i+2);
      if (a < b + c) {
        ans = Math.max(ans, a + b + c);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    tree = new List[4*N];
    int Q = sc.nextInt();
    for (int i=0;i<N;i++) {
      build(1, 0, N-1, i, sc.nextInt());
    }

    while (Q-- > 0) {
      int op = sc.nextInt();
      int l = sc.nextInt();
      int r = sc.nextInt();
      if (op == 1) {
        build(1, 0, N-1, l-1, r);
      } else {
        System.out.println(get(l-1, r-1));
      }
    }
  }
}
