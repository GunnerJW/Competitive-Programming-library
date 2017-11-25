package graphs;

import java.util.*;

public class FordFulkerson
{
	public HashMap<Integer,HashMap<Integer,Integer>> adj;
	public int n;
	public boolean[] visited;
	public HashMap<Integer,Integer> parent;

	public FordFulkerson(int v)
	{
		n=v;
		adj=new HashMap<Integer,HashMap<Integer,Integer>>(v);
		for(int i=0;i<v;i++)
			adj.put(i,new HashMap<Integer,Integer>());
		visited=new boolean[v];
		parent=new HashMap<Integer,Integer>(n);
	}

	public void add(int a,int b,int f)
	{
		adj.get(a).put(b,f);
	}

	public void dfs(int s,int t)
	{
		visited[s]=true;
		if(s==t) return;
		HashSet<Integer> hs=new HashSet<Integer>(adj.get(s).keySet());
		for(int v:hs)
		{
			if(!visited[v]&&adj.get(s).get(v)!=0)
			{
				parent.put(v,s);
				dfs(v,t);
			}
		}
		return;
	}

	public void reset()
	{
		visited=new boolean[n];
		parent=new HashMap<Integer,Integer>(n);
	}

	public int maxFlow(int s,int t)// O(|f|*E)
	{
		int f=0;
		dfs(s,t);
		while(parent.containsKey(t))
		{
			int min=Integer.MAX_VALUE;
			int vertex=t;
			while(vertex!=s)
			{
				int p=parent.get(vertex);
				min=Math.min(min,adj.get(p).get(vertex));
				vertex=p;
			}
			vertex=t;
			while(vertex!=s)
			{
				int p=parent.get(vertex);
				adj.get(p).put(vertex,adj.get(p).get(vertex)-min);
				if(adj.get(vertex).containsKey(p)) adj.get(vertex).put(p,adj.get(vertex).get(p)+min);
				else adj.get(vertex).put(p,min);
				vertex=p;
			}
			f+=min;
			reset();
			dfs(s,t);
		}
		return f;
	}
}
