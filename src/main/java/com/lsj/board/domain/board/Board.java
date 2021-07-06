package com.lsj.board.domain.board;

import com.lsj.board.domain.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

}
