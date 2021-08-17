package com.lsj.board.domain.board;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleLayout {
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(
            o)) {
            return false;
        }
        ArticleLayout that = (ArticleLayout) o;

        if (!Objects.equals(title, that.title)) {
            return false;
        }
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(title);
        result = 31 * result + (Objects.hashCode(content));
        return result;
    }
}
