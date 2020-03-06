package com.demo.aevicedemo.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.aevicedemo.R;
import com.demo.aevicedemo.utils.Constants;
import com.demo.aevicedemo.utils.Prefs;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {
    @BindView(R.id.cvIntro)
    CarouselView cvIntro;

    @BindView(R.id.tvSkip)
    TextView tvSkip;

    @BindView(R.id.btnGetStarted)
    Button btnGetStarted;

    int[][] introData = {{R.drawable.intro_1, R.string.intro_1, R.string.intro_des},
            {R.drawable.intro_2, R.string.intro_2, R.string.intro_des},
            {R.drawable.intro_3, R.string.intro_3, R.string.intro_des}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        // if first using, show intro page, otherwise go to main screen
        if (Prefs.with(this).getBoolean(Constants.KEY_FIRST_USING, true)) {
            Prefs.with(this).save(Constants.KEY_FIRST_USING, false);

            cvIntro.setPageCount(introData.length);
            cvIntro.setIndicatorGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
            cvIntro.setViewListener(position -> {
                View introView = getLayoutInflater().inflate(R.layout.item_intro, null);

                ((ImageView) introView.findViewById(R.id.ivIntro)).setImageResource(introData[position][0]);
                ((TextView) introView.findViewById(R.id.tvTitle)).setText(getResources().getString(introData[position][1]));
                ((TextView) introView.findViewById(R.id.tvDes)).setText(getResources().getString(introData[position][2]));

                return introView;
            });
            tvSkip.setVisibility(View.VISIBLE);
            cvIntro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tvSkip.setVisibility(position == introData.length - 1? View.GONE: View.VISIBLE);
                    btnGetStarted.setVisibility(position == introData.length - 1? View.VISIBLE: View.GONE);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            tvSkip.setOnClickListener(view -> gotoMain());
            btnGetStarted.setOnClickListener(view -> gotoMain());

        } else {
            gotoMain();
        }

    }

    void gotoMain() {
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(IntroActivity.this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        Intent intent  = new Intent(IntroActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent, bundle);
    }
}
