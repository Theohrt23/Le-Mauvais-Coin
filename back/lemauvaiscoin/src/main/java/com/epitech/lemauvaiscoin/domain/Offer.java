package com.epitech.lemauvaiscoin.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="offer")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @NonNull
    @Column()
    private String title;

    @NonNull
    @Column()
    private String description;

    @NonNull
    @Column()
    private String salary;

    @NonNull
    @Column()
    private String contract_type;

    @NonNull
    @Column()
    private String work_period;

    @NonNull
    @Column()
    private String creation_date;

    @NonNull
    @Column()
    private boolean teleworking;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinTable(name = "fp_offer_company_assoc",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private Company company;

}
