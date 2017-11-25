package data_structures;

import java.util.*;

public class UnionFind
{
	public int[] parent;
	public int[] rank;
	public int[] numberOfVertices;// number of vertices under this vertex
	public int dS;

	public UnionFind(int n)
	{
		parent=new int[n];
		rank=new int[n];
		numberOfVertices=new int[n];
		for(int i=0;i<n;i++)
			parent[i]=i;
		Arrays.fill(numberOfVertices,1);
		dS=n;
	}

	public int find(int x)
	{
		if(parent[x]==x) return x;
		return parent[x]=find(parent[x]);
	}

	public void unite(int a,int b)
	{
		int pa=this.find(a),pb=this.find(b);
		if(pa!=pb)
		{
			if(rank[pa]==rank[pb])
			{
				parent[pa]=pb;
				rank[pb]++;
				numberOfVertices[pb]+=numberOfVertices[pa];
			}
			else if(rank[pa]>rank[pb])
			{
				parent[pb]=pa;
				numberOfVertices[pa]+=numberOfVertices[pb];
			}
			else
			{
				parent[pa]=pb;
				numberOfVertices[pb]+=numberOfVertices[pa];
			}
			dS--;
		}
	}
}
