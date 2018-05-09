package com.example.xuan.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {
    //private String videoPath = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private String videoPath = "http://221.228.226.5/14/z/w/y/y/zwyyobhyqvmwslabxyoaixvyubmekc/sh.yinyuetai.com/4599015ED06F94848EBF877EAAE13886.mp4";
    private ImageView tvPlay, fulScreen;
    private SeekBar sbar;
    private TextView tvProgress;
    private IjkVideoPlayer videoPlayer;
    private long duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        tvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    videoPlayer.start();
                } else {
                    videoPlayer.pause();
                }
            }
        });
        sbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoPlayer.seekTo(seekBar.getProgress());
            }
        });

        videoPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.i("-----------", videoPlayer.getDuration() + "");
                duration = videoPlayer.getDuration();
                sbar.setMax((int) videoPlayer.getDuration());
                tvProgress.setText(getProgress("00:00"));
            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int width, int length, int i2, int i3) {

            }
        });
    }

    private void initData() {
        videoPlayer.setVideoPath(videoPath);
        Log.i("-----------", videoPlayer.getDuration() + "");
    }

    private boolean isPause;

    @Override
    protected void onResume() {
        if (isPause) {
            videoPlayer.load();
            isPause = false;
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        isPause = true;
        videoPlayer.pause();
        super.onPause();
    }

    private void initView() {
        tvPlay = findViewById(R.id.iv_video_play);
        sbar = findViewById(R.id.seek_video);
        videoPlayer = findViewById(R.id.view_player);
        fulScreen = findViewById(R.id.iv_ful_screen);
        tvProgress = findViewById(R.id.tv_video_time);
    }

    private String getProgress(String curDur) {
        return curDur + "/" + TimeUtils.formatDuring(duration);
    }

}
