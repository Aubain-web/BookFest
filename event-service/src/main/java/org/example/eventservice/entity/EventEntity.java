package org.example.eventservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de l'événement est obligatoire")
    private String name;

    @NotBlank(message = "La description est obligatoire")
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    @NotBlank(message = "L'emplacement est obligatoire")
    private String location;

    @NotNull(message = "La date est obligatoire")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Le format de la date doit être YYYY-MM-DD")
    private String date;

    @NotBlank(message = "L'heure est obligatoire")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Le format de l'heure doit être HH:mm")
    private String time;

    @NotBlank(message = "La durée est obligatoire")
    private String duration;

    @NotBlank(message = "La catégorie est obligatoire")
    private String category;

    @NotNull(message = "Le prix est obligatoire")
    @PositiveOrZero(message = "Le prix ne peut pas être négatif")
    private Double price;

    @NotNull(message = "La capacité est obligatoire")
    @Positive(message = "La capacité doit être supérieure à zéro")
    private Integer capacity;

    private String imageUrl;
}
