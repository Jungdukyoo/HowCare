package com.ewha.howcare;

import static android.view.View.GONE;
import static com.ewha.howcare.ConvertClass.CATEGORY_BEFORE_CARE;
import static com.ewha.howcare.ConvertClass.CATEGORY_IN_CARE;
import static com.ewha.howcare.ConvertClass.CATEGORY_STRANGE;
import static com.ewha.howcare.OptionsDB.FONT_LARGE;
import static com.ewha.howcare.OptionsDB.FONT_MID;
import static com.ewha.howcare.OptionsDB.FONT_SMALL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class DetailAdapter extends RecyclerView.Adapter {

  public static final int TYPE_HEAD = 0;
  public static final int TYPE_PRECONTENT = 1;
  public static final int TYPE_PRESUBTEXT = 2;
  public static final int TYPE_CONTENTHEAD = 3;
  public static final int TYPE_CONTENTITEM = 4;
  public static final int TYPE_CONTENTSUBITEM = 5;
  public static final int TYPE_IMAGE = 6;
  public static final int TYPE_READTITLE = 7;
  public static final int TYPE_EMPTY = 8;
  public static final int TYPE_CONTENTNUM = 9;
  public static final int TYPE_EMPTY3DP = 10;
  public static final int TYPE_GIFIMAGE = 11;
  public static final int TYPE_NEWIMAGE = 12;
  public static final int TYPE_MOUTH = 13;
  public static final int TYPE_Heimlich = 14;

  private static final int FONT_SMALL_23 = 20;
  private static final int FONT_SMALL_17 = 14;
  private static final int FONT_SMALL_16 = 13;

  private static final int FONT_MID_23 = 23;
  private static final int FONT_MID_17 = 17;
  private static final int FONT_MID_16 = 16;

  private static final int FONT_LARGE_23 = 28;
  private static final int FONT_LARGE_17 = 22;
  private static final int FONT_LARGE_16 = 21;

  private ArrayList<ContentItem> mDataSet;
  Context mContext;
  private int category;
  private int tab,itemnum;
  private int fonttype = FONT_MID;
  private int pagetype;
//  private final RequestManager glide;
  public TextToSpeech textToSpeech = null;


  public class viewHolderHeader extends RecyclerView.ViewHolder {
    private TextView callerNameTextView;
    private ImageView heartImage;
    private ImageView soundImage;

    public viewHolderHeader(View itemView) {
      super(itemView);
      this.callerNameTextView=itemView.findViewById(R.id.title_text);
      this.heartImage = itemView.findViewById(R.id.title_heart);
      this.soundImage = itemView.findViewById(R.id.title_sound);
      int fontsize = getFontSize(23);
      this.callerNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void showCallDetails(ContentItem item){
      // Attach values for each item
      String callerName =item.getItem();
      this.callerNameTextView.setText(callerName);

      int color;
      switch (category) {
        case CATEGORY_BEFORE_CARE:
          color = R.color.yellowish_orange;
          this.soundImage.setImageResource(R.mipmap.sound_icon);
          break;
        case CATEGORY_IN_CARE:
          color = R.color.grapefruit;
          this.soundImage.setImageResource(R.drawable.speakerred);

          break;
        case CATEGORY_STRANGE:
          color = R.color.greeny_blue;
          this.soundImage.setImageResource(R.drawable.speakergreen);

          break;
          default:
            color = R.color.yellowish_orange;
            this.soundImage.setImageResource(R.mipmap.sound_icon);

            break;
      }
      callerNameTextView.setTextColor(itemView.getResources().getColor(color));

      int pagenum = pagetype;

      soundImage.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          Log.d("SOUND TOUCH","");
          if(event.getAction()==MotionEvent.ACTION_DOWN) {
            Log.d("SOUND TOUCH","DOWN");
            ArrayList<ContentItem> textlist = ItemsDB.getInstance().getItemTextList(pagenum,itemnum);
            boolean flag=false;

//          MyTTS.getInstance().makeTTSStop(mContext);
//          MyTTS.getInstance().setMyTTS(mContext);
//
            for(int i=0;i<textlist.size();i++) {
              ContentItem item = textlist.get(i);
              if(item.getTitle()!=null) {
                int type = TextToSpeech.QUEUE_ADD;
                if(!flag) type = TextToSpeech.QUEUE_FLUSH;
                MyTTS.getInstance().setTTSRead(item.getTitle(), type);
                MyTTS.getInstance().setTTSSilent(700, TextToSpeech.QUEUE_ADD);
                flag=true;
              }
              if(item.getItem()!=null) {
                int type = TextToSpeech.QUEUE_ADD;
                if(!flag) type = TextToSpeech.QUEUE_FLUSH;
                MyTTS.getInstance().setTTSRead(item.getItem(), type);
                MyTTS.getInstance().setTTSSilent(700, TextToSpeech.QUEUE_ADD);
                flag=true;
              }
            }
            return true;
          }

          return false;
        }
      });

      boolean isFavorite = FavoriteDB.getInstance().getIsFavorite(pagenum,itemnum,mContext);
      Log.d("FAVORITE",isFavorite+" "+pagenum+" "+(itemnum));
      if(isFavorite) heartImage.setImageResource(R.mipmap.favorite_heart_fill);
      else heartImage.setImageResource(R.mipmap.favorite_heart_empty);

      heartImage.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if(event.getAction()==MotionEvent.ACTION_DOWN) {

            int pagenum = pagetype;
//          if(category<=4) pagenum+=1;
//          else if(category<=7) pagenum+=5;
//          else pagenum+=8;
            boolean isFavorite = FavoriteDB.getInstance().getIsFavorite(pagenum,itemnum,mContext);
            FavoriteDB.getInstance().setFavorite(pagenum,itemnum,!isFavorite,mContext);

            Log.d("CLICK","HEART");

            if(isFavorite) {
              heartImage.setImageResource(R.mipmap.favorite_heart_empty);
            } else {
              heartImage.setImageResource(R.mipmap.favorite_heart_fill);
            }
            return true;
          }
          return false;
        }
      });

    }
  }

  public class viewHolderPreSubContent extends RecyclerView.ViewHolder {
    private TextView presubcontentTextView;
    private TextView emptyView;
    private ConstraintLayout cl;

    public viewHolderPreSubContent(View itemView) {
      super(itemView);
      this.presubcontentTextView=itemView.findViewById(R.id.precontent_text);
      this.emptyView = itemView.findViewById(R.id.precontent_empty);
      this.cl = itemView.findViewById(R.id.precontent_cl);

      int fontsize = getFontSize(17);
      this.presubcontentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setPresubcontentTextView(ContentItem item, boolean afterImage) {
      this.presubcontentTextView.setText(item.getItem());
      if(afterImage) this.emptyView.setVisibility(GONE);
      else this.emptyView.setVisibility(View.VISIBLE);

    }
  }

  public class viewHolderPreSubText extends RecyclerView.ViewHolder {
    private TextView presubTextView;

    public viewHolderPreSubText(View itemView) {
      super(itemView);
      this.presubTextView=itemView.findViewById(R.id.presubtext_text);
      int fontsize = getFontSize(17);
      this.presubTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setPreSubText(ContentItem item) {

      this.presubTextView.setText(item.getItem());

    }
  }

  public class viewHolderContentHead extends RecyclerView.ViewHolder {
    private TextView contentHeadTextView;
    private ImageView contentHeadLine;

    public viewHolderContentHead(View itemView) {
      super(itemView);
      this.contentHeadTextView=itemView.findViewById(R.id.contenthead_text);
      this.contentHeadLine = itemView.findViewById(R.id.contenthead_line);
      int fontsize = getFontSize(23);
      this.contentHeadTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setContentHeadTextView(ContentItem item) {
      this.contentHeadTextView.setText(item.getItem());
      int color;
      int color2;
      switch (category) {
        case CATEGORY_BEFORE_CARE:
          color = R.color.yellowish_orange;
          color2 = R.color.apricot;
          break;
        case CATEGORY_IN_CARE:
          color = R.color.grapefruit;
          color2 = R.color.melon;
          break;
        case CATEGORY_STRANGE:
          color = R.color.greeny_blue;
          color2 = R.color.seafoam_blue_two;
          break;
        default:
          color = R.color.yellowish_orange;
          color2 = R.color.apricot;
          break;
      }
      contentHeadTextView.setTextColor(itemView.getResources().getColor(color));
      contentHeadLine.setBackgroundColor(itemView.getResources().getColor(color2));

    }
  }

  public class viewHolderContentItem extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private TextView contentTextView;
    private TextView emptySpace;
    private ImageView numberImage;
    private ConstraintLayout cl;

    public viewHolderContentItem(View itemView) {
      super(itemView);
      this.titleTextView=itemView.findViewById(R.id.contentitem_titletext);
      this.contentTextView=itemView.findViewById(R.id.contentitem_text);
      this.numberImage=itemView.findViewById(R.id.contentitem_square);
      this.emptySpace = itemView.findViewById(R.id.contentitem_emptyview);
      this.cl = itemView.findViewById(R.id.contentitem_cl);

      int fontsize = getFontSize(17);
      this.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
      fontsize = getFontSize(16);
      this.contentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setContentItem(ContentItem item, boolean afterImage) {
      this.contentTextView.setText(item.content);
      this.titleTextView.setText(item.title);
      if(item.content.equals("")) this.contentTextView.setVisibility(GONE);
      else this.contentTextView.setVisibility(View.VISIBLE);

      if(afterImage) this.emptySpace.setVisibility(GONE);
      else this.emptySpace.setVisibility(View.VISIBLE);


      String iconsrc = "";
      int color;
      switch (category) {
        case CATEGORY_BEFORE_CARE:
          color = R.color.light_salmon;
          iconsrc="r_";
          break;
        case CATEGORY_IN_CARE:
          color = R.color.apricot_two;
          iconsrc="y_";

          break;
        case CATEGORY_STRANGE:
          color = R.color.soft_green;
          iconsrc="g_";

          break;
        default:
          color = R.color.light_salmon;
          break;
      }
      if(item.color!=0) color = item.color;
      iconsrc+="s";
      iconsrc+=String.valueOf(item.number);

      numberImage.setImageResource(item.imageresource);
    }
  }


  public class viewHolderContentSubItem extends RecyclerView.ViewHolder {
    private TextView contentSubText;
    private ImageView contentIcon;

    public viewHolderContentSubItem(View itemView) {
      super(itemView);
      this.contentSubText=itemView.findViewById(R.id.contentnum_title);
      this.contentIcon=itemView.findViewById(R.id.contentnum_dot_icon);
      int fontsize = getFontSize(17);
      this.contentSubText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setContentHeadTextView(ContentItem item) {
      this.contentSubText.setText(item.getItem());
      contentIcon.setImageResource(item.imageresource);

    }
  }

  public class viewHolderHeimlichItem extends RecyclerView.ViewHolder {
    private TextView contentSubText;
    private ImageView contentIcon;
    private ImageView videoIcon;

    public viewHolderHeimlichItem(View itemView) {
      super(itemView);
      this.contentSubText=itemView.findViewById(R.id.contentnum_title);
      this.contentIcon=itemView.findViewById(R.id.contentnum_dot_icon);
      this.videoIcon = itemView.findViewById(R.id.contentnnum_video);
      int fontsize = getFontSize(17);
      this.contentSubText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setHeimlichItemView(ContentItem item) {
      this.contentSubText.setText("하임리히법");
      contentIcon.setImageResource(item.imageresource);


      this.videoIcon.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if(event.getAction()==MotionEvent.ACTION_DOWN) {
            String url = "https://youtu.be/Tt0QLa1zQM4";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(intent);
            return true;
          }
          return false;
        }
      });
    }
  }

  public class viewHolderImage extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public viewHolderImage(View itemView) {
      super(itemView);
      this.imageView=itemView.findViewById(R.id.item_image);
    }

    public void setImageView(ContentItem item) {
      int resourceId = item.imageresource;
      imageView.setImageResource(resourceId);
    }
  }

  public class viewHolderNewImage extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public viewHolderNewImage(View itemView) {
      super(itemView);
      this.imageView=itemView.findViewById(R.id.item_image);
    }

    public void setImageView(ContentItem item) {
      int resourceId = item.imageresource;
      if(resourceId == R.drawable.image036) {
        imageView.setPadding(130,30,50,10);
      }
      else {
        imageView.setPadding(0,0,0,0);

      }
//      glide.load(resourceId).into(this.imageView);
      imageView.setImageResource(resourceId);
    }
  }

  public class viewHolderGifImage extends RecyclerView.ViewHolder {
    private GifImageView imageView;
    private ImageView thumbimage;

    public viewHolderGifImage(View itemView) {
      super(itemView);
      this.imageView=itemView.findViewById(R.id.item_image);
      this.thumbimage = itemView.findViewById(R.id.item_thumb);
    }

    public void setImageView(ContentItem item) {
      thumbimage.setImageResource(item.thumbresource);
      thumbimage.setVisibility(View.VISIBLE);
      this.thumbimage.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if (event.getAction() == MotionEvent.ACTION_DOWN) {

            thumbimage.setVisibility(GONE);
            try {
              GifDrawable gifFromResource = new GifDrawable(mContext.getResources(),
                  item.imageresource);
              imageView.setImageDrawable(gifFromResource);
            } catch (IOException e) {
              e.printStackTrace();
            }
            return true;
          }
          return false;
        }
      });
    }
  }

  public class viewHolderReadTitle extends RecyclerView.ViewHolder{
    private TextView titleText;
    private ImageView readImg;

    public viewHolderReadTitle(View itemView) {
      super(itemView);
      this.titleText=itemView.findViewById(R.id.itemlist_title);
      this.readImg = itemView.findViewById(R.id.itemlist_isread);
      int fontsize = getFontSize(23);
      this.titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    public void setTitle(ContentItem item) {
      titleText.setText(item.getItem());
      int idx = Integer.parseInt(item.getItem().substring(0,1));

      ReadDB it = ReadDB.getInstance();
      if(it.getIsRead(pagetype,idx,mContext)) {
        readImg.setImageResource(R.mipmap.read_done_icon);
      }
      else {
        readImg.setImageResource(R.mipmap.read_icon);
      }
    }
  }

  public class viewHolderEmpty extends RecyclerView.ViewHolder {
    public viewHolderEmpty(View itemView) {
      super(itemView);
    }
  }

  public class viewHolderContentNum extends RecyclerView.ViewHolder{
    private TextView titleText;
    private TextView itemText;
    private ImageView dotIcon;

    public viewHolderContentNum(View itemView) {
      super(itemView);
      this.titleText=itemView.findViewById(R.id.contentnum_title);
      this.itemText=itemView.findViewById(R.id.contentnum_item);
      this.dotIcon = itemView.findViewById(R.id.contentnum_dot_icon);
      int fontsize = getFontSize(17);
      this.titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
      this.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);;
    }

    public void setContent(ContentItem item) {
      titleText.setText(item.title);
      if(item.getItem().equals("")) {
        itemText.setVisibility(GONE);
      }
      else {
        itemText.setText(item.getItem());
        itemText.setVisibility(View.VISIBLE);
      }

      dotIcon.setImageResource(item.imageresource);
    }
  }

  public class viewHolderEmpty3dp extends RecyclerView.ViewHolder {
    public viewHolderEmpty3dp(View itemView) {
      super(itemView);
    }
  }

  public class viewHolderMouth extends RecyclerView.ViewHolder {
    private ImageView btn1;
    private ImageView btn2;
    private ImageView btn3;
    private ImageView btn4;
    private ImageView btn5;
    private ImageView btn6;
    private TextView titleText;
    private TextView contentText;

    public viewHolderMouth(View itemView) {
      super(itemView);
      this.btn1=itemView.findViewById(R.id.mouth_one);
      this.btn2=itemView.findViewById(R.id.mouth_two);
      this.btn3=itemView.findViewById(R.id.mouth_three);
      this.btn4=itemView.findViewById(R.id.mouth_four);
      this.btn5=itemView.findViewById(R.id.mouth_five);
      this.btn6=itemView.findViewById(R.id.mouth_six);
      this.titleText=itemView.findViewById(R.id.mouth_title);
      this.contentText=itemView.findViewById(R.id.mouth_text);

      btn1.setOnTouchListener(myOnlyhandler);
      btn2.setOnTouchListener(myOnlyhandler);
      btn3.setOnTouchListener(myOnlyhandler);
      btn4.setOnTouchListener(myOnlyhandler);
      btn5.setOnTouchListener(myOnlyhandler);
      btn6.setOnTouchListener(myOnlyhandler);

      int fontsize = getFontSize(17);
      this.titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
      this.contentText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
    }

    View.OnTouchListener myOnlyhandler = new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
          String title="";
          String content="";
          btn1.setImageResource(R.drawable.mouthbtn_y1);
          btn2.setImageResource(R.drawable.mouthbtn_y2);
          btn3.setImageResource(R.drawable.mouthbtn_y3);
          btn4.setImageResource(R.drawable.mouthbtn_y4);
          btn5.setImageResource(R.drawable.mouthbtn_y5);
          btn6.setImageResource(R.drawable.mouthbtn_y6);

          switch(v.getId()) {
            case R.id.mouth_one:
              title = "① 입천장";
              content = "구형브러시나 스펀지브러시로 찌꺼기 제거";
              btn1.setImageResource(R.drawable.mouthbtn_r1);
              break;
            case R.id.mouth_two:
              title = "② 뺨 안쪽";
              content = "구형브러시나 스펀지브러시로 찌꺼기 제거";
              btn2.setImageResource(R.drawable.mouthbtn_r2);
              break;
            case R.id.mouth_three:
              title = "③ 혓바닥";
              content = "혀클리너나 구형브러시로 찌꺼기 제거";
              btn3.setImageResource(R.drawable.mouthbtn_r3);
              break;
            case R.id.mouth_four:
              title = "④ 잇몸";
              content = "구형브러시나 스펀지브러시로 찌꺼기 제거";
              btn4.setImageResource(R.drawable.mouthbtn_r4);
              break;
            case R.id.mouth_five:
              title = "⑤ 치아 표면";
              content = "칫솔로 찌꺼기 제거";
              btn5.setImageResource(R.drawable.mouthbtn_r5);
              break;
            case R.id.mouth_six:
              title = "⑥ 치아와 치아 사이";
              content = "치간칫솔로 찌꺼기 제거";
              btn6.setImageResource(R.drawable.mouthbtn_r6);
              break;
          }
          titleText.setText(title);
          contentText.setText(content);
          return true;
        }

        return false;
      }
    };
  }

  // Provide a suitable constructor (depends on the kind of dataset)
  public DetailAdapter(ArrayList<ContentItem> myDataset, Context mContext, int pagetype, int tab, int itemnum) {
    mDataSet = myDataset;
    this.pagetype = pagetype;
    this.mContext = mContext;
    this.tab = tab;
    this.itemnum = itemnum;

    this.fonttype = OptionsDB.getInstance().getFontSize(mContext);

    if(pagetype<=4) category = CATEGORY_BEFORE_CARE;
    else if(pagetype<=7) category = CATEGORY_IN_CARE;
    else category = CATEGORY_STRANGE;

    MyTTS.getInstance().setMyTTS(mContext);

    textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
      @Override
      public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
          //사용할 언어를 설정
          int result = textToSpeech.setLanguage(Locale.KOREA);
          //언어 데이터가 없거나 혹은 언어가 지원하지 않으면...
//          if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//            Toast.makeText(MainActivity.this, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
//          } else {
//            btnEnter.setEnabled(true);
//            //음성 톤
//            textToSpeech.setPitch(0.7f);
//            //읽는 속도
//            textToSpeech.setSpeechRate(1.2f);
//          }
        }
      }
    });

    Log.d("DATA",String.valueOf(myDataset));
  }



  public void itemChanged(ArrayList<ContentItem> myDataset, int pagetype, int tab, int itemnum) {
    this.mDataSet = myDataset;
    this.pagetype = pagetype;
    this.tab = tab;
    this.itemnum = itemnum;


    if(pagetype<=4) category = CATEGORY_BEFORE_CARE;
    else if(pagetype<=7) category = CATEGORY_IN_CARE;
    else category = CATEGORY_STRANGE;
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    return mDataSet.get(position).getType();
  }

  // Create new views (invoked by the layout manager)
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    RecyclerView.ViewHolder viewHolder;
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    switch (viewType) {
      case TYPE_HEAD :
        View v1 = inflater.inflate(R.layout.recycler_head, parent, false);
        viewHolder = new viewHolderHeader(v1);
        break;
      case TYPE_PRECONTENT :
        View v2 = inflater.inflate(R.layout.recycler_precontent, parent, false);
        viewHolder = new viewHolderPreSubContent(v2);
        break;
      case TYPE_PRESUBTEXT :
        View v3 = inflater.inflate(R.layout.recycler_presubtext, parent, false);
        viewHolder = new viewHolderPreSubText(v3);
        break;
      case TYPE_CONTENTHEAD :
        View v4 = inflater.inflate(R.layout.recycler_contenthead, parent, false);
        viewHolder = new viewHolderContentHead(v4);
        break;
      case TYPE_CONTENTITEM :
        View v5 = inflater.inflate(R.layout.recycler_contentitem, parent, false);
        viewHolder = new viewHolderContentItem(v5);
        break;
      case TYPE_CONTENTSUBITEM :
        View v6 = inflater.inflate(R.layout.recycler_contentsubitem, parent, false);
        viewHolder = new viewHolderContentSubItem(v6);
        break;
      case TYPE_IMAGE:
        View v7 = inflater.inflate(R.layout.recycler_image, parent, false);
        viewHolder = new viewHolderImage(v7);
        break;
      case TYPE_READTITLE:
        View v8 = inflater.inflate(R.layout.recycler_itemlist, parent, false);
        viewHolder = new viewHolderReadTitle(v8);
        break;
      case TYPE_EMPTY:
        View v9 = inflater.inflate(R.layout.recycler_empty, parent, false);
        viewHolder = new viewHolderEmpty(v9);
        break;
      case TYPE_EMPTY3DP:
        View v11 = inflater.inflate(R.layout.recycler_empty11, parent, false);
        viewHolder = new viewHolderEmpty(v11);
        break;
      case TYPE_CONTENTNUM:
        View v10 = inflater.inflate(R.layout.recycler_contentnum, parent, false);
        viewHolder = new viewHolderContentNum(v10);
        break;
      case TYPE_GIFIMAGE:
        View v12 = inflater.inflate(R.layout.recycler_gifimage, parent, false);
        viewHolder = new viewHolderGifImage(v12);
        break;
      case TYPE_NEWIMAGE:
        View v13 = inflater.inflate(R.layout.recycler_newimage, parent, false);
        viewHolder = new viewHolderNewImage(v13);
        break;
      case TYPE_MOUTH:
        View v14 = inflater.inflate(R.layout.recycler_mouthclick, parent, false);
        viewHolder = new viewHolderMouth(v14);
        break;
      case TYPE_Heimlich:
        View v15 = inflater.inflate(R.layout.recycler_heimlich, parent, false);
        viewHolder = new viewHolderHeimlichItem(v15);
        break;
      default:
        View vd = inflater.inflate(R.layout.recycler_head, parent, false);
        viewHolder = new viewHolderHeader(vd);
        break;
    }
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {

    int viewType = viewHolder.getItemViewType();

    switch (viewType) {
      case TYPE_HEAD :
        ((viewHolderHeader) viewHolder).showCallDetails(mDataSet.get(i));
        return;
      case TYPE_PRECONTENT :
        ((viewHolderPreSubContent) viewHolder).setPresubcontentTextView(mDataSet.get(i),(i>0 && (mDataSet.get(i-1).getType()==TYPE_IMAGE || mDataSet.get(i-1).getType()==TYPE_GIFIMAGE || mDataSet.get(i-1).getType()==TYPE_NEWIMAGE)));
        return;
      case TYPE_PRESUBTEXT :
        ((viewHolderPreSubText) viewHolder).setPreSubText(mDataSet.get(i));
        return;
        case TYPE_CONTENTHEAD:
          ((viewHolderContentHead) viewHolder).setContentHeadTextView(mDataSet.get(i));
          return;
      case TYPE_CONTENTITEM :
        ((viewHolderContentItem) viewHolder).setContentItem(mDataSet.get(i),(i>0 && (mDataSet.get(i-1).getType()==TYPE_IMAGE || mDataSet.get(i-1).getType()==TYPE_GIFIMAGE || mDataSet.get(i-1).getType()==TYPE_NEWIMAGE)));
        return;
      case TYPE_CONTENTSUBITEM :
        ((viewHolderContentSubItem) viewHolder).setContentHeadTextView(mDataSet.get(i));
        return;
      case TYPE_IMAGE :
        ((viewHolderImage) viewHolder).setImageView(mDataSet.get(i));
        return;
      case TYPE_READTITLE :
        ((viewHolderReadTitle) viewHolder).setTitle(mDataSet.get(i));
        return;
      case TYPE_CONTENTNUM :
        ((viewHolderContentNum) viewHolder).setContent(mDataSet.get(i));
        return;
      case TYPE_EMPTY:
        return;
      case TYPE_EMPTY3DP:
        return;
      case TYPE_GIFIMAGE :
        ((viewHolderGifImage) viewHolder).setImageView(mDataSet.get(i));
        return;
      case TYPE_NEWIMAGE :
        ((viewHolderNewImage) viewHolder).setImageView(mDataSet.get(i));
        return;
      case TYPE_Heimlich :
        ((viewHolderHeimlichItem) viewHolder).setHeimlichItemView(mDataSet.get(i));
        return;
      case TYPE_MOUTH :
        return;
        default:
          return;
    }
  }


  // Return the size of your dataset (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return mDataSet.size();
  }

  public static int getResId(String resName, Class<?> c) {

    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  private int getFontSize(int fontsize) {
    switch (fonttype) {
      case FONT_SMALL:
        switch (fontsize) {
          case 16:
            return FONT_SMALL_16;
          case 17:
            return FONT_SMALL_17;
          case 23:
            return FONT_SMALL_23;
            default:
              return FONT_SMALL_17;
        }
      case FONT_MID:
        switch (fontsize) {
          case 16:
            return FONT_MID_16;
          case 17:
            return FONT_MID_17;
          case 23:
            return FONT_MID_23;
          default:
            return FONT_MID_17;
        }
      case FONT_LARGE:
        switch (fontsize) {
          case 16:
            return FONT_LARGE_16;
          case 17:
            return FONT_LARGE_17;
          case 23:
            return FONT_LARGE_23;
          default:
            return FONT_LARGE_17;
        }
        default:
          return FONT_MID_17;
    }
  }
}