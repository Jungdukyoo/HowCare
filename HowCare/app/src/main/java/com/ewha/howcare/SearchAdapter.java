package com.ewha.howcare;

import static com.ewha.howcare.ConvertClass.CATEGORY_BEFORE_CARE;
import static com.ewha.howcare.ConvertClass.CATEGORY_IN_CARE;
import static com.ewha.howcare.ConvertClass.CATEGORY_STRANGE;
import static com.ewha.howcare.OptionsDB.FONT_LARGE;
import static com.ewha.howcare.OptionsDB.FONT_MID;
import static com.ewha.howcare.OptionsDB.FONT_SMALL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.ewha.howcare.DetailAdapter.viewHolderHeader;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchAdapter  extends RecyclerView.Adapter<ViewHolder> {

  public final static int IN_SEARCH_MODE = 1;
  public final static int SEARCH_RESULT_MODE = 2;

  private static final int FONT_SMALL_14 = 11;
  private static final int FONT_SMALL_15 = 12;
  private static final int FONT_SMALL_17 = 14;
  private static final int FONT_SMALL_20 = 17;

  private static final int FONT_MID_14 = 14;
  private static final int FONT_MID_15 = 15;
  private static final int FONT_MID_17 = 17;
  private static final int FONT_MID_20 = 20;

  private static final int FONT_LARGE_14 = 19;
  private static final int FONT_LARGE_15 = 20;
  private static final int FONT_LARGE_17 = 22;
  private static final int FONT_LARGE_20 = 25;

  ArrayList<SearchResultItem> mDataset;
  private int search_type = IN_SEARCH_MODE;
  private String query="";
  private int fonttype = 2;
  FirebaseAnalytics mFirebaseAnalytics;


  public SearchAdapter(ArrayList<SearchResultItem> mDataset, String query, Context context, FirebaseAnalytics firebaseAnalytics) {
    this.mDataset = mDataset;
    this.search_type = IN_SEARCH_MODE;
    this.query = query;
    this.fonttype = OptionsDB.getInstance().getFontSize(context);
    this.mFirebaseAnalytics = firebaseAnalytics;
  }

  @Override
  public int getItemViewType(int position) {
    return search_type;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewHolder viewHolder;
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    View view;
    switch (search_type) {
      case IN_SEARCH_MODE:
        View v1 = inflater.inflate(R.layout.searchrecycler_insearch, parent, false);
        viewHolder = new viewHolderInSearch(v1);
        break;
      case SEARCH_RESULT_MODE:
        View v2 = inflater.inflate(R.layout.searchrecycler_result, parent, false);
        viewHolder = new viewHolderResult(v2);
        break;
        default:
          View v3 = inflater.inflate(R.layout.searchrecycler_insearch, parent, false);
          viewHolder = new viewHolderInSearch(v3);
          break;
    }

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int viewType = holder.getItemViewType();

    switch (viewType) {
      case IN_SEARCH_MODE:
        ((viewHolderInSearch)holder).setInSearchView(mDataset.get(position));
        break;
      case SEARCH_RESULT_MODE:
        ((viewHolderResult)holder).setResultView(mDataset.get(position), position);
        break;
        default:
          ((viewHolderInSearch)holder).setInSearchView(mDataset.get(position));
          break;
    }
    return;
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }


  public void setSearch_type(int type) {
    this.search_type = type;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  private int getFontSize(int fontsize) {
    switch (fonttype) {
      case FONT_SMALL:
        switch (fontsize) {
          case 14:
            return FONT_SMALL_14;
          case 15:
            return FONT_SMALL_15;
          case 17:
            return FONT_SMALL_17;
          case 20:
            return FONT_SMALL_20;
          default:
            return FONT_SMALL_17;
        }
      case FONT_MID:
        switch (fontsize) {
          case 14:
            return FONT_MID_14;
          case 15:
            return FONT_MID_15;
          case 17:
            return FONT_MID_17;
          case 20:
            return FONT_MID_20;
          default:
            return FONT_MID_17;
        }
      case FONT_LARGE:
        switch (fontsize) {
          case 14:
            return FONT_LARGE_14;
          case 15:
            return FONT_LARGE_15;
          case 17:
            return FONT_LARGE_17;
          case 20:
            return FONT_LARGE_20;
          default:
            return FONT_LARGE_17;
        }
      default:
        return FONT_MID_17;
    }
  }

  public class viewHolderInSearch extends RecyclerView.ViewHolder {
    private TextView contentTitle;
    private ConstraintLayout insearchConstraint;

    public viewHolderInSearch(View itemView) {
      super(itemView);
      this.contentTitle=itemView.findViewById(R.id.insearch_title);
      this.insearchConstraint = itemView.findViewById(R.id.search_result_constranint);
      int fontsize = getFontSize(15);
      this.contentTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setInSearchView(SearchResultItem item) {
      this.contentTitle.setText(ConvertClass.getMainTitle(item.getPagenum()));
      this.insearchConstraint.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Context context = insearchConstraint.getContext();

          ReadDB.getInstance().setLastRead(item.getPagenum(),item.getItemnum(),context);

          Intent intent;
          intent = new Intent(context, ContentActivity.class);
          intent.putExtra("contentPage",item.getPagenum());
          ItemsDB.getInstance().setSlide_itemnum(item.getItemnum());

          //add log
          Date dt = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
          String realtime = sdf.format(dt).toString();
          String date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA).format(dt).toString();
          Log.d("LOGTEST","Content"+item.getPagenum()+" "+item.getItemnum());
          /*FirebaseDatabase.getInstance().getReference().child("userlist")
              .child(OptionsDB.getInstance().getUsername(context))
              .child("log").child(date)
              .child(String.valueOf(dt.getTime()))
              .setValue(new UserLog(dt.getTime(),item.getPagenum(),item.getItemnum(),5));

           */
          OptionsDB.getInstance().logcheck = true;

          ConvertClass.setAlalytics((Activity)context,mFirebaseAnalytics,item.getPagenum(), item.getItemnum());

          Bundle bundle = new Bundle();
          bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
          bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(item.getPagenum(), item.getItemnum()));
          mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

          context.startActivity(intent);
          ((Activity)context).finish();
        }
      });
    }
  }

  public class viewHolderResult extends RecyclerView.ViewHolder {
    private TextView contentTitle;
    private TextView contentSubTitle;
    private TextView contentItem;
    private LinearLayout contentLinear;

    public viewHolderResult(View itemView) {
      super(itemView);
      this.contentTitle=itemView.findViewById(R.id.search_result_title);
      this.contentSubTitle=itemView.findViewById(R.id.search_result_subtitle);
      this.contentItem=itemView.findViewById(R.id.search_result_item);
      this.contentLinear = itemView.findViewById(R.id.serarch_result_linear);
      int fontsize = getFontSize(20);
      this.contentTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
      fontsize = getFontSize(17);
      this.contentSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
      fontsize = getFontSize(14);
      this.contentItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setResultView(SearchResultItem item, int position) {
      String titlestr = "0"+item.getPagenum()+"  "+ConvertClass.getMainTitle(item.getPagenum());

      int pagecolor;
      int category = ConvertClass.getCategory(item.getPagenum());
      switch (category) {
        case CATEGORY_BEFORE_CARE:
          pagecolor = R.color.light_mustard;
          break;
        case CATEGORY_IN_CARE:
          pagecolor = R.color.melon;
          break;
        case CATEGORY_STRANGE:
          pagecolor = R.color.seafoam_blue_two;
          break;
        default:
          pagecolor = R.color.yellowish_orange;
          break;
      }
      SpannableStringBuilder spannableString = new SpannableStringBuilder(titlestr);
      spannableString.setSpan(new ForegroundColorSpan(this.contentLinear.getResources().getColor(pagecolor)),
          0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

      int blackcolor = this.contentLinear.getResources().getColor(R.color.black);

      for(int i=2;i<titlestr.length() && i+query.length()<titlestr.length();i++) {
        if(titlestr.substring(i,i+query.length()).contains(query)) {
          spannableString.setSpan(new StyleSpan(Typeface.BOLD),i,i+query.length(),0);
          spannableString.setSpan(new ForegroundColorSpan(blackcolor),
              i,i+query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
      }

      this.contentTitle.setText(spannableString);
      String subString = item.getItemContent().get(0).getItem();
      SpannableStringBuilder spannableSubString = new SpannableStringBuilder(subString);

      for(int i=0;i<subString.length() && i+query.length()<subString.length();i++) {
        if(subString.substring(i,i+query.length()).contains(query)) {
          spannableSubString.setSpan(new StyleSpan(Typeface.BOLD),i,i+query.length(),0);
          spannableSubString.setSpan(new ForegroundColorSpan(blackcolor),
              i,i+query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
      }

      this.contentSubTitle.setText(spannableSubString);

      String contentstr = item.getItemContent().get(item.getFocusnum()).getTitle();
      if(!contentstr.equals("")) contentstr+="\n";
      contentstr+=item.getItemContent().get(item.getFocusnum()).getItem();

      if(item.getItemContent().get(item.getFocusnum()).getTitle().equals("") ||
          item.getItemContent().get(item.getFocusnum()).getItem().equals("")) {

        if(!item.getItemContent().get(item.getFocusnum()).getItem().equals(""))
          contentstr+="\n";
        if(item.getItemContent().size()>item.getFocusnum()+1) {
          contentstr += item.getItemContent().get(item.getFocusnum() + 1).getTitle();
          if(item.getItemContent().get(item.getFocusnum() + 1).getTitle().equals("")) {
            contentstr += item.getItemContent().get(item.getFocusnum() + 1).getItem();
          }
        }
      }

      SpannableStringBuilder spannableItemString = new SpannableStringBuilder(contentstr);

      for(int i=0;i<contentstr.length() && i+query.length()<contentstr.length();i++) {
        if(contentstr.substring(i,i+query.length()).contains(query)) {
          spannableItemString.setSpan(new StyleSpan(Typeface.BOLD),i,i+query.length(),0);
          spannableItemString.setSpan(new ForegroundColorSpan(blackcolor),
              i,i+query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
      }

      this.contentItem.setText(spannableItemString);

      if(position%2==0) {
        this.contentLinear.setBackgroundColor(contentLinear.getResources().getColor(R.color.white));
      }
      else {
        this.contentLinear.setBackgroundColor(contentLinear.getResources().getColor(R.color.white_three));
      }

      this.contentLinear.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Context context = contentLinear.getContext();

          ReadDB.getInstance().setLastRead(item.getPagenum(),item.getItemnum(),context);

          Intent intent;
          intent = new Intent(context, ContentActivity.class);
          intent.putExtra("contentPage",item.getPagenum());
          ItemsDB.getInstance().setSlide_itemnum(item.getItemnum());

          //add log
          Date dt = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
          String realtime = sdf.format(dt).toString();
          String date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA).format(dt).toString();
          Log.d("LOGTEST","Content"+item.getPagenum()+" "+item.getItemnum());
          /*
          FirebaseDatabase.getInstance().getReference().child("userlist")
              .child(OptionsDB.getInstance().getUsername(context))
              .child("log").child(date)
              .child(String.valueOf(dt.getTime()))
              .setValue(new UserLog(dt.getTime(),item.getPagenum(),item.getItemnum(),5));

           */
          OptionsDB.getInstance().logcheck = true;

          ConvertClass.setAlalytics((Activity)context,mFirebaseAnalytics,item.getPagenum(), item.getItemnum());

          Bundle bundle = new Bundle();
          bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
          bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(item.getPagenum(), item.getItemnum()));
          mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

          context.startActivity(intent);
          ((Activity)context).finish();
        }
      });
    }
  }
}