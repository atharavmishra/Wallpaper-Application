package wallpaper.myapplication.mywallpaper;

import android.content.Context;
import android.content.Intent;
import android.myapplication.mywallpaper.R;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<srcClass> wallpapers;

    public Adapter(Context context, ArrayList<srcClass> wallpapers) {
        this.context = context;
        this.wallpapers = wallpapers;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, null, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(wallpapers.get(position).getSrc().getPortrait()).into(holder.imageview);

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.text.setMovementMethod(LinkMovementMethod.getInstance());
                holder.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browserintent = new Intent(Intent.ACTION_VIEW);
                        browserintent.setData(Uri.parse("https://www.pexels.com"));
                        browserintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(browserintent);
                    }
                });

            }
        });
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, setwallpaperactivity.class);
                intent.putExtra("image",wallpapers.get(holder.getAdapterPosition()).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);


        }
    }
}
