package com.example.catchkennygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
// importlama kısmı
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    // importladığımız textleri, imageları burada tanımlıyoruz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainActivity de tanımladık böylece açılacak veya tanımlanacak her classta kullannabiliriz ve tekrar tekrar çağırmamıza gerek kalmaz
        // Initilaize

        timeText = (TextView) findViewById(R.id.timeText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageVie6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        // id'leri ile tanımladık metodda kullanabiliriz artık
        imageArray = new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        // dizi oluşturduk.
        hideImages();
        // geriye doğru gitme , sayaç oluşturma .
        new CountDownTimer(10000 , 1000){
            public void onTick(long millisUntilFinished){
                timeText.setText("Time : " + millisUntilFinished/1000);
            }
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                // alert verme kısmı
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                //MainActivity.this ile MainActivity'e ulaşmak istediğimizi söyledik.
                alert.setTitle("Restart?");
                alert.setMessage("Are You Sure?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // yes , , restart
                        // onCreate'i tekrardan başlatmak için
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this , "Game Over" , Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
        // start geri sayımı çalıştır/başlat
     }

    public void increaseScore (View view) {
        score++;
        scoreText.setText("Score : " + score);
    }

    public void hideImages(){
        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

                // postDelayed runnable içerisinde bizim girdiğimiz parametler arasında çalıştır demek
            }
        };
        handler.post(runnable);

    }
}
