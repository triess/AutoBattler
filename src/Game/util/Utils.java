package Game.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Utils {
    private static int[] pointToCube(Point p){
        int[] arr = new int[3];
        arr[0]=p.x+(p.y+1)/2;
        arr[1]=p.y;
        arr[2]=p.x-p.y/2;
        return arr;
    }

    private static Point cubeToPoint(int[] c){
        int y = c[1];
        int x = c[2]+y/2;
        return new Point(x,y);
    }
    public static int hexDistance(Point p1,Point p2){
        int s1 = p1.x + (p1.y+1)/2;
        int s2 = p2.x + (p2.y+1)/2;
        int q1 = p1.x - p1.y/2;
        int q2 = p2.x - p2.y/2;
        return (Math.abs(s1-s2)+Math.abs(q1-q2)+Math.abs(p1.y-p2.y))/2;
    }

    public static Point nextStep(Point origin, Point destination){
        int[] ori = pointToCube(origin);
        int[] dest = pointToCube(destination);
        int[] delta = new int[3];
        for (int i = 0; i < 3; i++) {
            delta[i]=Math.abs(ori[i]-dest[i]);
        }
        int min = argMin(delta);
        for (int i = 0; i < 3; i++) {
            if(i!=min){
                if(ori[i]<dest[i]){
                    ori[i]++;
                }else{
                    ori[i]--;
                }
            }
        }
        return cubeToPoint(ori);
    }

    public static Point NSteps(Point origin, Point destination, int steps){
        if(steps==1)return nextStep(origin,destination);
        if(steps<1)return null;
        return NSteps(nextStep(origin, destination),destination,steps-1);
    }

    public static int argMin(int[] a) {
        int v = Integer.MAX_VALUE;
        int ind = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < v) {
                v = a[i];
                ind = i;
            }
        }
        return ind;
    }
}
