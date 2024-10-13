//package com.wallet.bank.dto;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.wallet.bank.domain.Biography;
//import com.wallet.bank.domain.Connections;
//import com.wallet.bank.domain.PowerStats;
//import com.wallet.bank.domain.Work;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Getter
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class SuperheroApiResponse {
//    private String id;
//    private String name;
//    private PowerStats powerstats;
//    private Biography biography;
//    private Work work;
//    private Connections connections;
//    private Image image;
//
//    @Getter
//    @NoArgsConstructor
//    public static class Image {
//        private String url;
//
//        @JsonCreator
//        public Image(@JsonProperty("url") String url) {
//            this.url = url;
//        }
//    }
//}
