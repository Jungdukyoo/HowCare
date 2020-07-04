package com.ewha.howcare;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

public class FavoriteItem {
  private String title;
  private String thumbnail;
  private boolean isSelected;
  private int pagenum;
  private int itemnum;

  public FavoriteItem(String title, String thumbnail, int pagenum, int itemnum, boolean isSelected) {
    this.title = title;
    this.thumbnail = thumbnail;
    this.isSelected = isSelected;
    this.pagenum = pagenum;
    this.itemnum = itemnum;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public String getTitle() {
    return title;
  }

  public int getItemnum() {
    return itemnum;
  }

  public int getPagenum() {
    return pagenum;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setUnselected() {
    isSelected = false;
  }

  public void setSelected() {
    isSelected = true;
  }
}
