package id.ac.ub.filkom.se.kcv.astechlauncher.controller.mainapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import id.ac.ub.filkom.se.kcv.astechlauncher.R;

/**
 * Created by Daneswara Jauhari on 30/08/2016.
 */
public class HelpLogin extends AppIntro2
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        //        addSlide(first_fragment);
        //        addSlide(second_fragment);
        //        addSlide(third_fragment);
        //        addSlide(fourth_fragment);
        ImageView a = new ImageView(this);
        a.setBackgroundColor(getResources().getColor(R.color.primary));
        setBackgroundView(a);
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Login", "Anda dapat login, jika anda sudah terdaftar di Astech, inputkan email dan password anda pada inputan yang tersedia (lihat no 1 dan 2), dan buat akun baru untuk jika belum mempunyai akun, klik kata create one (lihat no 3) ", R.drawable.login, ContextCompat.getColor(this, R.color.primary_dark)));
        addSlide(AppIntroFragment.newInstance("Daftar", "Inputkan biodata Anda pada inputan yang tersedia dan klik tombol create account untuk mendaftarkan akun (lihat no 3)", R.drawable.daftar, ContextCompat.getColor(this, R.color.primary_dark)));
        // OPTIONAL METHODS
        // Override bar/separator color.
        //        setBarColor(Color.parseColor("#3F51B5"));
        //        setSeparatorColor(Color.parseColor("#2196F3"));
        //
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
