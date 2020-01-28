package example.com.mynewapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    Button btnCheck;
    KeyguardManager keyguardManager;
    FingerprintManager fingerprintManager;
    BiometricPrompt biometricPrompt;

    CancellationSignal cancellationSignal;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLoginScreen();
        btnCheck=(Button)findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkAuthentication();
            }
        });
    }

    private void checkAuthentication(){
        Executor executor= Executors.newSingleThreadExecutor();
        cancellationSignal= new CancellationSignal();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            biometricPrompt.authenticate(cancellationSignal, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(MainActivity.this,"Error", LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                }

                @Override
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(MainActivity.this,"Error", LENGTH_SHORT).show();
                }
            });
        }
    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkLoginScreen(){
        keyguardManager= (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        fingerprintManager= (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
        if(keyguardManager.isKeyguardLocked()==false){
            Toast.makeText(this,"Not Locked", LENGTH_SHORT).show();
        }
         if(fingerprintManager.hasEnrolledFingerprints()==false){
             Toast.makeText(this, "Please enrool fingerprint", LENGTH_SHORT).show();
         }

    }



}
