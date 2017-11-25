package graphs;

import java.util.*;

public class Graph
{
	public ArrayList<Integer>[] adjList;
	public boolean[] visited;
	public ArrayDeque<Integer> q;
	public int n;

	public Graph(int v)
	{
		n=v;
		visited=new boolean[v];
		q=new ArrayDeque<Integer>(v);
		adjList=new ArrayList[v];
		for(int i=0;i<v;i++)
			adjList[i]=new ArrayList<Integer>();
	}

	public void add(int i,int j)
	{
		adjList[i].add(j);
		adjList[j].add(i);
		return;
	}

	public void dfs(int u)
	{
		if(visited[u]) return;
		visited[u]=true;
		for(int i=0;i<adjList[u].size();i++)
		{
			this.dfs(adjList[u].get(i));
		}
	}

	public void reset()
	{
		visited=new boolean[n];
	}

	public void bfs(int u)
	{
		q.add(u);
		visited[u]=true;
		while(!q.isEmpty())
		{
			int x=q.remove();
			for(int i=0;i<adjList[x].size();i++)
			{
				int z=adjList[x].get(i);
				if(!visited[z])
				{
					q.add(z);
					visited[z]=true;
				}
			}
		}
	}
}
