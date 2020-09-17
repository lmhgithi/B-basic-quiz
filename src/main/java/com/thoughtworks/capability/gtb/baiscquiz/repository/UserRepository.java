package com.thoughtworks.capability.gtb.baiscquiz.repository;

import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private static List<User> users = new ArrayList<User>(){
        {
            add(new User(1, "KAMIL", 24,
                    "https://inews.gtimg.com/newsapp_match/0/3581582328/0",
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, " +
                            "dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut " +
                            "fuga aliquam ad asperiores voluptatem dolorum! Quasi."));
        }
    };

    private static List<Education> educations = new ArrayList<Education>(){
        {
            add(new Education(1, 1990, "I was born in Katowice",
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                            "Sapiente, exercitationem, totam, dolores iste dolore est aut modi."));
            add(new Education(1, 2005, "Secondary school specializing in artistic",
                    "Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur " +
                            "ipsum ducimus quibusdam quis voluptatibus."));
            add(new Education(1, 2009, "First level graduation in Graphic Design",
                    "Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus " +
                            "quaerat quibusdam perferendis? Iusto, quibusdam asperiores unde repellat."));
            add(new Education(1, 2012, "Second level graduation in Graphic Design",
                    "Ducimus, aliquam tempore autem itaque et accusantium!"));
        }
    };

    public Optional<User> getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId() == id).findFirst();
    }

    public List<Education> getEducationsByUserId(Long userId) {
        return educations.stream()
                .filter(education -> education.getUserId() == userId).collect(Collectors.toList());
    }

    public User addUser(User user) {
        // GTB: - users.size()+1 和 users.size()-1 都是啥意思？这么写代码可读性差，且并发情况下更容易出现错误
        user.setId(users.size()+1);
        users.add(user);
        return users.get(users.size()-1);
    }

    public Education addEducation(Education education) {
        educations.add(education);
        return educations.get(educations.size()-1);
    }
}
