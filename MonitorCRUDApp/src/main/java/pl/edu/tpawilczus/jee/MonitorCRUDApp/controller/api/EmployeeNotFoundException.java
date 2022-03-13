package pl.edu.tpawilczus.jee.MonitorCRUDApp.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such employee in db")
public class EmployeeNotFoundException extends RuntimeException {
}
