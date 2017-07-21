package ru.homer.leaderboard.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vadmurzakov on 20.07.17.
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String getView() {
        return "index";
    }

    @RequestMapping("/views/{viewName}")
    public String getView(@PathVariable String viewName) {
        return "views/" + viewName;
    }

}