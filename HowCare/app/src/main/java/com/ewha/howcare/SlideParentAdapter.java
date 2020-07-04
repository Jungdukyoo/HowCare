package com.ewha.howcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class SlideParentAdapter extends ExpandableRecyclerAdapter<SlideParentViewHolder, SlideChildViewHolder> {

  private LayoutInflater mInflator;

  public SlideParentAdapter(Context context, List<? extends ParentListItem> parentItemList) {
    super(parentItemList);
    mInflator = LayoutInflater.from(context);
  }

  @Override
  public SlideParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
    View movieCategoryView = mInflator.inflate(R.layout.navi_recycler_head, parentViewGroup, false);
    return new SlideParentViewHolder(movieCategoryView);
  }

  @Override
  public SlideChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
    View moviesView = mInflator.inflate(R.layout.nav_recycler_item, childViewGroup, false);
    return new SlideChildViewHolder(moviesView);
  }

  @Override
  public void onBindParentViewHolder(SlideParentViewHolder movieCategoryViewHolder, int position, ParentListItem parentListItem) {
    SlideParent movieCategory = (SlideParent) parentListItem;
    movieCategoryViewHolder.bind(movieCategory);
  }

  @Override
  public void onBindChildViewHolder(SlideChildViewHolder moviesViewHolder, int position, Object childListItem) {
    SlideChild movies = (SlideChild) childListItem;
    moviesViewHolder.bind(movies);
  }
}