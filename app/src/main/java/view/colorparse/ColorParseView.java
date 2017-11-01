package view.colorparse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jbase.com.myapplication.R;

/**
 * Created by aaa on 2017/10/25.
 */

public class ColorParseView extends RelativeLayout {

    private LinearLayout colorTab;
    private ListView colorMsgs;
    private TextView off;
    private ImageView imageView;

    public ColorParseView(Context context) {
        this(context, null);
    }

    public ColorParseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorParseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.color_parse_view, this);
        colorTab = (LinearLayout) findViewById(R.id.colorTab);
        colorMsgs = (MyListView) findViewById(R.id.color_msgs);
        imageView = (ImageView) findViewById(R.id.image);
        off = (TextView) findViewById(R.id.off);
    }

    public void reset(){
        colorTab.removeAllViews();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setBitmap(Bitmap bitmap){
        reset();
        imageView.setImageBitmap(bitmap);

        Palette palette = Palette.generate(bitmap);

        Palette.Swatch vibrant = palette.getVibrantSwatch(); //充满活力的色板
        Palette.Swatch darkVibrant = palette.getDarkVibrantSwatch(); //充满活力的暗色类型色板
        Palette.Swatch lightVibrant = palette.getLightVibrantSwatch(); //充满活力的亮色类型色板
        Palette.Swatch muted = palette.getMutedSwatch(); //黯淡的色板
        Palette.Swatch darkMuted = palette.getDarkMutedSwatch(); //黯淡的暗色类型色板（翻译过来没有原汁原味的赶脚啊！）
        Palette.Swatch lightMuted = palette.getLightMutedSwatch(); //黯淡的亮色类型色板

        Palette.Swatch[] swatchs = new Palette.Swatch[]{vibrant,darkVibrant,lightVibrant,muted,darkMuted,lightMuted};
        List<Palette.Swatch> swatchList = new ArrayList<>();
        int populationCount = 0;
        for(Palette.Swatch swatch : swatchs){
            if(null !=swatch ){
                populationCount+=swatch.getPopulation();
                swatchList.add(swatch);
            }
        }
        colorTab.setWeightSum(populationCount);

        final List<ColorParseBean> colorParseBeens = new ArrayList<>();
        for(Palette.Swatch swatch : swatchList){
            int weight = swatch.getPopulation();
            int color = swatch.getRgb();
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);

            HSV hsv1 = ColorTools.getHsv(color);
            double temp = 100d;
            ColorParseBean tempBean = null;
            for (ColorParseBean colorParseBean1 : ColorDatas.COLORBEANS){
                double d = ColorTools.distanceOf(hsv1,colorParseBean1.getHsv());
                temp = Math.min(temp,d);
                if(temp==d){
                    tempBean = colorParseBean1;
                }


            }



            ColorParseBean colorParseBean = new ColorParseBean();
            colorParseBean.setRgb(new RGB(r,g,b));
            colorParseBean.setTitleZh(tempBean.getTitleZh());
            colorParseBean.setTitleEn(tempBean.getTitleEn());
            colorParseBean.setWeight(weight);
            colorParseBean.setWeightSum(populationCount);
            colorParseBeens.add(colorParseBean);


        }

        sort(colorParseBeens);
        for(ColorParseBean bean:colorParseBeens){
            TextView textView = new TextView(getContext());
            textView.setHeight(100);
            textView.setTextSize(10);
            textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,bean.getWeight()));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setText(bean.getTitleZh());
            textView.setBackgroundColor(Color.rgb(bean.getRGB().red,bean.getRGB().green,bean.getRGB().blue));
            colorTab.addView(textView);
        }
        off.setText("打开");
        off.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if("打开".equals(off.getText())){
                    colorMsgs.setVisibility(VISIBLE);
                    off.setText("收起");
                }else {
                    colorMsgs.setVisibility(GONE);
                    off.setText("打开");
                }

            }
        });

        colorMsgs.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return colorParseBeens.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if(null==convertView){
                    convertView = inflate(getContext(),R.layout.color_msg_item,null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                }else {
                    holder = (ViewHolder) convertView.getTag();
                }
                ColorParseBean bean = colorParseBeens.get(position);
                holder.color16.setText(bean.getRGB16());
                holder.colorView.setBackgroundColor(Color.parseColor(bean.getRGB16()));
                holder.colorWeight.setText(toFloat((float)bean.getWeight()/bean.getWeightSum())+"%");
                holder.colorTitle.setText(bean.getTitleZh());
                return convertView;
            }
        });




    }

    private float toFloat(float num){
        //(这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)
        return (float) (Math.round(num * 10000)) / 100;
    }

    public class ViewHolder{
        TextView colorView,color16,colorTitle,colorWeight;
        public ViewHolder(View convertView){
            colorView = (TextView) convertView.findViewById(R.id.colorView);
            color16 = (TextView) convertView.findViewById(R.id.color16);
            colorTitle = (TextView) convertView.findViewById(R.id.colorTitle);
            colorWeight = (TextView) convertView.findViewById(R.id.colorWeight);
        }

    }

    private void sort(List<ColorParseBean> lists){

        Collections.sort(lists, new Comparator<ColorParseBean>() {
            @Override
            public int compare(ColorParseBean o1, ColorParseBean o2) {
                return o1.getWeight()<=o2.getWeight()?1:-1;
            }
        });

    }

    public void setImagePath(String imagePath){

        Glide.with(getContext()).load(imagePath).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                setBitmap(resource);
            }
        });

    }



}
