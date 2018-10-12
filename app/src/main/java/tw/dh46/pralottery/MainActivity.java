package tw.dh46.pralottery;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private TextView tempNum;
    private LinearLayout middle;

    private TextView[] nums = new TextView[6];

    private int times;

    private long lastTimeClick = 0;

    private HashSet<Integer> history = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempNum = findViewById(R.id.tempNum);
        middle = findViewById(R.id.middle);

        nums[0] = findViewById(R.id.num1);
        nums[1] = findViewById(R.id.num2);
        nums[2] = findViewById(R.id.num3);
        nums[3] = findViewById(R.id.num4);
        nums[4] = findViewById(R.id.num5);
        nums[5] = findViewById(R.id.num6);

    }

    private Boolean isQuit(){
        long current = System.currentTimeMillis();

        if (current - lastTimeClick > 3*1000) {
            Toast.makeText(this, "再按一次重新選號", Toast.LENGTH_SHORT).show();
            lastTimeClick = current;
        } else {
            return true;
        }
        return false;
    }

    public void createNum(View view) {
        if (times >= 6) {
            if (isQuit()) {
                resetNums();
            } else {
              return;
            }
        }

        int rand;

        do {
            rand = (int)(Math.random()*49+1);
        } while (history.contains(rand));

        history.add(rand);

        tempNum.setText("" +rand);

        middle.setVisibility(View.VISIBLE);

    }

    private void resetNums() {
        times = 0;

        for (int i=0; i<6; i++) {
            nums[i].setText("--");
        }

        history.clear();

    }

    public void askGod(View view) {
        Intent intent = new Intent(this, GodActivity.class);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode > 5) {
            // OK
            nums[times].setText(tempNum.getText());
            times++;
        }

        middle.setVisibility(View.GONE);

    }
}
