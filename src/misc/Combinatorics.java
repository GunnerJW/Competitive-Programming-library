package misc;

import java.util.*;

public class Combinatorics
{
	public String s;
	public HashSet<String> subsets;
	public HashSet<String> permutations;

	public Combinatorics(String x)
	{
		s=x;
		subsets=new HashSet<String>((1<<x.length()));
		permutations=new HashSet<String>();
	}

	private void processSubsets(String tmp,int index)
	{
		if(index==s.length())
		{
			subsets.add(tmp);
			return;
		}
		processSubsets(tmp,index+1);
		processSubsets(tmp+String.valueOf(s.charAt(index)),index+1);
	}

	public void processSubsets()// O(2^s.length)
	{
		processSubsets("",0);
		subsets.remove("");
	}

	private int factorial(int n)//Use dynamic programming in case of multiple calls
	{
		if(n==0) return 1;
		return n*factorial(n-1);
	}

	private void permutations(String s)// O(s.length!)
	{
		if(s.length()==1)
		{
			permutations.add(s);
			return;
		}
		if(s.length()==2)
		{
			permutations.add(s);
			permutations.add((new StringBuilder(s)).reverse().toString());
			return;
		}
		StringBuilder sb=new StringBuilder(s);
		int n=s.length();
		int fc=factorial(n);
		int j=1;
		int m=0;
		for(int i=0;i<fc;)
		{
			StringBuilder perm=new StringBuilder(sb.toString());
			int k=0;
			while(k!=fc/n)
			{
				while(j!=n-1)
				{
					permutations.add(perm.toString());
					char a=perm.charAt(j);
					char b=perm.charAt(j+1);
					perm.setCharAt(j+1,a);
					perm.setCharAt(j,b);
					k++;
					i++;
					j++;
				}
				j=1;
			}
			m++;
			if(m==n) break;
			char a=sb.charAt(0);
			char b=sb.charAt(m);
			sb.setCharAt(0,b);
			sb.setCharAt(m,a);
		}
	}

	public void allPermutations()
	{
		for(String s:subsets)
			permutations(s);
		return;
	}
}
