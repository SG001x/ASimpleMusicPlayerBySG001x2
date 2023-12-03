package com.example.asimplemusicplayerbysg001x2;

// LoginFragment.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.asimplemusicplayerbysg001x2.ui.home.HomeFragment;

public class LoginFragment extends Fragment {

    private LiteSQLManager liteSQLManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        liteSQLManager = LiteSQLManager.getInstance();

        EditText etUsername = root.findViewById(R.id.etUsername);
        EditText etPassword = root.findViewById(R.id.etPassword);
        Button btnLogin = root.findViewById(R.id.btnLogin);
        Button btnRegister = root.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            // 处理登录按钮点击事件
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (liteSQLManager.loginUser(username, password)) {
                // 登录成功
                Toast.makeText(requireContext(), "登录成功", Toast.LENGTH_SHORT).show();
                // TODO: 处理登录成功后的操作，例如跳转到音乐列表界面
                loadMusicListFragment();
            } else {
                // 登录失败
                Toast.makeText(requireContext(), "登录失败，请检查用户名和密码", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            // 处理注册按钮点击事件
            // TODO: 跳转到注册界面
            loadRegisterFragment();
        });


        return root;
    }

    private void loadRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.nav_host_fragment_activity_main, registerFragment);
        transaction.replace(R.id.fragment_container, registerFragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadMusicListFragment() {

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.nav_host_fragment_activity_main, homeFragment);
        transaction.replace(R.id.fragment_container, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
