package site.bookmore.bookmore.books.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Builder.Default
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<Author> authors = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<Translator> translators = new HashSet<>();

    private Subject subject;

    private String publisher;

    private Integer pages;

    private String image;

    private String chapter;

    @Column(length = 600)
    private String introduce;

    private Integer price;

    @Column(nullable = false)
    private Boolean cached = false;

    @CreatedDate
    private LocalDateTime createdDatetime;

    public Book merge(Book book) {
        if (id == null) id = book.getId();
        if (title == null) title = book.getTitle();
        if (authors.isEmpty()) authors = book.getAuthors();
        if (translators.isEmpty()) translators = book.getTranslators();
        if (subject == null) subject = book.getSubject();
        if (publisher == null) publisher = book.getPublisher();
        if (pages == null) pages = book.getPages();
        if (image == null) image = book.getImage();
        if (chapter == null) chapter = book.getChapter();
        if (introduce == null) introduce = book.getIntroduce();
        if (price == null) price = book.getPrice();
        return this;
    }

    public void addAuthors(Set<Author> authors) {
        this.authors.addAll(authors);
    }

    public void addTranslators(Set<Translator> translators) {
        this.translators.addAll(translators);
    }
}
