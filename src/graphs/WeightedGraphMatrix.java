package graphs;

import java.util.*;

public class WeightedGraphMatrix
{
	public int n;
	public int[][] adjMatrix;
	public int[][] apspMatrix;
	public int[][] p;

	public WeightedGraphMatrix(int v)
	{
		n=v;
		p=new int[n][n];
		adjMatrix=new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<v;j++)
			{
				if(i!=j) adjMatrix[i][j]=Integer.MAX_VALUE;
				p[i][j]=i;
			}
		apspMatrix=new int[n][n];
		for(int i=0;i<n;i++)
			apspMatrix[i]=Arrays.copyOf(adjMatrix[i],n);
	}

	public void add(int i,int j,int w)
	{
		adjMatrix[i][j]=w;
		adjMatrix[j][i]=w;
	}

	public void floydW()
	{
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(apspMatrix[i][k]+apspMatrix[k][j]<apspMatrix[i][j])
					{
						apspMatrix[i][j]=apspMatrix[i][k]+apspMatrix[k][j];
						p[i][j]=p[k][j];
					}
	}

	public void cycleDetection()
	{
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(apspMatrix[i][k]+apspMatrix[k][j]<apspMatrix[i][j]) apspMatrix[i][j]=Integer.MIN_VALUE;
	}

	public void printPath(int i,int j)
	{
		if(i!=j) printPath(i,p[i][j]);
		System.out.print(j+" ");
	}
}
