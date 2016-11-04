package id.ac.ub.filkom.se.kcv.astechlauncher.controller.mainapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import id.ac.ub.filkom.se.kcv.astechlauncher.R;

/**
 * Created by Daneswara Jauhari on 30/08/2016.
 */
public class HelpPenggunaan extends AppIntro2
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        //        addSlide(a);
        //        addSlide(second_fragment);
        //        addSlide(third_fragment);
        //        addSlide(fourth_fragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Memilih aplikasi?", "Anda dapat memilih aplikasi dengan melakukan swipe pada layar kekanan atau kekiri, atau juga anda Anda dapat memilihnya dengan menekan tab masing-masing aplikasi (lihat no 2)", R.drawable.ss1, ContextCompat.getColor(this, R.color.primary_dark)));
        addSlide(AppIntroFragment.newInstance("Menjalankan aplikasi", "Anda dapat menjalankan aplikasi dengan menekan tombol jalankan pada layar masing-masing aplikasi (lihat no 3), ", R.drawable.ss1, ContextCompat.getColor(this, R.color.primary_dark)));
        addSlide(AppIntroFragment.newInstance("Memilih menu", "Untuk melihat menu, Anda dapat memilih triple dot pada pojok kanan atas (lihat no 1), menu tersebut juga berfungsi untuk logout dan keluar dari aplikasi", R.drawable.ss1, ContextCompat.getColor(this, R.color.primary_dark)));
        // OPTIONAL METHODS
        // Override bar/separator color.
        //        setBarColor(Color.parseColor("#3F51B5"));
        //        setSeparatorColor(Color.parseColor("#2196F3"));

        //        // Hide Skip/Done button.
        //        showSkipButton(true);
        setProgressButtonEnabled(true);
        setFadeAnimation();

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(false);
        //        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment)
    {
        super.onSkipPressed(currentFragment);
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment)
    {
        super.onDonePressed(currentFragment);
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment)
    {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
