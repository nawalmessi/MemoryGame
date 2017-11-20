package com.example.hppc.memorygame;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hppc.memorygame.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean isFirstImage = true;
    ImageView FirstImage;
    ImageView SecondImage;
    boolean canPlay = true;
    int i = 0;
    long currentTime;
    long startTime;
    Timer updateTimer;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().setTitle("Memory Game");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int[] array = {R.drawable.ic_beach_access_black_24dp, R.drawable.ic_beach_access_black_24dp, R.drawable.ic_ac_unit_black_24dp, R.drawable.ic_ac_unit_black_24dp, R.drawable.ic_brightness_3_black_24dp, R.drawable.ic_brightness_3_black_24dp, R.drawable.ic_brightness_5_black_24dp, R.drawable.ic_brightness_5_black_24dp, R.drawable.ic_cake_black_24dp, R.drawable.ic_cake_black_24dp, R.drawable.ic_child_care_black_24dp, R.drawable.ic_child_care_black_24dp, R.drawable.ic_cloud_queue_black_24dp, R.drawable.ic_cloud_queue_black_24dp, R.drawable.ic_drive_eta_black_24dp, R.drawable.ic_drive_eta_black_24dp, R.drawable.ic_favorite_black_24dp, R.drawable.ic_favorite_black_24dp, R.drawable.ic_filter_vintage_black_24dp, R.drawable.ic_filter_vintage_black_24dp, R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_fitness_center_black_24dp, R.drawable.ic_flight_black_24dp, R.drawable.ic_flight_black_24dp};

        final int[] images = shuffleArray(array);

        binding.image1.setOnClickListener(new MyOnClickListener(images));
        binding.image2.setOnClickListener(new MyOnClickListener(images));
        binding.image3.setOnClickListener(new MyOnClickListener(images));
        binding.image4.setOnClickListener(new MyOnClickListener(images));
        binding.image5.setOnClickListener(new MyOnClickListener(images));
        binding.image6.setOnClickListener(new MyOnClickListener(images));
        binding.image7.setOnClickListener(new MyOnClickListener(images));
        binding.image8.setOnClickListener(new MyOnClickListener(images));
        binding.image9.setOnClickListener(new MyOnClickListener(images));
        binding.image10.setOnClickListener(new MyOnClickListener(images));
        binding.image11.setOnClickListener(new MyOnClickListener(images));
        binding.image12.setOnClickListener(new MyOnClickListener(images));
        binding.image13.setOnClickListener(new MyOnClickListener(images));
        binding.image14.setOnClickListener(new MyOnClickListener(images));
        binding.image15.setOnClickListener(new MyOnClickListener(images));
        binding.image16.setOnClickListener(new MyOnClickListener(images));
        binding.image17.setOnClickListener(new MyOnClickListener(images));
        binding.image18.setOnClickListener(new MyOnClickListener(images));
        binding.image19.setOnClickListener(new MyOnClickListener(images));
        binding.image20.setOnClickListener(new MyOnClickListener(images));
        binding.image21.setOnClickListener(new MyOnClickListener(images));
        binding.image22.setOnClickListener(new MyOnClickListener(images));
        binding.image23.setOnClickListener(new MyOnClickListener(images));
        binding.image24.setOnClickListener(new MyOnClickListener(images));


//        Timer
        updateTimer = new Timer("update");
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                updateGUI();
            }
        }, 0, 1000);
        startTime = System.currentTimeMillis();

    }

    private void updateGUI() {
        currentTime = System.currentTimeMillis() - startTime;
        sdf = new SimpleDateFormat("mm:ss");
        binding.clock.setText(sdf.format(currentTime));
        binding.clock.invalidate();
    }

    int[] shuffleArray(int[] ar) {

        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }



    private class MyOnClickListener implements View.OnClickListener {
        private final int[] images;


        public MyOnClickListener(int[] images) {
            this.images = images;
        }

        @Override
        public void onClick(View view) {

            if (canPlay) {

                if (isFirstImage) {
                    isFirstImage = false;
                    FirstImage = (ImageView) view;
                    FirstImage.setImageResource(images[Integer.parseInt(FirstImage.getTag().toString())]);
                } else {
                    isFirstImage = true;
                    SecondImage = (ImageView) view;
                    SecondImage.setImageResource(images[Integer.parseInt(SecondImage.getTag().toString())]);
                    canPlay = false;
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (images[Integer.parseInt(FirstImage.getTag().toString())] != images[Integer.parseInt(SecondImage.getTag().toString())]) {
                                FirstImage.setImageResource(R.drawable.ic_texture_black_24dp);
                                SecondImage.setImageResource(R.drawable.ic_texture_black_24dp);
                            } else {
                                FirstImage.setEnabled(false);
                                SecondImage.setEnabled(false);
                                i += 2;
                            }
                            if (i == 24) {

                                updateTimer.cancel();
                                Toast.makeText(MainActivity.this, "You did it in  " + sdf.format(currentTime), Toast.LENGTH_SHORT).show();
                            }
                            canPlay = true;


                        }
                    }, 1000);


                }
            }
        }
    }
}

