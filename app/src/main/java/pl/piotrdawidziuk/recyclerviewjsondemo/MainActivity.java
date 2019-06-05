package pl.piotrdawidziuk.recyclerviewjsondemo;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity implements ExampleAdapter.OnItemClickListener{

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";

    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItem> exampleItems;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        exampleItems = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for (int i =0; i<jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String creatorName = hit.getString("user");
                        String imageUrl = hit.getString("webformatURL");
                        int likeCount = hit.getInt("likes");

                        exampleItems.add( new ExampleItem(imageUrl,creatorName,likeCount));
                    }

                    exampleAdapter = new ExampleAdapter(MainActivity.this, exampleItems);
                    recyclerView.setAdapter(exampleAdapter);

                    exampleAdapter.setOnItemClickListener(MainActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

    });

        requestQueue.add(jsonObjectRequest);


    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = exampleItems.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES, clickedItem.getmLikes());

        startActivity(detailIntent);
    }
}
