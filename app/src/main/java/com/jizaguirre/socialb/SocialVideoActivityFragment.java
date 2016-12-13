package com.jizaguirre.socialb;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class SocialVideoActivityFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
    private View view;
    public static final String DEVELOPER_KEY = "AIzaSyAyjvydo3Rkd0EEc03aRqa88JMW9XRVg2g"; //Clave de API
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerSupportFragment youTubePlayerView; //Crea un fragmento de YouTube

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_social_video, container, false);

        youTubePlayerView = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerView).commit();

        youTubePlayerView.initialize(DEVELOPER_KEY, this);
        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            //se carga la url del video a mostrar
            youTubePlayer.loadVideo("vPqslcwZ8Kk");
            //al iniciar correctamente se asigna el estilo del reproductor
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        }
        else{
            String errorMessage =  getString(R.string.error_repro)+ youTubeInitializationResult.toString();
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
