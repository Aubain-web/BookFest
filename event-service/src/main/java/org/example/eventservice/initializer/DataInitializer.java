package org.example.eventservice.initializer;

import org.example.eventservice.entity.EventEntity;
import org.example.eventservice.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EventRepository eventRepository;

    public DataInitializer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) {
        if (eventRepository.count() == 0) {
            List<EventEntity> events = List.of(
                    EventEntity.builder()
                            .name("Summer Festival")
                            .description("Annual summer music festival")
                            .location("Miami Beach")
                            .date("2025-06-15")
                            .time("14:00")
                            .duration("8 hours")
                            .category("Music")
                            .price(89.99)
                            .capacity(5000)
                            .imageUrl("https://parisjetaime.com/data/layout_image/fr-FR/Ambiance-We-Love-Green-pelouse--630x405--%C2%A9-We-Love-Green.jpg")
                            .build(),
                    EventEntity.builder()
                            .name("Tech Conference")
                            .description("Innovations in AI and blockchain")
                            .location("San Francisco")
                            .date("2025-05-20")
                            .time("09:00")
                            .duration("2 days")
                            .category("Technology")
                            .price(299.0)
                            .capacity(1200)
                            .imageUrl("https://fifpro.org/media/k54hvfmd/ab_ft.jpg?rxy=0.5071676002358491,0.6094325229677291&width=1600&height=1024&rnd=133851559148070000")
                            .build(),
                    EventEntity.builder()
                            .name("Wine Tasting")
                            .description("Premium wines from local vineyards")
                            .location("Napa Valley")
                            .date("2025-07-04")
                            .time("17:00")
                            .duration("3 hours")
                            .category("Food & Drink")
                            .price(65.5)
                            .capacity(150)
                            .imageUrl("https://example.com/wine-tasting.jpg")
                            .build(),
                    EventEntity.builder()
                            .name("Marathon")
                            .description("City annual marathon race")
                            .location("New York")
                            .date("2025-09-21")
                            .time("07:30")
                            .duration("6 hours")
                            .category("Sports")
                            .price(45.0)
                            .capacity(10000)
                            .imageUrl("https://img.20mn.fr/iNRDQNLkSj-kFs6HXzfEVyk/1444x920_le-depart-du-marathon-de-paris-a-lieu-en-bas-de-l-arc-de-triomphe")
                            .build(),
                    EventEntity.builder()
                            .name("Art Exhibition")
                            .description("Modern art showcase")
                            .location("Paris")
                            .date("2025-04-18")
                            .time("10:00")
                            .duration("5 hours")
                            .category("Art")
                            .price(25.0)
                            .capacity(300)
                            .imageUrl("https://www.dca.org.uk/images/uploads/f691bc033511cae5113d05e4ed0ba422.jpg")
                            .build(),
                    EventEntity.builder()
                            .name("Comedy Night")
                            .description("Stand-up comedy special")
                            .location("Chicago")
                            .date("2025-08-12")
                            .time("20:00")
                            .duration("2 hours")
                            .category("Entertainment")
                            .price(35.75)
                            .capacity(400)
                            .imageUrl("https://lapommedeve.com/wp-content/uploads/2023/09/comedy-night.jpeg")
                            .build(),
                    EventEntity.builder()
                            .name("Food Festival")
                            .description("International cuisine fair")
                            .location("Tokyo")
                            .date("2025-10-05")
                            .time("11:00")
                            .duration("7 hours")
                            .category("Food")
                            .price(15.0)
                            .capacity(2500)
                            .imageUrl("https://weezevent.com/wp-content/uploads/2021/12/06165440/lille-street-food-festival-1-2000x1400.jpg")
                            .build(),
                    EventEntity.builder()
                            .name("Yoga Retreat")
                            .description("Weekend wellness program")
                            .location("Bali")
                            .date("2025-11-15")
                            .time("08:00")
                            .duration("2 days")
                            .category("Wellness")
                            .price(199.0)
                            .capacity(100)
                            .imageUrl("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2a/e0/2e/2e/yoga-class.jpg?w=900&h=500&s=1")
                            .build(),
                    EventEntity.builder()
                            .name("Film Premiere")
                            .description("New blockbuster movie debut")
                            .location("Los Angeles")
                            .date("2025-07-30")
                            .time("19:30")
                            .duration("4 hours")
                            .category("Entertainment")
                            .price(120.0)
                            .capacity(800)
                            .imageUrl("https://ds.static.rtbf.be/article/image/1920x1920/d/8/3/e8de67aac98d923eb372575f30568a89-1400757734.jpg")
                            .build(),
                    EventEntity.builder()
                            .name("Business Summit")
                            .description("Global leadership conference")
                            .location("London")
                            .date("2025-09-10")
                            .time("08:30")
                            .duration("2 days")
                            .category("Business")
                            .price(450.0)
                            .capacity(2000)
                            .imageUrl("https://www.orange-business.com/sites/default/files/530x250_obs2022-076.png")
                            .build()
            );

            eventRepository.saveAll(events);
            System.out.println("✅ Données initialisées !");
        }
    }
}