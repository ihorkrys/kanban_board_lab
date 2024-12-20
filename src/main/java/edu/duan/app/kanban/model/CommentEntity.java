package edu.duan.app.kanban.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@SequenceGenerator(
        name = "comment_generator",
        sequenceName = "comment_generator_seq",
        initialValue = 100,
        allocationSize = 1
)
@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    private long id;
    @Column(length = 100)
    private String author;
    @Column(length = 5000)
    private String content;
    private Timestamp publishDate;
}
