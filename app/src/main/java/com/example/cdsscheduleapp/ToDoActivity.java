package com.example.cdsscheduleapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

// 투두 혹은 데일리 투두 리스트 액티비티
public class ToDoActivity extends AppCompatActivity {
    private AdView mAdView;
    EditText editText;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final ToDoAdapter adapter;

        // ----- 광고 리스너 -----
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // 광고 정상 실행시 출력
                Log.d("@@@", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // 광고 비정상 실행시 출력
                Log.d("@@@", "onAdFailedToLoad " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when he user is about to return
                // to the app after tapping on an ad.
            }
        });

        // ----- 어댑터 -----

        editText = (EditText) findViewById(R.id.editText);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ToDoAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ToDoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToDoAdapter.ViewHolder holder, View view, int position) {
                ToDoItem item = adapter.getItem(position);

                Toast.makeText(getApplicationContext(), "선택 : " + item.getToDo(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));
        adapter.addItem(new ToDoItem("스크롤 테스트 스크롤 테스트 스크롤 테스트 스크롤"));


        // ----- 버튼 리스너 -----

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button editTextButton = (Button) findViewById(R.id.editTextButton);
        editTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDo = editText.getText().toString();

                adapter.addItem(new ToDoItem(toDo));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(100);
            }
        });

        ImageButton imageButtonMain = (ImageButton) findViewById(R.id.imageButtonMain);
        ImageButton imageButtonRoutine = (ImageButton) findViewById(R.id.imageButtonRoutine);
        ImageButton imageButtonMemo = (ImageButton) findViewById(R.id.imageButtonMemo);
        ImageButton imageButtonCalendar = (ImageButton) findViewById(R.id.imageButtonCalendar);
        ImageButton imageButtonSettings = (ImageButton) findViewById(R.id.imageButtonSettings);

        imageButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
        imageButtonRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLocalClassName().equals("RoutineActivity")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), RoutineActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_in_left);

                    if (!getLocalClassName().equals("ToDoActivity")) {
                        finish();
                    }
                }
            }
        });
        imageButtonMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLocalClassName().equals("MemoActivity")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
                    if (!getLocalClassName().equals("ToDoActivity")) {
                        finish();
                    }
                }
            }
        });
        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLocalClassName().equals("CalendarActivity")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
                    if (!getLocalClassName().equals("ToDoActivity")) {
                        finish();
                    }
                }
            }
        });
        imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLocalClassName().equals("SettingActivity")) {

                } else {
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);
                    if (!getLocalClassName().equals("ToDoActivity")) {
                        finish();
                    }
                }
            }
        });

        // ----- 인탠트 생성 -----

    }

    // ----- 어댑터 클래스 -----

}

