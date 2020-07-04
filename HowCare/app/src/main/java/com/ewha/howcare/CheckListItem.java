package com.ewha.howcare;

public class CheckListItem {

  int state; // 0 = none, 1 = y , 2 = n , 3 = not
  String text = "";
  String etc = "";
  int type = 2; //1 = title , 2 = item , 3 = done

  public CheckListItem(String text, int type) {
    this.text = text;
    this.type = type;
  }

  public CheckListItem(String text, int state, String etc) {
    this.text = text;
    this.state = state;
    this.etc = "";
    this.type = 2;
  }

  public CheckListItem(String text) {
    this.text = text;
    this.state = 0;
    this.etc = "";
  }

  public String getText() {
    return text;
  }

  public int getState() {
    return state;
  }

  public String getEtc() {
    return etc;
  }

  public void setState(int state) {
    this.state = state;
  }

  public void setEtc(String etc) {
    this.etc = etc;
  }

  public int getType() {
    return type;
  }
}
