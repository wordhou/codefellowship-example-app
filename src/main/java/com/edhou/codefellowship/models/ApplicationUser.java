package com.edhou.codefellowship.models;

import com.edhou.codefellowship.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(unique=true, nullable=false)
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDateTime dateOfBirth;
    String profilePicture;
    String bio;

    @OneToMany(mappedBy = "author")
    List<Post> posts;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="follow_relation",
            joinColumns = {@JoinColumn(name="follow_to")},
            inverseJoinColumns = {@JoinColumn(name="follow_from")}
    )
    Set<ApplicationUser> following;

    @ManyToMany(mappedBy = "following")
    Set<ApplicationUser> followers;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getDisplayFirstName() { return firstName == null || firstName.isBlank() ? "Add first name" : firstName; }

    public String getDisplayLastName() { return lastName == null || lastName.isBlank() ? "Add last name" : lastName; }

    public String getDisplayBio() { return bio == null || bio.isBlank() ? "Add a bio to your profile" : bio; }

    public String getFullName() { return String.format("%s %s", firstName, lastName); }

    public String getDisplayName() {
        return firstName == null || firstName == "null" ?
                username :
                lastName == null || lastName == "null" ?
                        firstName :
                        String.format("%s %s", firstName, lastName);
    }

    public LocalDateTime getDateOfBirth() { return dateOfBirth; }

    public String getDisplayDateOfBirth() {
        return dateOfBirth == null ?
                "" :
                dateOfBirth.format(DateTimeFormatter.ofPattern("d MMMM uuuu"));
    }


    public String getBio() { return bio; }

    public String getShortBio() { return bio; }

    public List<Post> getPosts() { return posts; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setDateOfBirth(LocalDateTime dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public void setBio(String bio) { this.bio = bio; }

    public void setImageUrl(String imageUrl) { this.profilePicture = imageUrl; }

    public String getImageUrl() { return getId() + "/" + profilePicture; }

    public boolean getHasImage() { return profilePicture != null; }

    public Set<ApplicationUser> getFollowing() { return following; }

    public Set<ApplicationUser> getFollowers() { return followers; }

    public void addFollowing(ApplicationUser user) { following.add(user); }

    public void removeFollowing(ApplicationUser user) { following.remove(user); }
}
