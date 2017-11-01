package jbase.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import view.colorparse.ColorDatas;
import view.colorparse.ColorParseView;

public class MainActivity extends AppCompatActivity {

    String imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508934375843&di=65bdadcc181a0773a5c00f38fb2c5f01&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F962bd40735fae6cdded3aa7505b30f2442a70fba.jpg";
    String imagePath1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508934375843&di=03cb62ec53c484a88dab29d2d6d99c2e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc75c10385343fbf2618015f7ba7eca8064388fb2.jpg";
    String imagePath2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508996123703&di=575c97db3a01975ac4e1aad774debfb9&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fac4bd11373f0820207282ceb41fbfbedaa641baf.jpg";
    String imagePath3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509012575844&di=e7ca0ccbea76f557cf05df21aa335722&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F16%2F23%2F83n58PIC5Km_1024.jpg";
    String imagePath4 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509106768593&di=2a67392e83476f82a6c478151304532c&imgtype=0&src=http%3A%2F%2Fpic34.nipic.com%2F20131019%2F13958121_205650877000_2.jpg";
    String imagePath5 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509701526&di=c09f21df6582148a499b93942578b719&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D16ea97c0a9cc7cd9ee203c9a51684b4a%2F8c1001e93901213f024bc2f15ee736d12f2e95a7.jpg";
    String imagePath6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509106832260&di=2474147d10885e11d00a547f822cc720&imgtype=jpg&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D2897249708%2C1284509097%26fm%3D214%26gp%3D0.jpg";

    String[] images = new String[]{imagePath,imagePath1,imagePath2,imagePath3,imagePath4,imagePath5,imagePath6};
    private Random random;
    private ColorParseView colorParseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorDatas.init(this);

        random = new Random();
        imagePath = images[random.nextInt(6)];


        colorParseView = (ColorParseView) findViewById(R.id.colorParseView);
        colorParseView.setImagePath(imagePath);
        colorParseView.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorParseView.setImagePath(images[random.nextInt(6)]);
            }
        });

    }


}
