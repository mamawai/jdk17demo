package com.example.demo.javaPractice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
import java.util.function.BinaryOperator;

public class testReduce {
    public static void main(String[] args) {
        User john = new User("John", 30);
        john.getRating().add(new Review(5, ""));
        john.getRating().add(new Review(3, "not bad"));
        User julie = new User("Julie", 35);
        julie.getRating().add(new Review(4, "great!"));
        julie.getRating().add(new Review(2, "terrible experience"));
        julie.getRating().add(new Review(4, ""));
        List<User> users = Arrays.asList(john, julie);

        Rating averageRating = users.stream()
                .reduce(new Rating(),
                        (rating, user) -> Rating.average(rating, user.getRating()),
                        new BinaryOperator<Rating>() {
                            @Override
                            public Rating apply(Rating rating, Rating rating1) {
                                return rating;
                            }
                        });
        System.out.println(averageRating.points);

    }
}

@Getter
@Setter
class User {
    String name;
    int age;
    private Rating rating = new Rating();

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Review {
    private int points;
    private String review;
}


class Rating {
    double points;
    List<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        computeRating();
    }

    private void computeRating() {
        double totalPoints =
                reviews.stream().map(Review::getPoints).reduce(0, Integer::sum);
        this.points = totalPoints / reviews.size();
    }

    public static Rating average(Rating r1, Rating r2) {
        Rating combined = new Rating();
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.computeRating();
        return combined;
    }

}

