package nikkol21761.exchangerate.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${app.feign.giphy.config.name}", url = "${app.feign.giphy.config.url}")
public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET,
            value = "/random?api_key=${app.feign.giphy.config.api_key}")
    String getGif(@RequestParam(value = "tag") String tag);
}
