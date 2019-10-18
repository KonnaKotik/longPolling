package ru.itis.longpollingjwt.model.user;

public enum UserState {
    ACTIVE("Активный"),
    BANNED("Заблокированный"),
    DELETED("Удаленный");
  //  CONFIRMED, NOT_CONFIRMED

    private final String state;

    UserState(String state) {
        this.state = state;
    }
}
