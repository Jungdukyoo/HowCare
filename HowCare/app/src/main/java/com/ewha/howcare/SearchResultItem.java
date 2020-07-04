package com.ewha.howcare;

import java.util.ArrayList;

public class SearchResultItem {
  private ArrayList<ContentItem> itemContent;
  private int pagenum;
  private int itemnum;
  private int focusnum=0;

  public SearchResultItem(ArrayList<ContentItem> itemContent, int pagenum, int itemnum, int focusnum) {
    this.itemContent = itemContent;
    this.pagenum = pagenum;
    this.itemnum = itemnum;
    this.focusnum = focusnum;
  }

  public int getPagenum() {
    return pagenum;
  }

  public int getItemnum() {
    return itemnum;
  }

  public int getFocusnum() {
    return focusnum;
  }

  public ArrayList<ContentItem> getItemContent() {
    return itemContent;
  }
}
