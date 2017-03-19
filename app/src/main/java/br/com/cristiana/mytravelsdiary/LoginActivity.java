package br.com.cristiana.mytravelsdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import br.com.cristiana.mytravelsdiary.dao.LoginDAO;
import br.com.cristiana.mytravelsdiary.model.Login;
import br.com.cristiana.mytravelsdiary.utils.Constantes;

import static br.com.cristiana.mytravelsdiary.R.id.tilPassword;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void logar(View v){
        EditText edtUser = (EditText) findViewById(R.id.edtUser);
        EditText edtPassword = (EditText) findViewById(R.id.edtPassword);

        String usuario = ((TextInputLayout) findViewById(R.id.tilUser)).getEditText()
                .getText().toString();
        String password = ((TextInputLayout) findViewById(tilPassword)).getEditText()
                .getText().toString();
        CheckBox checkBox = (CheckBox) findViewById(R.id.ckConnect);

        LoginDAO loginDAO = new LoginDAO(this);
        List<Login> loginOficials = loginDAO.getLogin();

        if (usuario.equals(loginOficials.get(0).getUser())
                && password.equals(loginOficials.get(0).getPassword())){

            if(checkBox.isChecked()) {
                savePreferences(usuario, password, checkBox.isChecked());
                connect();
            } else {
                openWindow();
            }
        }else{
            Animation animacao = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.error);
            edtUser.startAnimation(animacao);
            edtPassword.startAnimation(animacao);
         }
    }

    private void connect(){
        SharedPreferences settings = getSharedPreferences(Constantes.KEY_PREFERENCIAS, MODE_PRIVATE);

        if (settings.getBoolean(Constantes.KEY_CONECTADO,false)){
            openWindow();
        }
    }

    private void savePreferences(String user, String password, boolean isChecked) {

        SharedPreferences settings = getSharedPreferences(Constantes.KEY_PREFERENCIAS, MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.KEY_USERS, user);
        editor.putString(Constantes.KEY_PASSWORD, password);
        editor.putBoolean(Constantes.KEY_CONECTADO, isChecked);
        editor.apply();

    }

    private void openWindow() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
