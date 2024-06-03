package com.epsyl.eps.entities;

import java.util.Date;

public class PushQualif {
  public String client;
  public Date datePush;
  public Date datePushRetour;
  public Date dateQualif;

  public PushQualif() {
  }

  public PushQualif(String client, Date datePush, Date datePushRetour, Date dateQualif) {
    this.client = client;
    this.datePush = datePush;
    this.datePushRetour = datePushRetour;
    this.dateQualif = dateQualif;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public Date getDatePush() {
    return datePush;
  }

  public void setDatePush(Date datePush) {
    this.datePush = datePush;
  }

  public Date getDatePushRetour() {
    return datePushRetour;
  }

  public void setDatePushRetour(Date datePushRetour) {
    this.datePushRetour = datePushRetour;
  }

  public Date getDateQualif() {
    return dateQualif;
  }

  public void setDateQualif(Date dateQualif) {
    this.dateQualif = dateQualif;
  }
}
