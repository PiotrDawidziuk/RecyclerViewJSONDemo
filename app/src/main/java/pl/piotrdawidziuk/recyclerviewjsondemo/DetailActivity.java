package pl.piotrdawidziuk.recyclerviewjsondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static pl.piotrdawidziuk.recyclerviewjsondemo.MainActivity.EXTRA_CREATOR;
import static pl.piotrdawidziuk.recyclerviewjsondemo.MainActivity.EXTRA_LIKES;
import static pl.piotrdawidziuk.recyclerviewjsondemo.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount = intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView creator = findViewById(R.id.creator_name_text_detail);
        TextView likes = findViewById(R.id.like_count_detail);

        Picasso.get().load(imageUrl).into(imageView);
        creator.setText(creatorName);
        likes.setText("Likes: " + likeCount);

    }
}
