package com.ewha.howcare;

public class NavigationItem {
  public String title="";
  public int type;
  public int number;
  public int pagenum;
  public int itemnum;
  public boolean isOpend=false;
  public boolean isTop=false;
  public boolean isBottom =false;

  public NavigationItem() {
  }

  public NavigationItem(String title, int type, int number, int pagenum, int itemnum) {
    this.type = type;
    this.title = title;
    this.pagenum = pagenum;
    this.itemnum = itemnum;
    this.number = number;
    this.isOpend =false;
    this.isTop=false;
    this.isBottom=false;
  }

  public int getItemnum() {
    return itemnum;
  }

  public int getPagenum() {
    return pagenum;
  }

  public String getTitle() {
    return title;
  }

  public int getNumber() {
    return number;
  }

  public int getType() {
    return type;
  }

  public boolean isOpend() {
    return isOpend;
  }

  public void setOpend(boolean flag) {
    isOpend = flag;
  }

  public boolean isTop() {
    return isTop;
  }

  public boolean isBottom() {
    return isBottom;
  }

  public void setTop(boolean top) {
    isTop = top;
  }

  public void setBottom(boolean bottom) {
    isBottom = bottom;
  }
}
