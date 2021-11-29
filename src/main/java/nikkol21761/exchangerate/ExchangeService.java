package nikkol21761.exchangerate;

import lombok.RequiredArgsConstructor;
import nikkol21761.exchangerate.clients.ExchangeClient;
import nikkol21761.exchangerate.clients.GiphyClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    @Value("${app.exchange.currency.base}")
    private String base;

    private final ExchangeClient exchangeClient;
    private final GiphyClient giphyClient;

    public RedirectView getExchange(String target) {
        target = target.toUpperCase();
        String date = LocalDate.now().minusDays(1).toString();
        RedirectView redirectView = new RedirectView();

        double rateNow = 0;
        double rateYesterday = 0;
        try {
            rateNow = exchangeClient.getRates(base, target).getRates().get(target);
            rateYesterday = exchangeClient.getHistoricalRates(date, base, target).getRates().get(target);
        } catch (Exception e) {
            e.printStackTrace();
            redirectView.setUrl("https://docs.openexchangerates.org/docs/supported-currencies");
            return redirectView;
        }
        String tag = (rateNow < rateYesterday) ? "rich" : "broke";
        redirectView.setUrl(getGifUrl(tag));

        return redirectView;
    }

    public String getGifUrl(String tag) {

        String data;
        try {
            JSONObject jsonObject = new JSONObject(giphyClient.getGif(tag));
            data = jsonObject.getJSONObject("data").getJSONObject("images")
                    .getJSONObject("downsized_large").getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
            return "http://localhost:8080";
        }

        return data;
    }


    public String getMessage() {
        String message = "Добавте в строку поиска код валюты (RUB, EUR, GBP, CNY) и обновите страницу." +
                "Пример http://localhost:8080/RUB";
        return message;
    }
}
