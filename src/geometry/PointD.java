package geometry;

public class PointD implements Comparable<PointD>
{
	private static final double EPS=1e-9;
	public double x;
	public double y;
	public static PointD pivot;

	public PointD(double a,double b)
	{
		x=a;
		y=b;
	}

	public boolean equals(PointD p)
	{
		return Math.abs(this.x-p.x)<EPS&&Math.abs(this.y-p.y)<EPS;
	}

	public static double distance(PointD a,PointD b)
	{
		double x=a.x-b.x;
		double y=a.y-b.y;
		return Math.hypot(x,y);
	}

	public PointD rotate(double theta)
	{
		double rad=Math.PI*theta/180;
		return new PointD(this.x*Math.cos(rad)-this.y*Math.sin(rad),this.x*Math.sin(rad)+this.y*Math.cos(rad));
	}

	public static PointD PointToLineDist(PointD p,PointD a,PointD b)
	{
		Vector ap=Vector.toVec(a,p);
		Vector ab=Vector.toVec(a,b);
		double u=Vector.dotProduct(ap,ab)/ab.norm();
		ab.scale(u);
		return ab.translate(a);
	}

	public static PointD PointToSegDist(PointD p,PointD a,PointD b)
	{
		Vector ap=Vector.toVec(a,p);
		Vector ab=Vector.toVec(a,b);
		double u=Vector.dotProduct(ap,ab)/ab.norm();
		if(u<0.0) return a;
		if(u>1.0) return b;
		return PointToLineDist(p,a,b);
	}

	public static double angle(PointD a,PointD o,PointD b)// returns angle in rad(doesn't care about orientation)
	{
		Vector oa=Vector.toVec(o,a);
		Vector ob=Vector.toVec(o,b);
		return Math.acos(Vector.dotProduct(oa,ob)/Math.sqrt(oa.norm()*ob.norm()));
	}

	public static boolean ccw(PointD p,PointD q,PointD r)
	{
		return Vector.crossProduct(Vector.toVec(p,q),Vector.toVec(p,r))>EPS;
	}

	public static boolean colinear(PointD p,PointD q,PointD r)
	{
		return Math.abs(Vector.crossProduct(Vector.toVec(p,q),Vector.toVec(p,r)))<EPS;
	}

	public static PointD lineSegIntersec(PointD p,PointD q,PointD A,PointD B)
	{
		double a=B.y-A.y;
		double b=B.x-A.x;
		double c=B.x*A.y-A.x*B.y;
		double u=Math.abs(a*p.x+b*p.y+c);
		double v=Math.abs(a*q.x+b*q.y+c);
		return new PointD((p.x*v+q.x*u)/(u+v),(p.y*v+q.y*u)/(u+v));
	}

	public static void setPivot(double x,double y)
	{
		pivot=new PointD(x,y);
	}

	@Override
	public int compareTo(PointD p)
	{
		if(PointD.colinear(pivot,this,p)) return Double.compare(PointD.distance(pivot,this),PointD.distance(pivot,p));
		double d1x=this.x-pivot.x;
		double d1y=this.y-pivot.y;
		double d2x=p.x-pivot.x;
		double d2y=p.y-pivot.y;
		return Double.compare(Math.atan2(d1y,d1x),Math.atan2(d2y,d2x));
	}

	@Override
	public String toString()
	{
		return "("+x+","+y+")";
	}
}
