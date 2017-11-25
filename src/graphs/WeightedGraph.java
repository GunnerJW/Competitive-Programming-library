package graphs;

import java.util.*;
import data_structures.UnionFind;

public class WeightedGraph
{
	public int n;
	public ArrayList<Pair>[] adjList;
	public ArrayList<Edge> edgeList;

	public static class Pair
	{
		public int v;
		public int w;

		public Pair(int a,int b)
		{
			v=a;
			w=b;
		}
	}

	public static class Pair2// For Dijkstra
	{
		public int d;
		public int v;

		public Pair2(int dd,int vv)
		{
			d=dd;
			v=vv;
		}

		public int d()
		{
			return d;
		}

		public int v()
		{
			return v;
		}

		public static Comparator<Pair2> pairComparator=Comparator.comparingInt(Pair2::d).thenComparingInt(Pair2::v);
	}

	public static class Edge
	{
		public int v1;
		public int v2;
		public int w;

		public Edge(int v1,int v2,int w)
		{
			this.w=w;
			this.v1=v1;
			this.v2=v2;
		}

		public int v1()
		{
			return v1;
		}

		public int v2()
		{
			return v2;
		}

		public int w()
		{
			return w;
		}

		public static Comparator<Edge> edgeComparator=Comparator.comparingInt(Edge::w).thenComparingInt(Edge::v1)
				.thenComparingInt(Edge::v2);
	}

	public WeightedGraph(int v)
	{
		n=v;
		adjList=new ArrayList[v];
		for(int i=0;i<v;i++)
			adjList[i]=new ArrayList<Pair>();
		edgeList=new ArrayList<Edge>();
	}

	public void add(int a,int b,int w)
	{
		adjList[a].add(new Pair(b,w));
		adjList[b].add(new Pair(a,w));
		edgeList.add(new Edge(w,a,b));
	}

	public void sortEdgeList()
	{
		edgeList.sort(Edge.edgeComparator);
	}

	public int mstKruskal()
	{
		int res=0;
		UnionFind uf=new UnionFind(n);
		this.sortEdgeList();
		for(Edge e:edgeList)
		{
			if(uf.dS==1) break;
			if(uf.find(e.v1)!=uf.find(e.v2))
			{
				uf.unite(e.v1,e.v2);
				res+=e.w;
			}
		}
		return res;
	}

	public int[] dijkstra(int x)
	{
		int[] distance=new int[n];
		Arrays.fill(distance,1000000000);
		distance[x]=0;
		PriorityQueue<Pair2> pq=new PriorityQueue<Pair2>(n,Pair2.pairComparator);
		pq.add(new Pair2(0,x));
		while(!pq.isEmpty())
		{
			Pair2 tmp=pq.poll();
			int dist=tmp.d;
			int vertex=tmp.v;
			if(dist>distance[vertex]) continue;
			for(Pair p:adjList[vertex])
			{
				int candidate=p.v;
				int weight=p.w;
				if(distance[vertex]+weight<distance[candidate])
				{
					distance[candidate]=distance[vertex]+weight;
					pq.add(new Pair2(distance[candidate],candidate));
				}
			}
		}
		return distance;
	}

	public int[] bellman_ford(int x)// if graph is directed , loop over edgeList
	{
		int[] distance=new int[n];
		Arrays.fill(distance,1000000000);
		distance[x]=0;
		for(int i=0;i<n-1;i++)
			for(int u=0;u<n;u++)
				for(Pair p:adjList[u])
					distance[p.v]=Math.min(distance[p.v],distance[u]+p.w);
		return distance;
	}

	public int[] cycleDetector(int[] arr)
	{
		int[] res=Arrays.copyOf(arr,n);
		for(int u=0;u<n;u++)
			for(Pair p:adjList[u])
				res[p.v]=Math.min(res[p.v],res[u]+p.w);
		return res;
	}
}