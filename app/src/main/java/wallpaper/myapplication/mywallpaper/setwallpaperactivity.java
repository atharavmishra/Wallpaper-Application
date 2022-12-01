package wallpaper.myapplication.mywallpaper;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.myapplication.mywallpaper.R;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;

public class setwallpaperactivity extends AppCompatActivity {
    Intent intent;
    ImageView imageview;
    ImageButton back;
    Button button;
    private static final int CHOOSE_IMAGE = 22;
    String[] options = new String[]{
            "Set As Home Screen",
            "Set As Lock Screen",
            "Set As Both"
    };
    private Button btnSetWallpaper;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setwallpaperactivity);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        button = findViewById(R.id.button);

        imageview = findViewById(R.id.imageview);
        intent = getIntent();
        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               WallpaperChooser();

            }


        });

    }
   public void WallpaperChooser() {
                    Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
                         AlertDialog.Builder builder = new AlertDialog.Builder(setwallpaperactivity.this);
                        builder.setTitle("Choose from below");
                        final Bitmap finalBitmap = bitmap;
                    AlertDialog dialog = builder.create();

                    final WallpaperManager[] wallpapermanager = {WallpaperManager.getInstance(setwallpaperactivity.this)};
                    AlertDialog finalDialog = dialog;
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String selectedItem = Arrays.asList(options).get(i);
                                if (selectedItem.equals(options[0])) {
                                    try {
                                        wallpapermanager[0] = WallpaperManager.getInstance(setwallpaperactivity.this);
                                        wallpapermanager[0].setBitmap(finalBitmap, null, false, WallpaperManager.FLAG_SYSTEM);
                                        Toast.makeText(setwallpaperactivity.this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();
                                        finalDialog.dismiss();
                                    } catch (Exception e) {
                                        Log.e(TAG, "onResourceReady: " + e.getMessage());
                                    }
                                } else if (selectedItem.equals(options[1])) {
                                    try {
                                        wallpapermanager[0] = WallpaperManager.getInstance(setwallpaperactivity.this);
                                        wallpapermanager[0].setBitmap(finalBitmap, null, false, WallpaperManager.FLAG_LOCK);
                                        Toast.makeText(setwallpaperactivity.this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();
                                        finalDialog.dismiss();
                                    } catch (Exception e) {
                                        Log.e(TAG, "onResourceReady: " + e.getMessage());
                                    }

                                } else if (selectedItem.equals(options[2])) {
                                    try {
                                        wallpapermanager[0] = WallpaperManager.getInstance(setwallpaperactivity.this);
                                        wallpapermanager[0].setBitmap(finalBitmap, null, false, WallpaperManager.FLAG_LOCK | WallpaperManager.FLAG_SYSTEM);
                                        Toast.makeText(setwallpaperactivity.this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();
                                        finalDialog.dismiss();
                                    } catch (Exception e) {
                                        Log.e(TAG, "onResourceReady: " + e.getMessage());
                                    }
                                }
                            }
                        });
                        dialog = builder.create();
                        dialog.show();

                    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}