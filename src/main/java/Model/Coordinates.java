package main.java.Model;

public class Coordinates {
    private Double x;
    private int y;

    public Coordinates(Double x, int y) {
        setX(x);
        setY(y);
    }

    public Coordinates() {};

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("Координата X не может быть null");
        }
        if (x > 117) {
            throw new IllegalArgumentException("Максимальное значение координаты X: 117");
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}