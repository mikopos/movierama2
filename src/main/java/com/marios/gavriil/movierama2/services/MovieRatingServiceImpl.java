package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.dto.MovieRatingdto;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.MovieRating;
import com.marios.gavriil.movierama2.repositories.MovieRatingRepository;
import com.marios.gavriil.movierama2.services.interfaces.MovieRatingService;
import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    @Autowired
    MovieRatingRepository movieRatingRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public MovieRating voteForMovie(MovieRatingdto movieRatingdto) throws Exception {

        MovieRating movieRating = new MovieRating();


       Optional<Movie> optionalMovie =  movieService.findById(movieRatingdto.getMovie().getId());

       if(optionalMovie.isPresent()){
           if(movieRatingdto.getUser().getId() == optionalMovie.get().getUser().getId()){
               //TODO owner movie exception
               throw new Exception("The user cannot vote for this movie");
           }
           else{

               MovieRating tempMovieRating = movieRatingRepository.findByUserAndMovie(optionalMovie.get().getUser(), optionalMovie.get());
               MovieDto movieDto = modelMapper.map(optionalMovie.get(), MovieDto.class);

               if(tempMovieRating == null){
                   movieRating.setUser(movieRatingdto.getUser());
                   movieRating.setMovie(movieRatingdto.getMovie());
                   movieRating.setVote(movieRatingdto.isVote());

                   if(movieRatingdto.isVote()){
                       movieDto.setNumberOfLikes(movieDto.getNumberOfLikes()+1);
                   }
                   else{
                       movieDto.setNumberOfHates(movieDto.getNumberOfHates()+1);
                   }

                   try{
                       movieService.addMovie(movieDto);
                   }catch (PersistenceException e){
                       throw new Exception("Something went wrong");
                   }
               }
               else{
                   if (movieRatingdto.isVote() == tempMovieRating.isVote()){
                       throw new Exception("You can not vote a movie with the same vote ");
                   }
                   else{
                       movieRating.setUser(movieRatingdto.getUser());
                       movieRating.setMovie(movieRatingdto.getMovie());
                       movieRating.setVote(movieRatingdto.isVote());

                       if(movieRatingdto.isVote()){
                           movieDto.setNumberOfLikes(movieDto.getNumberOfLikes()+1);
                           movieDto.setNumberOfHates(movieDto.getNumberOfHates()-1);
                       }
                       else{
                           movieDto.setNumberOfLikes(movieDto.getNumberOfLikes()-1);
                           movieDto.setNumberOfHates(movieDto.getNumberOfHates()+1);
                       }

                       try{
                           movieService.addMovie(movieDto);
                       }catch (PersistenceException e){
                           throw new Exception("Something went wrong");
                       }
                   }
               }


           }
       }
       else {
           //TODO movie not exist exception
           throw new Exception("There is no such movie");
       }

        return movieRatingRepository.save(movieRating);
    }
}
