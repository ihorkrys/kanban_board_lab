package edu.duan.app.kanban.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "board_column")
@SequenceGenerator(
        name = "b_column_generator",
        sequenceName = "b_column_generator_seq",
        initialValue = 100,
        allocationSize = 1
)
@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "b_column_generator")
    private long id;
    @Column(length = 100)
    private String name;
    @Column(length = 25, unique = true, nullable = false)
    private String slug;
    @Column(length = 7)
    private String hexColor;
    private boolean active;
    private int position;
}
