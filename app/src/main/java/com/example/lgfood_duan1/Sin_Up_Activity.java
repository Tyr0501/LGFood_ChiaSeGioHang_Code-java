package com.example.lgfood_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Sin_Up_Activity extends AppCompatActivity {

    private TextView
            SignUp_tv_btn_Login,
            SignUp_tv_btn_loginText,

            Login_tv_Phone,
            Login_tv_google;
    private TextInputLayout
            SignUp_edt_tenDangNhap,
            SignUp_edt_gmail,
            SignUp_edt_MatKhau,
            SignUp_edt_NhapLaiMatKhau,
            SignUp_edt_DiaChi,
            SignUp_edt_SDT;
    private LinearLayout
            SignUp_lv_btn_submid;
    private ImageView
            Login_tv_back,
            SingUp_Im_ViTri;
//Login with google
//    private static final String TAG = "GoogleActivity";
//    private static final int RC_SIGN_IN = 9001;
//    private FirebaseAuth mAuth;
//    private GoogleSignInClient mGoogleSignInClient;
//thai

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser!=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity4.class));
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);

        anhXa();
//        processrequest();
        batSuKien();
    }
//khai báo client của google
//    private void processrequest() {
//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//    }
//khi ấn nút login sẽ chạy vào hàm này
//    private void processLogin() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//mở dialog tài khoản gg
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//lay dữ liệu tài khoản
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            firebaseAuthWithGoogle(account.getIdToken());
//
//        } catch (ApiException e) {
//            Toast.makeText(this, "fff", Toast.LENGTH_SHORT).show();
//        }
//    }
//xử lí đăng nhập
//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            if (user!=null){
//                                startActivity(new Intent(getApplicationContext(),MainActivity4.class));
//                            }
//                        } else {
//                            // If sign in fails, display a message to the user.
//
//                        }
//
//                        // ...
//                    }
//                });
//    }
    private void batSuKien() {
//        chuyển sang trang đăng kí tài khoản
        SignUp_tv_btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
        SignUp_tv_btn_loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);

                startActivity(intent);
            }
        });
//        Login_tv_google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                processLogin();
//            }
//        });
        Login_tv_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


//        back về login
        Login_tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sin_Up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });

        //        check tài khoản
        SignUp_lv_btn_submid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      gọi hàm check tài khoản
//                checkValidateSet();
            }
        });
    }

    private void anhXa() {


//        TextView
        SignUp_tv_btn_Login= findViewById(R.id.signUp_tv_btn_Login);
        SignUp_tv_btn_loginText = findViewById(R.id.signUp_tv_btn_loginText);

        Login_tv_google = findViewById(R.id.login_tv_google);
        Login_tv_Phone= findViewById(R.id.login_tv_Phone);


//        EditText
        SignUp_edt_tenDangNhap = findViewById(R.id.signUp_edt_tenDangNhap);
        SignUp_edt_gmail = findViewById(R.id.signUp_edt_gmail);
        SignUp_edt_MatKhau = findViewById(R.id.signUp_edt_MatKhau);
        SignUp_edt_NhapLaiMatKhau=  findViewById(R.id.signUp_edt_NhapLaiMatKhau);
        SignUp_edt_DiaChi = findViewById(R.id.signUp_edt_DiaChi);
        SignUp_edt_SDT = findViewById(R.id.signUp_edt_SDT);

//        LinearLayout
        SignUp_lv_btn_submid= findViewById(R.id.signUp_lv_btn_submid);

//        Imgview
        Login_tv_back = findViewById(R.id.login_tv_back);
        SingUp_Im_ViTri = findViewById(R.id.singUp_Im_ViTri);
//        Firebase auth
//        mAuth = FirebaseAuth.getInstance();
    }
}