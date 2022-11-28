package android.myapplication.mywallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class setwallpaperactivity extends AppCompatActivity {
    Intent intent;
    ImageView imageview;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setwallpaperactivity);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpapermanager = WallpaperManager.getInstance(getApplicationContext());
        button = findViewById(R.id.button);
        imageview = findViewById(R.id.imageview);
        intent = getIntent();
        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
                try {
                    wallpapermanager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(),"Wallpaper Applied Successfully",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Couldn't set wallpaper",Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }


            }
        });

    }
}