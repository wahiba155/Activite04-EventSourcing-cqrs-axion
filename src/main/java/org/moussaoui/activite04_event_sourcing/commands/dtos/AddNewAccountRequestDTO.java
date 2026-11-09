package org.moussaoui.activite04_event_sourcing.commands.dtos;

public record AddNewAccountRequestDTO(double initialBalance, String currency) {
}
