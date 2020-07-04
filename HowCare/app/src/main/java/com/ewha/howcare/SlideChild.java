package com.ewha.howcare;

public class SlideChild {
  private String title;
  private int pagenum;
  private int itemnum;
  private boolean isTop;
  private boolean isBottom;


  public SlideChild(String title, int pagenum,int itemnum) {
    this.title = title;
    this.pagenum = pagenum;
    this.itemnum = itemnum;
  }

  public void setTop(boolean top) {
    isTop = top;
  }

  public void setBottom(boolean bottom) {
    isBottom = bottom;
  }

  public String getTitle() {
    return title;
  }

  public int getPagenum() {
    return pagenum;
  }

  public int getItemnum() {
    return itemnum;
  }

  public boolean isBottom() {
    return isBottom;
  }

  public boolean isTop() {
    return isTop;
  }
}
