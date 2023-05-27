package com.messas.epcladmin__11;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.myview> {
    public List<filemodel> data;
    FirebaseFirestore firebaseFirestore;
    SimpleExoPlayer simpleExoPlayer;
    FirebaseAuth firebaseAuth;
    public VideoAdapter(List<filemodel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VideoAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sss, parent, false);
        return new VideoAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

       try {
           BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
           TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
           simpleExoPlayer =(SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(holder.itemView.getContext(),trackSelector);
           Uri videoURI = Uri.parse(data.get(position).getVurl());

           DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
           ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
           MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

           holder.simpleExoPlayerView.setPlayer(simpleExoPlayer);
           simpleExoPlayer.prepare(mediaSource);
           simpleExoPlayer.setPlayWhenReady(false);
           holder.vtitleview.setText(data.get(position).getTitle());
       }catch (Exception e) {
           BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
           TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
           simpleExoPlayer =(SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(holder.itemView.getContext(),trackSelector);
           Uri videoURI = Uri.parse(data.get(position).getVurl());

           DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
           ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
           MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

           holder.simpleExoPlayerView.setPlayer(simpleExoPlayer);
           simpleExoPlayer.prepare(mediaSource);
           simpleExoPlayer.setPlayWhenReady(false);
           holder.vtitleview.setText(data.get(position).getTitle());
       }
       holder.card.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
               builder.setTitle("Confirmation")
                       .setMessage("Are you want to delete it?")
                       .setPositiveButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();
                           }
                       }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       firebaseFirestore.collection("Daily_videos")
                               .document("1")
                               .collection("1")
                               .document("1")
                               .delete()
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful()) {
                                           Toast.makeText(v.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                           v.getContext().startActivity(new Intent(v.getContext(),HomeActivity.class));
                                       }
                                   }
                               });
                   }
               }).create().show();
           }
       });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        SimpleExoPlayerView simpleExoPlayerView;

        TextView vtitleview;
        ImageSlider slider;
        CardView card;

        public myview(@NonNull View itemView) {
            super(itemView);
            simpleExoPlayerView=itemView.findViewById(R.id.exoplayerview);
            vtitleview=itemView.findViewById(R.id.vtitle);
            slider=itemView.findViewById(R.id.slider);
            card=itemView.findViewById(R.id.card);

        }
    }
}