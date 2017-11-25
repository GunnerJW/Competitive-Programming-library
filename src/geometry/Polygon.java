package geometry;

import java.util.*;

public class Polygon
{
	static final double EPS=1e-9;
	public PointD[] arr;// must be stored in CW or CCW
	int n;

	public Polygon(int n)
	{
		this.n=n;
		arr=new PointD[n+1];
	}

	public void add(PointD p,int index)// !!Important : Loop back to first point
	{
		arr[index]=p;
	}

	public double perimeter()
	{
		double perimeter=0;
		for(int i=0;i<arr.length-1;i++)
			perimeter+=PointD.distance(arr[i],arr[i+1]);
		return perimeter;
	}

	public double area()
	{
		double area=0;
		for(int i=0;i<arr.length-1;i++)
			area+=(arr[i+1].x-arr[i].x)*(arr[i+1].y+arr[i].y)/2;
		return area;
	}

	public boolean isConvex()// doesn't care about the ordering of the points (CW or CCW)
	{
		if(n<=2) return false;
		boolean isLeft=PointD.ccw(arr[0],arr[1],arr[2]);
		for(int i=1;i<n;i++)
			if(PointD.ccw(arr[i],arr[i+1],arr[(i+2)==n+1?1:i+2])!=isLeft) return false;
		return true;
	}

	public boolean isInside(PointD p)
	{
		if(n<=2)// if the shape is not a polygon
			return false;
		double sum=0;
		for(int i=0;i<n;i++)
		{
			if(PointD.ccw(p,arr[i],arr[i+1])) sum+=PointD.angle(arr[i],p,arr[i+1]);
			else sum-=PointD.angle(arr[i],p,arr[i+1]);
		}
		return Math.abs(sum-2*Math.PI)<EPS;
	}

	public Polygon cut(PointD a,PointD b)// return the left cut of line (ab) oriented by vector a->b
	{
		ArrayList<PointD> al=new ArrayList<PointD>(n);
		for(int i=0;i<n+1;i++)
		{
			double left1=Vector.crossProduct(Vector.toVec(a,b),Vector.toVec(a,arr[i]));
			double left2=0;
			if(i!=n) left2=Vector.crossProduct(Vector.toVec(a,b),Vector.toVec(a,arr[i+1]));
			if(left1>-EPS) al.add(arr[i]);
			if(left1*left2<-EPS) al.add(PointD.lineSegIntersec(arr[i],arr[i+1],a,b));
		}
		if(!al.isEmpty()&&!al.get(0).equals(al.get(al.size()-1))) al.add(al.get(0));
		Polygon res=new Polygon(al.size()-1);
		for(int i=0;i<al.size();i++)
			res.add(al.get(i),i);
		return res;
	}

	public ArrayList<PointD> grahamScan()// returns the hull in CCW order/assumes all points are distinct
	{
		ArrayList<PointD> res=new ArrayList<PointD>();
		if(n<=3)
		{
			for(int i=0;i<n+1;i++)
				res.add(arr[i%n]);
			return res;
		}
		int pivotId=0;
		for(int i=1;i<n;i++)
			if((arr[i].y<arr[pivotId].y)||(arr[i].y==arr[pivotId].y&&arr[i].x>arr[pivotId].x)) pivotId=i;
		PointD.setPivot(arr[pivotId].x,arr[pivotId].y);
		PointD tmp=arr[0];
		arr[0]=arr[pivotId];
		arr[pivotId]=tmp;
		Arrays.sort(arr,1,n);
		res.add(arr[n-1]);
		res.add(arr[0]);
		res.add(arr[1]);
		int i=2;
		while(i<n)
		{
			int j=res.size()-1;
			if(PointD.ccw(res.get(j-1),res.get(j),arr[i])) res.add(arr[i++]);
			else res.remove(j);
		}
		return res;
	}
}
