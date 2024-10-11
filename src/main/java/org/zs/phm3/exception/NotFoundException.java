package org.zs.phm3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such entity")
public class NotFoundException extends Exception  {
}

