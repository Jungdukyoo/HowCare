package com.ewha.howcare;


import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class SlideParentViewHolder extends ParentViewHolder  {
  private static final float INITIAL_POSITION = 0.0f;
  private static final float ROTATED_POSITION = 180f;

  private TextView mTitleView;
  private TextView mNumberView;


  public SlideParentViewHolder(View itemView) {
    super(itemView);
    mTitleView = (TextView) itemView.findViewById(R.id.parent_title);
    mNumberView = (TextView) itemView.findViewById(R.id.parent_number);
  }

  public void bind(SlideParent slideParent) {
    mTitleView.setText(slideParent.getTitle());
    mNumberView.setText(String.valueOf(slideParent.getPagenum()));
    int color;
    if(slideParent.getPagenum()<=4) {
      color = R.color.light_mustard;
    }
    else if(slideParent.getPagenum()<=7) {
      color = R.color.melon;
    }
    else {
      color = R.color.seafoam_blue_two;
    }
    mNumberView.setTextColor(mNumberView.getResources().getColor(color));
  }

  @Override
  public void setExpanded(boolean expanded) {
    super.setExpanded(expanded);
  }

  @Override
  public void onExpansionToggled(boolean expanded) {
    super.onExpansionToggled(expanded);

    RotateAnimation rotateAnimation;
    if (expanded) { // rotate clockwise
      rotateAnimation = new RotateAnimation(ROTATED_POSITION,
          INITIAL_POSITION,
          RotateAnimation.RELATIVE_TO_SELF, 0.5f,
          RotateAnimation.RELATIVE_TO_SELF, 0.5f);
    } else { // rotate counterclockwise
      rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
          INITIAL_POSITION,
          RotateAnimation.RELATIVE_TO_SELF, 0.5f,
          RotateAnimation.RELATIVE_TO_SELF, 0.5f);
    }
    rotateAnimation.setDuration(200);
    rotateAnimation.setFillAfter(true);
  }
}
