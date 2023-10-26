package dev.edugovi.dir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dev.edugovi.dir.controller.exception.IdNotFoundException;
import dev.edugovi.dir.controller.exception.IdNumberFormatException;
import dev.edugovi.dir.model.User;
import dev.edugovi.dir.property.MainIdProperty;
import dev.edugovi.dir.resource.Granted;
import dev.edugovi.dir.service.UserService;

@RepositoryRestController
public class UserController {

    private UserService userService;
    private MainIdProperty mainIdProperty;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMainIdProperty(MainIdProperty mainIdProperty) {
        this.mainIdProperty = mainIdProperty;
    }


   @RequestMapping(method = RequestMethod.POST, value = "/users/check")
    public HttpEntity<Granted> checkPostAction(@RequestParam(value = "id") String idStr,
                                               @RequestParam(value = "password") String password)
            throws IdNotFoundException, IdNumberFormatException {

        try {
            Granted granted = getGranted(idStr, password);
            return new ResponseEntity<>(granted, HttpStatus.OK);
        } catch (NumberFormatException nfe) {
            throw IdNumberFormatException.getInstance(idStr);
        } catch (NullPointerException npe) {
            throw IdNotFoundException.getInstance(idStr);
        }
    }


    private Granted getGranted(String idStr, String password) {
        User user;
        switch (mainIdProperty.getMainIdentifier()) {
            case "idNum":
                Long idNum = Long.parseLong(idStr);
                user = userService.findByIdNumAndPasswordAndEnabled(idNum, password);
                break;
            case "idStr":
                user = userService.findByIdStrAndPasswordAndEnabled(idStr, password);
                break;
            default:
                Long id = Long.parseLong(idStr);
                user = userService.findByIdAndPasswordAndEnabled(id, password);
        }
        if (user != null)
            return new Granted(true, user.getId());
        else
            return new Granted(false, -1L);
    }
}
