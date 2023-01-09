package tests.kucoin;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class StreemApiExample {

    public List<TickerData> getTickers(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()//.log().all()
               .extract().jsonPath().getList("data.ticker", TickerData.class);
       //int i =0;
    }
    @Test
    public void checkCrypto(){
        List<TickerData> usdTickers = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
        Assertions.assertTrue(usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT")));

    }

    @Test
    public void sortHighTolow(){
        List<TickerData> highToLow = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) {
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        }).collect(Collectors.toList());

        List<TickerData> top10 = highToLow.stream().limit(10).collect(Collectors.toList());
        Assertions.assertEquals(top10.get(0).getSymbol(),"INDI-USDT");
        int i =0;
    }
    public void sortLowToHigh(){
        List<TickerData> lowHigh = getTickers().stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow()).limit(10).collect(Collectors.toList());

    }
    @Test
    public void map(){
        Map<String, Float> usd = new HashMap<>();
        List<String> lowerCases = getTickers().stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
        getTickers().forEach(x->usd.put(x.getSymbol(), Float.parseFloat(x.getChangeRate())));
    }
}
