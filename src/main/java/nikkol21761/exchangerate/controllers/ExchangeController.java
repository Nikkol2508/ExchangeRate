package nikkol21761.exchangerate.controllers;

import lombok.RequiredArgsConstructor;
import nikkol21761.exchangerate.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping(value = "/")
    public ResponseEntity main() {
        return ResponseEntity.ok(exchangeService.getMessage());
    }

    @GetMapping(value = "/{target}")
    public RedirectView getRatesData(@PathVariable String target) {

        return exchangeService.getExchange(target);
    }

}
