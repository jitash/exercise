package cn.jitash.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.jitash.exercise.android_advanced_light.chapter_1.card_view.CardViewActivity;
import cn.jitash.exercise.android_advanced_light.chapter_1.notification.NofiticationActivity;
import cn.jitash.exercise.android_advanced_light.chapter_1.recycler_view.MyRecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, NofiticationActivity.class));
    }
}
