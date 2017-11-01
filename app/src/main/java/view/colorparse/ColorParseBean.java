package view.colorparse;

import android.graphics.Color;
import android.text.TextUtils;

/**
 * Created by aaa on 2017/10/25.
 */

public class ColorParseBean {

    private int weight;
    private int weightSum;

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }

    public int getWeightSum() {
        return weightSum;
    }

    private String titleEn;
    private String titleZh;

    private RGB rgb;

    private String rgb16;

    private HSL hsl;

    private HSV hsv;

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public HSV getHsv() {
        if(null == hsv){
            if(rgb != null) {
                float[] hsvs = new float[3];
                Color.RGBToHSV(rgb.red, rgb.green, rgb.blue, hsvs);
                hsv = new HSV(hsvs[0], hsvs[1], hsvs[2]);
            }
        }
        return hsv;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleZh() {
        return titleZh;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;

    }

    public void setTitleZh(String titleZh) {
        this.titleZh = titleZh;
    }

    public void setRgb(RGB rgb) {
        this.rgb = rgb;
    }

    public void setRgb16(String rgb16) {
        this.rgb16 = rgb16;
    }



    public RGB getRGB() {
        return rgb;
    }

    public String getRGB16() {
        if(TextUtils.isEmpty(rgb16)){
            if(null!=rgb){
                rgb16 =  convertRGBToHex(rgb.red,rgb.green,rgb.blue);
                return rgb16;
            }else {
                return "RGB IS NULL";
            }
        }else {
            return rgb16;
        }

    }

    public  String convertRGBToHex(int r, int g, int b) {
        String rFString, rSString, gFString, gSString,
                bFString, bSString, result;
        int red, green, blue;
        int rred, rgreen, rblue;
        red = r / 16;
        rred = r % 16;
        if (red == 10) rFString = "A";
        else if (red == 11) rFString = "B";
        else if (red == 12) rFString = "C";
        else if (red == 13) rFString = "D";
        else if (red == 14) rFString = "E";
        else if (red == 15) rFString = "F";
        else rFString = String.valueOf(red);

        if (rred == 10) rSString = "A";
        else if (rred == 11) rSString = "B";
        else if (rred == 12) rSString = "C";
        else if (rred == 13) rSString = "D";
        else if (rred == 14) rSString = "E";
        else if (rred == 15) rSString = "F";
        else rSString = String.valueOf(rred);

        rFString = rFString + rSString;

        green = g / 16;
        rgreen = g % 16;

        if (green == 10) gFString = "A";
        else if (green == 11) gFString = "B";
        else if (green == 12) gFString = "C";
        else if (green == 13) gFString = "D";
        else if (green == 14) gFString = "E";
        else if (green == 15) gFString = "F";
        else gFString = String.valueOf(green);

        if (rgreen == 10) gSString = "A";
        else if (rgreen == 11) gSString = "B";
        else if (rgreen == 12) gSString = "C";
        else if (rgreen == 13) gSString = "D";
        else if (rgreen == 14) gSString = "E";
        else if (rgreen == 15) gSString = "F";
        else gSString = String.valueOf(rgreen);

        gFString = gFString + gSString;

        blue = b / 16;
        rblue = b % 16;

        if (blue == 10) bFString = "A";
        else if (blue == 11) bFString = "B";
        else if (blue == 12) bFString = "C";
        else if (blue == 13) bFString = "D";
        else if (blue == 14) bFString = "E";
        else if (blue == 15) bFString = "F";
        else bFString = String.valueOf(blue);

        if (rblue == 10) bSString = "A";
        else if (rblue == 11) bSString = "B";
        else if (rblue == 12) bSString = "C";
        else if (rblue == 13) bSString = "D";
        else if (rblue == 14) bSString = "E";
        else if (rblue == 15) bSString = "F";
        else bSString = String.valueOf(rblue);
        bFString = bFString + bSString;
        result = "#" + rFString + gFString + bFString;
        return result;

    }





    @Override
    public String toString() {
        return "ColorParseBean{" +
                "titleEn='" + titleEn + '\'' +
                ", titleZh='" + titleZh + '\'' +
                ", rgb=" + rgb +
                ", rgb16='" + rgb16 + '\'' +
                '}';
    }
}
