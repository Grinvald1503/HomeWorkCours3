package com.example.homeworkcourse3.Controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String launchingApplication () {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String infoProject() {
        return "Имя ученика Александр\n" +
                "Название проекта: Книга рецептов\n" +
                "В проекте использованы Spring Boot DevTools, Lombok, Spring Web\n" +
                "Функции проекта: Внесение рецептов и их хранение";
    }
}
