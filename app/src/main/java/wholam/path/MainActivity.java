package wholam.path;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import wholam.path.drawable.DefensePathRunDrawable;
import wholam.path.drawable.DefenseSpreadDrawable;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progress;
    private ProgressBar progressBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progress);
        progress.setIndeterminateDrawable(new DefensePathRunDrawable(8, getResources().getColor(R.color.colorPrimary)));
        progressBg = findViewById(R.id.progressBg);
        progressBg.setIndeterminateDrawable(new DefenseSpreadDrawable(8
                , Color.parseColor("#A73F51B5")
                , Color.parseColor("#003F51B5")));
    }
}
