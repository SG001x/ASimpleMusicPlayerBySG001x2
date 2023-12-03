package com.example.asimplemusicplayerbysg001x2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RegisterFragment extends Fragment {

    private LiteSQLManager liteSQLManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        liteSQLManager = LiteSQLManager.getInstance();

        EditText etUsername = root.findViewById(R.id.etUsername);
        EditText etPassword = root.findViewById(R.id.etPassword);
        Button btnRegister = root.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            // 处理注册按钮点击事件
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                if (liteSQLManager.registerUser(username, password)) {
                    // 注册成功
                    Toast.makeText(requireContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    // TODO: 处理注册成功后的操作，例如返回到登录界面
                    loadLoginFragment();
                } else {
                    // 注册失败
                    Toast.makeText(requireContext(), "注册失败，用户名已存在", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 用户名或密码为空
                Toast.makeText(requireContext(), "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    private void loadLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.nav_host_fragment_activity_main, loginFragment);
        transaction.replace(R.id.fragment_container, loginFragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }
}