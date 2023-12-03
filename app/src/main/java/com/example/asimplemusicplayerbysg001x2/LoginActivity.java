package com.example.asimplemusicplayerbysg001x2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asimplemusicplayerbysg001x2.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LiteSQLManager liteSQLManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        liteSQLManager = LiteSQLManager.getInstance();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理登录按钮点击事件
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();

                if (liteSQLManager.loginUser(username, password)) {
                    // 登录成功
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    // 登录成功后,跳转到音乐列表界面(MainActivity)
                    loginSuccess();
                } else {
                    // 登录失败
                    Toast.makeText(LoginActivity.this, "登录失败，请检查用户名和密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理注册按钮点击事件
                // 跳转到注册界面
                loadRegisterActivity();
            }
        });
    }

    private void loginSuccess() {
        // 登录成功，跳转到 MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // 结束当前 Activity，使用户不能通过返回按钮回到登录界面
    }

    // 跳转到注册界面
    private void loadRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish(); // 结束当前 Activity，使用户不能通过返回按钮回到登录界面
    }
}