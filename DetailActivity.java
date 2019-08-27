package com.example.dickysuryo.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dickysuryo.moviecatalogue.Model.DetailPopular_Model;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;

public class DetailActivity extends AppCompatActivity {

    ImageView image_header;
    TextView txt_title,txt_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();



        if (bundle.getParcelable("detailMovieNewest") != null){
            MovieNewest_Model.Result parcel = bundle.getParcelable("detailMovieNewest");
            setupDetailNewest(parcel);
        }
        else if (bundle.getParcelable("detailMovie") != null){
            DetailPopular_Model parcel = bundle.getParcelable("detailMovie");
            setupDetailPopular(parcel);
        }


    }

    public void setupDetailNewest(MovieNewest_Model.Result model) {
        image_header = (ImageView)findViewById(R.id.image_header);
        txt_title = (TextView)findViewById(R.id.txtTitle);
        txt_description = (TextView)findViewById(R.id.txtDescription);
        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide
                .with(this)
                .applyDefaultRequestOptions(options)
                .load(Constant.BASE_URL_IMAGE+model.getBackdropPath())
                .into(image_header);



        txt_title.setText(model.getTitle().toString());
        txt_description.setText(model.getOverview().toString());
    }

    public void setupDetailPopular(DetailPopular_Model model) {
        image_header = (ImageView)findViewById(R.id.image_header);
        txt_title = (TextView)findViewById(R.id.txtTitle);
        txt_description = (TextView)findViewById(R.id.txtDescription);
        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide
                .with(this)
                .applyDefaultRequestOptions(options)
                .load(Constant.BASE_URL_IMAGE+model.getBackdropPath())
                .into(image_header);



        txt_title.setText(model.getTitle().toString());
        txt_description.setText(model.getOverview().toString());
    }
}
