package geometry;

public class Vector
{
	public double x;
	public double y;

	public Vector(double a,double b)
	{
		x=a;
		y=b;
	}

	public static Vector toVec(PointD f,PointD t)
	{
		return new Vector(t.x-f.x,t.y-f.y);
	}

	public void scale(double s)
	{
		x*=s;
		y*=s;
	}

	public PointD translate(PointD p)
	{
		return new PointD(p.x+x,p.y+y);
	}

	public static double dotProduct(Vector a,Vector b)
	{
		return a.x*b.x+a.y*b.y;
	}

	public double norm()
	{
		return this.x*this.x+this.y*this.y;
	}

	public static double crossProduct(Vector a,Vector b)
	{
		return a.x*b.y-a.y*b.x;
	}
}
