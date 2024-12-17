package edu.duan.app.kanban.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 256)
    private String title;
    @Column(length = 5000)
    private String description;
    @Column(length = 100)
    private String assignee;
    private Timestamp createdTime;
    private Timestamp deadlineTime;
    private Timestamp lastUpdatedTime;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> commentaries;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BoardColumnEntity activeColumn;
}
