package view.colorparse;

/**
 * Created by aaa on 2017/10/25.
 */

public class HSL {

    public float h;
    public float s;
    public float l;

    public float getH() {
        return h;
    }

    public float getS() {
        return s;
    }

    public float getL() {
        return l;
    }

    public HSL(float h, float s, float l) {
        this.h = h;
        this.s = s;
        this.l = l;
    }
}
