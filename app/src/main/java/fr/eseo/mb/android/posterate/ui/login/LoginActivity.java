package fr.eseo.mb.android.posterate.ui.login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.net.MalformedURLException;
import java.net.URL;
import fr.eseo.mb.android.posterate.MainActivity;
import fr.eseo.mb.android.posterate.R;
import fr.eseo.mb.android.posterate.controller.AsynchTaskRole;
import fr.eseo.mb.android.posterate.controller.AsynchTaskToken;
import fr.eseo.mb.android.posterate.data.model.LoggedInUser;
import fr.eseo.mb.android.posterate.ui.pseudoJury.MainPseudoJuryActivity;

public class LoginActivity extends Activity {
    Button b1, btn_pj;
    EditText ed1,ed2;
    public boolean okLog = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = (Button)findViewById(R.id.button);
        btn_pj = (Button)findViewById(R.id.button_loggon_pj);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoggedInUser.displayName = ed1.getText().toString();
                //login(ed1.getText().toString(),ed2.getText().toString());
                login("alberpat","w872o32HkYAO");
                //login("jpo","F8609vYJXZMn");
                //login("aubinseb","Lsm5hs51s9ks");
            }
        });
        btn_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainPseudoJuryActivity.class);
                startActivity(intent);
            }
        });

    }


    public void login(String user, String pass) {
        //TODO : Tentative connnexion serveur
        try {
            new AsynchTaskToken(this).execute(new URL("https://172.24.5.16/pfe/webservice.php?q=LOGON&user="+user+"&pass="+pass));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void goLog() {
        if(this.okLog) {
            ed1.setText("");
            ed2.setText("");
            Toast.makeText(getApplicationContext(),
                    "Redirecting...",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
        }
    }
    public void getRole() throws MalformedURLException {
        if(this.okLog) {
            System.out.println("https://172.24.5.16/pfe/webservice.php?q=MYINF&user="+LoggedInUser.getDisplayName());
            new AsynchTaskRole(this).execute(new URL("https://172.24.5.16/pfe/webservice.php?q=MYINF&user="+LoggedInUser.getDisplayName()));
        }
    }



}