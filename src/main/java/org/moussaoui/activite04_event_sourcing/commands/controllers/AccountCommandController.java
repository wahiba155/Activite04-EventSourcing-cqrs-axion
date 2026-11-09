package org.moussaoui.activite04_event_sourcing.commands.controllers;


import org.moussaoui.activite04_event_sourcing.commands.commands.AddAccountCommand;
import org.moussaoui.activite04_event_sourcing.commands.dtos.AddNewAccountRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;

    }

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO request) {
        CompletableFuture<String> result = this.commandGateway.send(new AddAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return result;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception  exception){
        return exception.getMessage();
    }
}
