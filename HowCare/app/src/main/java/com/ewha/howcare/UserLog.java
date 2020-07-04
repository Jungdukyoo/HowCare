package com.ewha.howcare;

public class UserLog {
  public long timestamp;
  public int item_category;
  public int item_number;
  public int others;

  public UserLog(long timestamp, int item_category, int item_number) {
    this.timestamp = timestamp;
    this.item_category = item_category;
    this.item_number = item_number;
    this.others = 0;
  }

  public UserLog(long timestamp, int others) {
    this.timestamp = timestamp;
    this.others = others;
    this.item_number = 0;
    this.item_category = 0;
  }

  public UserLog(long timestamp, int item_category, int item_number, int others) {
    this.timestamp = timestamp;
    this.item_category = item_category;
    this.item_number = item_number;
    this.others = others;
  }

}
