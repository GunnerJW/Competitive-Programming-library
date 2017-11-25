package geometry;

public class Point
{
	public int x;
	public int y;

	public Point(int a,int b)
	{
		x=a;
		y=b;
	}

	public boolean equals(Point p)
	{
		return this.x==p.x&&this.y==p.y;
	}

	public static double distance(Point a,Point b)
	{
		int x=a.x-b.x;
		int y=a.y-b.y;
		return Math.hypot(x,y);
	}

	/*
	 * public static long orientationDet(Point p,Point q,Point r)//may lead to
	 * overflow
	 * 
	 * {
	 * 
	 * return q.x*r.y-r.x*q.y-p.x*(r.y-q.y)+p.y*(r.x-q.x);
	 * 
	 * }
	 * 
	 * public static double triangleArea(Point p,Point q,Point r)
	 * 
	 * {
	 * 
	 * return Math.abs(orientationDet(p,q,r))/2;
	 * 
	 * }
	 * 
	 * public double angleToPivot(Point pivot)
	 * 
	 * {
	 * 
	 * if(this.equals(pivot))
	 * 
	 * return Double.MIN_VALUE;
	 * 
	 * return Math.acos((this.x-pivot.x)/Math.hypot(this.x-pivot.x,
	 * this.y-pivot.y));
	 * 
	 * }
	 */
	@Override
	public String toString()
	{
		return "("+x+","+y+")";
	}
}
