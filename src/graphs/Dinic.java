package graphs;

import java.util.*;

public class Dinic
{
	public static class Edge
	{
		int to;
		int flow;
		int capacity;
		int reverseId;

		public Edge(int t,int rev,int c)
		{
			to=t;
			capacity=c;
			reverseId=rev;
		}

		@Override
		public String toString()
		{
			return to+" "+flow+"/"+capacity+" : "+reverseId;
		}
	}

	public int n;
	public int[] level;
	public ArrayList<Edge>[] graph;

	public Dinic(int v)
	{
		n=v;
		graph=new ArrayList[v];
		for(int i=0;i<v;i++)
			graph[i]=new ArrayList<Edge>();
		level=new int[v];
	}

	public void add(int u,int v,int c)
	{
		graph[u].add(new Edge(v,graph[v].size(),c));
		graph[v].add(new Edge(u,graph[u].size()-1,0));
	}

	public boolean bfs(int s,int t)
	{
		Arrays.fill(level,-1);
		level[s]=0;
		ArrayDeque<Integer> q=new ArrayDeque<Integer>();
		q.add(s);
		while(!q.isEmpty())
		{
			int u=q.pollFirst();
			for(Edge e:graph[u])
			{
				if(level[e.to]<0&&e.flow<e.capacity)
				{
					level[e.to]=level[u]+1;
					q.add(e.to);
				}
			}
		}
		return level[t]>=0;
	}

	public int sendFlow(int src,int dst,int flow,int[] start)
	{
		if(src==dst) return flow;
		for(;start[src]<graph[src].size();++start[src])
		{
			Edge e=graph[src].get(start[src]);
			if(level[e.to]==level[src]+1&&e.flow<e.capacity)
			{
				int curr_flow=Math.min(flow,e.capacity-e.flow);
				int temp_flow=sendFlow(e.to,dst,curr_flow,start);
				if(temp_flow>0)
				{
					e.flow+=temp_flow;
					graph[e.to].get(e.reverseId).flow-=temp_flow;
					return temp_flow;
				}
			}
		}
		return 0;
	}

	public int maxFlow(int s,int t)// O(V^2*E)
	{
		if(s==t) return -1;
		int res=0;
		while(bfs(s,t))
		{
			int[] start=new int[n];
			while(true)
			{
				int f=sendFlow(s,t,Integer.MAX_VALUE,start);
				if(f==0) break;
				res+=f;
			}
		}
		return res;
	}
}
