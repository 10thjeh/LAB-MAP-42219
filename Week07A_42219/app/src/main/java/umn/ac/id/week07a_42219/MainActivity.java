package umn.ac.id.week07a_42219;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button foto, video;
    ImageView kotakFoto;
    VideoView kotakVideo;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_VIDEO_CAPUTURE =2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            kotakFoto.setImageBitmap(imageBitmap);
        } else if(requestCode == REQUEST_VIDEO_CAPUTURE && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            kotakVideo.setVideoURI(videoUri);
            kotakVideo.seekTo(100);
            kotakVideo.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foto = findViewById(R.id.button);
        video = findViewById(R.id.button2);
        kotakFoto = findViewById(R.id.imageView);
        kotakVideo = findViewById(R.id.videoView);

        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(kotakVideo);
        kotakVideo.setMediaController(controller);

        foto.setOnClickListener( v ->{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        video.setOnClickListener( v ->{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(takePictureIntent, REQUEST_VIDEO_CAPUTURE);
            }
        });



    }
}