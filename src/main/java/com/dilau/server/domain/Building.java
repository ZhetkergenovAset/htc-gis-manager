package com.dilau.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"postCode", "rka", "type", "street", "number"})
@Entity
@Table(name = "buildyngs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(name = "postcode")
    @JsonProperty(value = "postcode")
    private String postCode;
    @Column(name = "rka")
    private String rka;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Street street;
    @Column(name = "number_house")
    private String number;
    @Column(name = "date_update")
    @JsonIgnore
    private LocalDate dateUpdate;
    @Column(name = "longlat")
    private String longlat; //долгота
    @Column(name = "width")
    private String width;//ширина
    @Column(name = "verified")
    @JsonIgnore
    private String verified;
}
