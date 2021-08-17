package com.lsj.board.web.dto;

import com.lsj.board.domain.board.ArticleLayout;
import com.lsj.board.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public BoardSaveRequestDto(String title, String content, String author) {
        this.title   = title;
        this.content = content;
        this.author  = author;
    }

    public Board toEntity(){
        return Board.builder()
                    .articleLayout(new ArticleLayout(title, content))
                    .author(author)
                    .build();
    }
}
