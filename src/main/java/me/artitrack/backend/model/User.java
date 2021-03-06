package me.artitrack.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
  private static final long serialVersionUID = 5327804285969688785L;
  @Id
  private String steam64;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "avatarurl")
  private String avatarUrl;

  protected User() {
  }

  public User(String steam64) {
    this(steam64, "", "");
  }

  public User(String steam64, String nickname, String avatarUrl) {
    this.steam64 = steam64;
    this.nickname = nickname;
    this.avatarUrl = avatarUrl;
  }


  public String getSteam64() {
    return steam64;
  }

  @Override
  public String toString() {
    return "User{" +
        "steam64='" + steam64 + '\'' +
        ", nickname='" + nickname + '\'' +
        ", avatarUrl='" + avatarUrl + '\'' +
        '}';
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getNickname() {
    return nickname;
  }

  public void setSteam64(String steam64) {
    this.steam64 = steam64;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}
