//package com.wallet.bank.service;
//
//import com.wallet.bank.dao.CustomerRepository;
//import com.wallet.bank.domain.Biography;
//import com.wallet.bank.domain.Connections;
//import com.wallet.bank.domain.Customer;
//import com.wallet.bank.domain.PowerStats;
//import com.wallet.bank.domain.Work;
//import com.wallet.bank.dto.SuperheroApiResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.IntStream;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class SuperheroService {
//
//    private static final String API_URL = "https://superheroapi.com/api/6f636867ee194d504d1b3f4f9c65e51f/";
//
//    private final CustomerRepository customerRepository;
//
//    private final RestTemplate restTemplate;
//
//    public void fetchAndSaveSuperheroes() {
//        List<Customer> customers = new ArrayList<>();
//        for (Integer id : IntStream.rangeClosed(0, 750).boxed().toList()) {
//            String url = API_URL + id;
//            SuperheroApiResponse response = restTemplate.getForObject(url, SuperheroApiResponse.class);
//            if (response != null) {
//                Customer entity = mapTo(response);
//                if (entity != null) {
//                    log.info(entity.toString());
//                    customers.add(customerRepository.save(entity));
//                }
//            }
//        }
//        customerRepository.saveAll(customers);
//    }
//
//    private Customer mapTo(SuperheroApiResponse superhero) {
//        if (superhero.getPowerstats() == null) {
//            return null;
//        }
//        PowerStats powerStats = PowerStats.builder()
//                .intelligence(superhero.getPowerstats().getIntelligence())
//                .strength(superhero.getPowerstats().getStrength())
//                .speed(superhero.getPowerstats().getSpeed())
//                .durability(superhero.getPowerstats().getDurability())
//                .power(superhero.getPowerstats().getPower())
//                .combat(superhero.getPowerstats().getCombat())
//                .build();
//
//        Biography biography = Biography.builder()
//                .fullName(superhero.getBiography().getFullName())
//                .alterEgos(superhero.getBiography().getAlterEgos())
//                .aliases(superhero.getBiography().getAliases())
//                .placeOfBirth(superhero.getBiography().getPlaceOfBirth())
//                .firstAppearance(superhero.getBiography().getFirstAppearance())
//                .publisher(superhero.getBiography().getPublisher())
//                .alignment(superhero.getBiography().getAlignment())
//                .build();
//
//        Work work = Work.builder()
//                .occupation(superhero.getWork().getOccupation())
//                .baseOfOperations(superhero.getWork().getBaseOfOperations())
//                .build();
//
//        Connections connections = Connections.builder()
//                .groupAffiliation(superhero.getConnections().getGroupAffiliation())
//                .relatives(superhero.getConnections().getRelatives())
//                .build();
//
//        return Customer.builder()
//                .name(superhero.getName())
//                .powerStats(powerStats)
//                .biography(biography)
//                .work(work)
//                .email(biography.getFullName() + "@i.ua")
//                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
//                .connections(connections)
//                .imageUrl(superhero.getImage().getUrl())
//                .build();
//    }
//}
