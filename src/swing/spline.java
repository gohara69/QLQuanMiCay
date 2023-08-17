/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VU HOANG
 */
public class spline {
    public splinePoint[] copyPoints() {
        splinePoint[] ps = new splinePoint[points.length];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = new splinePoint(points[i].getX(), points[i].getY());
        }
        return ps;
    }

    public splinePoint[] getPoints() {
        return points;
    }

    public void setPoints(splinePoint[] points) {
        this.points = points;
    }

    private splinePoint[] points;

    public spline() {
    }

    public List<splinePoint> createSpline(float f, splinePoint... point) {
        this.points = point;
        List<splinePoint> list = new ArrayList<>();
        for (float t = 0f; t < (float) (point.length - 3.0f) * f; t += 0.01f) {
            list.add(getSpline(t));
        }
        return list;
    }

    public splinePoint getSpline(float t) {
        int p0, p1, p2, p3;
        p1 = (int) t + 1;
        p2 = p1 + 1;
        p3 = p2 + 1;
        p0 = p1 - 1;
        t = t - (int) t;
        float tt = t * t;
        float ttt = tt * t;
        float q1 = -ttt + 2.0f * tt - t;
        float q2 = 3.0f * ttt - 5.0f * tt + 2.0f;
        float q3 = -3.0f * ttt + 4.0f * tt + t;
        float q4 = ttt - tt;
        double tx = 0.5f * (points[p0].getX() * q1 + points[p1].getX() * q2 + points[p2].getX() * q3 + points[p3].getX() * q4);
        double ty = 0.5f * (points[p0].getY() * q1 + points[p1].getY() * q2 + points[p2].getY() * q3 + points[p3].getY() * q4);
        return new splinePoint(tx, ty);
    }
}
