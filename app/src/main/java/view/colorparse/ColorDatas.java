package view.colorparse;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by aaa on 2017/10/25.
 */

public class ColorDatas {

    public static ArrayList<ColorParseBean> COLORBEANS = new ArrayList<>();


    public static void init(Context context){
        if(COLORBEANS.size()>0){return;}
        InputStream is = null;
        try {
            is = context.getAssets().open("colodatas.xml");
            if(is !=null){
                InputStreamReader inputreader = new InputStreamReader(is);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                //分行读取
                while (( line = buffreader.readLine()) != null) {
                    String[] msgs = line.split(" ");
                    ColorParseBean bean = new ColorParseBean();
                    bean.setTitleEn(msgs[0]);
                    bean.setTitleZh(msgs[1]);
                    bean.setRgb16(msgs[2]);
                    String[] rgbs = msgs[3].split(",");
                    bean.setRgb(new RGB(Integer.parseInt(rgbs[0]),Integer.parseInt(rgbs[1]),Integer.parseInt(rgbs[2])));
                    Log.e("ColorDatas",bean.toString());
                    COLORBEANS.add(bean);
                }


            }else {
                Log.e("ColorDatas"," == null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
