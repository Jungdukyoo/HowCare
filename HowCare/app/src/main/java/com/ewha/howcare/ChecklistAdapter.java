package com.ewha.howcare;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.security.cert.PKIXRevocationChecker.Option;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChecklistAdapter extends RecyclerView.Adapter {

  private DatabaseReference mDatabase;
  Context mContext;
  private ArrayList<CheckListItem> mDataSet;

  public ChecklistAdapter(Context context, ArrayList<CheckListItem> myDataset) {
    this.mDataSet = myDataset;
    this.mContext = context;
    SharedPreferences sharedPreferences = (mContext).getSharedPreferences("pref",MODE_PRIVATE);
//    String uniqueID = sharedPreferences.getString("option_uuid","");
    String username = OptionsDB.getInstance().getUsername(context);

    Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    String today = sdf.format(dt).toString();

    /*
    mDatabase = FirebaseDatabase.getInstance().getReference().child("userlist")
        .child(username).child("survey").child(today);
     */
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    RecyclerView.ViewHolder viewHolder;
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    switch (viewType) {
      case 1: // title
        View v1 = inflater.inflate(R.layout.recycler_checktitle, parent, false);
        viewHolder = new viewHolderCheckTitle(v1);
        break;
      case 3: // done
        View v2 = inflater.inflate(R.layout.recycler_check_done, parent, false);
        viewHolder = new viewHolderDoneButton(v2);
        break;

        default: //item
          View v3 = inflater.inflate(R.layout.recycler_checklist, parent, false);
          viewHolder = new viewHolderCheckList(v3);
    }
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int viewType = holder.getItemViewType();

    switch (viewType) {
      case 1: //title
        ((viewHolderCheckTitle) holder).setItem(mDataSet.get(position));
        return;
      case 3: //done
        ((viewHolderDoneButton) holder).setItem(mDataSet);
        return;
      default: //item
        ((viewHolderCheckList) holder).setItem(mDataSet.get(position),position);
        return;
    }
  }

  @Override
  public int getItemCount() {
    return mDataSet.size();
  }

  @Override
  public int getItemViewType(int position) {
    return mDataSet.get(position).getType();
  }

  public class viewHolderCheckList extends RecyclerView.ViewHolder {
    private TextView qeustionText;
    private RadioButton radio1, radio2, radio3;
    private RadioGroup radioGroup;
    private ImageView createButton;

    public viewHolderCheckList(View itemView) {
      super(itemView);
      this.qeustionText=itemView.findViewById(R.id.check_question);
      this.radio1 = itemView.findViewById(R.id.radio1);
      this.radio2 = itemView.findViewById(R.id.radio2);
      this.radio3 = itemView.findViewById(R.id.radio3);
      this.radioGroup = itemView.findViewById(R.id.radioGroup);
      this.createButton = itemView.findViewById(R.id.editbutton);
    }

    public void setItem(CheckListItem item,int position) {
      this.qeustionText.setText(item.getText());
      switch (item.getState()) {
        case 1:
          radio1.toggle();
          break;
        case 2:
          radio2.toggle();
          break;
        case 3:
          radio3.toggle();
          break;
          default:
            radioGroup.clearCheck();
            break;
      }

      this.radio1.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          item.setState(1);
          radio1.toggle();
        }
      });

      this.radio2.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          item.setState(2);
          radio2.toggle();
        }
      });

      this.radio3.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          item.setState(3);
          radio3.toggle();
        }
      });

      if(item.getEtc()==null || item.getEtc().equals("")) {
        createButton.setImageResource(R.drawable.ic_baseline_create_24px);
      }
      else {
        createButton.setImageResource(R.drawable.ic_baseline_create_color_24px);
      }

      createButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
          builder.setTitle("Title");
// I'm using fragment here so I'm using getView() to provide ViewGroup
// but you can provide here any other instance of ViewGroup from your Fragment / Activity
          View viewInflated = LayoutInflater.from(mContext).inflate(R.layout.dialog_etcinput, null);

// Set up the input
          final EditText input = (EditText) viewInflated.findViewById(R.id.input);
          input.setText(item.getEtc());
          input.requestFocus();
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
          builder.setView(viewInflated);

// Set up the buttons
          builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              item.setEtc(input.getText().toString());
              Log.d("DIALOG",input.getText().toString());
              if(item.getEtc()==null || item.getEtc().equals("")) {
                createButton.setImageResource(R.drawable.ic_baseline_create_24px);
              }
              else {
                createButton.setImageResource(R.drawable.ic_baseline_create_color_24px);
              }
              dialog.dismiss();
            }
          });
          builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
            }
          });

          builder.show();
        }
      });
    }
  }

  public class viewHolderCheckTitle extends RecyclerView.ViewHolder {
    private TextView titleView;

    public viewHolderCheckTitle(View itemView) {
      super(itemView);
      this.titleView=itemView.findViewById(R.id.checktitle);
    }

    public void setItem(CheckListItem item) {
      this.titleView.setText(item.getText());
      if(item.getText().equals("식사전 Care")) {
        this.titleView.setBackgroundColor(Color.parseColor("#ffcc72"));
      }
      else {
        this.titleView.setBackgroundColor(Color.parseColor("#ff9d76"));
      }
    }
  }

  public class viewHolderDoneButton extends RecyclerView.ViewHolder {
    private TextView saveButton;

    public viewHolderDoneButton(View itemView) {
      super(itemView);
      this.saveButton=itemView.findViewById(R.id.savebutton);
    }

    public void setItem(ArrayList<CheckListItem> itemlist) {
      this.saveButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

          for(int i=0;i<itemlist.size();i++) {
            if(itemlist.get(i).getType()==2 && itemlist.get(i).getState()==0) {
              AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
              builder.setTitle("설문조사 미완료");
              builder.setMessage("누락된 항목이 있습니다. 모든 항목에 체크하여 주십시오.");
              builder.setPositiveButton("확인",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  dialog.cancel();
                }
              });
              builder.show();
              return;
            }
          }

          OptionsDB.getInstance().setCheckliststate(mContext,itemlist);

          int cnt=0;
          for(int i=0;i<itemlist.size();i++) {
            if(itemlist.get(i).getType()!=2) continue;

            /*
            mDatabase.child(cnt+"").setValue(itemlist.get(i).getState());
            mDatabase.child(cnt+"_etc").setValue(itemlist.get(i).getEtc());

             */
            cnt++;
          }
          ((Activity) mContext).finish();
        }
      });
    }
  }
}
