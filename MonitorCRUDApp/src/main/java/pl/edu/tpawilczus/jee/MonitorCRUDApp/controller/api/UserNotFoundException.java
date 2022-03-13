package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user in db")
public class UserNotFoundException extends RuntimeException {
}
