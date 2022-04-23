package com.ivan.mostovyi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "telegram_user")
public class User {

  @Id
  private String id;

}
