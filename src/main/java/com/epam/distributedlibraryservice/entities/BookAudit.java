package com.epam.distributedlibraryservice.entities;

import com.epam.distributedlibraryservice.constants.AuditAction;
import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book_audit")
public class BookAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "action", nullable = false, columnDefinition = "ENUM('ADD', 'EDIT')")
    @Enumerated(EnumType.STRING)
    private AuditAction action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_id", nullable = false)
    private User contributor;

    @Column(name = "action_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTime;

    @PrePersist
    protected void onCreate() {
        actionTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public User getContributor() {
        return contributor;
    }

    public void setContributor(User contributor) {
        this.contributor = contributor;
    }

    public Date getActionTime() {
        return actionTime;
    }
}
