package geometry;

public class Circle
{
	public double x;
	public double y;
	public double r;
	public static final double EPS=1e-9;

	public Circle(double h,double v,double rd)
	{
		x=h;
		y=v;
		r=rd;
	}

	public static boolean areTangent(Circle c1,Circle c2)
	{
		return Math.abs(Math.pow(c1.x-c2.x,2)+Math.pow(c1.y-c2.y,2)-c1.r*c1.r+c2.r*c2.r)<EPS;
	}

	public double chordLength(double centralAngle)// angle given in deg
	{
		double rad=Math.PI*centralAngle/180;
		return Math.sqrt(2*r*r*(1-Math.cos(rad)));
	}
}
