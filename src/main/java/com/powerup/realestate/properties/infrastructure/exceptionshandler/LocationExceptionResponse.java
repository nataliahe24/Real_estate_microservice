package com.powerup.realestate.properties.infrastructure.exceptionshandler;

import java.time.LocalDateTime;

public record LocationExceptionResponse(String message, LocalDateTime timeStamp) {
}
