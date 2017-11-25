package geometry;

public class Line// Defined by Ax+By=C , B=0 for vertical lines, B=1 otherwise
{
	public double A;
	public double B;
	public double C;
	private static final double EPS=1e-9;

	public Line(double x1,double y1,double x2,double y2)// Points must be different
	{
		if(Math.abs(x1-x2)<EPS)
		{
			A=1;
			B=0;
			C=-x1;
		}
		else
		{
			A=-1*(y1-y2)/(x1-x2);
			B=1;
			C=-1*A*x1-y1;
		}
	}

	public Line(double a,double b,double c)
	{
		A=a;
		B=b;
		C=c;
	}

	public static boolean areParallel(Line x,Line y)
	{
		return Math.abs(x.A-y.A)<EPS&&Math.abs(x.B-y.B)<EPS;
	}

	public static boolean areSame(Line x,Line y)
	{
		return areParallel(x,y)&&Math.abs(x.C-y.C)<EPS;
	}

	public static PointD intersectionPoint(Line l1,Line l2)
	{
		if(areParallel(l1,l2)) return null;
		double xc=(l2.B*l1.C-l1.B*l2.C)/(l2.A*l1.B-l1.A*l2.B);
		double yc;
		if(Math.abs(l1.B)>EPS) yc=-1*(l1.A*xc+l1.C);
		else yc=-1*(l2.A*xc+l2.C);
		return new PointD(xc,yc);
	}

	public double distanceToPoint(double x0,double y0)
	{
		return Math.abs(A*x0+B*y0+C)/Math.hypot(A,B);
	}

	public static Line perpendicular(Line l,double x0,double y0)
	{
		return new Line(-l.B,l.A,-l.B*x0+l.A*y0);
	}

	@Override
	public String toString()
	{
		return "y="+(-A)+"*x+"+C;
	}
}
