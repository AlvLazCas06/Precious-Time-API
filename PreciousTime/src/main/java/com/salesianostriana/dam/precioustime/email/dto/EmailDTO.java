package com.salesianostriana.dam.precioustime.email.dto;

public record EmailDTO(
    String receiver,
    String title,
    String message
) {
}
