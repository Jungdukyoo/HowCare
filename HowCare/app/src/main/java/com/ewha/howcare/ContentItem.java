package com.ewha.howcare;

public class ContentItem {
  public String content="";
  public int type;
  public int number;
  public String title="";
  public int imageresource;
  public int color = 0;
  public int thumbresource;

  public ContentItem() {

  }

  public ContentItem(String item, int type) {
    this.content = item;
    this.type = type;
    this.number = 0;
    this.title = "";
  }

  public ContentItem(String item, int type,int color) {
    this.content = item;
    this.type = type;
    this.number = 0;
    this.title = "";
    this.color = color;
  }

//  public ContentItem(String item, int number, int type) {
//    this.content = item;
//    this.type = type;
//    this.number = number;
//    this.title = "";
//
  //}
  public ContentItem(String item, int type, int number, String title) {
    this.content = item;
    this.type = type;
    this.number = number;
    this.title = title;
  }
  public ContentItem(String item, int type, String title,int resid) {
    this.content = item;
    this.type = type;
//    this.number = number;
    this.title = title;
    this.imageresource = resid;
  }
  public ContentItem(int resid, String item, int type) {
    this.content = item;
    this.type = type;
    this.imageresource = resid;
  }
  public ContentItem(String item, int type, int number, String title, int color) {
    this.content = item;
    this.type = type;
    this.number = number;
    this.title = title;
    this.color = color;
  }


  public ContentItem(int img, int type) {
    this.type = type;
    this.imageresource = img;
  }

  public ContentItem(int gif, int gifthumb, int type) {
    this.type = type;
    this.imageresource = gif;
    this.thumbresource = gifthumb;
  }

  public int getType() {
    return type;
  }
  public String getItem() {
    return content;
  }
  public String getTitle() {
    return title;
  }
}
