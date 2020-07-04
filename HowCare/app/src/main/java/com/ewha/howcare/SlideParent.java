package com.ewha.howcare;

import java.util.List;

public class SlideParent implements ParentListItem {
  private String title;
  private int pagenum;
  private List<SlideChild> subMenus;

  public SlideParent(int pagenum, String title, List<SlideChild> subMenus) {
    this.title = title;
    this.subMenus = subMenus;
    this.pagenum = pagenum;
  }

  public String getTitle() {
    return title;
  }

  public int getPagenum() {
    return pagenum;
  }

  @Override
  public List<?> getChildItemList() {
    return subMenus;
  }

  @Override
  public boolean isInitiallyExpanded() {
    return false;
  }
}