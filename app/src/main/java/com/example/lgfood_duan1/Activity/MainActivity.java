package com.example.lgfood_duan1.Activity;import androidx.appcompat.app.AppCompatActivity;import android.content.Intent;import android.os.Bundle;//import android.support.annotation.NonNull;import android.view.View;import android.widget.TextView;import com.example.lgfood_duan1.R;import com.google.android.gms.auth.api.signin.GoogleSignIn;import com.google.android.gms.auth.api.signin.GoogleSignInAccount;import com.google.android.gms.auth.api.signin.GoogleSignInClient;import com.google.firebase.auth.FirebaseAuth;public class MainActivity extends AppCompatActivity {    GoogleSignInClient mGoogleSignInClient;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        TextView txt=findViewById(R.id.text123);        String canXoa;        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);        txt.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                FirebaseAuth.getInstance().signOut();                startActivity(new Intent(getApplicationContext(),Sin_Up_Activity.class));                finish();            }        });    }}