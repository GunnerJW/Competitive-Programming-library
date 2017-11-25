package maths;

import java.util.*;

public class Maths
{
	public static boolean isPrime(int n,ArrayList<Integer> primes)
	{
		if(n==0||n==1) return false;
		int i=0;
		while(i<primes.size()&&primes.get(i)*primes.get(i)<=n)
		{
			if(n%primes.get(i)==0) return false;
			i++;
		}
		return true;
	}

	public static int gcd(int a,int b)
	{
		return b==0?a:gcd(b,a%b);
	}

	public static int lcm(int a,int b)
	{
		return a*b/gcd(a,b);
	}

	public static int[] egcd(int a,int b)// returns a triplet(d,u,v) such that au+bv=d=gcd(a,b)
	{
		int x=0,y=1,u=1,v=0;
		while(a!=0)
		{
			int q=b/a,r=b%a;
			int m=x-u*q,n=y-v*q;
			b=a;
			a=r;
			x=u;
			y=v;
			u=m;
			v=n;
		}
		return new int[]{b,x,y};
	}

	public static int modInverse(int a,int m)// assumes m and a are coprime
	{
		int[] tmp=egcd(a,m);
		int x=tmp[1];
		if(x<0) x+=m;
		return x;
	}

	public static HashMap<Integer,Integer> primeFctPow(int n,ArrayList<Integer> primes)// handle n==0
	{
		HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
		if(n==1)
		{
			hm.put(2,0);
			return hm;
		}
		int i=0,tmp=n;
		int pf=primes.get(i);
		while(pf*pf<=tmp)
		{
			while(tmp%pf==0)
			{
				tmp/=pf;
				if(hm.containsKey(pf)) hm.put(pf,hm.get(pf)+1);
				else hm.put(pf,1);
			}
			i++;
			if(i>=primes.size()) break;
			pf=primes.get(i);
		}
		if(tmp!=1)
		{
			if(hm.containsKey(tmp)) hm.put(tmp,hm.get(tmp)+1);
			else hm.put(tmp,1);
		}
		return hm;
	}

	public static ArrayList<Integer> primeFct(int n,ArrayList<Integer> primes)// handle n<=1
	{
		int m=(int)Math.sqrt(n);
		double d=(m/Math.log(m))*(1+1.2762/Math.log(m));
		ArrayList<Integer> al=new ArrayList<Integer>((int)d);
		int i=0,tmp=n;
		int pf=primes.get(i);
		while(pf*pf<=tmp)
		{
			while(tmp%pf==0)
			{
				tmp/=pf;
				al.add(pf);
			}
			i++;
			if(i>=primes.size()) break;
			pf=primes.get(i);
		}
		if(tmp!=1)
		{
			al.add(tmp);
		}
		return al;
	}

	public static ArrayList<Integer> primeSieve(int n)
	{
		ArrayList<Integer> primes=new ArrayList<Integer>();
		boolean[] crossed=new boolean[n+1];
		crossed[0]=true;
		crossed[1]=true;
		int m=0,i;
		while(m!=n)
		{
			for(i=2;i<n+1;i++)
			{
				if(crossed[i]==false)
				{
					m=i;
					primes.add(m);
					break;
				}
			}
			if(i==n+1) break;
			for(i=m;i<n+1;i+=m)
			{
				crossed[i]=true;
			}
		}
		return primes;
	}

	public static long modPow(long base,long exponent,long mod)
	{
		if(exponent==0) return 1;
		if(exponent%2==0)
		{
			long tmp=modPow(base,exponent/2,mod);
			return (tmp*tmp)%mod;
		}
		return (base*modPow(base,exponent-1,mod))%mod;
	}

	public static int eulerPhi(int n,ArrayList<Integer> primes)// does not work for 1
	{
		int phi=1;
		int i=0,tmp=n;
		int pf=primes.get(i);
		int exp;
		while(pf*pf<=tmp)
		{
			exp=0;
			while(tmp%pf==0)
			{
				tmp/=pf;
				exp++;
				if(exp==1) phi*=pf-1;
				else phi*=pf;
			}
			i++;
			if(i>=primes.size()) break;
			pf=primes.get(i);
		}
		if(tmp!=1)
		{
			phi*=tmp-1;
		}
		return phi;
	}
}
