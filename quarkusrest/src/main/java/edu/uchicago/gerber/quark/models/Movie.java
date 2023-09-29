package edu.uchicago.gerber.quark.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Movie {
    private String id;
    private String email;
    private String title;
    private String year;
    private String imdbID;
    private String poster;
    private String comment;


    public Movie(){
        this.email = "";
        this.title = "";
        this.year = "";
        this.imdbID = "";
        this.poster = "";
        this.comment = "";
    }

    public Movie(String id, String email, String title, String year, String imdbID, String poster, String comment) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
        this.comment = comment;
    }

    public String getComment() {return comment;}

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
