package kedia.pk.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText textView;
    ImageView imageView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (EditText) findViewById(R.id.tv);
        imageView = (ImageView)findViewById(R.id.image);
        progressBar = (ProgressBar)findViewById(R.id.progress);


    }

    public void onClick(View view) {
        textView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiPoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPoint api = retrofit.create(ApiPoint.class);
        Call<GitProfile> call = api.userRepo(textView.getText().toString());
        call.enqueue(new Callback<GitProfile>() {
            @Override
            public void onResponse(Call<GitProfile> call, Response<GitProfile> response) {
                GitProfile gitProfile = response.body();
                Log.d("TAG!",gitProfile.avatar_url.toString());
                Glide.with(getApplicationContext()).load(gitProfile.avatar_url.toString()).into(imageView);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<GitProfile> call, Throwable t) {
                Log.d("TAG!",t.getMessage().toString());
            }
        });
    }
}
