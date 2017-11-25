package geometry;

public class Triangle
{
	//Law of cosines : c2=a2+b2-2*a*b*cos(Opposed angle)
	//Law of sines : a/sin(opposed angle to a)=b/sin(opposed angle to b)=c/sin(opposed angle to c)=2*Radius_Of_CircumCircle
	public static double area(double a,double b,double c)
	{
		double s=0.5*(a+b+c);
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	public static double radiusInCircle(double a,double b,double c)
	{
		return area(a,b,c)/(0.5*(a+b+c));
	}

	public static double radiusInCircle(PointD a,PointD b,PointD c)
	{
		return radiusInCircle(PointD.distance(a,b),PointD.distance(b,c),PointD.distance(a,c));
	}

	public static double radiusCircumCircle(double a,double b,double c)
	{
		return (a*b*c)/(4*area(a,b,c));
	}

	public static double radiusCircumCircle(PointD a,PointD b,PointD c)
	{
		return radiusCircumCircle(PointD.distance(a,b),PointD.distance(b,c),PointD.distance(a,c));
	}
}
