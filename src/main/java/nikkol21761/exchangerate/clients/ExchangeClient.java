package nikkol21761.exchangerate.clients;

import nikkol21761.exchangerate.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${app.feign.exchange.config.name}", url = "${app.feign.exchange.config.url}")
public interface ExchangeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json?app_id=${app.feign.exchange.config.app_id}")
    ResponseDTO getRates(@RequestParam(value = "base") String base, @RequestParam(value = "symbols") String symbols);

    @RequestMapping(method = RequestMethod.GET,
            value = "/historical/{date}.json?app_id=${app.feign.exchange.config.app_id}")
    ResponseDTO getHistoricalRates(@PathVariable String date,
                                   @RequestParam(value = "base") String base,
                                   @RequestParam(value = "symbols") String symbols);
}
