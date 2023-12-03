package com.example.asimplemusicplayerbysg001x2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asimplemusicplayerbysg001x2.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private LiteSQLManager liteSQLManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: 添加注册界面的逻辑
        liteSQLManager = LiteSQLManager.getInstance();

        binding.btnRegister.setOnClickListener(v -> {
            // 处理注册按钮点击事件
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (liteSQLManager.registerUser(username, password)) {
                    // 注册成功
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    // 返回到登录界面
                    loadLoginActivity();
                } else {
                    // 注册失败
                    Toast.makeText(RegisterActivity.this, "注册失败，用户名已存在", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 用户名或密码为空
                Toast.makeText(RegisterActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // 结束当前 Activity，使用户不能通过返回按钮回到登录界面
    }
}