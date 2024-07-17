## 1. Configurer votre Repository MongoDB : Créez un repository pour accéder aux données de la base de données MongoDB.

```java
public interface PersonnalRepository extends MongoRepository<Personnal, String> {
    List<Personnal> findAllByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
```

## 2. Service pour Récupérer les Nouvelles Entrées : Implémentez un service qui utilisera le repository pour récupérer les personnals ajoutés la veille.

```java
@Service
public class PersonnalService {

    @Autowired
    private PersonnalRepository personnalRepository;

    public List<Personnal> getPersonnalsAddedYesterday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfYesterday = now.minusDays(1).with(LocalTime.MIN);
        LocalDateTime endOfYesterday = now.minusDays(1).with(LocalTime.MAX);

        return personnalRepository.findAllByCreatedDateBetween(startOfYesterday, endOfYesterday);
    }
}
```

## 3. Contrôleur pour Exposer le Service : Créez un contrôleur REST pour exposer le service via une API.

```java
@RestController
@RequestMapping("/api/personnals")
public class PersonnalController {

    @Autowired
    private PersonnalService personnalService;

    @GetMapping("/new")
    public List<Personnal> getNewPersonnals() {
        return personnalService.getPersonnalsAddedYesterday();
    }
}
```

#  Automatiser

## Ajouter les dépendances nécessaires : Assurez-vous que les dépendances pour Spring Mail et Spring Scheduler sont ajoutées dans votre pom.xml.

```xml
<dependencies>
    <!-- Spring Boot Starter Mail -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <!-- Spring Boot Starter Scheduling -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```
## Configurer les propriétés de l'email : Ajoutez les configurations nécessaires dans le fichier application.properties.

```properties
spring.mail.host=smtp.your-email-provider.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Service d'envoi d'email : Créez un service pour envoyer des emails.

```java
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
```

## Configurer la tâche planifiée : Implémentez un planificateur pour récupérer les nouveaux personnals et envoyer un email chaque jour de la semaine sauf le samedi et le dimanche.

```java
@Component
public class ScheduledTasks {

    @Autowired
    private PersonnalService personnalService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 8 * * MON-FRI") // S'exécute à 8h00 du lundi au vendredi
    public void reportNewPersonnals() {
        List<Personnal> newPersonnals = personnalService.getPersonnalsAddedYesterday();
        if (!newPersonnals.isEmpty()) {
            StringBuilder emailContent = new StringBuilder("Nouveaux personnals ajoutés hier :\n\n");
            for (Personnal personnal : newPersonnals) {
                emailContent.append(personnal.toString()).append("\n");
            }
            emailService.sendEmail("recipient@example.com", "Rapport des nouveaux personnals", emailContent.toString());
        }
    }
}
```

## Activer la planification dans votre application Spring Boot : Ajoutez l'annotation @EnableScheduling à votre classe principale Spring Boot.

```java
@SpringBootApplication
@EnableScheduling
public class YourApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}
```

### Explications supplémentaires :

- **Propriétés de l'email :** Les configurations de l'email dans application.properties sont nécessaires pour que Spring Boot puisse se connecter à votre serveur SMTP.

- **Service d'envoi d'email :** EmailService utilise JavaMailSender pour envoyer des emails. Vous pouvez personnaliser le contenu du message selon vos besoins.

- **Planificateur :** La classe ScheduledTasks utilise @Scheduled pour définir une tâche planifiée qui s'exécute à 8h00 du lundi au vendredi. Le format CRON "0 0 8 * * MON-FRI" signifie "à 8h00 du lundi au vendredi".

- **Classe principale Spring Boot :** L'annotation @EnableScheduling permet d'activer la fonctionnalité de planification dans votre application Spring Boot.