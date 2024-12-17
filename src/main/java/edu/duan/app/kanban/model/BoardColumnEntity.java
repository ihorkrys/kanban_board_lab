package edu.duan.app.kanban.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 100)
    private String name;
    @Column(length = 25)
    private String slug;
    @Column(length = 6)
    private String hexColor;
    private boolean active;
    private int position;
}
