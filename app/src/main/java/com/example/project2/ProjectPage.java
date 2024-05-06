package com.example.project2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project2.databinding.ProjectPageBinding;


public class ProjectPage extends AppCompatActivity {

    ProjectPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PreviousPageButton.setOnClickListener(v -> startActivity(new Intent(ProjectPage.this, OpenProjectPage.class)));

    }
}
