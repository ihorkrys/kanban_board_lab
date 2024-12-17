package edu.duan.app.kanban.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 100)
    private String author;
    @Column(length = 5000)
    private String content;
    private Timestamp publishDate;
}
