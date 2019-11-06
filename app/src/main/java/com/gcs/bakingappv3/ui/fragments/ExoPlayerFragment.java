package com.gcs.bakingappv3.ui.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gcs.bakingappv3.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExoPlayerFragment extends Fragment implements Player.EventListener {

    private PlayerView videoPlayer;
    private SimpleExoPlayer player;
    private String vidoeUrl = "";

    private FrameLayout exoplayer_container;
    private ImageView imageView_novideo;

    private SwipeRefreshLayout swipeRefresh_video;


    public ExoPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exo_player, container, false);
        swipeRefresh_video = view.findViewById(R.id.swipeRefresh_exoplayer_fragment);
        videoPlayer = view.findViewById(R.id.videoPlayer);
        videoPlayer.setVisibility(View.VISIBLE);
        exoplayer_container = view.findViewById(R.id.exoplayer_container);
        imageView_novideo = view.findViewById(R.id.imageView_novideo);
        imageView_novideo.setVisibility(View.GONE);

        swipeRefresh_video.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (checkConnection() == false || vidoeUrl.equals("")){
                    videoPlayer.setVisibility(View.GONE);
                    imageView_novideo.setVisibility(View.VISIBLE);
                    if (swipeRefresh_video.isRefreshing())
                        swipeRefresh_video.setRefreshing(false);
                }
                else {
                    imageView_novideo.setVisibility(View.GONE);
                    videoPlayer.setVisibility(View.VISIBLE);
                    if (swipeRefresh_video.isRefreshing())
                        swipeRefresh_video.setRefreshing(false);
                }
            }
        });

        return view;
    }


    public void setVideoUrl(String videoUrl){
        this.vidoeUrl = videoUrl;
    }

    private void initPlayer(){
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(), new DefaultLoadControl());
            videoPlayer.setPlayer(player);
            player.setPlayWhenReady(true);
            MediaSource mediaSource = buildMediaSource(Uri.parse(vidoeUrl));
            player.prepare(mediaSource, true, false);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ProgressiveMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer_nd")).createMediaSource(uri);
    }


    private void releasePlayer(){
        if (player != null) {
            player.release();
            player = null;
        }
    }




    @Override
    public void onStart() {
        super.onStart();
        initPlayer();
        if (checkConnection() == false){
            videoPlayer.setVisibility(View.GONE);
            imageView_novideo.setVisibility(View.VISIBLE);
        }
        else {
            imageView_novideo.setVisibility(View.GONE);
            videoPlayer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initPlayer();
        if (checkConnection() == false){
            videoPlayer.setVisibility(View.GONE);
            imageView_novideo.setVisibility(View.VISIBLE);
        }
        else {
            imageView_novideo.setVisibility(View.GONE);
            videoPlayer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private boolean checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
            return true;
        }
        else {
            return false;
        }
    }


}
