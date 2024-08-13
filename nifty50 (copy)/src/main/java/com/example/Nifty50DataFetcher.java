package com.example;
import org.json.JSONObject;

import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class Nifty50DataFetcher {

    private static final String[] NIFTY_50_STOCKS = {
        "ADANIENT", "ADANIPORTS", 
        // "ASIANPAINT.BO", "AXISBANK.BO", "BAJAJ-AUTO.BO",
        // "BAJAJ-FINANCE.BO", "BAJAJ-FINSV.BO", "BHARTIARTL.BO", "BPCL.BO", "BRITANNIA.BO",
        // "CIPLA.BO", "COALINDIA.BO", "DIVISLAB.BO", "DRREDDY.BO", "HCLTECH.BO",
        // "HDFCBANK.BO", "HDFCLIFE.BO", "HINDALCO.BO", "HINDUNILVR.BO", "ICICIBANK.BO",
        // "INDUSINDBK.BO", "INFY.BO", "ITC.BO", "JSWSTEEL.BO", "KOTAKBANK.BO",
        // "L&T.BO", "M&M.BO", "MARUTI.BO", "NESTLEIND.BO", "NTPC.BO",
        // "ONGC.BO", "POWERGRID.BO", "RELIANCE.BO", "SBIN.BO", "SHREECEM.BO",
        // "SUNPHARMA.BO", "TATACONSUM.BO", "TATAMOTORS.BO", "TATASTEEL.BO", "TECHM.BO",
        // "TITAN.BO", "ULTRACEMCO.BO", "UPL.BO", "WIPRO.BO", "ZEEL.BO"
    };

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1); // Go back one month
        Date oneMonthAgo = cal.getTime();
        
        System.out.println("Fetching data from: " + sdf.format(oneMonthAgo));

        for (String stock : NIFTY_50_STOCKS) {
            try {
                System.out.println("Fetching data for: " + stock);
                List<HistoricalQuote> history = YahooFinance.get(stock)
                        .getHistory();

                System.out.println("Historical data for " + stock + ":");
                for (HistoricalQuote quote : history) {
                    System.out.println("Date: " + quote.getDate().getTime() + ", Close: " + quote.getClose());
                }
            } catch (IOException e) {
                System.err.println("Error fetching data for " + stock + ": " + e.getMessage());
            }
        }
    }
}
