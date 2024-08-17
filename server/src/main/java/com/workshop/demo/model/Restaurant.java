package com.workshop.demo.model;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.workshop.demo.model.audit.UserDateAudit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "Restaurant", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }),
        @UniqueConstraint(columnNames = { "email" }) })
// @JsonIgnoreProperties(value = { "updatedBy", "updatedAt", "createdBy" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Restaurant extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @NotBlank
    @NaturalId
    @Size(max = 40)
    @Column(name = "email")
    @Email
    private String email;

    @NotBlank
    @Column(name = "location")
    private String location;

    @NotBlank
    @Column(name = "zipcode")
    private String zipcode;

    @NotBlank
    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "image_urls", columnDefinition = "json")
    private String imageUrls;

    @Column(name = "hours", columnDefinition = "json")
    private String hours;

    @Column(name = "website", length = 255)
    private String website;

    @Column(name = "menu", length = 255)
    private String menu;

    @Column(name = "rating", length = 255)
    private String rating;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Favorite> favoritedBy;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // Avoid including 'reviews' here
                '}';
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    // public List<Review> getReviews() {
    // return reviews == null ? null : new ArrayList<>(reviews);
    // }

    // public void setReviews(List<Review> reviews) {
    // if (reviews == null) {
    // this.reviews = null;
    // } else {
    // this.reviews = Collections.unmodifiableList(reviews);
    // }
    // }

    // public List<Favorite> getFavoriteBy() {
    // return favoritedBy == null ? null : new ArrayList<>(favoritedBy);
    // }

    // public void setFavoriteBy(List<Favorite> favoritedBy) {
    // if (favoritedBy == null) {
    // this.favoritedBy = null;
    // } else {
    // this.favoritedBy = Collections.unmodifiableList(favoritedBy);
    // }
    // }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return String return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @return String return the location
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * @param cuisine the cuisine to set
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

}