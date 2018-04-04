package codechef.cookoff.march2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CO92TREE {
  static int[] rank, size, dsu, parent;
  static List<Integer>[] adj;

  // get the root of the set and perform path compression
  public static int get(int x) {
    return (x == dsu[x]) ? x : (dsu[x] = get(dsu[x]));
  }

  // union by rank
  public static int unite(int x, int y) {
    int px = get(x);
    int py = get(y);

    if (px == py) return size[px];

    if (rank[px] < rank[py]) {
      dsu[px] = py;
      size[py] += size[px];
      return size[py];
    } else if (rank[px] > rank[py]) {
      dsu[py] = px;
      size[px] += size[py];
      return size[px];
    } else {
      dsu[px] = py;
      size[py] += size[px];
      rank[py]++;
      return size[py];
    }
  }

  public static void assignParent(int v, int p) {
    parent[v] = p;
    for (int c : adj[v]) {
      if (c != p) {
        assignParent(c, v);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int test = sc.nextInt();
    while (test-- > 0) {
      int N = sc.nextInt();
      rank = new int[N];
      size = new int[N];
      dsu = new int[N];
      parent = new int[N];
      adj = new List[N];
      for (int i=0;i<N;i++) {
        rank[i] = 1;
        size[i] = 1;
        dsu[i] = i;
        adj[i] = new ArrayList<>();
      }
      for (int i=0;i<N-1;i++) {
        int u = sc.nextInt() - 1;
        int v = sc.nextInt() - 1;
        adj[u].add(v);
        adj[v].add(u);
      }
      assignParent(0,0);
      long ans = 0;
      for (int i=1;i<=N;i++) {
        int mx = 1;
        for (int j=i;j<=N;j=(j+1)|i) {
          int p = parent[j-1] + 1;
          if ((p & i) == i) {
            mx = Math.max(mx, unite(p-1, j-1));
          }
        }
        ans = Math.max(ans, (long) mx*(long) i);
        for (int j=i;j<=N;j=(j+1)|i) {
          dsu[j-1] = j-1;
          size[j-1] = 1;
          rank[j-1] = 1;
        }
      }
      for (int i=0;i<N;i++) {
        adj[i].clear();
      }
      System.out.println(ans);
    }
  }
}
