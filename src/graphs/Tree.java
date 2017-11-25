package graphs;

import java.util.*;

public class Tree// How to use : Instanciate -> Add edges -> Do sssp on root -> Do Dfs on root with level 0 -> Do buildRMQ
{
	private static class Pair
	{
		public int v;
		public int w;

		public Pair(int a,int b)
		{
			v=a;
			w=b;
		}
	}

	private static class SegmentTreeMin
	{
		private int[] st;
		private int[] arr;
		private int n;

		private void build(int p,int L,int R)
		{
			if(L==R)
			{
				st[p]=L;
				return;
			}
			build(2*p,L,(L+R)/2);
			build(2*p+1,(L+R)/2+1,R);
			int p1=st[2*p],p2=st[2*p+1];
			st[p]=(arr[p1]<=arr[p2])?p1:p2;
		}

		private int query(int p,int L,int R,int i,int j)
		{
			if(i>R||j<L) return -1;
			if(L>=i&&R<=j) return st[p];
			int p1=query(2*p,L,(L+R)/2,i,j);
			int p2=query(2*p+1,(L+R)/2+1,R,i,j);
			if(p1==-1) return p2;
			if(p2==-1) return p1;
			return (arr[p1]<arr[p2])?p1:p2;
		}

		public SegmentTreeMin(int[] a)
		{
			this.n=a.length;
			this.arr=Arrays.copyOf(a,n);
			this.st=new int[4*n];
			build(1,0,n-1);
		}

		public int query(int i,int j)
		{
			return query(1,0,n-1,i,j);
		}
	}

	public ArrayList<ArrayList<Pair>> adjList;
	public ArrayList<ArrayList<Integer>> desc;
	public int[] L,H,E;
	public int idx;
	public boolean[] visited;
	public int[] d;
	public SegmentTreeMin stm;

	public Tree(int n)
	{
		adjList=new ArrayList<ArrayList<Pair>>(n);
		for(int i=0;i<n;i++)
			adjList.add(new ArrayList<Pair>());
		desc=new ArrayList<ArrayList<Integer>>(n);
		for(int i=0;i<n;i++)
			desc.add(new ArrayList<Integer>());
		L=new int[2*n];
		E=new int[2*n];
		H=new int[n];
		Arrays.fill(H,-1);
		visited=new boolean[n];
		d=new int[n];
		idx=0;
	}

	public void sssp(int u)
	{
		if(visited[u]) return;
		visited[u]=true;
		for(int i=0;i<adjList.get(u).size();i++)
		{
			Pair p=adjList.get(u).get(i);
			if(!visited[p.v])
			{
				desc.get(u).add(p.v);
				d[p.v]=d[u]+p.w;
				sssp(p.v);
			}
		}
	}

	public void add(int i,int j,int w)
	{
		adjList.get(i).add(new Pair(j,w));
		adjList.get(j).add(new Pair(i,w));
	}

	public void dfs(int cur,int depth)
	{
		H[cur]=idx;
		E[idx]=cur;
		L[idx++]=depth;
		for(int i=0;i<desc.get(cur).size();i++)
		{
			dfs(desc.get(cur).get(i),depth+1);
			E[idx]=cur;
			L[idx++]=depth;
		}
	}

	public void buildRMQ()
	{
		stm=new SegmentTreeMin(E);
	}

	public int lca(int u,int v)
	{
		int x=H[u];
		int y=H[v];
		int id=stm.query(Math.min(x,y),Math.max(x,y));// x can be bigger than y
		return E[id];
	}

	public int distance(int u,int v)
	{
		return d[u]+d[v]-2*d[lca(u,v)];
	}
}
