package edu.uchicago.gerber.quark.services;


import edu.uchicago.gerber.quark.models.Movie;
import edu.uchicago.gerber.quark.repositories.MovieMongodbRepo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class MovieService {

    @Inject
    MovieMongodbRepo studentRepo;

    //to use DynamoDB, read the README.md first, also comment MovieMongodbRepo above, and uncomment MovieDynamodbRepo below
    // @Inject
    //MovieDynamodbRepo movieRepo;

    public List<Movie> findAll(){
        return studentRepo.findAll();
    }
    public List<Movie> add(Movie stud){
        return studentRepo.add(stud);
    }
    public Movie get(String id){
        return studentRepo.get(id);
    }
    public List<Movie> getByEmail(String email){
        return studentRepo.getByEmail(email);
    }
    public List<Movie> getByTitle(String title) {return  studentRepo.getByTitle(title);}
    public List<Movie> paged(int page){ return  studentRepo.paged(page);}
    public List<Movie> delete(String id) {return studentRepo.delete(id);}
    public List<Movie> update(String id, Movie stud){return studentRepo.update(id, stud);}



}
