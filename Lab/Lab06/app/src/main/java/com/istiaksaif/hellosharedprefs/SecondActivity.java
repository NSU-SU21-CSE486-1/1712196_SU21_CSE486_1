package com.istiaksaif.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SecondActivity extends AppCompatActivity {

    private View parantView;
    private SwitchMaterial switchMaterial;
    private TextView theme,title;
    private userSetting uSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        theme = findViewById(R.id.light);
        title = findViewById(R.id.name);
        switchMaterial = findViewById(R.id.togolbutton);
        parantView = findViewById(R.id.theme);

        uSetting = (userSetting) getApplication();
        load();
        initWidgets();
    }

    private void initWidgets() {
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    uSetting.setCustomTheme(userSetting.Dark_Theme);
                else
                    uSetting.setCustomTheme(userSetting.Light_Theme);
                SharedPreferences.Editor editor = getSharedPreferences(userSetting.PREF,MODE_PRIVATE).edit();
                editor.putString(userSetting.Custom_Theme,uSetting.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }

    private void updateView() {
        final int black = ContextCompat.getColor(this,R.color.black);
        final int white = ContextCompat.getColor(this,R.color.white);

        if (uSetting.getCustomTheme().equals(userSetting.Dark_Theme)){
            parantView.setBackgroundColor(black);
            title.setTextColor(white);
            theme.setTextColor(white);
            theme.setText("Dark");
            switchMaterial.setChecked(true);

        }else {
            parantView.setBackgroundColor(white);
            title.setTextColor(black);
            theme.setTextColor(black);
            theme.setText("Light");
            switchMaterial.setChecked(false);
        }
    }

    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences(userSetting.PREF,MODE_PRIVATE);
        String theme = sharedPreferences.getString(userSetting.Custom_Theme,userSetting.Light_Theme);
        uSetting.setCustomTheme(theme);
    }
}