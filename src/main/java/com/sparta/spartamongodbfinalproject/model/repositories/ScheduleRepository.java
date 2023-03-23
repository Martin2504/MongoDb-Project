package com.sparta.spartamongodbfinalproject.model.repositories;

import com.sparta.spartamongodbfinalproject.model.entities.Schedule;
import com.sparta.spartamongodbfinalproject.model.entities.Theatre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {


        @Query("{ '_id' : ?0 }")
        Optional<Schedule> findScheduleById(String id);

        @Query("{ 'theater_id' : ?0 }")
        Optional<Schedule> findScheduleByTheatreId(ObjectId id);

        @Query("{ 'movie_id' : ?0 }")
        List<Schedule> findScheduleByMovieId(String movieId);

        @Query("{ 'startTime' : ?0 }")
        List<Schedule> findScheduleByStartTime(LocalDateTime startTime);


        @Query("{ 'movie_id' : ?0 }")
        List<Schedule> findSchedulesByMovie_Id(String id);





}
