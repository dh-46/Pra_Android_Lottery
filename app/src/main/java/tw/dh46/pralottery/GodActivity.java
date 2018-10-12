package tw.dh46.pralottery;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class GodActivity extends AppCompatActivity {
    private ImageView coinImg;

    private int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god);

        coinImg = findViewById(R.id.imgCoin);

        rand = (int)(Math.random()*11);

        if (rand > 5) {
            coinImg.setImageResource(R.drawable.coin1);
        } else {
            coinImg.setImageResource(R.drawable.coin2);
        }

        setResult(rand);

    }

}
