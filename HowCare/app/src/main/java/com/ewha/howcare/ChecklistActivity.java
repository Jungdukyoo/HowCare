package com.ewha.howcare;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChecklistActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  ArrayList<CheckListItem> checkListItems = new ArrayList<>();
  TextView dateText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_checklist);

    Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd", Locale.KOREA);

    String datetext = sdf.format(dt).toString();
    dateText = findViewById(R.id.datetext);
    dateText.setText(datetext);

    recyclerView = findViewById(R.id.check_recycler);

    setNewCheckListItems();

    if(OptionsDB.getInstance().getChecklistDate(getApplicationContext()).equals(datetext)) {
      setCheckListItems();
    } else {
      ResetChecklist();
      OptionsDB.getInstance().setChecklistDate(getApplicationContext(),datetext);
    }

    ChecklistAdapter mAdapter = new ChecklistAdapter(ChecklistActivity.this, checkListItems);
    recyclerView.setAdapter(mAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

  }

  void setNewCheckListItems() {
    checkListItems.add(new CheckListItem("식사전 Care",1)); //title
    checkListItems.add(new CheckListItem("즐거운 식사 분위기를 만들어 주었다."));
    checkListItems.add(new CheckListItem("식사전 준비사항을 따랐다.\n"
        + "- 쾌적하고 청결한 환경\n"
        + "- 배설확인\n"
        + "- 손 닦기"));
    checkListItems.add(new CheckListItem("노인에게 적합한 식사도구 및 식기류를 제공하였다."));
    checkListItems.add(new CheckListItem("식사시 바른자세로 유지하도록 하였다."));
    checkListItems.add(new CheckListItem("연하운동(목운동, 어깨운동)을 시행하였다."));
    checkListItems.add(new CheckListItem("입운동을 시행하였다."));
    checkListItems.add(new CheckListItem("침샘 마사지 운동을 시행하였다."));
    checkListItems.add(new CheckListItem("삼킴 운동을 시행하였다."));

    checkListItems.add(new CheckListItem("식사 중/후 Care",1)); //title

    checkListItems.add(new CheckListItem("대상자 옆에서 보조하였다."));
    checkListItems.add(new CheckListItem("음식물은 아래서 위로 가져갔다."));
    checkListItems.add(new CheckListItem("음식 삼킴을 확인하였다."));
    checkListItems.add(new CheckListItem("기능유지를 위해 가능한 노인 스스로 식사 할 수 있도록 격려하였다."));
    checkListItems.add(new CheckListItem("식사 후 구강 간호를 시행하였다."));
    checkListItems.add(new CheckListItem("",3));

  }

  void setCheckListItems() {
    int[] state = OptionsDB.getInstance().getCheckliststate(getApplicationContext());
    String[] etc = OptionsDB.getInstance().getChecklistetc(getApplicationContext());

    int cnt=0;
    for(int i=0;i<checkListItems.size();i++) {
      if(checkListItems.get(i).getType()==2) {
        checkListItems.get(i).setState(state[cnt]);
        checkListItems.get(i).setEtc(etc[cnt]);
        cnt++;
      }
    }
  }

  void ResetChecklist() {
    OptionsDB.getInstance().setCheckliststate(getApplicationContext(), checkListItems);
  }
}