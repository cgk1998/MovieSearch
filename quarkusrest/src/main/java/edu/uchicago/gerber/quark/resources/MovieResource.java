package edu.uchicago.gerber.quark.resources;


import edu.uchicago.gerber.quark.models.Movie;
import edu.uchicago.gerber.quark.services.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieService movieService;

    @GET
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @GET
    @Path("{id}")
    public Movie getFromId(@PathParam("id") String id){
        try{
            new ObjectId(id);
        } catch (IllegalArgumentException e){
            throw new WebApplicationException(Response.status(400).entity(id + " is not valid").build());
        }
        Movie stud = movieService.get(id);

        if (null == stud){
            throw new WebApplicationException(Response.status(404).entity("The movie with id " + id + " was not found!").build());

        }
        return stud;
    }


    @GET
    @Path("/title/{title}")
    public List<Movie> getByTitle(@PathParam("title") String title){
        List<Movie> movies = movieService.getByTitle(title);

        if (movies == null){
            throw new WebApplicationException(Response.status(404).entity("Movies with title " + title + " was not found!").build());
        }

        return movies;
    }

    @GET
    @Path("/email/{email}")
    public List<Movie> getByEmail(@PathParam("email") String email){

        List<Movie> stud = movieService.getByEmail(email);

        if (null == stud){
            throw new WebApplicationException(Response.status(404).entity("Movies with email " + email + " was not found!").build());

        }
        return stud;
    }

    //https://www.technicalkeeda.com/java-mongodb-tutorials/java-mongodb-driver-3-3-0-pagination-example
    @GET
    @Path("/paged/{page}")
    public List<Movie> paged(@PathParam("page") int page){
        return movieService.paged(page);
    }


    @POST
    public List<Movie> add(Movie stud){
        if (stud.getEmail() == "" || stud.getId() == "" || stud.getTitle() == "" || stud.getPoster() == ""){
            throw new WebApplicationException(Response.status(400).entity("Some fields of movie are missing (Required: email, poster etc)").build());
        }
        movieService.add(stud);
        return getAll();
    }

    @DELETE
    @Path("/delete/{id}")
    public List<Movie> delete(@PathParam("id") String id){
        try{
            new ObjectId(id);
        } catch (IllegalArgumentException e){
            throw new WebApplicationException(Response.status(400).entity(id + " is not valid").build());
        }
        List<Movie> list = movieService.delete(id);
        if (list == null){
            throw new WebApplicationException(Response.status(400).entity("The movie with id " + id + " was not found!").build());
        }
        else{
            return list;
        }
    }

    @PUT
    @Path("/update/{id}")
    public List<Movie> update(@PathParam("id") String id, Movie stud){
        try{
            new ObjectId(id);
        } catch (IllegalArgumentException e){
            throw new WebApplicationException(Response.status(400).entity(id + " is not valid").build());
        }

        if (stud.getComment() == "") {
            throw new WebApplicationException(Response.status(400).entity("Invalid Input: can only update comment").build());
        }

        List<Movie> list = movieService.update(id, stud);
        if (list == null){
            throw new WebApplicationException(Response.status(400).entity("The movie with id " + id + " was not found!").build());
        }
        else{
            return list;
        }

    }
}
