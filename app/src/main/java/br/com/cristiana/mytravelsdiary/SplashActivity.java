package br.com.cristiana.mytravelsdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.cristiana.mytravelsdiary.api.UserApi;
import br.com.cristiana.mytravelsdiary.dao.LoginDAO;
import br.com.cristiana.mytravelsdiary.model.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener, Callback<Login> {

    ImageView ivLogo;
    Animation animacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        animacao = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash);
        ivLogo.startAnimation(animacao);

        animacao.setAnimationListener(this);
        loadLogin();
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    private void loadLogin(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.mocky.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi api = retrofit.create(UserApi.class);
        Call<Login> call = api.login();
        call.enqueue(this);

       ;
    }

    @Override
    public void onResponse(Call<Login> call, Response<Login> response) {
        //persiste na base de dados
        Login login = response.body();
        try {
            LoginDAO dao = new LoginDAO(this);
            dao.add(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFailure(Call<Login> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
