package ru.homer.leaderboard.service;

import ru.homer.leaderboard.entity.UserDto;

/**
 * Created by vadmurzakov on 23.07.17.
 */
public interface User {

    public UserDto getUserByUsername(String username);

}
