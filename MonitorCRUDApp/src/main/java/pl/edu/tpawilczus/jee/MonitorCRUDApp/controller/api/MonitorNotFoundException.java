package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such monitor in db")
public class MonitorNotFoundException extends RuntimeException {
}
