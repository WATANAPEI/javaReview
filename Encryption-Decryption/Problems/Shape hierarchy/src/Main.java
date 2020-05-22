abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    private double e1;
    private double e2;
    private double e3;

    public Triangle(double e1, double e2, double e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    double getPerimeter() {
        return e1 + e2 + e3;
    }

    double getArea() {
        double s = (e1 + e2 + e3) / 2.0;
        return Math.sqrt(s * (s - e1) * (s - e2) * (s - e3));
    }
}

class Rectangle extends Shape {

    private double e1;
    private double e2;

    public Rectangle(double e1, double e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    double getArea() {
        return e1 * e2;
    }

    double getPerimeter() {
        return (e1 + e2) * 2;
    }
}

class Circle extends Shape {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    double getArea() {
        return r * r * Math.PI;
    }

    double getPerimeter() {
        return 2 * r * Math.PI;
    }
}