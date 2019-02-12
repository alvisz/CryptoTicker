package com.cryptoticker;

public class Crypto {
        private int id;
        private String name;
        private String imgURL;
        private double price;
        private double volume;
        private double percent_change_1h;
        private double percent_change_24h;
        private double percent_change_7d;
        private double market_cap;
        private String last_updated;

    /*{
        "id": 1,
            "name": "Bitcoin",
            "symbol": "BTC",
            "slug": "bitcoin",
            "circulating_supply": 17369600,
            "total_supply": 17369600,
            "max_supply": 21000000,
            "date_added": "2013-04-28T00:00:00.000Z",
            "num_market_pairs": 6654,
            "cmc_rank": 1,
            "last_updated": "2018-11-10T23:07:10.000Z",
            "quote": {
        "USD": {
            "price": 6423.27459928,
                    "volume_24h": 3742425124.74069,
                    "percent_change_1h": 0.108467,
                    "percent_change_24h": 0.526559,
                    "percent_change_7d": 0.979431,
                    "market_cap": 111569710479.65388,
                    "last_updated": "2018-11-10T23:07:10.000Z"
        }
    }
    }*/

        public Crypto(
                int id, String name, String imgURL,
                double price, double volume, double percent_change_1h,
                double percent_change_24h, double percent_change_7d, double market_cap, String last_updated){
            this.id = id;
            this.name = name;
            this.imgURL = imgURL;
            this.price = price;
            this.volume = volume;
            this.percent_change_1h = percent_change_1h;
            this.percent_change_24h = percent_change_24h;
            this.percent_change_7d = percent_change_7d;
            this.market_cap = market_cap;
            this.last_updated = last_updated;
        }

        protected String getName(){
            return this.name;
        }

        public String toString(){
        return name;
        }

        protected double getPrice(){
            return this.price;
        }

        protected double getVolume(){
            return this.volume;
        }

        protected double getChange1H(){
            return this.percent_change_1h;
        }

        protected double getChange24H(){
            return this.percent_change_24h;
        }

        protected double getChange7D(){
            return this.percent_change_7d;
        }

        protected double getMarketCap(){
            return this.market_cap;
        }

        protected String getImgURL(){
            return this.imgURL;
        }
}
