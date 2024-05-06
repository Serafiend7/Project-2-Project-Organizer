package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project2.databinding.ProjectPageBinding;
import com.example.project2.databinding.SharedProjectPageBinding;


public class ProjectPage extends AppCompatActivity {

    ProjectPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProjectPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.PreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProjectPage.this, OpenProjectPage.class));
            }
        });

    }
}
