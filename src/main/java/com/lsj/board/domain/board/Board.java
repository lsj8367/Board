package com.lsj.board.domain.board;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
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
